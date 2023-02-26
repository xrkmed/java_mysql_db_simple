package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBQuery {
	
	//xrkmed
	
	private PreparedStatement statement = null;
	private Connection conn = null;
	
	public DBQuery(String query) throws SQLException {
		conn = Database.getConnection();
		statement = conn.prepareStatement(query);
	}
	
	public ResultSet executeQuery() {
		try {
			return statement.executeQuery();
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public int executeUpdate() {
		try {
			return statement.executeUpdate();
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public void run() {
		try {
			statement.execute();
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public <T> void replace(int index, T newValue) {
		try {
		if (newValue instanceof String) {
			statement.setString(index, (String) newValue);
		}else
		if(newValue instanceof Integer) {
			statement.setInt(index, (Integer) newValue);
		}else
		if(newValue instanceof Float) {
			statement.setFloat(index, (float) newValue);
		}else
		if(newValue instanceof Double) {
			statement.setDouble(index, (double) newValue);
		}else {
			throw new DBException("Só é possível realizar a troca de resultados String, Integer, Float e Double.");
		}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	
	
	

}
