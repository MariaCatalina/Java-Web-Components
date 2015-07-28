package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class ClientService {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "sql";

	Connection conn = null;
	Statement stmt = null;
	
	public model.User getUserData(String email){
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			/* preluare informatii din baza de date */
			String sql = "SELECT user_id,user_firstName, user_lastName FROM users WHERE user_email = '" + email + "'";
			ResultSet rs = stmt.executeQuery(sql);

			model.User user = new User();

			while(rs.next()){

				int id = rs.getInt("user_id");
				String firstName = rs.getString("user_firstName");
				String lastName = rs.getString("user_lastName");
				
				user.setIndex(id);
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				
			}	
			
			return user;
		
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
	
public void modifyUserData(String email, String firstName, String lastName){
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			String sql;
	
			/* modificare informatii din baza de date */
			sql = "UPDATE users SET user_firstName ='" + firstName + "', user_lastName = '" + lastName + "'";
			sql += "WHERE user_email = '" + email + "'";
			
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
