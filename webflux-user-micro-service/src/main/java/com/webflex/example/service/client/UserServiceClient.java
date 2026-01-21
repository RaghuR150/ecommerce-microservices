package com.webflex.example.service.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.webflex.example.dto.AddressDTO;
import com.webflex.example.dto.UserWithAddress;
import com.webflex.example.model.User;
import com.webflex.example.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserServiceClient {

    private final UserRepository userRepository;
    private final WebClient webClient;

    public UserServiceClient(UserRepository repo, WebClient webClient) {
        this.userRepository = repo;
        this.webClient = webClient;
    }

    public Mono<UserWithAddress> getUserWithAddress(String id) {

        Mono<User> userMono = userRepository.findById(id);

        Mono<List<AddressDTO>> addressMono =
            webClient.get()
                .uri("http://localhost:8082/addresses/user/{id}", id)
                .retrieve()
                .bodyToFlux(AddressDTO.class)
                .collectList();

        return userMono.zipWith(addressMono,
            (user, addresses) ->
                new UserWithAddress(user, addresses));
    }
}

