package com.pravinkatiyar.onlinetest.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pravinkatiyar.onlinetest.entity.User;

public class JwtUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1084254370560340172L;

	private final String id;
	private final String username;
	private final String password;
	private final User user;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;

	public JwtUser(String id, String username, String password, User user,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	@JsonIgnore
	public String getId() {
		return id;
	}

	@JsonIgnore
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	// Because we don't want to send JSON data to front end
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
