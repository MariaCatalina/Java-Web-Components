package servlets;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Returneaza extends HttpServlet{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* clasa starge din lista de carti imprumutate cartea, pentru a fi returnata */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String index = (String)request.getParameter("bookIndexR");

		Connection conn = null;
		Statement stmt = null;
		String sql;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* cautare in baza de date index-ul carti selectate pentru a lua numatrul de exemplare al cartii */
			sql = "SELECT b.book_id ,b.book_nrExemplareImp FROM books b RIGHT JOIN borrowed_books bB ON b.book_id = bB.borrowedB_index  WHERE bB.borrowedB_id = '" + index + "'";

			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			int nrExemplareImp  = rs.getInt("book_nrExemplareImp") ;
			int idBook = rs.getInt("book_id");

			/* actualizare numar de carti imprumutate din tabelul books*/
			sql = "UPDATE books SET book_nrExemplareImp = '" + (nrExemplareImp - 1) + "'" + "WHERE book_id = '" + idBook + "'";
			stmt.executeUpdate(sql);

			/* actualizare tabelul cartilor imprumutate */
			sql = "DELETE FROM borrowed_books WHERE borrowedB_id = '" + index + "'";
			stmt.executeUpdate(sql);

			RequestDispatcher view = request.getRequestDispatcher("ListaCartiImprumutateUser");
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
