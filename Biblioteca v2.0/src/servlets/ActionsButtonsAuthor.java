package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Author;
import services.AuthorService;

public class ActionsButtonsAuthor extends HttpServlet{

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String tipe = (String)request.getParameter("actiune");
		String indexS = (String)request.getParameter("authorIndex");
		int index = Integer.parseInt(indexS);	

		RequestDispatcher view;

		AuthorService authorService = new AuthorService();

		Author author = authorService.getSpecifiedAuthor(index);

		if(tipe.equals("modifica")){
			
			request.setAttribute("modifyAuthor", author);
			view = request.getRequestDispatcher("/modifyAuthor.jsp");
		}
		else if(tipe.equals("sterge")){
		
			boolean confirm = authorService.deleteAuthor(index);
			request.setAttribute("ConfirmDelete",confirm);
			
			view = request.getRequestDispatcher("/deleteAuthor.jsp");
		}
			else{
				view = request.getRequestDispatcher("/pageJSP.jsp");
			}
		
		view.forward(request, response);
	}
}
