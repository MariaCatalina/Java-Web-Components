package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowedBook;

public class SortareTitlu extends HttpServlet{
	
	/* metorda apelata pentru a afisa lista sortata dupa titlu */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{
		
		ArrayList<model.MyBook> books =  new ArrayList<model.MyBook>();
		
		books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getSortByTitlu();
		request.setAttribute("listaTitlu",books);
		
		RequestDispatcher view = request.getRequestDispatcher("/ListeAutor.jsp");
		view.forward(request, response);
		
	}
}
