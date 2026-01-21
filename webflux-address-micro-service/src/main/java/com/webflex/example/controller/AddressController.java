package com.webflex.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflex.example.model.Address;
import com.webflex.example.service.AddressService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public Flux<Address> getByUser(@PathVariable String userId) {
        return service.getAddressByUser(userId);
    }
}
