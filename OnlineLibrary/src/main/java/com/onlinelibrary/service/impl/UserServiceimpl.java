package com.onlinelibrary.service.impl;

import com.onlinelibrary.dao.UserDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.User;
import com.onlinelibrary.service.UserService;
import com.onlinelibrary.utils.JpaUtil;

public class UserServiceimpl implements UserService {
	private DaoFactory daoFactory;
	private UserDao userDao;
	
	public UserServiceimpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		userDao = daoFactory.createUserDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		userDao = daoFactory.createUserDao();
	}
	
	@Override
	public User getUserById(Long userId) {
		User user = null;
		try {
			JpaUtil.beginTransaction();
			user = userDao.findByID(User.class, userId);
			JpaUtil.commitTransaction();
		} catch (Throwable e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		User user = null;
		try {
			JpaUtil.beginTransaction();
			user = userDao.getUserByEmailAndPassword(email, password);
			JpaUtil.commitTransaction();
		} catch (Throwable e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public void createNewUser(User user) {
		try {
			JpaUtil.beginTransaction();
			userDao.save(user);
			JpaUtil.commitTransaction();
		} catch (Throwable e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
	}
}
