package servlets;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class DeleteBook extends HttpServlet {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* metoda este apelata cand bibliotecarul sterge o carte */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String index = (String)request.getParameter("bookIndex");

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
			sql = "SELECT b.book_nrExemplare, b.book_nrExemplareImp FROM books b WHERE b.book_id = '" + index + "'";

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int nrExemplare  = rs.getInt("book_nrExemplare") ;
			int nrExemplareImp = rs.getInt("book_nrExemplareImp");
			
			/* daca nr extras =1 carte se sterge de tot */
			if( nrExemplare > nrExemplareImp && nrExemplare > 1 ){
				
				sql = "UPDATE books SET book_nrExemplare = '" + (nrExemplare - 1) + "'" + "WHERE book_id = '" + index + "'";
				stmt.executeUpdate(sql);
		
			}
			/* daca mai e un singur exemplar cartea se sterge */
			else if (nrExemplare == 1 && nrExemplareImp == 0){
				
				sql = "DELETE FROM books b WHERE b.book_id = '" + index + "'";
				stmt.executeUpdate(sql);
			}
			
			RequestDispatcher view = request.getRequestDispatcher("ListaCarti");
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
