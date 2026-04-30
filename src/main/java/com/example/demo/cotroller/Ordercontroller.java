package com.example.demo.cotroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Orderentity;
import com.example.demo.service.Orderservice;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class Ordercontroller {

	private final Orderservice orderservice;

	public Ordercontroller(Orderservice orderservice) {
		this.orderservice = orderservice;
	}

	@GetMapping("/order")
	public Mono<ResponseEntity<List<Orderentity>>> getOrder(){
		return orderservice.getByids()
				.map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
