package services;

import java.sql.*;
import java.util.*;
import java.util.Date;

import model.*;

public class BorrowedBookService {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	private static final String USER = "postgres";
	private static final String PASS = "sql";

	private Connection conn;
	private PreparedStatement stmt;

	private ArrayList<BorrowedBook> borrowedB ;

	public BorrowedBookService(){
		conn = null;
		stmt = null;
		borrowedB = new ArrayList<BorrowedBook>(); 
	}

	/**
	 * metoda uneste cele 4 tabele si extrage datele din ele
	 * @return list cu carti imprumutate
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<BorrowedBook> getAllBorrowedBooks() throws SQLException, ClassNotFoundException{
		String sql;

		model.MyBook b;
		model.BorrowedBook bookB;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* accesare baza de date */
			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName, borrowedbook_id,borrowedbook_date";
			sql +=", book_title, book_noCopies, book_noBorrowedCopies, user_firstName, user_lastName,user_id,user_email FROM borrowedbooks ";
			sql += " INNER JOIN books ON book_id = borrowedbook_book_id";
			sql += " INNER JOIN authors a ON book_author_id = a.author_id ";
			sql += " INNER JOIN users ON borrowedbook_user_id = user_id";

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			User u;
			Author a;

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
	
	/**
	 * 
	 * @param indexBook
	 * @param userEmail
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("deprecation")
	public void setBorrowedBook(int indexBook, String userEmail) throws SQLException, ClassNotFoundException{
		Date dateT = new Date();
		String sql;
		
		ResultSet rs;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* preluare index user in functie de email */
			
			sql = "SELECT user_id FROM users WHERE user_email = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userEmail);
			
			rs = stmt.executeQuery();
			
			int indexUser = -1;
			
			while(rs.next()){
				indexUser = rs.getInt("user_id");
			}
			
			/* cautare in baza de date index-ul carti selectate pentru a lua numatrul de exemplare al cartii */
			sql = "SELECT b.book_noCopies, b.book_noborrowedcopies FROM books b WHERE b.book_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, indexBook);
			
			rs = stmt.executeQuery();

			rs.next();
			int nrExemplare  = rs.getInt("book_noCopies") ;
			int nrExemplareImp = rs.getInt("book_noborrowedcopies");

			/* daca mai sunt carti care pot fi imprumutate */
			if( nrExemplare > nrExemplareImp){

				/* actualizare numar de carti imprumutate */
				sql = "UPDATE books SET book_noborrowedcopies = ? WHERE book_id = ?";
				stmt =  conn.prepareStatement(sql);
				stmt.setInt(1, nrExemplareImp + 1 );
				stmt.setInt(2, indexBook);
				
				stmt.executeUpdate();

				/* actualizare tabelul cartilor imprumutate */
				sql = "INSERT INTO borrowedbooks (borrowedbook_user_id,borrowedbook_book_id,borrowedbook_date ) VALUES ( ? ,?, ? )";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, indexUser);
				stmt.setInt(2, indexBook);
				stmt.setDate(3, new java.sql.Date(dateT.getDate()));
				
				stmt.executeUpdate();

			}
			
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
	
	public ArrayList<BorrowedBook> getSpecifiedBorrowedBooks(String emailUser) throws SQLException, ClassNotFoundException{
		String sql;

		MyBook b;
		BorrowedBook bookB;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName, borrowedbook_id,borrowedbook_date";
			sql +=", book_title, book_noCopies, book_noBorrowedCopies, user_firstName, user_lastName,user_id,user_email FROM borrowedBooks ";
			sql += "INNER JOIN books ON book_id = borrowedbook_book_id ";
			sql += "INNER JOIN authors a ON book_author_id = a.author_id ";
			sql += "INNER JOIN users ON borrowedbook_user_id = user_id WHERE user_email = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailUser);
			
			ResultSet rs = stmt.executeQuery();

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
	
	/**
	 * metoda este apelata cand se seturneaza o carte si se modifica numerul de carti imprumutate
	 * @param indexBBook
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void returnBorrowedBook(int indexBBook) throws SQLException, ClassNotFoundException{
		ResultSet rs;
		String sql;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* cautare in baza de date a tabeluli borrowedBooks indexul cartii cerute */
			sql = "SELECT borrowedbook_book_id FROM borrowedbooks WHERE borrowedbook_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, indexBBook);
			
			rs = stmt.executeQuery();
			
			int indexBook = -1;
			
			rs.next();
			indexBook = rs.getInt("borrowedbook_book_id");
			
			/* cautare in baza de date index-ul carti selectate pentru a lua numatrul de exemplare al cartii */
			sql = "SELECT book_id ,book_noBorrowedCopies FROM books WHERE book_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, indexBook);
		
			rs = stmt.executeQuery();

			rs.next();
			int nrExemplareImp  = rs.getInt("book_noBorrowedCopies") ;
			
			/* actualizare numar de carti imprumutate din tabelul books*/
			sql = "UPDATE books SET book_noBorrowedCopies = ? WHERE book_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nrExemplareImp - 1);
			stmt.setInt(2, indexBook);
			
			stmt.executeUpdate();

			/* actualizare tabelul cartilor imprumutate */
			sql = "DELETE FROM borrowedbooks WHERE borrowedbook_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, indexBBook);
			
			stmt.executeUpdate();

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
