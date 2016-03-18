package com.onlinelibrary.service;

import java.util.List;

import com.onlinelibrary.model.UserType;

public interface UserTypeService {
	public List<UserType> getAllUserType();
	public UserType getUserTypeByName(String type);
}
