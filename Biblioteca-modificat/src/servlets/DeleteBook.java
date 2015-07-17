package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Gestiune;

public class DeleteBook extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	
		String index = (String)request.getParameter("bookIndex");
		
		int ind = Integer.parseInt(index);
		
		model.Gestiune g = (Gestiune) request.getServletContext().getAttribute("gestiune");
		g.deleteB(ind);
		
		RequestDispatcher view = request.getRequestDispatcher("ListaCarti");
		view.forward(request, response);
	}
}
