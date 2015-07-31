package servlets.modifyData;

import java.io.IOException;
import java.sql.SQLException;

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
		RequestDispatcher view;
	
		try {
			b.setBorrowedBook(indexBook, userEmail);
			view = request.getRequestDispatcher("ListaCartiUser");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}

		view.forward(request, response);
	}
}
