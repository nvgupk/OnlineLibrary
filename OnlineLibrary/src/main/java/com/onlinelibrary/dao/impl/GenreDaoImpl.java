package com.onlinelibrary.dao.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.onlinelibrary.dao.GenreDao;
import com.onlinelibrary.model.Genre;
import com.onlinelibrary.utils.JpaUtil;

public class GenreDaoImpl extends GenericDaoImpl<Genre, Long> implements GenreDao {
	@Override
	public Set<Genre> getAllGenres() {
		EntityManager entityManager = JpaUtil.getEntityManager();
		Query query = entityManager.createQuery("from Genre");
		return new HashSet<Genre>(query.getResultList());
	}
}
