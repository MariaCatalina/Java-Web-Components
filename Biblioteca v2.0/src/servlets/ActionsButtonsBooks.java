
package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AuthorService;
import services.BooksService;

public class ActionsButtonsBooks extends HttpServlet{

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String tipe = (String)request.getParameter("actiune");
		String indexBook = (String)request.getParameter("bookIndex");

		int index = Integer.parseInt(indexBook);	

		RequestDispatcher view;

		services.BooksService bookService = new BooksService();

		model.MyBook book = bookService.getSpecifiedBook(index); 
		
		//System.out.println(author.getFirstName() + " "+ author.getLastName());

		if(tipe.equals("modifica")){
			
			request.setAttribute("modifyBook", book);
			
			/* preia lista de autori */
			AuthorService authorsService = new AuthorService();
			ArrayList<model.Author> authors = authorsService.getAllAuthors();
			
			request.setAttribute("listAuthors",authors);

			
			view = request.getRequestDispatcher("/modifyBook.jsp");
		}
		else if(tipe.equals("sterge")){
		
			bookService.deleteBook(index);
			
			view = request.getRequestDispatcher("/ListaCarti");
		}
			else{
				view = request.getRequestDispatcher("/pageJSP.jsp");
		}
		
		view.forward(request, response);
	}
}

