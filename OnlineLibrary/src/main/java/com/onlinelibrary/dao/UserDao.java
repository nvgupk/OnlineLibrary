package com.onlinelibrary.dao;

import com.onlinelibrary.model.User;

public interface UserDao extends GenericDao<User, Long> {
	public User getUserByEmailAndPassword(String email, String password);
}
