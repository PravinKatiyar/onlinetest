package com.pravinkatiyar.onlinetest.controller;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pravinkatiyar.onlinetest.entity.Role;
import com.pravinkatiyar.onlinetest.entity.User;
import com.pravinkatiyar.onlinetest.entity.UserRole;
import com.pravinkatiyar.onlinetest.exception.UnauthorizedException;
import com.pravinkatiyar.onlinetest.security.JwTokenUtil;
import com.pravinkatiyar.onlinetest.security.JwtUser;
import com.pravinkatiyar.onlinetest.security.Response;
import com.pravinkatiyar.onlinetest.security.UserDTO;
import com.pravinkatiyar.onlinetest.service.impl.AuthServiceImpl;

@RestController
@CrossOrigin(value="*")

public class AuthController {
	
	@Autowired
	private AuthServiceImpl authService;
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwTokenUtil jwTokenUtil;

		@PostMapping(value="/login")
		public ResponseEntity<UserDTO> Login(@RequestBody User user,HttpServletRequest request, HttpServletResponse response){
			try {
				Authentication  authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
				final JwtUser userDetails=(JwtUser)authentication.getPrincipal();
				SecurityContextHolder.getContext().setAuthentication(authentication);
				final String token =jwTokenUtil.generateToken(userDetails);
				response.setHeader("Token", token);
				
				return new ResponseEntity<UserDTO>(new UserDTO(userDetails.getUser(),token),HttpStatus.OK);			
			} catch (Exception e) {
				throw new UnauthorizedException("Bad Credentials");				
			}
		}
		

	@PostMapping(value="/registration")
	public ResponseEntity<Response> registration (@RequestBody User user){
		Set<UserRole> userRoles=new HashSet<>();
		Role role=new Role();
		role.setRoleName("ADMIN");
		
		UserRole userRole =new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoles.add(userRole);
		
		
		User dbUser=authService.registerUser(user, userRoles);
		if(dbUser!=null) {
			System.out.println(dbUser);
			return new ResponseEntity<Response>(new Response("User is Registered Successfully"),HttpStatus.OK);
		} 
		return new ResponseEntity<Response>(new Response("Something went wrong!!"),HttpStatus.BAD_REQUEST);
	}

}
	
	


