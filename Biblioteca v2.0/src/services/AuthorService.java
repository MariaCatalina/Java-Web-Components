package services;

import java.sql.*;
import java.util.ArrayList;

import model.Author;

public class AuthorService {

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";
	
	//  Database credentials
	private static final String USER = "postgres";
	private static final String PASS = "sql";

	private Connection conn = null;
	private PreparedStatement stmt = null;

	private ArrayList<model.Author> authorsList;

	public AuthorService(){
		authorsList = new ArrayList<>();
	}

	/**
	 * functia returneaza lista de autori din baza de date
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<Author> getAllAuthors() throws SQLException, ClassNotFoundException{
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* preluare informatii din baza de date */
			String sql = "SELECT author_id,author_firstName, author_lastName FROM authors";
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			Author a;

			while(rs.next()){

				int id = rs.getInt("author_id");
				String firstName = rs.getString("author_firstName");
				String lastName = rs.getString("author_lastName");

				/* adaugare in lista */
				a = new model.Author(firstName,lastName,id);
				authorsList.add(a);
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

		return authorsList;

	}

	/**
	 * metoda verifica daca informatiile date ca paramentru sunt in baza de date
	 * in caz contrar le adauga
	 * @param firstName
	 * @param lastName
	 * @return 2 - autorul exista in baza de date
	 * 		   1 - autorul a fost adaugat in baza de date  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public int createAuthor(String firstName, String lastName) throws ClassNotFoundException, SQLException{
		String sql;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			/* verificare daca autorul este deja adaugat */
			sql = "SELECT author_id FROM authors WHERE author_firstName = ? AND author_lastName = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			
			ResultSet rs = stmt.executeQuery();

			/* daca autorul introdus nu s-a gasit in baza de date se adauga */
			if ( rs.next() ){
				
				return 2;
			
			}
			else {
				/* se adauga datele in sql */					
				sql = "INSERT INTO authors (author_firstName, author_lastName) VALUES (?,?)";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
			
				stmt.executeUpdate();
				return 1;
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
	 * metoda returneaza valorile de la un anumit index din baza de date a autorilor
	 * @param index
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Author getSpecifiedAuthor(int idAuthor) throws ClassNotFoundException, SQLException{
		Author author = new Author();
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* preluare informatii din baza de date */
			String sql = "SELECT author_id,author_firstName, author_lastName FROM authors WHERE author_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idAuthor);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){

				int id = rs.getInt("author_id");
				String firstName = rs.getString("author_firstName");
				String lastName = rs.getString("author_lastName");

				/* adaugare in lista */
				author.setFirstName(firstName);
				author.setLastName(lastName);
				author.setId(id);
			}
			
			return author;
				
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}
	
	/**
	 * metoda cauta in baza de date in functie de index si modifica datele
	 * @param firstNameM
	 * @param lastNameM
	 * @param index
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void modifyAuthor(String firstNameM, String lastNameM, int index) throws ClassNotFoundException, SQLException{
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql;

			/* actualizeaza baza de date */
			sql = "UPDATE authors SET author_firstName = ?, author_lastName = ? WHERE author_id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstNameM);
			stmt.setString(2, lastNameM);
			stmt.setInt(3, index);
				
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
	 * metoda cauta in baza de data daca unui autor ii e asignata una sau mai multe carti
	 * si sterge in functie de rezultat
	 * @param index
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public boolean deleteAuthor(int idAuthor) throws ClassNotFoundException, SQLException{
		String sql;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* verific daca autorul are carte in tabelul cu carti */
			sql = "SELECT book_id FROM books WHERE book_author_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idAuthor);
			
			ResultSet rs = stmt.executeQuery();
			
			if( rs.next() ){
				/* autorul nu se poate sterge */
				return false;
			}
			
			else{
				/* stergere din baza de date */
				sql = "DELETE FROM authors WHERE author_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, idAuthor);
				
				stmt.executeUpdate();
				return true;
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
}
