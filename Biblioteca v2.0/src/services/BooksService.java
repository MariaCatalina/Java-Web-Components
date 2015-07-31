package services;

import java.sql.*;
import java.util.ArrayList;

import model.Author;
import model.MyBook;

public class BooksService {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	private static final String USER = "postgres";
	private static final String PASS = "sql";

	private Connection conn;
	private PreparedStatement stmt;

	private ArrayList<model.MyBook> books ;

	public BooksService(){
		conn = null;
		stmt = null;
		books = new ArrayList<model.MyBook>();
	}

	/**
	 * metoda returneaza toate cartile din baza de date
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<MyBook> getAllBooks() throws SQLException, ClassNotFoundException{
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* accesare baza de date */
			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName";
			sql +=", book_title, book_noCopies, book_noBorrowedCopies FROM books ";
			sql += "LEFT JOIN authors a ON book_author_id = a.author_id";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

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
	 * @param authorId
	 * @param title
	 * @param noCopies
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void setBook(int authorId, String title, int noCopies) throws SQLException, ClassNotFoundException{
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* se cauta daca cartea a mai fost adaugata anterior */
			sql = "SELECT b.book_noCopies FROM books b WHERE b.book_author_id = ? AND ";
			sql += "b.book_title = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, authorId);
			stmt.setString(2, title);

			rs = stmt.executeQuery();

			/* daca intoarce adevarat => cartea a mai fost introdusa */
			if(rs.next()){
				int nrNou = rs.getInt("book_noCopies") + noCopies;
				sql = "UPDATE books SET book_noCopies = ? WHERE book_author_id = ? AND book_title = ?";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, nrNou);
				stmt.setInt(2, authorId);
				stmt.setString(3, title);

				stmt.executeUpdate();
			}
			else {
				/* se adauga datele in sql */					
				sql = "INSERT INTO books (book_author_id,book_title, book_noCopies ) VALUES ( ?, ? ,? )";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, authorId);
				stmt.setString(2, title);
				stmt.setInt(3, noCopies);

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

	/**
	 * metoda extrage din baza de date cartea specificata prin index
	 * @param indexBook
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MyBook getSpecifiedBook(int idBook) throws SQLException, ClassNotFoundException{
		String sql;
		ResultSet rs;
		MyBook b = new MyBook();

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* preluare informatii din baza de date */
			sql = "SELECT book_title, book_author_id FROM books WHERE book_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idBook);
			
			rs = stmt.executeQuery();
			Author a = new Author();
			
			while(rs.next()){

				String title = rs.getString("book_title");
				int authorID = rs.getInt("book_author_id");

				b.setTitle(title);
				b.setId(idBook);
				a.setId(authorID);
				b.setAutor(a);
				
			}

			return b;

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
	 * metoda modifica datele unei carti
	 * @param indexBook
	 * @param indexAuthor
	 * @param title
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void modifyBook(int indexBook, int indexAuthor, String title) throws SQLException, ClassNotFoundException{
		String sql;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* actualizeaza baza de date */
			sql = "UPDATE books SET book_title = ?, book_author_id = ? WHERE book_id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setInt(2, indexAuthor);
			stmt.setInt(3, indexBook);
			
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

	/**
	 * metoda vefirica daca o carte poate fi stersa si in funtie
	 * de rezultat se sterge un exemplar sau toata cartea
	 * @param index-ul carti selectate
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void deleteBook(int index) throws SQLException, ClassNotFoundException{
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* cautare in baza de date index-ul carti selectate pentru a lua numatrul de exemplare al cartii */
			sql = "SELECT b.book_noCopies, b.book_noborrowedcopies FROM books b WHERE b.book_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, index);
			rs = stmt.executeQuery();

			rs.next();
			int nrExemplare  = rs.getInt("book_noCopies") ;
			int nrExemplareImp = rs.getInt("book_noborrowedcopies");

			/* daca nr extras =1 carte se sterge de tot */
			if( nrExemplare > nrExemplareImp && nrExemplare > 1 ){
				sql = "UPDATE books SET book_noCopies = ? WHERE book_id = ?";
			
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, nrExemplare - 1);
				stmt.setInt(2,index);
				
				stmt.executeUpdate();
			}
			/* daca mai e un singur exemplar cartea se sterge */
			else if (nrExemplare == 1 && nrExemplareImp == 0){
				sql = "DELETE FROM books b WHERE b.book_id = ?";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, index);
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

	/**
	 * metoda returneaza lista de carti sorta in functie de parametri dati
	 * @param sort
	 * @param tipSortAuthor
	 * @param tipSortTitle
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<MyBook> getSortedListBooks(String sort, String tipSortAuthor, String tipSortTitle) throws SQLException, ClassNotFoundException{
		String sql;
		ResultSet rs;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

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
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

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
