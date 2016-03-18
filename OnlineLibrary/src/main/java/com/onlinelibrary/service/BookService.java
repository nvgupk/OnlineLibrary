package com.onlinelibrary.service;

import java.util.List;

import com.onlinelibrary.model.Book;

public interface BookService {
	public List<Book> getAllBooks();
	public Book getBookById(Long id);
	public List<Book> getBooksByName(String name);
	public List<Book> getBooksByAuthorsName(String authorName);
	public List<Book> getBooksByGenresName(String genreName);
	public void updateBook(Book book);
	public void deleteBook(Book book);
	public void saveNewBook(Book book);
}
