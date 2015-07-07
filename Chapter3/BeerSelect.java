package com.example.web;

import com.example.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class BeerSelect extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	/*	-- pentru codul scris in java
		response.setContentType("text/thml");
		PrintWriter out =  response.getWriter();
		out.println("Beer Selection Advice");
	*/
		String c = request.getParameter("color");
		/* se adauga clasa BeerExpert */
		BeerExpert be = new BeerExpert();
		List result = be.getBrands(c);
		
		/*
		 out.println("Got beer color " + c);
		// afisare rezultate din metoda getBrends 
		 Iterator it = result.iterator();
		 while(it.hasNext()){
		 	out.println("try: "+ it.next());
		 }
		*/
		 /* cod folosit de aplicatia JSP */
		request.setAttribute("styles",result);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request,response);
	}
}
