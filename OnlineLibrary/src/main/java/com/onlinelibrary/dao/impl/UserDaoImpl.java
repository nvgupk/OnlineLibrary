package com.onlinelibrary.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.onlinelibrary.dao.UserDao;
import com.onlinelibrary.model.User;
import com.onlinelibrary.utils.JpaUtil;

public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		Query query = entityManager.createQuery("from User where email = :email AND password = :password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		return users.isEmpty() ? null : users.get(0);
	}
}
