package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowedBook;
import model.Gestiune;

public class ReturneazaServlet extends HttpServlet{
	
	/* clasa starge din lista de carti imprumutate cartea, pentru a fi returnata */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{
		
		String index = (String)request.getParameter("bookIndexR");
		
		String userEmail = (String) request.getAttribute("email");
		
		int ind = Integer.parseInt(index);
		
		model.Gestiune g = (Gestiune) request.getServletContext().getAttribute("gestiune");
		model.DataBorrowedBook d = (model.DataBorrowedBook) request.getServletContext().getAttribute("tableUser");
		
		g.returneaza(ind);
		d.removeUser(ind);
	
		RequestDispatcher view = request.getRequestDispatcher("ListaCartiImprumutateUser");
		view.forward(request, response);
		
	}
}
