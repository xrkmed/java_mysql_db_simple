package main;

import java.sql.Connection;
import java.sql.ResultSet;

import database.DBListQueries;
import database.DBQuery;
import database.DBResult;
import database.Database;

public class main {
	
	//@xrkmed
	//EXAMPLE USE
	
	private static Connection conn;

	public static void main(String[] args) {
		System.out.println("Tentando conectar ao banco de dados...");
		try {
			conn = Database.getConnection();
			System.out.println("Conectado ao banco de dados!");
			
			ResultSet rs = new DBQuery("select * from teste").executeQuery();
			while(rs.next()) {
				DBResult result = new DBResult(rs);
				System.out.println("ID: " + result.getNumber("id") + ", user: " + result.getString("username") + " and permissions " + result.getNumber("permissions"));
			}
			
			DBListQueries queries = new DBListQueries();
			DBQuery query = new DBQuery("UPDATE teste SET username = ? WHERE id = ?;");
			queries.insert(query);
			query.replace(1, "trocado");
			query.replace(2, 0);
			queries.executeSafety();
			
			rs = new DBQuery("select * from teste").executeQuery();
			while(rs.next()) {
				DBResult result = new DBResult(rs);
				System.out.println("ID: " + result.getNumber("id") + ", user: " + result.getString("username") + " and permissions " + result.getNumber("permissions"));
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(conn != null) {
				Database.closeConnection();
			}
		}
	}

}
