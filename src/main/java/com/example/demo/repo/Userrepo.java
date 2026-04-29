package com.example.demo.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Userentity;

@Repository
public interface Userrepo extends ReactiveCrudRepository<Userentity, Long>{
	


}
