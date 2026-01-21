package com.webflex.example.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webflex.example.model.User;

public interface UserRepository
        extends ReactiveMongoRepository<User, String> {
}