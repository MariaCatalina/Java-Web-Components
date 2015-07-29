package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.BooksService;

public class ModifyBook extends HttpServlet{
	
	/* servletul modifica numele unui autor cand este apasat butonu "modifica" */
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		BooksService booksService = new BooksService();
		
		/* preia noile date modificate */
		String bookIndex = (String) request.getParameter("bookIndexM");
		String authorId = (String) request.getParameter("chooseBook");
		String title = (String ) request.getParameter("TitleMod");
		
		int indexBook = Integer.parseInt(bookIndex);
		int indexAuthor = Integer.parseInt(authorId);
		
		booksService.modifyBook(indexBook, indexAuthor, title);
		/* actualizeaza baza de date */
		
		
		RequestDispatcher view = request.getRequestDispatcher("ListaCarti");
		view.forward(request, response);

	}
}
