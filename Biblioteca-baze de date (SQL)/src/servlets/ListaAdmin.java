package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class ListaAdmin extends HttpServlet{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* metoda seteaza un atribut cu lista totala de carti pentru admin */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

		ArrayList<model.MyBook> books =  new ArrayList<model.MyBook>();
		model.MyBook b;
		//books = ((model.Gestiune)request.getServletContext().getAttribute("gestiune")).getList();

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
			String sql = "SELECT book_id ,book_autor,book_titlu,book_nrExemplare,book_nrExemplareImp FROM books";
			ResultSet rs = stmt.executeQuery(sql);
			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String autor = rs.getString("book_autor");
				String titlu = rs.getString("book_titlu");
				int nrExemplare  = rs.getInt("book_nrExemplare");
				int nrExemplareImp = rs.getInt("book_nrExemplareImp");
				int ind = rs.getInt("book_id");
				
				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(autor, titlu,ind, nrExemplare, nrExemplareImp);
				books.add(b);
			}
			rs.close();

			request.setAttribute("listaCompleta",books);

			RequestDispatcher view = request.getRequestDispatcher("/ListaAdmin.jsp");
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
