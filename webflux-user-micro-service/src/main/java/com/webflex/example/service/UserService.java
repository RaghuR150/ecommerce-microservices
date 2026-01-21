package com.webflex.example.service;


import org.springframework.stereotype.Service;

import com.webflex.example.model.User;
import com.webflex.example.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user); // flatMap NOT needed here
    }

    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
}
