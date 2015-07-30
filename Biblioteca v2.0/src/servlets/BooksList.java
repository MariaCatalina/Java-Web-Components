package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.MyBook;
import services.BooksService;

public class BooksList extends HttpServlet{

	/* metoda seteaza un atribut cu lista totala de carti pentru admin */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

		BooksService booksS = new BooksService();
		
		ArrayList<MyBook> books =  booksS.getAllBooks();
		
		request.setAttribute("listaCompleta",books);

		RequestDispatcher view = request.getRequestDispatcher("/BooksList.jsp");
		view.forward(request, response);


	}
}
