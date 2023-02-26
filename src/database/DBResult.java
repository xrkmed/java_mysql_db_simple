package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBResult {

	//@xrkmed
	
	private ResultSet result = null;
	
	public DBResult(ResultSet _r){ result = _r; }
	
	public int getNumber(String column){
		try {
			return result.getInt(column);
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public String getString(String column) {
		try {
			return result.getString(column);
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public float getFloat(String column) {
		try {
			return result.getFloat(column);
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	
}
