package com.onlinelibrary.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlinelibrary.dao.BookDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.utils.JpaUtil;

public class BookServiceImpl implements BookService {
	private DaoFactory daoFactory;
	private BookDao bookDao;
	
	public BookServiceImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		bookDao = daoFactory.createBookDao();
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		bookDao = daoFactory.createBookDao();
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = null;
		try {
			JpaUtil.beginTransaction();
			books = bookDao.getAllBooks();
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return books != null ? books : new ArrayList<Book>();
	}
	
	@Override
	public Book getBookById(Long id) {
		Book book = null;
		try {
			JpaUtil.beginTransaction();
			book = bookDao.findByID(Book.class, id);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return book;
	}
	
	@Override
	public List<Book> getBooksByName(String name) {
		List<Book> books = null;
		try {
			JpaUtil.beginTransaction();
			books = bookDao.getBooksByName(name);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return books != null ? books : new ArrayList<Book>();
	}
	
	@Override
	public List<Book> getBooksByAuthorsName(String authorName) {
		List<Book> books = null;
		try {
			JpaUtil.beginTransaction();
			books = bookDao.getBooksByAuthorsName(authorName);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return books != null ? books : new ArrayList<Book>();
	}
	
	@Override
	public List<Book> getBooksByGenresName(String genreName) {
		List<Book> books = null;
		try {
			JpaUtil.beginTransaction();
			books = bookDao.getBooksByAuthorsName(genreName);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return books != null ? books : new ArrayList<Book>();
	}
	
	@Override
	public void updateBook(Book book) {
		try {
			JpaUtil.beginTransaction();
			bookDao.update(book);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteBook(Book book) {
		try {
			JpaUtil.beginTransaction();
			bookDao.delete(book);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void saveNewBook(Book book) {
		try {
			JpaUtil.beginTransaction();
			bookDao.save(book);
			JpaUtil.commitTransaction();
		} catch (Exception e){
			JpaUtil.rollbackTransaction();
			e.printStackTrace();
		}
	}
}
