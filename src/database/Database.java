package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {

	// @xrkmed
	
	private static Connection conn = null;
	private static Statement st = null;
	
	public static Connection getConnection() {
		if(conn == null) {			
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dbUrl");
				String username = props.getProperty("user");
				String password = props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
			}catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
			
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			if(st != null) {
				st.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}catch(IOException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public static ResultSet executeQuery(String query) {
		try {
			if(conn != null) {
				st = conn.createStatement();
				ResultSet _r = st.executeQuery(query);
				
				return _r;
			}
		} catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		
		return null;
	} 
}
