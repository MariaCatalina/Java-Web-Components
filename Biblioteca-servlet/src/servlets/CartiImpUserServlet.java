package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowedBook;

public class CartiImpUserServlet extends HttpServlet{

	public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{
		
		model.DataBorrowedBook table = ((model.DataBorrowedBook) getServletContext().getAttribute("tableUser"));
		
		ArrayList<BorrowedBook> users = table.getUser();
		ArrayList<BorrowedBook> sUser = new ArrayList<BorrowedBook>();
		
		String email = (String) request.getAttribute("role");
		
		/* se parcurge lista de carti imprumutate si se creeaza o lista doar cu cartile user-ului curent */
		for(BorrowedBook b : users){
			if (b.getUserEmail().equals(email))
				sUser.add(b);
		}
		
		request.setAttribute("listaCartiUser",sUser);
		
		RequestDispatcher view = request.getRequestDispatcher("/CartiImprumutate.jsp");
		view.forward(request, response);
	}
}
