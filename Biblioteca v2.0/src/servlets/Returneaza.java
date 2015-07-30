package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import services.BorrowedBookService;

public class Returneaza extends HttpServlet{

	/* clasa starge din lista de carti imprumutate cartea, pentru a fi returnata */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String index = (String)request.getParameter("bookIndexR");
		int indexBBook = Integer.parseInt(index);
		
		BorrowedBookService b = new BorrowedBookService();
		b.returnBorrowedBook(indexBBook);
		
		RequestDispatcher view = request.getRequestDispatcher("ListaCartiImprumutateUser");
		view.forward(request, response);
	}
}
