package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Apiresponse;
import com.example.demo.entity.Orderentity;
import com.example.demo.entity.Userentity;
import com.example.demo.repo.Orderrepo;
import com.example.demo.repo.Userrepo;

import reactor.core.publisher.Mono;

@Service
public class Springservice {

	private final Userrepo userrepo;

	private final Orderrepo orderrepo;

	public Springservice(Userrepo userrepo, Orderrepo orderrepo) {
		this.userrepo = userrepo;
		this.orderrepo = orderrepo;
	}

	List<Integer> listOfId = List.of(101, 102);

	public Mono<Apiresponse> getAllData(Long id) {

		Mono<Userentity> user = userrepo.findById(id);

		Mono<List<Orderentity>> order = orderrepo.findByOrderId(listOfId).collectList();	

		return Mono.zip(user, order).map(t -> {


			return new Apiresponse(t.getT1(), t.getT2());
		});
	}

}
