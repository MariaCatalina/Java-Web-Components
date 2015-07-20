package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowedBook;

public class SortareServlet extends HttpServlet{
	
	/* servlet apelat pentru a afisa lista sortata dupa autor */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{
		
		ArrayList<model.MyBook> books =  new ArrayList<model.MyBook>();
		
		books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getSortByAutor();
	
		request.setAttribute("listaAutor",books);
		
		RequestDispatcher view = request.getRequestDispatcher("/ListeAutor.jsp");
		view.forward(request, response);
		
	}
}
