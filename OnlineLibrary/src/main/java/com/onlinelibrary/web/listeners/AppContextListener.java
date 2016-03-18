package com.onlinelibrary.web.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.onlinelibrary.dao.factory.DaoFactoryImpl;
import com.onlinelibrary.model.UserType;
import com.onlinelibrary.security.UserRole;
import com.onlinelibrary.service.UserTypeService;
import com.onlinelibrary.service.impl.UserTypeServiceImpl;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event) { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent event) {
    	UserTypeService userTypeService = new UserTypeServiceImpl(new DaoFactoryImpl());
    	List<UserType> userTypes = userTypeService.getAllUserType();
    	Map<String, String> roles = new HashMap<String, String>();
        for (UserRole userRole : UserRole.values()) {
        	Optional<UserType> optUsrType = userTypes.stream()
            		.filter(userType -> userType.getType().equalsIgnoreCase(userRole.getValue()))
            		.findFirst();
        	if(optUsrType.isPresent()) {
        		roles.put(userRole.toString(), optUsrType.get().getType());
        	}
		}
        event.getServletContext().setAttribute("roles", roles);
    }

}
