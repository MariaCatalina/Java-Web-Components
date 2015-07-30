package services;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import model.Author;
import model.BorrowedBook;
import model.MyBook;
import model.User;

public class BorrowedBookService {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	private static final String USER = "postgres";
	private static final String PASS = "sql";

	private Connection conn;
	private Statement stmt;

	private ArrayList<BorrowedBook> borrowedB ;

	public BorrowedBookService(){
		conn = null;
		stmt = null;
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
			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName, borrowedbook_id,borrowedbook_date";
			sql +=", book_title, book_noCopies, book_noBorrowedCopies, user_firstName, user_lastName,user_id,user_email FROM borrowedbooks ";
			sql += " INNER JOIN books ON book_id = borrowedbook_book_id";
			sql += " INNER JOIN authors a ON book_author_id = a.author_id ";
			sql += " INNER JOIN users ON borrowedbook_user_id = user_id";

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
				String book_name = rs.getString("book_title");
				int nrExemplare  = rs.getInt("book_noCopies");
				int nrExemplareImp = rs.getInt("book_noBorrowedCopies");
				int ind = rs.getInt("book_id");

				/* date din tabelul users */
				String userFirstName = rs.getString("user_firstName");
				String userLastName = rs.getString("user_lastName");
				String userEmail = rs.getString("user_email");
				int userId = rs.getInt("user_id");


				int borrowedId = rs.getInt("borrowedbook_id");
				Date date = rs.getDate("borrowedbook_date");

				a = new Author(author_firstName, author_lastName, author_id);
				u = new User(userId, userEmail, userFirstName, userLastName);

				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(a, book_name,ind, nrExemplare, nrExemplareImp);

				bookB = new BorrowedBook();
				bookB.setBorrowedBook(u, b, borrowedId,date);

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
	
	/**
	 * 
	 * @param indexBook
	 * @param userEmail
	 */
	public void setBorrowedBook(int indexBook, String userEmail){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateT = new Date();
		String sql;
		
		ResultSet rs;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* preluare index user in functie de email */
			
			sql = "SELECT user_id FROM users WHERE user_email = '" + userEmail + "'";
			rs = stmt.executeQuery(sql);
			
			int indexUser = -1;
			
			while(rs.next()){
				indexUser = rs.getInt("user_id");
			}
			
			/* cautare in baza de date index-ul carti selectate pentru a lua numatrul de exemplare al cartii */
			sql = "SELECT b.book_noCopies, b.book_noborrowedcopies FROM books b WHERE b.book_id = '" + indexBook + "'";

			rs = stmt.executeQuery(sql);

			rs.next();
			int nrExemplare  = rs.getInt("book_noCopies") ;
			int nrExemplareImp = rs.getInt("book_noborrowedcopies");

			/* daca mai sunt carti care pot fi imprumutate */
			if( nrExemplare > nrExemplareImp){

				/* actualizare numar de carti imprumutate */
				sql = "UPDATE books SET book_noborrowedcopies = '" + (nrExemplareImp + 1) + "'" + "WHERE book_id = '" + indexBook + "'";
				stmt.executeUpdate(sql);

				/* actualizare tabelul cartilor imprumutate */
				sql = "INSERT INTO borrowedbooks (borrowedbook_user_id,borrowedbook_book_id,borrowedbook_date ) VALUES ('";
				sql += indexUser + "','" + indexBook + "','" + dateFormat.format(dateT) + "')";
				stmt.executeUpdate(sql);

			}

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
	
	public ArrayList<BorrowedBook> getSpecifiedBorrowedBooks(String emailUser){
		String sql;

		MyBook b;
		BorrowedBook bookB;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* accesare baza de date */
			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName, borrowedbook_id,borrowedbook_date";
			sql +=", book_title, book_noCopies, book_noBorrowedCopies, user_firstName, user_lastName,user_id,user_email FROM borrowedBooks ";
			sql += "INNER JOIN books ON book_id = borrowedbook_book_id ";
			sql += "INNER JOIN authors a ON book_author_id = a.author_id ";
			sql += "INNER JOIN users ON borrowedbook_user_id = user_id WHERE user_email = '" + emailUser + "'";

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
				String book_name = rs.getString("book_title");
				int nrExemplare  = rs.getInt("book_noCopies");
				int nrExemplareImp = rs.getInt("book_noBorrowedCopies");
				int ind = rs.getInt("book_id");

				/* date din tabelul users */
				String userFirstName = rs.getString("user_firstName");
				String userLastName = rs.getString("user_lastName");
				String userEmail = rs.getString("user_email");
				int userId = rs.getInt("user_id");


				int borrowedId = rs.getInt("borrowedbook_id");
				Date date = rs.getDate("borrowedbook_date");

				a = new Author(author_firstName, author_lastName, author_id);
				u = new User(userId, userEmail, userFirstName, userLastName);

				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(a, book_name,ind, nrExemplare, nrExemplareImp);

				bookB = new BorrowedBook();
				bookB.setBorrowedBook(u, b, borrowedId,date);

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
	
	/**
	 * metoda este apelata cand se seturneaza o carte si se modifica numerul de carti imprumutate
	 * @param indexBBook
	 */
	public void returnBorrowedBook(int indexBBook){
		ResultSet rs;
		String sql;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* cautare in baza de date a tabeluli borrowedBooks indexul cartii cerute */
			sql = "SELECT borrowedbook_book_id FROM borrowedbooks WHERE borrowedbook_id = '" + indexBBook + "'";
			rs = stmt.executeQuery(sql);
			
			int indexBook = -1;
			
			rs.next();
			indexBook = rs.getInt("borrowedbook_book_id");
			
			/* cautare in baza de date index-ul carti selectate pentru a lua numatrul de exemplare al cartii */
			sql = "SELECT book_id ,book_noBorrowedCopies FROM books WHERE book_id = '" + indexBook + "'";
			rs = stmt.executeQuery(sql);

			rs.next();
			int nrExemplareImp  = rs.getInt("book_noBorrowedCopies") ;
			
			/* actualizare numar de carti imprumutate din tabelul books*/
			sql = "UPDATE books SET book_noBorrowedCopies = '" + (nrExemplareImp - 1) + "'" + "WHERE book_id = '" + indexBook + "'";
			stmt.executeUpdate(sql);

			/* actualizare tabelul cartilor imprumutate */
			sql = "DELETE FROM borrowedbooks WHERE borrowedbook_id = '" + indexBBook + "'";
			stmt.executeUpdate(sql);

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
