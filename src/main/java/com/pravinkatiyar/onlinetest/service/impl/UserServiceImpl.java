package com.pravinkatiyar.onlinetest.service.impl;

import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pravinkatiyar.onlinetest.entity.User;
import com.pravinkatiyar.onlinetest.entity.UserRole;
import com.pravinkatiyar.onlinetest.exception.UserAlreadyExistsException;
import com.pravinkatiyar.onlinetest.repository.RoleRepository;
import com.pravinkatiyar.onlinetest.repository.UserRepository;
import com.pravinkatiyar.onlinetest.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Cacheable(value = "one-hour-cache", key = "#user")
	public User createUser(User user, Set<UserRole> userRoles) {
		User dbUser = userRepository.findByUsername(user.getUsername());
		if (dbUser != null) {
			throw new UserAlreadyExistsException("User with username: "+user.getUsername()+" already exists in database!");
		} else {
			for (UserRole uRole : userRoles) {
				roleRepository.save(uRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
		}
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
