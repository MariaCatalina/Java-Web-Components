package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Gestiune;

public class ImprumutaServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	
		String index = (String)request.getParameter("bookIndexI");
		String userEmail = (String) request.getAttribute("role");
		
		int ind = Integer.parseInt(index);
		
		model.Gestiune g = (Gestiune) request.getServletContext().getAttribute("gestiune");
		model.DataTable d = (model.DataTable) request.getServletContext().getAttribute("tableUser");
		g.imprumuta(ind);
		d.addUser(userEmail,g.getList(),ind);
		
		RequestDispatcher view = request.getRequestDispatcher("ListaCartiUser");
		view.forward(request, response);
	}
}
