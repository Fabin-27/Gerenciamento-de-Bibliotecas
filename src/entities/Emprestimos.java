package entities;

public class Emprestimos {
	
	private int LivroId;
	private int ClienteId;
	private String DataEmprestimo;
	private String DataDevolucao;
	
	public Emprestimos(int livroId, int clienteId, String dataEmprestimo, String dataDevolucao) {
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
	
	
	
	
	
	

}
