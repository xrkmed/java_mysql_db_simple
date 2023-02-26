package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBListQueries {

	// Executar queries em massa é algo complicado devido ao problema de exceção gerado por uma query no inicio ou no meio da transação, podendo ocarionar perda total nas informações e não prosseguir com toda a negociação. Esta tabela trata este caso em específico visando a organização das queries mantendo todas as informações.
	// @xrkmed
	
	private List<DBQuery> queries = new ArrayList();
	private Connection conn = null;
	
	public DBListQueries() {
		conn = Database.getConnection();
	}
	
	public void insert(DBQuery _q) {
		queries.add(_q);
	}
	
	public void executeSafety() {
		if(conn != null) {
			boolean oldCommit = false;
			try {
				oldCommit = conn.getAutoCommit();
				
				conn.setAutoCommit(false);
				for(DBQuery query : queries) {
					query.run();
				}
				conn.commit();
			}catch(SQLException e) {
				try {
					conn.rollback();
					throw new DBException("Transaction rolled back caused by " + e.getMessage());
				}catch(SQLException criticalError) {
					throw new DBException(criticalError.getMessage());
				}				
			}finally {
				try {
					conn.setAutoCommit(oldCommit);
				}catch(SQLException e) {
					throw new DBException(e.getMessage());
				}
			}
		}
	}
	
}
