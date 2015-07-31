package servlets.listData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.BorrowedBook;
import services.BorrowedBookService;

public class SpecifiedUserList extends HttpServlet{

	/* /* metoda seteaza un atribut cu lista de carti imprumutate de user-ul curent */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String email = (String) request.getAttribute("email");

		BorrowedBookService b = new BorrowedBookService();
		RequestDispatcher view;
		ArrayList<BorrowedBook> lista;
		
		try {
			lista = b.getSpecifiedBorrowedBooks(email);
			request.setAttribute("listaCartiSpUser",lista);
			
			view = request.getRequestDispatcher("/listDataJSP/ListaSpUser.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
				
		view.forward(request, response);

	}
}