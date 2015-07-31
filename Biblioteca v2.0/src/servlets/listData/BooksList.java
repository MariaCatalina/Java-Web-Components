package servlets.listData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.MyBook;
import services.BooksService;

public class BooksList extends HttpServlet{

	/* metoda seteaza un atribut cu lista totala de carti pentru admin */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

		BooksService booksS = new BooksService();
		
		ArrayList<MyBook> books;
		RequestDispatcher view;
		try {
			books = booksS.getAllBooks();
			request.setAttribute("listaCompleta",books);

			view = request.getRequestDispatcher("/listDataJSP/BooksList.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		
		view.forward(request, response);
	}
}
