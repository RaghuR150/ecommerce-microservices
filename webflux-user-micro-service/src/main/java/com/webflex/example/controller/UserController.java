package com.webflex.example.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflex.example.dto.UserWithAddress;
import com.webflex.example.model.User;
import com.webflex.example.service.UserService;
import com.webflex.example.service.client.UserServiceClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserServiceClient userServiceClient;

    public UserController(UserService userService, UserServiceClient userServiceClient) {
        this.userService = userService;
        this.userServiceClient = userServiceClient;
    }
    
    @GetMapping("/{id}/details")
    public Mono<UserWithAddress> getUserDetails(@PathVariable String id) {
        return userServiceClient.getUserWithAddress(id);
    }


    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

