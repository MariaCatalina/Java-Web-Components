package servlets.addData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Author;
import services.AuthorService;


public class AddAuthor extends HttpServlet{

	/* clasa este apelata cand se intruduce in baza de date un autor nou */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		AuthorService authorService = new AuthorService();
		Author author;

		String firstName = (String) request.getParameter("FirstName");
		String lastName = (String ) request.getParameter("LastName");
		
		RequestDispatcher view;
		
		try {
			/* verificare parametri de intrare */
			if( !firstName.isEmpty() && !lastName.isEmpty()){

				int result;

				result = authorService.createAuthor(firstName, lastName);


				if( result == 1 ){
					request.setAttribute("ExistaAutor", "NUexista");
					request.setAttribute("author",new Author());
				}
				else if( result == 2 ){
					request.setAttribute("ExistaAutor", "exista");
				}
			}
			/* datele introduse nu sunt valide */
			else {

				author = new Author();

				if(!firstName.isEmpty())
					author.setFirstName(firstName);

				if(!lastName.isEmpty())
					author.setLastName(lastName);

				/* set atribute folosite in jsp */
				request.setAttribute("author",author);
				request.setAttribute("ExistaAutor", "dateInvalide");

			}
			
			view = request.getRequestDispatcher("/addDataJSP/AddedAuthor.jsp");
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		
		view.forward(request, response);

	}
}
