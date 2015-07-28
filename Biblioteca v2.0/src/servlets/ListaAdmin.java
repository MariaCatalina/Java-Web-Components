package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import services.BooksService;

public class ListaAdmin extends HttpServlet{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* metoda seteaza un atribut cu lista totala de carti pentru admin */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

		BooksService booksS = new BooksService();
		
		ArrayList<model.MyBook> books =  booksS.getAllBooks();
		
		request.setAttribute("listaCompleta",books);

		RequestDispatcher view = request.getRequestDispatcher("/ListaAdmin.jsp");
		view.forward(request, response);


	}
}
