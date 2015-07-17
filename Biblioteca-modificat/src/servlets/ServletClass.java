package servlets;
import java.io.*;
import model.MyBook;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletClass extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		//response.setContentType("text/html");
		
		System.out.println(request.getAttribute("role"));
		
		RequestDispatcher view = request.getRequestDispatcher("/pageJSP.jsp");
		view.forward(request, response);
	}
}
