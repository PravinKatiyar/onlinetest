package com.pravinkatiyar.onlinetest.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.pravinkatiyar.onlinetest.utils.StringPrefixedSequenceIdGenerator;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userrole_seq")
	@GenericGenerator(name = "userrole_seq", strategy = "com.pravinkatiyar.onlinetest.utils.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "UR_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String userRoleId;

	// user
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	// user
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

