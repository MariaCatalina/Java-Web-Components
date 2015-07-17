package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Gestiune;
import model.MyBook;

public class AddBookServelt extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		String autor = (String)request.getParameter("Autor"); 
		String titlu = (String)request.getParameter("Titlu"); 
		String nrE = (String)request.getParameter("Numar");
		
		model.MyBook b = new model.MyBook();
		int nrExemplare;	
	
		model.Gestiune g = (Gestiune) request.getServletContext().getAttribute("gestiune");
		
		/* vefificare dacă datele nu sunt null */
		if(!autor.isEmpty() && !titlu.isEmpty() && !nrE.isEmpty()){	
			
			nrExemplare = Integer.parseInt(nrE);
			b.setBook(autor,titlu,nrExemplare,0);
			
			System.out.println(b.getAutor() + "  " + b.getTitlu() + "  ");
		//	lg.add(b);
			g.addB(b);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/AddedBook.jsp");
		view.forward(request, response);
	}
}
