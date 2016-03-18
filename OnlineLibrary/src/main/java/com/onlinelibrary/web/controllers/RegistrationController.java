package com.onlinelibrary.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.User;
import com.onlinelibrary.model.UserType;
import com.onlinelibrary.security.UserRole;
import com.onlinelibrary.service.UserService;
import com.onlinelibrary.service.UserTypeService;
import com.onlinelibrary.service.impl.UserServiceimpl;
import com.onlinelibrary.service.impl.UserTypeServiceImpl;


@WebServlet("/Registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceimpl(new DaoFactoryImpl());
		UserTypeService userTypeService = new UserTypeServiceImpl(new DaoFactoryImpl());
		UserType userType = userTypeService.getUserTypeByName(UserRole.COMMON_USER.getValue());
		//Save new user
		User newUser = new User();
		newUser.setEmail(request.getParameter("email"));
		newUser.setName(request.getParameter("name"));
		newUser.setPassword(request.getParameter("password"));
		newUser.setUserType(userType);
		userService.createNewUser(newUser);
		//Bind new user to session
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30 * 24 * 60 * 60);
		session.setAttribute("currentSessionUser", newUser);
		Cookie authenticationCookie = new Cookie(session.getServletContext().getInitParameter("AuthenticationCookieName"), String.valueOf(newUser.getUserId()));
		authenticationCookie.setMaxAge(30 * 24 * 60 * 60);
		response.addCookie(authenticationCookie);
		response.sendRedirect("Books");
	}
}
