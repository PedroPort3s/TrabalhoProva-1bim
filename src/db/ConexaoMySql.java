package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMySql {

	private String url = "jdbc:mysql://localhost:3306/prova1bim?useTimezone=true&serverTimezone=UTC";
	private String root = "root";
	private String key = "Srv20@@";
	
	public Connection ConectarBanco() throws SQLException, ClassNotFoundException {		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(url, root, key);		
	}
}
