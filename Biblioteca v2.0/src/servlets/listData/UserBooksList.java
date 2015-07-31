package servlets.listData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import model.MyBook;
import services.BooksService;

public class UserBooksList extends HttpServlet{

	/* metoda seteaza un atribut cu lista de carti pentru user in functie de cerinta*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {

		ArrayList<MyBook> books ;
		
		String sort = (String)request.getParameter("selSort");
		String tipSortAutor = (String)request.getParameter("tipA");
		String tipSortTitle = (String)request.getParameter("tipT");
		
		BooksService bookService = new BooksService();
		RequestDispatcher view;
		
		try {
			books = bookService.getSortedListBooks(sort, tipSortAutor, tipSortTitle);
			request.setAttribute("listaCompleta",books);
			
			view = request.getRequestDispatcher("/listDataJSP/ListeAleator.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		
		view.forward(request, response);
	}
}
