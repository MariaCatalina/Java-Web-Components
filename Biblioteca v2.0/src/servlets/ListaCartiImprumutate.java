package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BorrowedBook;
import services.BorrowedBookService;

public class ListaCartiImprumutate extends HttpServlet {

	

	/* metoda seteaza un atribut cu lista totala de carti */
	public void doGet(HttpServletRequest request, HttpServletResponse response )throws IOException, ServletException{

	 
		BorrowedBookService bookService = new BorrowedBookService();
		ArrayList<BorrowedBook> borrowedB = bookService.getAllBorrowedBooks();
		
		request.setAttribute("listaImp",borrowedB);


		RequestDispatcher view = request.getRequestDispatcher("/BorrowedBooksList.jsp");
		view.forward(request, response);

		
	}
}
