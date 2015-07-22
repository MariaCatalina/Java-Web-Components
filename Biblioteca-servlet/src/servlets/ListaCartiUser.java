package servlets;

import java.io.IOException;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaCartiUser extends HttpServlet{

	/* metoda seteaza un atribut cu lista de carti pentru user in functie de cerinta*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {

		ArrayList<model.MyBook> books = new ArrayList<model.MyBook>();;

		String sort = (String)request.getParameter("selSort");
		String tip = (String)request.getParameter("tipA");
		String tipT = (String)request.getParameter("tipT");

		model.Gestiune g = ((model.Gestiune)request.getServletContext().getAttribute("gestiune"));
		
		/* daca nu e selectat nici un button */
		if(sort == null){
			books = g.getList();
		}
		else
			/* tip == selDupaAutor -> lista sortata dupa autor */
			if(sort.equals("selDupaAutor")){
				
				books = g.getSortByAutor(tip);
	
			}
			else{
				/* tip == selDupaTitlu -> lista sortata dupa titlu */
				if(sort.equals("selDupaTitlu")){

					books = g.getSortByTitlu(tipT);				
				}
			}

		request.setAttribute("listaCompleta",books);

		RequestDispatcher view = request.getRequestDispatcher("/ListeAleator.jsp");
		view.forward(request, response);
	}
}
