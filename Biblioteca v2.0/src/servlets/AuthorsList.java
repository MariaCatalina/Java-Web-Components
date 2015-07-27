package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AuthorService;

public class AuthorsList extends HttpServlet{

	/* accesare baza de date si preluare lista de autori */
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		AuthorService authorsService = new AuthorService();

		ArrayList<model.Author> authorsList = authorsService.getAllAuthors();

		/* set atribute -> prelucret in jsp */
		request.setAttribute("AuthorsListAttribute",authorsList);

		RequestDispatcher view = request.getRequestDispatcher("/AuthorsList.jsp");
		view.forward(request, response);

	}
}
