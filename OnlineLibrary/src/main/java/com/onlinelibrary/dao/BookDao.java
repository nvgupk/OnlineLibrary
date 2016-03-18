package com.onlinelibrary.dao;

import java.util.List;

import com.onlinelibrary.model.Book;

public interface BookDao extends GenericDao<Book, Long> {
	public List<Book> getAllBooks();
	public List<Book> getBooksByName(String name);
	public List<Book> getBooksByAuthorsName(String authorName);
	public List<Book> getBooksByGenresName(String name);
}
