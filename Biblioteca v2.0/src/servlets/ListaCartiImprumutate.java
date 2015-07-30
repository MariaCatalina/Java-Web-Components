package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import model.BorrowedBook;
import services.BorrowedBookService;

public class ListaCartiImprumutate extends HttpServlet {

	/* metoda seteaza un atribut cu lista totala de carti */
	public void doGet(HttpServletRequest request, HttpServletResponse response )throws IOException, ServletException{
		
		BorrowedBookService bookService = new BorrowedBookService();
		
		ArrayList<BorrowedBook> borrowedB = bookService.getAllBorrowedBooks();
		
		request.setAttribute("listaImp",borrowedB);

		RequestDispatcher view = request.getRequestDispatcher("/BorrowedBooksList.jsp");
		view.forward(request, response);

	}
}
