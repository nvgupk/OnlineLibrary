package com.onlinelibrary.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.onlinelibrary.dao.GenreDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.Genre;
import com.onlinelibrary.service.GenreService;
import com.onlinelibrary.utils.JpaUtil;

public class GenreServiceImpl implements GenreService {
	private DaoFactory daoFactory;
	private GenreDao genreDao;
	
	public GenreServiceImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		genreDao = daoFactory.createGenreDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		genreDao = daoFactory.createGenreDao();
	}
	@Override
	public Set<Genre> getAllGenres() {
		Set<Genre> genres = null;
		try {
			JpaUtil.beginTransaction();
			genres = genreDao.getAllGenres();
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return genres != null ? genres : new HashSet<Genre>();
	}
}
