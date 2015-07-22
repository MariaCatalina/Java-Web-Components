package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaAdmin extends HttpServlet{
	
	/* metoda seteaza un atribut cu lista totala de carti pentru admin */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		
		ArrayList<model.MyBook> books =  new ArrayList<model.MyBook>();
		books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getList();
		
		request.setAttribute("listaCompleta",books);
		
		RequestDispatcher view = request.getRequestDispatcher("/ListaAdmin.jsp");
		view.forward(request, response);
	}
}
