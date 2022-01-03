package com.pravinkatiyar.onlinetest.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pravinkatiyar.onlinetest.entity.User;
import com.pravinkatiyar.onlinetest.entity.UserRole;
import com.pravinkatiyar.onlinetest.exception.EmailAlreadyExistsException;
import com.pravinkatiyar.onlinetest.repository.RoleRepository;
import com.pravinkatiyar.onlinetest.repository.UserRepository;
import com.pravinkatiyar.onlinetest.security.JwtuserFactory;
import com.pravinkatiyar.onlinetest.security.PasswordUtil;


@Service
@Transactional
public class AuthServiceImpl implements UserDetailsService{
	
	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findByEmailIgnoreCase(username);
		if(user==null) {
			throw new UsernameNotFoundException("No User found with username"+ user.getFirstName()+" "+user.getLastName());
		}
		else {
			return JwtuserFactory.create(user);
		}}

	
	public User registerUser(User user,  Set<UserRole> userRoles) {
		if (userRepository.findUserByEmail(user.getEmail()) != null) {
			throw new EmailAlreadyExistsException("user with Email " + user.getEmail() + " is Already Exist");
		
		} else {
			for (UserRole uRole : userRoles) {
				roleRepository.save(uRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
		}
		
		
		user.setEnabled(true);		
		String password=PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		
		System.out.println(user);
		return userRepository.save(user);
	} 


}
