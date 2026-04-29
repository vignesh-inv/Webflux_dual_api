package com.example.demo.repo;

import java.util.List;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Orderentity;

import reactor.core.publisher.Flux;

@Repository
public interface Orderrepo extends ReactiveCrudRepository<Orderentity, Long> {

	@Query("select * from orders  where orderId in (:ids)")
	Flux<Orderentity> findByOrderId(List<Integer> listOfId);

}
