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
	
	Connection conn = null;
	Statement stmt = null;
	
	ArrayList<model.MyBook> books ;
	
	public BooksService(){
		books = new ArrayList<model.MyBook>();
	}
	
	public ArrayList<MyBook> getAllBooks(){
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			/* accesare baza de date */
			sql = "SELECT book_id , a.author_id, a.author_firstName, a.author_lastName";
			sql +=", book_name, book_noExemplare, book_noExemplareImp FROM books ";
			sql += "LEFT JOIN authors a ON book_author_id = a.author_id";
			ResultSet rs = stmt.executeQuery(sql);
			
			
			MyBook b;
			Author a;
			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String author_firstName = rs.getString("author_firstName");
				String author_lastName = rs.getString("author_lastName");
				int author_id = rs.getInt("author_id");
				
				String book_name = rs.getString("book_name");
				int nrExemplare  = rs.getInt("book_noExemplare");
				int nrExemplareImp = rs.getInt("book_noExemplareImp");
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
	
}
