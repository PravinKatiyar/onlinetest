package com.pravinkatiyar.onlinetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pravinkatiyar.onlinetest.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, String>{

	public User findByUsername(String userName);
	public User findByEmailIgnoreCase(String username);
	public User findUserByEmail(String email);
}
