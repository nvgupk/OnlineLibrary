package com.onlinelibrary.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlinelibrary.dao.UserTypeDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.UserType;
import com.onlinelibrary.service.UserTypeService;
import com.onlinelibrary.utils.JpaUtil;

public class UserTypeServiceImpl implements UserTypeService {
	private DaoFactory daoFactory;
	private UserTypeDao userTypeDao;
	
	public UserTypeServiceImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		userTypeDao = daoFactory.createUserTypeDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		userTypeDao = daoFactory.createUserTypeDao();
	}

	@Override
	public List<UserType> getAllUserType() {
		List<UserType> userTypes = null;
		try {
			JpaUtil.beginTransaction();
			userTypes = userTypeDao.getAllUserType();
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return userTypes != null ? userTypes : new ArrayList<UserType>();
	}
	
	@Override
	public UserType getUserTypeByName(String type) {
		UserType userType = null;
		try {
			JpaUtil.beginTransaction();
			userType = userTypeDao.getUserTypeByName(type);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return userType;
	}
}
