package com.example.demo.cotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Apiresponse;
import com.example.demo.service.Springservice;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class Springcontroller {

	private final Springservice springservice;

	public Springcontroller(Springservice springservice) {
		this.springservice = springservice;
	}

	@GetMapping("/user-full/{id}")
	public Mono<ResponseEntity<Apiresponse>> getAll(@PathVariable Long id) {
		return springservice.getAllData(id).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	

}
