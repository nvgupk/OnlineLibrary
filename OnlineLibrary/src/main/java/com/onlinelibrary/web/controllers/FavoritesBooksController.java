package com.onlinelibrary.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.Book;
import com.onlinelibrary.service.BookService;
import com.onlinelibrary.service.impl.BookServiceImpl;
import com.onlinelibrary.validation.Validation;

@WebServlet(
		urlPatterns = {"/Personal", "/Addbook"}
)
public class FavoritesBooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/personal.jsp")
			.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		BookService bookService = new BookServiceImpl(new DaoFactoryImpl());
		Book book = null;
		if(Validation.isPositiveInteger(bookId)) {
			book = bookService.getBookById(Long.valueOf(bookId));	
		}
		if(book == null) {
			request.setAttribute("message", "Сталася помилка, книгу не знайдено");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pageinfo.jsp");
			view.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			Map<Long, Book> favoritesBooks = (HashMap<Long, Book>) session.getAttribute("favoritesList");
			JSONObject json = new JSONObject();
			synchronized (session.getId().intern()) {
				if(favoritesBooks == null) {
					favoritesBooks = new HashMap<Long, Book>();
					session.setAttribute("favoritesList", favoritesBooks);
				}
				if(favoritesBooks.containsKey(book.getBookId())) {
					json.put("status", "exist");
				} else {
					favoritesBooks.put(book.getBookId(), book);
					json.put("status", "added");
				}
			}
			response.getWriter().write(json.toJSONString());
		}
	}
}
