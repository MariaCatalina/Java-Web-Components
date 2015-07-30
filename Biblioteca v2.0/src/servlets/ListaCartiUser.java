package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import model.MyBook;
import services.BooksService;

public class ListaCartiUser extends HttpServlet{

	/* metoda seteaza un atribut cu lista de carti pentru user in functie de cerinta*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {

		ArrayList<MyBook> books ;
		
		String sort = (String)request.getParameter("selSort");
		String tipSortAutor = (String)request.getParameter("tipA");
		String tipSortTitle = (String)request.getParameter("tipT");
		
		BooksService bookService = new BooksService();
		books = bookService.getSortedListBooks(sort, tipSortAutor, tipSortTitle);

		/* set atribute -> prelucret in jsp */
		request.setAttribute("listaCompleta",books);

		RequestDispatcher view = request.getRequestDispatcher("/ListeAleator.jsp");
		view.forward(request, response);

	}
}
