package negocio;

public class Cliente {
	
	private int id;
	private String nome;
	private String endere�o;
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
	public String getEndere�o() {
		return endere�o;
	}
	public void setEndere�o(String endere�o) {
		this.endere�o = endere�o;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", endere�o=" + endere�o + ", telefone=" + telefone + "]";
	}
	
	
	
	

}
