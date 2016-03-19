package com.onlinelibrary.web.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.User;
import com.onlinelibrary.service.UserService;
import com.onlinelibrary.service.impl.UserServiceimpl;


@WebServlet(
		urlPatterns = {"/Login", "/Logout"}
)
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Executing when user logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("currentSessionUser");
		session.removeAttribute("favoritesList");
		response.sendRedirect("Books");
	}
	
	//Executing when user login
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserService userService = new UserServiceimpl(new DaoFactoryImpl());
		User user = userService.getUserByEmailAndPassword(email, password);
		if(user == null) {
			request.setAttribute("isUserExist", false);
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("currentSessionUser", user);
			response.sendRedirect("Books");
		}
	}
}
