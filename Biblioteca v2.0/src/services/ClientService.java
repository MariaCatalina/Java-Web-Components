package services;

import java.sql.*;

import model.User;

public class ClientService {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/catalina";

	//  Database credentials
	private static final String USER = "postgres";
	private static final String PASS = "sql";

	private Connection conn = null;
	private PreparedStatement stmt = null;

	/**
	 * returneaza un obiect de tip User care corespunde cerintei
	 * @param email
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public User getUserData(String email) throws SQLException, ClassNotFoundException{
		ResultSet rs;
		User user = new User();
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			/* preluare informatii din baza de date */
			String sql = "SELECT user_id,user_firstName, user_lastName FROM users WHERE user_email = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			rs = stmt.executeQuery();

			while ( rs.next() ){
				int id = rs.getInt("user_id");
				String firstName = rs.getString("user_firstName");
				String lastName = rs.getString("user_lastName");

				user.setId(id);
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setLastName(lastName);
			}
			
			return user;

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
	 * metoda modifica datele din tabelul users
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void modifyUserData(String email, String firstName, String lastName) throws SQLException, ClassNotFoundException{
		String sql;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			/* modificare informatii din baza de date */
			sql = "UPDATE users SET user_firstName = ?, user_lastName = ? WHERE user_email = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, email);
			
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
