package servlets.listData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Author;
import services.AuthorService;

public class AuthorsList extends HttpServlet{

	/* accesare baza de date si preluare lista de autori */
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		AuthorService authorsService = new AuthorService();
		ArrayList<Author> authorsList;
		
		RequestDispatcher view;
	
		try {
			authorsList = authorsService.getAllAuthors();
			request.setAttribute("AuthorsListAttribute",authorsList);

			view = request.getRequestDispatcher("/listDataJSP/AuthorsList.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		
		view.forward(request, response);

	}
}
