package com.onlinelibrary.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.Author;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.model.Genre;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.service.GenreService;
import com.onlinelibrary.service.impl.BookServiceImpl;
import com.onlinelibrary.service.impl.GenreServiceImpl;

@WebServlet("/Admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GenreService genreService = new GenreServiceImpl(new DaoFactoryImpl());
		Set<Genre> genres = genreService.getAllGenres();
		request.setAttribute("genres", genres);
		request.getRequestDispatcher("WEB-INF/admin.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String coverImgPath = request.getParameter("books-cover-path");
		String booksFilePath = request.getParameter("books-file-path");
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
			if(reqParamName.startsWith("new-author_")){
				newAuthors.add(new Author(request.getParameter(reqParamName)));
			}
		}
		BookService bookService = new BookServiceImpl(new DaoFactoryImpl());
		Book book = new Book();
		book.setName(name);
		book.setDescription(description);
		book.setCoverImgPath(coverImgPath);
		book.setBooksFilePath(booksFilePath);
		book.setGenres(newGenres);
		book.setAuthors(newAuthors);
		bookService.updateBook(book);
	}

}
