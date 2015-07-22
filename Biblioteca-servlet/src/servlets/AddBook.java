package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddBook extends HttpServlet {

	/* metoda verifica datele primite si adauga in memorie cartea */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String autor = (String)request.getParameter("Autor"); 
		String titlu = (String)request.getParameter("Titlu"); 
		String nrE = (String)request.getParameter("Numar");

		model.MyBook b = new model.MyBook();
		int nrExemplare;	

		model.Gestiune g = (model.Gestiune) request.getServletContext().getAttribute("gestiune");

		/* vefificare dacă datele nu sunt null */
		if(!autor.isEmpty() && !titlu.isEmpty() && !nrE.isEmpty() && b.verificaNrExemplare(nrE) == 0 ){	
			
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
			
			if(!nrE.isEmpty() && b.verificaNrExemplare(nrE) == 0)
				b.setNrExemplare(Integer.parseInt(nrE));

			request.setAttribute("book",b);
		}

		RequestDispatcher view = request.getRequestDispatcher("/AddedBook.jsp");
		view.forward(request, response);
	}
}
