package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Apiresponse;
import com.example.demo.entity.Userentity;
import com.example.demo.repo.Orderrepo;
import com.example.demo.repo.Userrepo;

import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Service
public class Userservice {
	
	private final Logger log = LoggerFactory.getLogger(Springservice.class);

	private Userrepo userrepo;
	
	private ObjectMapper mapper;

	public Userservice(Userrepo userrepo, ObjectMapper mapper) {
		this.userrepo = userrepo;
		this.mapper = mapper;
	}

	public Mono<Userentity> getById(Long id) {
		
		 Mono<Userentity> user = userrepo.findById(id)
				.doOnNext(res->log.info("Id found for user: {}",mapper.writeValueAsString(res)))
				.switchIfEmpty(Mono.error(new RuntimeException("user not found")));
		 
		 return user;
		
	}

}
