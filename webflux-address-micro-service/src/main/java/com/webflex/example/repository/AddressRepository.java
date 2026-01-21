package com.webflex.example.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webflex.example.model.Address;

import reactor.core.publisher.Flux;

public interface AddressRepository
extends ReactiveMongoRepository<Address, String> {
Flux<Address> findByUserId(String userId);
}
