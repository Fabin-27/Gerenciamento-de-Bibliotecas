package entities;

public class Emprestimos {
	
	private int id;
	private int LivroId;
	private int ClienteId;
	private String DataEmprestimo;
	private String DataDevolucao;
    private String livroTitulo;
    private String clienteNome;
    private String livroStatus;
	
	public Emprestimos() {
		
	}
	
	public Emprestimos(int id, int livroId, int clienteId, String dataEmprestimo, String dataDevolucao, String status) {
		this.setId(id);
		LivroId = livroId;
		ClienteId = clienteId;
		DataEmprestimo = dataEmprestimo;
		DataDevolucao = dataDevolucao;
	}

	public int getLivroId() {
		return LivroId;
	}

	public void setLivroId(int livroId) {
		LivroId = livroId;
	}

	public int getClienteId() {
		return ClienteId;
	}

	public void setClienteId(int clienteId) {
		ClienteId = clienteId;
	}

	public String getDataEmprestimo() {
		return DataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		DataEmprestimo = dataEmprestimo;
	}

	public String getDataDevolucao() {
		return DataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		DataDevolucao = dataDevolucao;
	}

	@Override
	public String toString() {
		return "Emprestimos [LivroId=" + LivroId + ", ClienteId=" + ClienteId + ", DataEmprestimo=" + DataEmprestimo
				+ ", DataDevolucao=" + DataDevolucao + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLivroTitulo() {
        return livroTitulo;
    }

    public void setLivroTitulo(String livroTitulo) {
        this.livroTitulo = livroTitulo;
    }

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getLivroStatus() {
		return livroStatus;
	}

	public void setLivroStatus(String livroStatus) {
		this.livroStatus = livroStatus;
	}


}
