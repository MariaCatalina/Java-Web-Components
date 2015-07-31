package servlets.addData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.MyBook;
import services.BooksService;

public class AddBook extends HttpServlet {

	/* metoda verifica datele primite si adauga in memorie cartea */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		/* id-ul autorulul selectat */
		String autorId = (String)request.getParameter("chooseAnAuthor"); 
		/* titlul cartii*/
		String title = (String)request.getParameter("Title"); 
		/* nr de examplare */
		String nrE = (String)request.getParameter("noOfCopies");

		MyBook b = new MyBook();
		RequestDispatcher view;
		try{
			/* vefificare dacă datele sunt introduse corect */
			if( b.verifyNumber(autorId) && b.verifyNumber(nrE) && ! title.isEmpty() ){

				int author_id = Integer.parseInt(autorId);
				int noCopies = Integer.parseInt(nrE);

				BooksService bookService = new BooksService();
				bookService.setBook(author_id, title, noCopies);

				request.setAttribute("book",new MyBook());
				request.setAttribute("bookVerification", "corect");
			}
			else{

				if( !title.isEmpty() )
					b.setTitle(title);
				if( b.verifyNumber(nrE) )
					b.setNoCopies(Integer.parseInt(nrE));

				request.setAttribute("book",b);
				request.setAttribute("bookVerification", "incorect");
			}
			view = request.getRequestDispatcher("/addDataJSP/AddedBook.jsp");
			 
		}catch(ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		
		view.forward(request, response);

	}
}
