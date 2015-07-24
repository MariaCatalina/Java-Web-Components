package servlets;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddBook extends HttpServlet {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* metoda verifica datele primite si adauga in memorie cartea */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			String autor = (String)request.getParameter("Autor"); 
			String titlu = (String)request.getParameter("Titlu"); 
			String nrE = (String)request.getParameter("Numar");

			model.MyBook b = new model.MyBook();
			int nrExemplare;	
			String sql;

			/* vefificare dacă datele nu sunt null */
			if(!autor.isEmpty() && !titlu.isEmpty() && !nrE.isEmpty() && b.verificaNrExemplare(nrE)){	

				nrExemplare = Integer.parseInt(nrE);

				/* se adauga datele in structura tabelului de adugare */
				request.setAttribute("book", new model.MyBook());

				/* se cauta daca cartea a mai fost adaugata anterior */
				sql = "SELECT b.book_nrExemplare FROM books b WHERE b.book_autor = '" + autor + "' AND ";
				sql += "b.book_titlu = '" + titlu + "'";
				ResultSet rs = stmt.executeQuery(sql);

				/* daca intoarce adevarat => cartea a mai fost introdusa */
				if(rs.next()){

					int nrNou = rs.getInt("book_nrExemplare") + nrExemplare;

					sql = "UPDATE books SET book_nrExemplare = '" + nrNou + "' WHERE book_autor = '" + autor + "' AND ";
					sql += "book_titlu = '" + titlu + "'";

					stmt.executeUpdate(sql);
				}
				else {
					/* se adauga datele in sql */					
					sql = "INSERT INTO books (book_autor,book_titlu, book_nrExemplare )" + " VALUES ( '" + autor +"' , '" + titlu + "' , '" + nrExemplare + "')"  ;

					stmt.executeUpdate(sql);
				}	
			}

			/* daca datele nu sunt integral corecte se pastreaza in memorie */
			else{

				if(!autor.isEmpty())
					b.setAutor(autor);

				if(!titlu.isEmpty())
					b.setTitlu(titlu);

				if(!nrE.isEmpty() && b.verificaNrExemplare(nrE))
					b.setNrExemplare(Integer.parseInt(nrE));

				request.setAttribute("book",b);
			}

			RequestDispatcher view = request.getRequestDispatcher("/AddedBook.jsp");
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
