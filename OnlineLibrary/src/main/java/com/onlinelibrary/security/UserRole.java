package com.onlinelibrary.security;

import com.onlinelibrary.model.UserType;

public enum UserRole {
	ADMIN("admin"),
	COMMON_USER("common_user");
	
	private String value;
	
	private UserRole(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
