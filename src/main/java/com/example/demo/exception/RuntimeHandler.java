package com.example.demo.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class RuntimeHandler {

	@ExceptionHandler(RuntimeException.class)
	public Mono<ResponseEntity<Map<String, String>>> runtimeHandler(RuntimeException ex) {
		return Mono.just(ResponseEntity.badRequest().body(Map.of("error", ex.getMessage())));

	}

}
