package com.webflex.example.service;

import org.springframework.stereotype.Service;

import com.webflex.example.model.Address;
import com.webflex.example.repository.AddressRepository;

import reactor.core.publisher.Flux;

@Service
public class AddressService {
    private final AddressRepository repo;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    public Flux<Address> getAddressByUser(String userId) {
        return repo.findByUserId(userId);
    }
}
