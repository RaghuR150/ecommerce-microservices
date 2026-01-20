package com.ecommerce.user.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.user.entity.Role;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.repository.RoleRepository;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Override
	public User createUser(User user) {
		Role role = roleRepository.save(user.getRole());
		user.setRole(role);

		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

}
