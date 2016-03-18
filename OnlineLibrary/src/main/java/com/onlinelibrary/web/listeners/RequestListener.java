package com.onlinelibrary.web.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.onlinelibrary.utils.JpaUtil;

@WebListener
public class RequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent event) {
         JpaUtil.closeEntityManager();
    }

    public void requestInitialized(ServletRequestEvent event) { 
         // TODO Auto-generated method stub
    }
	
}
