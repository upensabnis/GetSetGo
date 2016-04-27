package main.java.com.getsetgo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NewServlet extends HttpServlet  {
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
	    throws ServletException, IOException
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		//HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
		 
		//response.getWriter().println(helloWorld.getBeanValue());
	
			// Set response content type
		response.setContentType("text/html");
		
		// Actual logic goes here.
		//PrintWriter out = response.getWriter();
		//out.println("<h1>" + message + "</h1>");
	}

}
