package servlets.modifyData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import services.BorrowedBookService;

public class Returneaza extends HttpServlet{

	/* clasa starge din lista de carti imprumutate cartea, pentru a fi returnata */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String index = (String)request.getParameter("bookIndexR");
		int indexBBook = Integer.parseInt(index);
		
		BorrowedBookService b = new BorrowedBookService();
		RequestDispatcher view;
		
		try {
			b.returnBorrowedBook(indexBBook);
			view = request.getRequestDispatcher("ListaCartiImprumutateUser");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}

		view.forward(request, response);
	}
}
