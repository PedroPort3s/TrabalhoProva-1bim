package negocio;

import java.util.Date;

import util.Verifica;

public class Funcionario {
	
	private int id;
	private String nome;
	private long telefone;	
	private float salario;
	private Date data_cadastro;
	private String endereco;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if(Verifica.ehNumeroLong(telefone)) {
			this.telefone = Long.parseLong(telefone);
		}
		else {
			this.telefone = 0;
		}
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public void setSalario(String salario) {		
		if(Verifica.ehNumeroFloat(salario)) {
			this.salario = Float.parseFloat(salario);
		}
		else {
			this.salario = 0;
		}
	}
	
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", salario=" + salario
				+ ", data_cadastro=" + data_cadastro + ", endereco=" + endereco + "]";
	}
	
}
