package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.MyBook;
import services.BooksService;

public class AddBook extends HttpServlet {

	/* metoda verifica datele primite si adauga in memorie cartea */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		/* id-ul autorulul selectat */
		String autorId = (String)request.getParameter("chooseAnAuthor"); 
		/* titlul cartii*/
		String title = (String)request.getParameter("Title"); 
		/* nr de examplare */
		String nrE = (String)request.getParameter("noOfCopies");
		
		MyBook b = new MyBook();
		
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
				b.setTitlu(title);
			if( b.verifyNumber(nrE) )
				b.setNrExemplare(Integer.parseInt(nrE));
			
			request.setAttribute("book",b);
			request.setAttribute("bookVerification", "incorect");
		}

		RequestDispatcher view = request.getRequestDispatcher("/AddedBook.jsp");
		view.forward(request, response);

	}
}
