package com.onlinelibrary.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.Author;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.model.Genre;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.service.GenreService;
import com.onlinelibrary.service.impl.BookServiceImpl;
import com.onlinelibrary.service.impl.GenreServiceImpl;
import com.onlinelibrary.validation.Validation;

@WebServlet(
		urlPatterns = {"/Book", "/Editbook", "/Deletebook"}
)
public class BooksManipulationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String path = request.getRequestURI().substring(request.getContextPath().length());
		BookService bookService = new BookServiceImpl(new DaoFactoryImpl());
		Book book = null;
		if(Validation.isPositiveInteger(bookId)) {
			book = bookService.getBookById(Long.valueOf(bookId));	
		}
		if(book != null) {
			request.setAttribute("book", book);
			switch (path) {
				case "/Editbook":
					GenreService genreService = new GenreServiceImpl(new DaoFactoryImpl());
					Set<Genre> genres = genreService.getAllGenres();
					request.setAttribute("genres", genres);
					request.getRequestDispatcher("WEB-INF/editbook.jsp")
							.forward(request, response);
					break;
				case "/Deletebook":
					bookService.deleteBook(book);
					JSONObject json = new JSONObject();
					json.put("done", true);
					response.getWriter().write(json.toJSONString());
					break;
				default:
					request.getRequestDispatcher("WEB-INF/book.jsp")
							.forward(request, response);
			}
		} else {
			request.setAttribute("message", "Параметри адресного рядка введено невірно");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pageinfo.jsp");
			view.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String coverImgPath = request.getParameter("books-cover-path");
		String booksFilePath = request.getParameter("books-file-path");
		Book book = null;
		BookService bookService = new BookServiceImpl(new DaoFactoryImpl());
		if(Validation.isPositiveInteger(bookId)) {
			System.out.println("GET BOOK BY ID");
			book = bookService.getBookById(Long.valueOf(bookId));
			System.out.println("END");
		}
		if(book == null) {
			request.setAttribute("message", "Сталася помилка, книгу не знайдено");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pageinfo.jsp");
			view.forward(request, response);
			return;
		}
		Set<Genre> newGenres = new HashSet<Genre>();
		List<Author> newAuthors = new ArrayList<Author>();
		for(String reqParamName : Collections.list(request.getParameterNames())) {
			if(reqParamName.startsWith("genre_")){
				newGenres.add(new Genre(Long.valueOf(reqParamName.substring(6)), request.getParameter(reqParamName)));
				continue;
			}
			if(reqParamName.startsWith("new-genre_")){
				newGenres.add(new Genre(request.getParameter(reqParamName)));
				continue;
			}
			if(reqParamName.startsWith("author_")){
				newAuthors.add(new Author(Long.valueOf(reqParamName.substring(7)), request.getParameter(reqParamName)));
				continue;
			}
			if(reqParamName.startsWith("new-author_")){
				newAuthors.add(new Author(request.getParameter(reqParamName)));
				continue;
			}
		}
		book.setName(name);
		book.setDescription(description);
		book.setCoverImgPath(coverImgPath);
		book.setBooksFilePath(booksFilePath);
		book.setGenres(newGenres);
		book.setAuthors(newAuthors);
		bookService.updateBook(book);
		response.sendRedirect("Book?bookId=" + bookId);
	}
}
