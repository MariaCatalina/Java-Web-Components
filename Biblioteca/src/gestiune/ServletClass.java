package gestiune;
import java.io.*;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletClass extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		System.out.println("in metorda get");
		
		ArrayList<MyBook> book = new ArrayList();
		
		//ServletContext c = request.getServletContext();
		System.out.println(request.getParameter("Autor"));
		
		
//		RequestDispatcher view = request.getRequestDispatcher("/AddBooks.jsp");
	//	view.forward(request, response);
	}
}
