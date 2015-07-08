package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ListenerTester extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		System.out.println("a intrat in servlet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("test context attributes set by listener<br>");
		out.println("<br>");
		/* get the Dog from the ServletContext  */
		Dog dog = (Dog) getServletContext().getAttribute("dog");
		if(dog == null)
			System.out.println("dog e null");
		else
			System.out.println("dog diferit de null");
		out.println("Dog's breed is: " + dog.getBreed());

	}
}