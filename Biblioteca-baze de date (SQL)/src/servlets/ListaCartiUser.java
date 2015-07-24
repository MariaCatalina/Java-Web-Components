package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ListaCartiUser extends HttpServlet{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* metoda seteaza un atribut cu lista de carti pentru user in functie de cerinta*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {

		ArrayList<model.MyBook> books = new ArrayList<model.MyBook>();;
		model.MyBook b;
		
		String sort = (String)request.getParameter("selSort");
		String tip = (String)request.getParameter("tipA");
		String tipT = (String)request.getParameter("tipT");
		
		String sql;
		ResultSet rs;

		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* daca nu e selectata nici o optiune de sortare */
			
			sql = "SELECT book_id ,book_autor,book_titlu,book_nrExemplare,book_nrExemplareImp FROM books";

			/* sort == selDupaAutor -> lista sortata dupa autor */
			if(sort != null && sort.equals("selDupaAutor")){

				if ( tip == null || tip.equals("asc")){
					sql += " ORDER BY book_autor ASC ";
				}
				else
					if(tip.equals("desc")){
						sql += " ORDER BY book_autor DESC ";
					}
			}
			else{
				/* sort == selDupaTitlu -> lista sortata dupa titlu */
				if(sort != null && sort.equals("selDupaTitlu")){
					
					if ( tipT == null || tipT.equals("asc")){
						sql += " ORDER BY book_titlu ASC ";
					}
					else
						if(tipT.equals("desc")){
							sql += " ORDER BY book_titlu DESC ";
						}
				}
			}

			rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){

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

			/* set atribute -> prelucret in jsp */
			request.setAttribute("listaCompleta",books);

			RequestDispatcher view = request.getRequestDispatcher("/ListeAleator.jsp");
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
