package com.example.demo.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Userentity;
import com.example.demo.service.Userservice;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

	private final Userservice userservice;

	public UserController(Userservice userservice) {
		this.userservice = userservice;
	}
	
	@GetMapping("/user/{id}")
	public Mono<Userentity> getById(@PathVariable Long id){
		return userservice.getById(id);
		
	}

}
