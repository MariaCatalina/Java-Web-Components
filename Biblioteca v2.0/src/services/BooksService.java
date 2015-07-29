package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Author;
import model.MyBook;

public class BooksService {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	Connection conn;
	Statement stmt;

	ArrayList<model.MyBook> books ;

	public BooksService(){
		conn = null;
		stmt = null;
		books = new ArrayList<model.MyBook>();
	}

	/**
	 * metoda returneaza toate cartile din baza de date
	 * @return
	 */
	public ArrayList<MyBook> getAllBooks(){
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();


			/* accesare baza de date */
			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName";
			sql +=", book_title, book_noCopies, book_noBorrowedCopies FROM books ";
			sql += "LEFT JOIN authors a ON book_author_id = a.author_id";
			rs = stmt.executeQuery(sql);

			MyBook b;
			Author a;

			//STEP 5: Extract data from result set
			while(rs.next()){

				/* date din tabelul authors*/
				String author_firstName = rs.getString("author_firstName");
				String author_lastName = rs.getString("author_lastName");
				int author_id = rs.getInt("author_id");

				/* date din tabelul books */
				String book_name = rs.getString("book_title");
				int nrExemplare  = rs.getInt("book_noCopies");
				int nrExemplareImp = rs.getInt("book_noBorrowedCopies");
				int ind = rs.getInt("book_id");

				a = new Author(author_firstName, author_lastName, author_id);

				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(a, book_name,ind, nrExemplare, nrExemplareImp);
				books.add(b);

			}
			rs.close();

			return books;

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

		return books;

	}

	/**
	 * 
	 * @param authorId
	 * @param title
	 * @param noCopies
	 */
	public void setBook(int authorId, String title, int noCopies){
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* se cauta daca cartea a mai fost adaugata anterior */
			sql = "SELECT b.book_noCopies FROM books b WHERE b.book_author_id = '" + authorId + "' AND ";
			sql += "b.book_title = '" + title + "'";
			rs = stmt.executeQuery(sql);

			/* daca intoarce adevarat => cartea a mai fost introdusa */
			if(rs.next()){

				int nrNou = rs.getInt("book_noCopies") + noCopies;

				sql = "UPDATE books SET book_noCopies = '" + nrNou + "' WHERE book_author_id = '" + authorId + "' AND ";
				sql += "book_title = '" + title + "'";

				stmt.executeUpdate(sql);
			}
			else {
				/* se adauga datele in sql */					
				sql = "INSERT INTO books (book_author_id,book_title, book_noCopies )" ;
				sql += " VALUES ( '" + authorId +"' , '" + title + "' , '" + noCopies + "')"  ;

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

	/**
	 * metoda extrage din baza de date cartea specificata prin index
	 * @param indexBook
	 * @return
	 */
	public MyBook getSpecifiedBook(int indexBook){
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* preluare informatii din baza de date */
			sql = "SELECT book_id,book_title, book_author_id FROM books WHERE book_id = '" + indexBook +"'";
			rs = stmt.executeQuery(sql);

			MyBook b = new MyBook();
			while(rs.next()){

				int id = rs.getInt("book_id");
				String title = rs.getString("book_title");
				int authorID = rs.getInt("book_author_id");

				b.setTitlu(title);
				b.setIndex(id);
				b.getAutor().setIndex(authorID);
			}

			return b;

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

		return null;
	}

	/**
	 * metoda modifica datele unei carti
	 * @param indexBook
	 * @param indexAuthor
	 * @param title
	 */
	public void modifyBook(int indexBook, int indexAuthor, String title){
		String sql;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* actualizeaza baza de date */
			sql = "UPDATE books SET book_title ='" + title + "', book_author_id = '" + indexAuthor + "'";
			sql += "WHERE book_id = '" + indexBook + "'";
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

	/**
	 * metoda vefirica daca o carte poate fi stersa si in funtie
	 * de rezultat se sterge un exemplar sau toata cartea
	 * @param index-ul carti selectate
	 */
	public void deleteBook(int index){
		String sql;
		ResultSet rs;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* cautare in baza de date index-ul carti selectate pentru a lua numatrul de exemplare al cartii */
			sql = "SELECT b.book_noCopies, b.book_noborrowedcopies FROM books b WHERE b.book_id = '" + index + "'";

			rs = stmt.executeQuery(sql);
			
			rs.next();
			int nrExemplare  = rs.getInt("book_noCopies") ;
			int nrExemplareImp = rs.getInt("book_noborrowedcopies");

			/* daca nr extras =1 carte se sterge de tot */
			if( nrExemplare > nrExemplareImp && nrExemplare > 1 ){

				sql = "UPDATE books SET book_noCopies = '" + (nrExemplare - 1) + "'" + "WHERE book_id = '" + index + "'";
				stmt.executeUpdate(sql);

			}
			/* daca mai e un singur exemplar cartea se sterge */
			else if (nrExemplare == 1 && nrExemplareImp == 0){

				sql = "DELETE FROM books b WHERE b.book_id = '" + index + "'";
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

	/**
	 * metoda returneaza lista de carti sorta in functie de parametri dati
	 * @param sort
	 * @param tipSortAuthor
	 * @param tipSortTitle
	 * @return
	 */
	public ArrayList<MyBook> getSortedListBooks(String sort, String tipSortAuthor, String tipSortTitle){
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* daca nu e selectata nici o optiune de sortare */

			sql = "SELECT author_id, author_firstName, author_lastName, book_id , book_title, ";
			sql +="book_noCopies,book_noBorrowedCopies FROM books LEFT JOIN authors ON author_id = book_author_id";

			/* sort == selDupaAutor -> lista sortata dupa autor */
			if(sort != null && sort.equals("selDupaAutor")){

				if ( tipSortAuthor == null || tipSortAuthor.equals("asc")){
					sql += " ORDER BY TRIM (author_lastName) ASC, TRIM (author_firstName) ASC";
				}
				else
					if(tipSortAuthor.equals("desc")){
						sql += " ORDER BY TRIM (author_lastName) DESC,  TRIM (author_firstName) DESC ";
					}
			}
			else{
				/* sort == selDupaTitlu -> lista sortata dupa titlu */
				if(sort != null && sort.equals("selDupaTitlu")){

					if ( tipSortTitle == null || tipSortTitle.equals("asc")){
						sql += " ORDER BY book_title ASC ";
					}
					else
						if(tipSortTitle.equals("desc")){
							sql += " ORDER BY book_title DESC ";
						}
				}
			}

			rs = stmt.executeQuery(sql);
			
			Author a;
			MyBook b;
		
			while(rs.next()){

				/* date din tabelul authors */
				int author_id = rs.getInt("author_id");
				String autor_firstName = rs.getString("author_firstName");
				String autor_lastName = rs.getString("author_lastName");

				a = new Author(autor_firstName, autor_lastName, author_id);
				
				/* date din tabelul books */
				String book_title = rs.getString("book_title");
				int nrExemplare  = rs.getInt("book_noCopies");
				int nrExemplareImp = rs.getInt("book_noborrowedcopies");
				int ind = rs.getInt("book_id");

				/* adaugarea valorilor in lista pentru a fi setate ca atribut */
				b = new model.MyBook();
				b.setBook(a, book_title,ind, nrExemplare, nrExemplareImp);
				books.add(b);
			}

			rs.close();

			return books;

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

		return books;
	}
}
