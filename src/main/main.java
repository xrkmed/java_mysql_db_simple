package main;

import java.sql.Connection;
import java.sql.ResultSet;

import database.DBQuery;
import database.DBResult;
import database.Database;

public class main {
	
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
			
			DBQuery query = new DBQuery("UPDATE teste SET username = ? WHERE id = ?;");
			query.replace(1, "trocado");
			query.replace(2, 0);
			query.run();
			
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
