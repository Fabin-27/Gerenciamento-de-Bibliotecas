package entities;

public class Clientes {
	
	private int Id;
	private String Nome;
	private String Cpf;
    private String Email;
    private String Telefone;
    private String Endereco;
    
	public Clientes(int id, String nome, String cpf, String email, String telefone, String endereco) {
		Id = id;
		Nome = nome;
		Cpf = cpf;
		Email = email;
		Telefone = telefone;
		Endereco = endereco;
	}

	public Clientes() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
	
	@Override
	public String toString() {
	    return "ID: " + Id 
	           + ", Nome: " + Nome 
	           + ", CPF: " + Cpf 
	           + ", Email: " + Email 
	           + ", Telefone: " + Telefone 
	           + ", Endereço: " + Endereco;
	}

	
	
    
    
}
