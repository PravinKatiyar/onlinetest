package com.pravinkatiyar.onlinetest.security;


import com.pravinkatiyar.onlinetest.entity.User;

public class UserDTO  {


	private User user;
	private String token;

	public UserDTO() {
	}

	public UserDTO(User user, String token) {
		this.user = user;
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}

