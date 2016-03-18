package com.onlinelibrary.web.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/File")
public class FileLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("file");
		String filePath = getServletContext().getInitParameter("bookFilePath") + fileName;
		System.out.println(filePath);
		File downloadFile = new File(filePath);
		if(downloadFile.exists() && !downloadFile.isDirectory()) {
			String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
			String mimeType = getServletContext().getMimeType(filePath);
			if(mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(downloadFile));
			BufferedOutputStream bout = new BufferedOutputStream(response.getOutputStream());
			int num = 0;
			while((num = bin.read()) != -1) {
				bout.write(num);
			}
			bin.close();
			bout.close();
		} else {
			throw new FileNotFoundException();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
