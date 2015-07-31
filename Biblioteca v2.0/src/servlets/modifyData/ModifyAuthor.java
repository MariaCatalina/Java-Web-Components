package servlets.modifyData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AuthorService;

public class ModifyAuthor extends HttpServlet {
	
	/* servletul modifica numele unui autor cand este apasat butonu "modifica" */
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		/* preia noile date modificate */
		String firstName = (String) request.getParameter("FirstNameM");
		String lastName = (String ) request.getParameter("LastNameM");
		String indexS = (String)request.getParameter("authorIndexM");
		
		AuthorService authorService = new AuthorService();
		RequestDispatcher view;
		/* preia vechile valori ale numelui */
		int index = Integer.parseInt(indexS);
		
		/* actualizeaza baza de date */
		try {
			authorService.modifyAuthor(firstName, lastName, index);
			view = request.getRequestDispatcher("ListAuthors");
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}

		view.forward(request, response);
	}
}
