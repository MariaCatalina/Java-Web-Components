package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowedBook;

public class ListaCartiImprumutate extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response )throws IOException, ServletException{
		
		model.DataTable table = ((model.DataTable) getServletContext().getAttribute("tableUser"));
		
		ArrayList<BorrowedBook> users = table.getUser();
		
		request.setAttribute("listaImp",users);
		
		RequestDispatcher view = request.getRequestDispatcher("/ListaCartiUser.jsp");
		view.forward(request, response);
	}
}
