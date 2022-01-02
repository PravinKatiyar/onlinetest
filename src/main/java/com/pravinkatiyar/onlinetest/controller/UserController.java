package com.pravinkatiyar.onlinetest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pravinkatiyar.onlinetest.entity.Role;
import com.pravinkatiyar.onlinetest.entity.User;
import com.pravinkatiyar.onlinetest.entity.UserRole;
import com.pravinkatiyar.onlinetest.service.UserService;
import com.pravinkatiyar.onlinetest.utils.EmailSenderService;
import com.pravinkatiyar.onlinetest.utils.TestInfoEmail;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	private EmailSenderService service;

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		
		Set<UserRole> userRoles=new HashSet<>();
		Role role=new Role();
		role.setRoleName("ADMIN");
		
		UserRole userRole =new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoles.add(userRole);
		
		User localuser=userService.createUser(user, userRoles);
		return new ResponseEntity<User>(localuser,HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping("/sendmail")
	public void triggerMail() {
		TestInfoEmail testInfoEmail = new TestInfoEmail("Pravin : Katiyar","Core Java Quiz", 100, 80, "Passed!!");
		service.sendSimpleEmail("pravinskatiyar99@gmail.com", testInfoEmail);

	}

}
