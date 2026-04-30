package com.example.demo.service;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.Apiresponse;
import com.example.demo.entity.Orderentity;
import com.example.demo.entity.Userentity;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import tools.jackson.databind.ObjectMapper;

@Service
public class Springservice {

	private final Logger log = LoggerFactory.getLogger(Springservice.class);

	private final ObjectMapper mapper;

	private final WebClient webclient;

	public Springservice(ObjectMapper mapper, WebClient webclient) {
		this.mapper = mapper;
		this.webclient = webclient;
	}

	public Mono<Apiresponse> getAllData(Long id) {

		Mono<Userentity> user = webclient.get()
				.uri("http://localhost:8090/api/user/{id}", id)
				.exchangeToMono(res -> {
			if (res.statusCode().is2xxSuccessful()) {
				return res.bodyToMono(Userentity.class)
						.doOnNext(c -> log.info("User api called: {}", mapper.writeValueAsString(c)));
			} else {
				return res.bodyToMono(String.class).flatMap(er -> {
					log.error("Error {}", er);
					return Mono.error(new RuntimeException(er));
				});
			}
		});

		Mono<List<Orderentity>> order = webclient.get().uri("http://localhost:8090/api/order").retrieve()
				.bodyToFlux(Orderentity.class).collectList()
				.doOnNext(l -> log.info("The order api success:{} ", mapper.writeValueAsString(l)))
				.timeout(Duration.ofMillis(10))
				.retryWhen(Retry.max(2)
						.doBeforeRetry(signal -> log.warn("Retry {} for order api due to : {}",
						signal.totalRetries() + 1, signal.failure().getMessage())))
				.onErrorResume(ex -> {
					log.error("The orderapi failed with maximum retry");
					return Mono.just(List.of());
				});

		return Mono.zip(user, order).map(t -> {

			Apiresponse res = new Apiresponse();
			res.setUsers(t.getT1());
			res.setOrders(t.getT2());
			
			res.setStatus(t.getT2().isEmpty()? "PARTIAL_SUCCESS":"SUCCESS");

			
			return res;

		}).doOnNext(res -> log.info("Final api response: {}", mapper.writeValueAsString(res)))
				.doOnError(e -> log.error("Api faild: {}", e.getMessage()));

	}

}
