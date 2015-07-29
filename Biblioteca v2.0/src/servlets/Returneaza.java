package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import services.BorrowedBookService;

public class Returneaza extends HttpServlet{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* clasa starge din lista de carti imprumutate cartea, pentru a fi returnata */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String index = (String)request.getParameter("bookIndexR");
		int indexBBook = Integer.parseInt(index);
		
		BorrowedBookService b = new BorrowedBookService();
		b.returnBorrowedBook(indexBBook);
		
		RequestDispatcher view = request.getRequestDispatcher("ListaCartiImprumutateUser");
		view.forward(request, response);
	}
}
