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

public class ListaCartiImprumutate extends HttpServlet {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* metoda seteaza un atribut cu lista totala de carti */
	public void doGet(HttpServletRequest request, HttpServletResponse response )throws IOException, ServletException{

		ArrayList<BorrowedBook> borrowedB = new ArrayList<BorrowedBook>(); 
		model.MyBook b;
		model.BorrowedBook bookB;

		String sql;
		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* accesare baza de date */
			sql = "SELECT b.book_id ,b.book_autor,b.book_titlu, b.book_nrExemplare,b.book_nrExemplareImp, " ; 
			sql += " bB.borrowedB_id,bB.borrowedB_emailUser, bB.borrowedB_date FROM books b";
			sql += " RIGHT JOIN borrowed_books bB ON b.book_id = bB.borrowedB_index ";

			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String autor = rs.getString("book_autor");
				String titlu = rs.getString("book_titlu");
				String email = rs.getString("borrowedB_emailUser");
				Date d = rs.getDate("borrowedB_date");

				int nrExemplare  = rs.getInt("book_nrExemplare");
				int nrExemplareImp = rs.getInt("book_nrExemplareImp");
				int ind = rs.getInt("book_id");

				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(autor, titlu,ind, nrExemplare, nrExemplareImp);
				
				bookB = new BorrowedBook();
				bookB.setBookB(email, b, d);

				borrowedB.add(bookB);
			}

			rs.close();

			request.setAttribute("listaImp",borrowedB);


			RequestDispatcher view = request.getRequestDispatcher("/ListaCartiUser.jsp");
			view.forward(request, response);


		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	}
}
