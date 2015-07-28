package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AuthorService;

public class ActionsButtonsAuthor extends HttpServlet{

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String tipe = (String)request.getParameter("actiune");
		String indexS = (String)request.getParameter("authorIndex");

		int index = Integer.parseInt(indexS);	

		System.out.println("**** " + tipe);

		RequestDispatcher view;

		AuthorService authorService = new AuthorService();

		model.Author author = authorService.getSpecifiedAuthor(index);
		System.out.println(author.getFirstName() + " "+ author.getLastName());

		if(tipe.equals("modifica")){
			
			request.setAttribute("modifyAuthor", author);
			view = request.getRequestDispatcher("/modifyAuthor.jsp");
		}
		else if(tipe.equals("sterge")){
			System.out.println("!!!!!!!!AICI    ");
		
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
