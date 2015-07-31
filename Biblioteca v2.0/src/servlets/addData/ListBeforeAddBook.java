package servlets.addData;

import java.io.IOException;
import java.sql.SQLException;
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
		ArrayList<Author> authors;
		RequestDispatcher view;
		try {
			authors = authorsService.getAllAuthors();
			request.setAttribute("listA",authors);
			view = request.getRequestDispatcher("/addDataJSP/AddBooks.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		
		
		view.forward(request, response);
	
	}
}
