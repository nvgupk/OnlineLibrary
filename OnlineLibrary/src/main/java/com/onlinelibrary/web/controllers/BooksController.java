package com.onlinelibrary.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.service.impl.BookServiceImpl;

@WebServlet("/Books")
public class BooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("searchQuery");
		String searchBy = request.getParameter("searchBy");
		BookService bookService = new BookServiceImpl(new DaoFactoryImpl());
		List<Book> books = null;
		if(searchBy == null) {
			searchBy = "";
		}
		switch (searchBy) {
			case "name" :
				books = bookService.getBooksByName(searchQuery);
				break;
			case "author" :
				books = bookService.getBooksByAuthorsName(searchQuery);
				break;
			case "genre" :
				books = bookService.getBooksByGenresName(searchQuery);
				break;
			default:
				books = bookService.getAllBooks();
		}
		request.setAttribute("books", books);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/books.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
