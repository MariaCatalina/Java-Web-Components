package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Gestiune;


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
			
			/* se adauga datele in structura */
			b.setBook(autor,titlu,nrExemplare,0);
			g.addB(b);
			request.setAttribute("book", new model.MyBook());
		}
		/* daca datele nu sunt integral corecte se pastreaza in memorie */
		else{
			
			if(!autor.isEmpty())
				b.setAutor(autor);
			if(!titlu.isEmpty())
				b.setTitlu(titlu);
			if(!nrE.isEmpty())
				b.setNrExemplare(Integer.parseInt(nrE));
			
			request.setAttribute("book",b);
		}
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/AddedBook.jsp");
		view.forward(request, response);
	}
}
