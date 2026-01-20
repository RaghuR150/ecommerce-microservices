package com.ecommerce.user.service;

import org.springframework.stereotype.Service;

import com.ecommerce.user.entity.User;

@Service
public interface UserService {

	User createUser(User user);

	User getUserById(Long id);

}
