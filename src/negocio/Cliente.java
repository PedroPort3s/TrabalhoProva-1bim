package negocio;

public class Cliente {
	
	private int id;
	private String nome;
	private String endereço;
	private long telefone;
	
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
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", endereço=" + endereço + ", telefone=" + telefone + "]";
	}
	
	
	
	

}
