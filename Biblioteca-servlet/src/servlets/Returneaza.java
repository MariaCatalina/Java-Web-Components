package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Gestiune;

public class Returneaza extends HttpServlet{
	
	/* clasa starge din lista de carti imprumutate cartea, pentru a fi returnata */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{
		
		String index = (String)request.getParameter("bookIndexR");
		
		int ind = Integer.parseInt(index);
		
		model.Gestiune g = (Gestiune) request.getServletContext().getAttribute("gestiune");
		model.DataBorrowedBook d = (model.DataBorrowedBook) request.getServletContext().getAttribute("tableUser");
		
		/* actualizeaza numarul de exemplare imprumutate */
		g.returneaza(ind);
		/* sterge cartea din lista de carti imprumutate */
		d.removeUser(ind);
	
		RequestDispatcher view = request.getRequestDispatcher("ListaCartiImprumutateUser");
		view.forward(request, response);
		
	}
}
