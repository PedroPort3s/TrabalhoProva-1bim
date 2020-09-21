package controlFuncionario;

import java.sql.SQLException;
import java.util.List;

import db.Funcionario.FuncionarioDAO;
import negocio.Funcionario;

public class ControlFuncionario {
	
	private void ValidarFuncionarioId(Funcionario funcionario) throws Exception {		
		if(funcionario == null) {
			throw new Exception("Nenhum funcionario foi informado!");
		}
		
		if(funcionario.getId() <= 0) {
			throw new Exception("A id " +funcionario.getId() +" informada � inv�lida." );
		}
	}
	
	private void ValidarFuncionarioCompleto(Funcionario funcionario) throws Exception {		
		if(funcionario == null) {
			throw new Exception("Nenhum funcionario foi informado!");
		}
		
		if(funcionario.getNome() == null || funcionario.getNome().equals("")) {
			throw new Exception("Informe o nome do cliente");
		}
		
		if(funcionario.getNome().length() < 3) {
			throw new Exception("Informe um nome com pelo menos 3 caracteres");
		}
		
		if(funcionario.getSalario() <= 998) {
			throw new Exception("Informe pelo menos um sal�rio m�nimo para o funcion�rio");
		}
		
		if(funcionario.getEndereco() == null || funcionario.getEndereco().equals("")) {
			throw new Exception("Informe o endere�o do funcion�rio");
		}
		
		if(funcionario.getTelefone() <= 0) {
			throw new Exception("Informe o telefone do funcion�rio");
		}
	}
	
	public List<Funcionario> ListarFuncionarios() throws ClassNotFoundException, SQLException{
		return new FuncionarioDAO().ListarFuncionarios();
	}
	
	public int DeletarFuncionario(Funcionario funcionario) throws Exception {
		int retorno = 0;
		
		try {
			this.ValidarFuncionarioId(funcionario);
			
			retorno = new FuncionarioDAO().DeletarFuncionario(funcionario.getId());
			
		} catch (Exception e) {
			throw e;
		}
		
		
		return retorno;
	}
	
	public int AtualizarFuncionario(Funcionario funcionario) throws Exception {
		int retorno = 0;
		
		try {
			this.ValidarFuncionarioCompleto(funcionario);
			
			retorno = new FuncionarioDAO().AtualizarFuncionario(funcionario);
			
		} catch (Exception e) {
			throw e;
		}
		
		
		return retorno;
	}
	
	public int GravarFuncionario(Funcionario funcionario) throws Exception {
		int retorno = 0;
		
		try {
			this.ValidarFuncionarioCompleto(funcionario);
			
			retorno = new FuncionarioDAO().GravarFuncionario(funcionario);
			
		} catch (Exception e) {
			throw e;
		}
		
		
		return retorno;
	}

	
}
