package com.onlinelibrary.dao.factory;

import com.onlinelibrary.dao.AuthorDao;
import com.onlinelibrary.dao.BookDao;
import com.onlinelibrary.dao.GenreDao;
import com.onlinelibrary.dao.UserDao;
import com.onlinelibrary.dao.UserTypeDao;

public interface DaoFactory {
	public AuthorDao createAuthorDao();
	public BookDao createBookDao();
	public GenreDao createGenreDao();
	public UserDao createUserDao();
	public UserTypeDao createUserTypeDao();
}
