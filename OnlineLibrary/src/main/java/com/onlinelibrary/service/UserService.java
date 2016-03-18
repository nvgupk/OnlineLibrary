package com.onlinelibrary.service;

import com.onlinelibrary.model.User;

public interface UserService {
	public User getUserById(Long userId);
	public User getUserByEmailAndPassword(String email, String password);
	public void createNewUser(User user);
}
