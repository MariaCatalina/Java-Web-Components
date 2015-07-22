package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowedBook;

public class ListaSpUser extends HttpServlet{

	/* /* metoda seteaza un atribut cu lista de carti imprumutate de user-ul curent */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String email = (String) request.getAttribute("email");
		String sort = (String)request.getParameter("selSortD");

		model.DataBorrowedBook table = ((model.DataBorrowedBook) getServletContext().getAttribute("tableUser"));

		ArrayList<BorrowedBook> users = table.getUser();
		ArrayList<BorrowedBook> lista =  new ArrayList<BorrowedBook>();
		ArrayList<BorrowedBook> listaSort =  new ArrayList<BorrowedBook>();

		for (BorrowedBook book : users){
			if(book.getUserEmail().equals(email)){
				lista.add(book);
			}
		}

		if(sort == null){
			request.setAttribute("listaCartiSpUser",lista);
		}
		else
			if(sort.equals("selDupaData")){

				listaSort = table.sortByDate(lista);
				request.setAttribute("listaCartiSpUser",listaSort);
			}

		RequestDispatcher view = request.getRequestDispatcher("/ListaSpUser.jsp");
		view.forward(request, response);
	}
}