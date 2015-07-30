package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Author;
import services.AuthorService;

public class ListBeforeAddBook extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		AuthorService authorsService = new AuthorService();
		ArrayList<Author> authors = authorsService.getAllAuthors();
		
		request.setAttribute("listA",authors);

		RequestDispatcher view = request.getRequestDispatcher("/AddBooks.jsp");
		view.forward(request, response);
	
	}
}
