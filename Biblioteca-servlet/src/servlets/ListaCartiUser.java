package servlets;

import java.io.IOException;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class ListaCartiUser extends HttpServlet{

	/* metoda seteaza un atribut cu lista de carti pentru user in functie de cerinta*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {

		ArrayList<model.MyBook> books =  new ArrayList<model.MyBook>();

		String sort = (String)request.getParameter("selSort");
		String tip = (String)request.getParameter("tip");

		/* daca nu e selectat nici un button */
		if(sort == null){

			books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getList();

		}
		else
			/* tip == selDupaAutor -> lista sortata dupa autor */
			if(sort.equals("selDupaAutor")){
					
				System.out.println("IN SORTATEA DUPA AUTOR " + request.getParameter("tipD"));
				
				if( ((String)request.getParameter("tip")).equals("asc")){

					System.out.println("**AICI ASC **" +request.getParameter("tip"));
					books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getSortByAutor();
					
					request.setAttribute("tip",null);
				}
				else
				if( ((String)request.getParameter("tip")).equals("desc") ){
						
						books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getSortByAutorDesc();
						System.out.println("**AICIC DEDS *** " +request.getParameter("tipD"));
						
						request.setAttribute("tip",null);
					}
					else{
						
						books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getSortByAutor();
						request.setAttribute("tip",null);
					}
			}
			else{
				System.out.println("IN SORTAREA DUPA TITLU");
				/* tip == selDupaTitlu -> lista sortata dupa titlu */
				if(sort.equals("selDupaTitlu"))
					books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getSortByTitlu();

			}

		request.setAttribute("listaCompleta",books);

		RequestDispatcher view = request.getRequestDispatcher("/ListeAleator.jsp");
		view.forward(request, response);
	}
}
