package com.onlinelibrary.dao.factory;

import com.onlinelibrary.dao.AuthorDao;
import com.onlinelibrary.dao.BookDao;
import com.onlinelibrary.dao.GenreDao;
import com.onlinelibrary.dao.UserDao;
import com.onlinelibrary.dao.UserTypeDao;
import com.onlinelibrary.dao.impl.AuthorDaoImpl;
import com.onlinelibrary.dao.impl.BookDaoImpl;
import com.onlinelibrary.dao.impl.GenreDaoImpl;
import com.onlinelibrary.dao.impl.UserDaoImpl;
import com.onlinelibrary.dao.impl.UserTypeDaoImpl;

public class DaoFactoryImpl implements DaoFactory {

	@Override
	public AuthorDao createAuthorDao() {
		return new AuthorDaoImpl();
	}

	@Override
	public BookDao createBookDao() {
		return new BookDaoImpl();
	}

	@Override
	public GenreDao createGenreDao() {
		return new GenreDaoImpl();
	}

	@Override
	public UserDao createUserDao() {
		return new UserDaoImpl();
	}

	@Override
	public UserTypeDao createUserTypeDao() {
		return new UserTypeDaoImpl();
	}

}
