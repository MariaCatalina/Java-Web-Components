package services;

import java.sql.*;
import java.util.ArrayList;

import model.Author;

public class AuthorService {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	Connection conn = null;
	Statement stmt = null;

	private ArrayList<model.Author> authorsList;


	public AuthorService(){
		authorsList = new ArrayList<>();
	}

	/**
	 * functia returneaza lista de autori din baza de date
	 * @return
	 */
	public ArrayList<model.Author> getAllAuthors(){

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* preluare informatii din baza de date */
			String sql = "SELECT author_id,author_firstName, author_lastName FROM authors";
			ResultSet rs = stmt.executeQuery(sql);

			model.Author a;

			while(rs.next()){

				int id = rs.getInt("author_id");
				String firstName = rs.getString("author_firstName");
				String lastName = rs.getString("author_lastName");

				/* adaugare in lista */
				a = new model.Author(firstName,lastName,id);
				authorsList.add(a);
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

		return authorsList;

	}

	/**
	 * metoda verifica daca informatiile date ca paramentru sunt in baza de date
	 * in caz contrar le adauga
	 * @param firstName
	 * @param lastName
	 * @return 2 - autorul exista in baza de date
	 * 		   1 - autorul a fost adaugat in baza de date  
	 */
	public int createAuthor(String firstName, String lastName){

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			String sql;

			/* verificare daca autorul este deja adaugat */

			sql = "SELECT author_id FROM authors WHERE author_firstName = '" + firstName + "' AND ";
			sql += "author_lastName = '" + lastName + "'";

			ResultSet rs = stmt.executeQuery(sql);

			/* daca autorul introdus nu s-a gasit in baza de date se adauga */
			if ( rs.next() ){
				return 2;
			}
			else {
				/* se adauga datele in sql */					
				sql = "INSERT INTO authors (author_firstName, author_lastName) VALUES ('" + firstName + "','" + lastName + "')";

				stmt.executeUpdate(sql);
				return 1;
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

		return 0;
	}
	
	/**
	 * metoda returneaza valorile de la un anumit index din baza de date a autorilor
	 * @param index
	 * @return
	 */
	public model.Author getSpecifiedAuthor(int index){
		
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* preluare informatii din baza de date */
			String sql = "SELECT author_id,author_firstName, author_lastName FROM authors WHERE author_id = '" + index +"'";
			ResultSet rs = stmt.executeQuery(sql);
		
			model.Author a = new Author();
			while(rs.next()){

				int id = rs.getInt("author_id");
				String firstName = rs.getString("author_firstName");
				String lastName = rs.getString("author_lastName");

				/* adaugare in lista */
				a.setFirstName(firstName);
				a.setLastName(lastName);
				a.setIndex(id);
			}
			
			return a;
				
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
	 * metoda cauta in baza de date in functie de index si modifica datele
	 * @param firstNameM
	 * @param lastNameM
	 * @param index
	 */
	public void modifyAuthor(String firstNameM, String lastNameM, int index){
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			String sql;

			/* actualizeaza baza de date */
			sql = "UPDATE authors SET author_firstName ='" + firstNameM + "', author_lastName = '" + lastNameM + "'";
			sql += "WHERE author_id = '" + index + "'";
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
	 * metoda cauta in baza de data daca unui autor ii e asignata una sau mai multe carti
	 * si sterge in functie de rezultat
	 * @param index
	 * @return
	 */
	public boolean deleteAuthor(int index){
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			String sql;
			
			/* verific daca autorul are carte in tabelul cu carti */
			
			sql = "SELECT book_id FROM books WHERE book_author_id = '" + index +"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			
			if( rs.next() ){
				/* autorul nu se poate sterge */
				return false;
			}
			
			else{
				/* stergere din baza de date */
				sql = "DELETE FROM authors WHERE author_id = '" + index +"'";
				stmt.executeUpdate(sql);
				return true;
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
		
		return false;
	}
}
