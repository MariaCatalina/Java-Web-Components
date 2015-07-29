package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import services.BorrowedBookService;


public class Imprumuta extends HttpServlet {

	/* metoda este apelata cand se imprumuta o carte */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String index = (String)request.getParameter("bookIndexI");
		String userEmail = (String) request.getAttribute("email");
		
		int indexBook = Integer.parseInt(index);
		
		BorrowedBookService b = new BorrowedBookService();
		b.setBorrowedBook(indexBook, userEmail);
		
		RequestDispatcher view = request.getRequestDispatcher("ListaCartiUser");
		view.forward(request, response);
	}
}
