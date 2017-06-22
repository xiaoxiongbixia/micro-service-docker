package com.microservice.base.entity;

import java.io.Serializable;

public class ComplexBean1 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String field;
	
	private User user;
	
	private Role role;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
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

	@Override
	public String toString() {
		return "ComplexBean1 [field=" + field + ", user=" + user + ", role=" + role + "]";
	}

}
