package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;

import model.Author;
import model.BorrowedBook;
import model.MyBook;
import model.User;

public class BorrowedBookService {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	Connection conn = null;
	Statement stmt = null;

	ArrayList<BorrowedBook> borrowedB ;

	public BorrowedBookService(){
		borrowedB = new ArrayList<BorrowedBook>(); 
	}

	/**
	 * metoda uneste cele 4 tabele si extrage datele din ele
	 * @return list cu carti imprumutate
	 */
	public ArrayList<BorrowedBook> getAllBorrowedBooks(){
		String sql;

		model.MyBook b;
		model.BorrowedBook bookB;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* accesare baza de date */
			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName, borrowedbook_id";
			sql +=", book_name, book_noExemplare, book_noExemplareImp, user_firstName, user_lastName,user_id,user_email FROM books ";
			sql += "INNER JOIN authors a ON book_author_id = a.author_id ";
			sql += "INNER JOIN borrowedBooks ON book_id = borrowedbook_id ";
			sql += "INNER JOIN users ON borrowedbook_user_id = user_id";

			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set

			User u;
			Author a;

			//STEP 5: Extract data from result set
			while(rs.next()){

				/* date din tabelul authors */
				String author_firstName = rs.getString("author_firstName");
				String author_lastName = rs.getString("author_lastName");
				int author_id = rs.getInt("author_id");

				/* date din tabelul books */
				String book_name = rs.getString("book_name");
				int nrExemplare  = rs.getInt("book_noExemplare");
				int nrExemplareImp = rs.getInt("book_noExemplareImp");
				int ind = rs.getInt("book_id");

				/* date din tabelul users */
				String userFirstName = rs.getString("user_firstName");
				String userLastName = rs.getString("user_lastName");
				String userEmail = rs.getString("user_email");
				int userId = rs.getInt("user_id");


				int borrowedId = rs.getInt("borrowedbook_id");

				a = new Author(author_firstName, author_lastName, author_id);
				u = new User(userId, userEmail, userFirstName, userLastName);

				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(a, book_name,ind, nrExemplare, nrExemplareImp);

				bookB = new BorrowedBook();
				bookB.setBookB(u, b, borrowedId);

				borrowedB.add(bookB);
			}

			rs.close();

			return borrowedB;

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

		return borrowedB;
	}
}
