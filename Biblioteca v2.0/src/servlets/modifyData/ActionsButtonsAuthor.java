package servlets.modifyData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Author;
import services.AuthorService;

public class ActionsButtonsAuthor extends HttpServlet{

	public void doPost( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String tipe = (String)request.getParameter("actiune");
		String indexS = (String)request.getParameter("authorIndex");
		int index = Integer.parseInt(indexS);	

		RequestDispatcher view;

		AuthorService authorService = new AuthorService();

		Author author;
		
		try {
			author = authorService.getSpecifiedAuthor(index);


			if(tipe.equals("modifica")){

				request.setAttribute("modifyAuthor", author);
				view = request.getRequestDispatcher("/modifyDataJSP/modifyAuthor.jsp");
			}
			else if(tipe.equals("sterge")){

				boolean confirm = authorService.deleteAuthor(index);
				request.setAttribute("ConfirmDelete",confirm);

				view = request.getRequestDispatcher("/modifyDataJSP/deleteAuthor.jsp");
			}
			else{
				view = request.getRequestDispatcher("/modifyDataJSP/deleteAuthor.jsp");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
		view.forward(request, response);
	}
}
