package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Apiresponse;
import com.example.demo.entity.Orderentity;
import com.example.demo.repo.Orderrepo;

import reactor.core.publisher.Mono;


@Service
public class Orderservice {

	private final Orderrepo orderrepo;

	private final Logger log = LoggerFactory.getLogger(Orderservice.class);

	public Orderservice(Orderrepo orderrepo) {
		this.orderrepo = orderrepo;
	}

	List<Integer> listofid = List.of(101, 102);

	public Mono<List<Orderentity>> getByids() {

		return orderrepo.findByOrderId(listofid)
				.collectList().onErrorResume(e -> {
			log.error("The is a error in order api: {}", e.getMessage());
			return Mono.just(List.of());
		});

	}

}
