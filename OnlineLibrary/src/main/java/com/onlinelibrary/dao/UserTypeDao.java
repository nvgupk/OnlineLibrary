package com.onlinelibrary.dao;

import java.util.List;

import com.onlinelibrary.model.UserType;

public interface UserTypeDao extends GenericDao<UserType, Long> {
	public List<UserType> getAllUserType();
	public UserType getUserTypeByName(String type);
}
