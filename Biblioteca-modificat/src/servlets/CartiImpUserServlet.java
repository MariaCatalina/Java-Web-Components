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
		model.DataTable table = ((model.DataTable) getServletContext().getAttribute("tableUser"));
		
		ArrayList<BorrowedBook> users = table.getUser();
		ArrayList<BorrowedBook> sUser = new ArrayList<BorrowedBook>();
		
		String email = (String) request.getAttribute("role");
		
		for(BorrowedBook b : users){
			if (b.getUser().equals(email))
				sUser.add(b);
		}
		
		request.setAttribute("listaCartiUser",sUser);
		
		RequestDispatcher view = request.getRequestDispatcher("/CartiImprumutate.jsp");
		view.forward(request, response);
	}
}
