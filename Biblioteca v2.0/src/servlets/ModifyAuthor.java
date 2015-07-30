package servlets;

import java.io.IOException;

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

		/* preia vechile valori ale numelui */
		int index = Integer.parseInt(indexS);
		
		/* actualizeaza baza de date */
		authorService.modifyAuthor(firstName, lastName, index);
		
		RequestDispatcher view = request.getRequestDispatcher("ListAuthors");
		view.forward(request, response);
	}
}
