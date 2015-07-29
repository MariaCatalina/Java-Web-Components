package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.BorrowedBook;
import services.BorrowedBookService;

public class ListaSpUser extends HttpServlet{

	/* /* metoda seteaza un atribut cu lista de carti imprumutate de user-ul curent */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String email = (String) request.getAttribute("email");
		
		BorrowedBookService b = new BorrowedBookService();
		ArrayList<BorrowedBook> lista = b.getSpecifiedBorrowedBooks(email);
			/* set atribute -> prelucret in jsp */
			request.setAttribute("listaCartiSpUser",lista);


			RequestDispatcher view = request.getRequestDispatcher("/ListaSpUser.jsp");
			view.forward(request, response);



	}
}