
package servlets.modifyData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MyBook;
import services.AuthorService;
import services.BooksService;

public class ActionsButtonsBooks extends HttpServlet{

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String tipe = (String)request.getParameter("actionBook");
		String idBook = (String)request.getParameter("bookId");

		int idBookInt = Integer.parseInt(idBook);	

		RequestDispatcher view;

		services.BooksService bookService = new BooksService();
		
		try {
			MyBook book = bookService.getSpecifiedBook(idBookInt); 

			if(tipe.equals("modifica")){

				request.setAttribute("modifyBook", book);

				/* preia lista de autori */
				AuthorService authorsService = new AuthorService();
				ArrayList<model.Author> authors;

				authors = authorsService.getAllAuthors();

				request.setAttribute("listAuthors",authors);

				view = request.getRequestDispatcher("/modifyDataJSP/modifyBook.jsp");
			}
			else if(tipe.equals("sterge")){

				bookService.deleteBook(idBookInt);

				view = request.getRequestDispatcher("ListaCarti");
			}
			else{
				view = request.getRequestDispatcher("ListaCarti");
			}

		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}

		view.forward(request, response);
	}
}

