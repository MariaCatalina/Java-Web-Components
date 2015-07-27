package services;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

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

			model.Author author = new model.Author();
			String sql;

			/* vefificare dacă datele nu sunt null */
			if( !firstName.isEmpty() && !lastName.isEmpty() ){	

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
}
