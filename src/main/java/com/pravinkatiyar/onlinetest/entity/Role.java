package com.pravinkatiyar.onlinetest.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pravinkatiyar.onlinetest.utils.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="roles")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @GenericGenerator(
        name = "role_seq", 
        strategy = "com.pravinkatiyar.onlinetest.utils.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "U_"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
	private String roleName;
	
    //user have many roles   
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY
    		,mappedBy="user")
    @JsonIgnore
    private Set<UserRole> userRoles= new HashSet<UserRole>();
	
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Role() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
