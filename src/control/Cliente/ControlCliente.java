package control.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import negocio.Cliente;
import util.Verifica;
import view.Principal.Principal;

public class ControlCliente {	
	
	public ControlCliente() {
		if(Principal.listaDeClientes == null) {			
			Principal.listaDeClientes = new ArrayList<Cliente>();
		}
	}
	
	public Cliente CarregarCliente(int id) throws Exception {
		Cliente cliente = null;
		
		try {
			
			if(id <= 0) {
				throw new Exception("Id inv�lida");
			}
			
			if(Principal.listaDeClientes!= null) {
				cliente = Principal.listaDeClientes.get(id);
				
				if(cliente == null) {
					throw new Exception("Cliente n�o encontrado na lista.");
				}
				
			}
			else {
				throw new Exception("N�o h� nenhum cliente para carregar");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return cliente;
	}
	
	public List<Cliente> ListarClientes(){
		return Principal.listaDeClientes;		
	}
	
	public int DeletarCliente(int id) throws Exception{
		int retorno = 0;
		
		try {
			
			if(id <= 0) {
				throw new Exception("Nenhum cliente foi informado para a exclus�o");
			}
			
			if (Principal.listaDeClientes != null) {
				
				if (Principal.listaDeClientes.removeIf(x->x.getId() == id) == false) {
					throw new Exception("Erro ao remover o cliente da lista.");
				}
				
				retorno = 1;
			}
			else {
				throw new Exception("N�o h� clientes a serem removidos");
			}
		} catch (Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public int EditarCliente(Cliente cliente) throws Exception {
		int retorno = 0;
		try {
			
			ValidarCliente(cliente);
			
			if (Principal.listaDeClientes != null) {
				
				if (Principal.listaDeClientes.removeIf(x->x.getId() == cliente.getId()) == false) {
					throw new Exception("Erro ao remover o cliente da lista.");
				}
				
				if (Principal.listaDeClientes.add(cliente) == false) {
					throw new Exception("Erro ao editar o cliente da lista.");
				}
				
				retorno = 1;
			}
			else {
				throw new Exception("N�o h� clientes a serem editados");
			}
		} catch (Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public int CadastrarCliente(Cliente cliente) throws Exception {
		
		int retorno = 0;
		
		try {			
			
			ValidarCliente(cliente);
			
			if (Principal.listaDeClientes != null) {						
				cliente.setId(Principal.listaDeClientes.size() + 1);
				
				if (Principal.listaDeClientes.add(cliente) == false) {
					throw new Exception("Erro ao adicionar o cliente na lista.");
				}
				
				retorno = 1;
			}
			else {
				throw new Exception("N�o h� clientes a serem editados");
			}
		} catch (Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public void ValidarCliente(Cliente cliente) throws Exception {
		if(cliente == null) {
			throw new Exception("Nenhum cliente foi informado.");
		}
		
		if(cliente.getNome() == null || cliente.getNome().equals("")) {
			throw new Exception("Informe o nome do cliente");
		}
		
		if(cliente.getNome().length() < 3) {
			throw new Exception("Informe um nome com pelo menos 3 caracteres");
		}
		
		if(cliente.getEndere�o() == null || cliente.getEndere�o().equals("")) {
			throw new Exception("Informe o endere�o do cliente");
		}
		
		if(cliente.getTelefone() <= 0) {
			throw new Exception("Informe o telefone do cliente");
		}
	}
	
	public List<Cliente> PesquisarLista(String pesquisa) throws Exception{
		
		List<Cliente> lstCliente = null;
		
		try {			
			if (Principal.listaDeClientes != null) {
				
				if (pesquisa != null && pesquisa != "") {
					
					lstCliente = Principal.listaDeClientes.stream().filter(x->x.getEndere�o().toUpperCase().contains(pesquisa.toUpperCase()) || 
																   x.getNome().toUpperCase().contains(pesquisa.toUpperCase()) ||
																   x.getTelefone() == (Verifica.ehNumeroLong(pesquisa) == true ? Long.parseLong(pesquisa) : 0) ||
																   x.getId() == (Verifica.ehNumeroInt(pesquisa) == true ? Integer.parseInt(pesquisa) : 0)).collect(Collectors.toList());
				}
				else {
					throw new Exception("Informe a id, nome, telefone ou endere�o para a pesquisa");
				}
			}
			else {
				throw new Exception("N�o h� clientes a serem listados");
			}
		} catch (Exception e) {
			throw e;
		}
		
		return lstCliente;
	}

}
