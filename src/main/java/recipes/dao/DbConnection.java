package recipes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import recipes.exception.DbException;

public class DbConnection {
	private static final String SCHEMA = "recipes";
	private static final String USER = "recipes";
	private static final String PASSWORD = "recipes";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	
	// connection method
	public static Connection getConnection() {
		// This is the JDBC connection string
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, PORT, SCHEMA, USER, PASSWORD);
		
		// Used only for troubleshooting and testing
		//System.out.println("Connecting with url = " + url);
		
		try {
			// DriverManager is the JDBC class that manages drivers and connections
			Connection conn = DriverManager.getConnection(url);
			System.out.println("\nSuccessfully obtained connection!");
			return conn;
		} catch (SQLException e) {
			System.out.println("\nError getting connection.");
			throw new DbException(e);
		}
		
	}

} // end CLASS
