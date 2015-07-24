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

public class ListaSpUser extends HttpServlet{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	/* /* metoda seteaza un atribut cu lista de carti imprumutate de user-ul curent */
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		String email = (String) request.getAttribute("email");
		String sort = (String)request.getParameter("selSortD");

		ArrayList<BorrowedBook> lista =  new ArrayList<BorrowedBook>();
		model.MyBook b;
		model.BorrowedBook bB;

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

			sql = "SELECT b.book_id ,b.book_autor,b.book_titlu, b.book_nrExemplare,b.book_nrExemplareImp, " ; 
			sql += " bB.borrowedB_id,bB.borrowedB_emailUser, bB.borrowedB_date FROM books b";
			sql += " RIGHT JOIN borrowed_books bB ON b.book_id = bB.borrowedB_index WHERE bB.borrowedB_emailUser= '" + email + "'";

			if(sort != null && sort.equals("selDupaData")){
				sql += " ORDER BY bB.borrowedB_date";
			}

			rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){

				String autor = rs.getString("book_autor");
				String titlu = rs.getString("book_titlu");
				int nrExemplare  = rs.getInt("book_nrExemplare");
				int nrExemplareImp = rs.getInt("book_nrExemplareImp");
				int ind = rs.getInt("book_id");
				int indBbook = rs.getInt("borrowedB_id");
				Date d = rs.getDate("borrowedB_date");
				
				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(autor, titlu,ind, nrExemplare, nrExemplareImp);
				
				bB = new model.BorrowedBook();
				bB.setBookB(email,b,d,indBbook);
				
				lista.add(bB);
			
			}

			rs.close();

			/* set atribute -> prelucret in jsp */
			request.setAttribute("listaCartiSpUser",lista);


			RequestDispatcher view = request.getRequestDispatcher("/ListaSpUser.jsp");
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