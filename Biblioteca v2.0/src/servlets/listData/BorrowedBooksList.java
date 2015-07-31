package servlets.listData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import model.BorrowedBook;
import services.BorrowedBookService;

public class BorrowedBooksList extends HttpServlet {

	/* metoda seteaza un atribut cu lista totala de carti */
	public void doGet(HttpServletRequest request, HttpServletResponse response )throws IOException, ServletException{
		
		BorrowedBookService bookService = new BorrowedBookService();
		
		ArrayList<BorrowedBook> borrowedB;
		RequestDispatcher view;
		try {
			borrowedB = bookService.getAllBorrowedBooks();

			request.setAttribute("listaImp",borrowedB);
			view = request.getRequestDispatcher("/listDataJSP/BorrowedBooksList.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		
		view.forward(request, response);

	}
}
