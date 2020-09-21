package db.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.ConexaoMySql;
import negocio.Funcionario;

public class FuncionarioDAO extends ConexaoMySql {
	
	final String SQL_INSERT_FUNCIONARIO = "INSERT INTO Funcionario (nome,telefone,salario,data_cadastro,endereco) VALUES (?,?,?,?,?)";
	final String SQL_UPDATE_FUNCIONARIO = "UPDATE Funcionario SET nome=?,telefone=?,salario=?,endereco=? WHERE id=?";
	final String SQL_SELECT_FUNCIONARIO = "SELECT * FROM Funcionario";
	final String SQL_DELETE_FUNCIONARIO = "DELETE FROM Funcionario where id = ?";
	
	public int DeletarFuncionario(int id) throws SQLException, ClassNotFoundException {
		int retorno = 0;
		
		try (Connection connection = this.ConectarBanco();
			PreparedStatement pst = connection.prepareStatement(SQL_DELETE_FUNCIONARIO)){
			pst.setInt(1, id);
			
			retorno = pst.executeUpdate();			
		}
				
		catch (SQLException e) {
			throw e;
		}
		
		return retorno;
	}
	
	public int AtualizarFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
		int retorno = 0;
		
		try (Connection connection = this.ConectarBanco();
			PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_FUNCIONARIO);){
			pst.setString(1, funcionario.getNome());
			pst.setLong(2, funcionario.getTelefone());
			pst.setFloat(3, funcionario.getSalario());
			pst.setString(4, funcionario.getEndereco());
			pst.setInt(5, funcionario.getId());
			
			retorno = pst.executeUpdate();
			
		}
				
		catch (SQLException e) {
			throw e;
		}
		
		return retorno;
	}
	
	public int GravarFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException {
		int retorno = 0;
		
		try (Connection connection = this.ConectarBanco();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_FUNCIONARIO);){
			pst.setString(1, funcionario.getNome());
			pst.setLong(2, funcionario.getTelefone());
			pst.setFloat(3, funcionario.getSalario());
			pst.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pst.setString(5, funcionario.getEndereco());
			retorno = pst.executeUpdate();			
		}		
		catch (SQLException e) {
			throw e;
		}
		
		return retorno;
	}
	
	public List<Funcionario> ListarFuncionarios() throws SQLException, ClassNotFoundException {
		List<Funcionario> lstFuncionario = new ArrayList<Funcionario>();
		
		try (Connection connection = this.ConectarBanco();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_FUNCIONARIO);){			
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Funcionario funcionario = new Funcionario(); 
			
			funcionario.setId(rs.getInt("id"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setSalario(rs.getFloat("salario"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setEndereco(rs.getString("endereco"));
			funcionario.setData_cadastro(rs.getDate("data_cadastro"));
			
			lstFuncionario.add(funcionario);			
		}
	} 	
	catch (SQLException e) {
		throw e;
	}			
	return lstFuncionario;
	}
}
