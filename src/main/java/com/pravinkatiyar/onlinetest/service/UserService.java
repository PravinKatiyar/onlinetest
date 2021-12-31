package com.pravinkatiyar.onlinetest.service;

import java.util.List;
import java.util.Set;

import com.pravinkatiyar.onlinetest.entity.User;
import com.pravinkatiyar.onlinetest.entity.UserRole;

public interface UserService {
	public User createUser(User user, Set<UserRole> userRoles);

	public List<User> getAllUsers();

}
