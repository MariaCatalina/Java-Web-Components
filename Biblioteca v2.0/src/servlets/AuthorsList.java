package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Author;
import services.AuthorService;

public class AuthorsList extends HttpServlet{

	/* accesare baza de date si preluare lista de autori */
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		AuthorService authorsService = new AuthorService();

		ArrayList<Author> authorsList = authorsService.getAllAuthors();

		/* set atribute -> prelucret in jsp */
		request.setAttribute("AuthorsListAttribute",authorsList);

		RequestDispatcher view = request.getRequestDispatcher("/AuthorsList.jsp");
		view.forward(request, response);

	}
}
