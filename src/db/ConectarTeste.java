package db;

import java.sql.Connection;
import java.sql.SQLException;

public class ConectarTeste {

	public static void main(String[] args) {
		
		ConexaoMySql connect = new ConexaoMySql();
		
		Connection connection;
		try {
			connection = connect.ConectarBanco();
			System.out.println(connection);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
