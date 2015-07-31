package servlets.modifyData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import services.BooksService;

public class ModifyBook extends HttpServlet{
	
	/* servletul modifica numele unui autor cand este apasat butonu "modifica" */
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		BooksService booksService = new BooksService();
		
		/* preia noile date modificate */
		String bookIndex = (String) request.getParameter("bookIndexM");
		String authorId = (String) request.getParameter("chooseAuthor");
		String title = (String ) request.getParameter("TitleMod");
		
		int indexBook = Integer.parseInt(bookIndex);
		int indexAuthor = Integer.parseInt(authorId);
		RequestDispatcher view;
		
		try {
			
			booksService.modifyBook(indexBook, indexAuthor, title);
			view = request.getRequestDispatcher("ListaCarti");
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}

		view.forward(request, response);
	}
}
