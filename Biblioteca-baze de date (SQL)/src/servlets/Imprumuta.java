package servlets;

import java.io.IOException;
import java.sql.*;
import java.text.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;


public class Imprumuta extends HttpServlet {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* metoda este apelata cand se imprumuta o carte */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		String index = (String)request.getParameter("bookIndexI");
		String userEmail = (String) request.getAttribute("email");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateT = new Date();

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

			/* daca mai sunt carti care pot fi imprumutate */
			if( nrExemplare > nrExemplareImp){

				/* actualizare numar de carti imprumutate */
				sql = "UPDATE books SET book_nrExemplareImp = '" + (nrExemplareImp + 1) + "'" + "WHERE book_id = '" + index + "'";
				stmt.executeUpdate(sql);

				/* actualizare tabelul cartilor imprumutate */
				sql = "INSERT INTO borrowed_books (borrowedB_index,borrowedB_emailUser,borrowedB_date ) VALUES ('";
				sql += index + "','" + userEmail + "','" + dateFormat.format(dateT) + "')";
				stmt.executeUpdate(sql);

			}

			RequestDispatcher view = request.getRequestDispatcher("ListaCartiUser");
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
