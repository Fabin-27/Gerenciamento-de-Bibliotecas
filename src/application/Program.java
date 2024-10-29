package application;

import java.util.List;
import java.util.Scanner;

import DAO.Cliente_Dao;
import DAO.Livro_Dao;
import db.DB;
import entities.Clientes;
import entities.Livros;

@SuppressWarnings("unused")
public class Program {

	static Scanner sc = new Scanner(System.in);
	static Cliente_Dao clienteDAO = new Cliente_Dao();
	static Livro_Dao livroDAO = new Livro_Dao();

	public static void main(String[] args) {

		// DB.TestarConexão();
		Cabecalho_Principal();

	}

	public static void Cabecalho_Principal() {
		System.out.println("=============================================");
		System.out.println("           Sistema de Gerenciamento           ");
		System.out.println("                de Biblioteca                     ");
		System.out.println("=============================================");
		System.out.println();
		System.out.println("1. Área de Clientes");
		System.out.println("2. Área de Livros");
		System.out.println("0. Sair");
		System.out.println();
		switch_inicial();
	}

	public static void switch_inicial() {

		int x = sc.nextInt();

		switch (x) {

		case 0:

			System.out.println("Até a próxima :)");
			System.exit(0);

			break;

		case 1:

			cabecalho_clientes();
			opções_switch_cliente();

			break;

		case 2:

			cabecalho_Livros();
			opções_switch_livro();

			break;
		}

	}
	
	
	// ÁREA CLIENTES
	
	public static void cabecalho_clientes() {
		
		System.out.println("=============================================");
		System.out.println("           Área de Gerenciamento           ");
		System.out.println("                de Clientes                     ");
		System.out.println("=============================================");
		System.out.println();
		System.out.println("1. Inserir Cliente");
		System.out.println("2. Listar Clientes");
		System.out.println("3. Deletar Cliente");
		System.out.println("4. Editar Cliente");
		System.out.println("5. Buscar Cliente");
		System.out.println("6. Voltar");
		System.out.println("0. Sair");
		System.out.println();
		
	}
	
	public static void opções_switch_cliente() {

		int x = sc.nextInt();

		switch (x) {

		case 0:

			System.out.println("Saindo do sistema...");
			System.exit(0);

			break;

		case 1:

			InserirCliente();

			break;

		case 2:

			clienteDAO.listarClientes();

			System.out.println("Digite '9' para voltar para a área de Gerenciamento de clientes");
			int z = sc.nextInt();

			while (z != 9) {

				System.out.println("Comando inválido");
				System.out.println("Digite '9' para voltar para a área de Gerenciamento de clientes");
				z = sc.nextInt();
			}

			opções_switch_cliente();

			break;

		case 3:

			deletarClientePorId();

			break;

		case 4:

			editarClientePorId();

			break;

		case 5:

			System.out.println("1. Buscar Cliente por Id");
			System.out.println("2. Buscar Cliente por Nome");

			int opção_buscar = sc.nextInt();

			switch (opção_buscar) {

			case 1:

				buscarClientePorId();

				break;

			case 2:

				buscarClientePorNome();

				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");

			}

			break;
			
		case 6:

			Cabecalho_Principal();
			switch_inicial();

			break;

		default:
			System.out.println("Opção inválida. Tente novamente.");
		}

	}

	public static void buscarClientePorNome() {
		System.out.print("Digite o nome do cliente que deseja buscar: ");
		sc.nextLine();
		String nome = sc.nextLine();

		List<Clientes> clientesEncontrados = Cliente_Dao.buscarClientesPorNome(nome);

		if (!clientesEncontrados.isEmpty()) {
			System.out.println("Clientes encontrados:");
			for (Clientes cliente : clientesEncontrados) {
				System.out.println(cliente);
			}
		} else {
			System.out.println("Nenhum cliente encontrado com o nome especificado.");
		}
	}

	public static void buscarClientePorId() {
		System.out.print("Digite o ID do cliente que deseja buscar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();
			return;
		}

		try {
			int id = Integer.parseInt(valor_id);
			Clientes cliente = Cliente_Dao.buscarClientePorId(id);

			if (cliente != null) {
				System.out.println("Cliente encontrado: " + cliente);
			} else {
				System.out.println("Cliente com o ID especificado não encontrado.");
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
		}
	}

	public static void editarClientePorId() {
		System.out.print("Digite o ID do cliente que deseja editar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();
			return;
		}

		try {
			int id = Integer.parseInt(valor_id);

			System.out.print("Digite o novo nome (ou 'cancelar' para sair): ");
			String nome = sc.nextLine();
			if (nome.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			System.out.print("Digite o novo CPF (ou 'cancelar' para sair): ");
			String cpf = sc.nextLine();
			if (cpf.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			System.out.print("Digite o novo email (ou 'cancelar' para sair): ");
			String email = sc.nextLine();
			if (email.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			System.out.print("Digite o novo telefone (ou 'cancelar' para sair): ");
			String telefone = sc.nextLine();
			if (telefone.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			System.out.print("Digite o novo endereço (ou 'cancelar' para sair): ");
			String endereco = sc.nextLine();
			if (endereco.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			boolean editado = Cliente_Dao.editarCliente(id, nome, cpf, email, telefone, endereco);

			if (editado) {
				System.out.println("Cliente editado com sucesso!");
				Cabecalho_Principal();
			} else {
				System.out.println("Cliente com o ID especificado não encontrado.");
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
		}
	}

	public static void deletarClientePorId() {
		System.out.print("Digite o ID do cliente que deseja deletar (ou 'cancelar' para sair): ");
		sc.nextLine();
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();
			return;
		}

		try {
			int id = Integer.parseInt(valor_id);
			boolean deletado = Cliente_Dao.deletarCliente(id);

			if (deletado) {
				System.out.println("Cliente deletado com sucesso!");
				Cabecalho_Principal();
			} else {
				System.out.println("Cliente com o ID especificado não encontrado.");
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
		}
	}

	public static void InserirCliente() {

		System.out.print("Digite o nome do cliente (ou 'cancelar' para sair): ");
		sc.nextLine();
		String nome = sc.nextLine();
		if (nome.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			Cabecalho_Principal();
		}

		System.out.print("Digite o CPF do cliente (ou 'cancelar' para sair): ");
		String cpf = sc.nextLine();
		if (cpf.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			Cabecalho_Principal();
		}

		System.out.print("Digite o email do cliente (ou 'cancelar' para sair): ");
		String email = sc.nextLine();
		if (email.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			Cabecalho_Principal();
		}

		System.out.print("Digite o telefone do cliente (ou 'cancelar' para sair): ");
		String telefone = sc.nextLine();
		if (telefone.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			Cabecalho_Principal();
		}

		System.out.print("Digite o endereço do cliente (ou 'cancelar' para sair): ");
		String endereco = sc.nextLine();
		if (endereco.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			Cabecalho_Principal();
		}

		boolean inserido = Cliente_Dao.inserirCliente(nome, cpf, email, telefone, endereco);
		if (inserido) {
			System.out.println("Cliente inserido com sucesso!");
			Cabecalho_Principal();
		} else {
			System.out.println("Falha ao inserir o cliente.");
			Cabecalho_Principal();
		}
	}

	
	
	
	// ÁREA DOS LIVROS
	public static void cabecalho_Livros() {

		System.out.println("=============================================");
		System.out.println("           Área de Gerenciamento           ");
		System.out.println("                de Livros                     ");
		System.out.println("=============================================");
		System.out.println();
		System.out.println("1. Inserir Livro");
		System.out.println("2. Listar Livros");
		System.out.println("3. Deletar Livro");
		System.out.println("4. Editar Livro");
		System.out.println("5. Buscar Livro");
		System.out.println("6. Voltar");
		System.out.println("0. Sair");
		System.out.println();

	}

	public static void opções_switch_livro() {

		int x = sc.nextInt();

		switch (x) {

		case 0:

			System.out.println("Até a próxima :)");
			System.exit(0);

			break;

		case 1:

			InserirLivro();

			break;

		case 2:

			livroDAO.listarLivros();

			System.out.println("Digite '9' para voltar para a área de Gerenciamento de livros");

			int z = sc.nextInt();

			while (z != 9) {

				System.out.println("Comando inválido");
				System.out.println("Digite '9' para voltar para a área de Gerenciamento de livros");
				z = sc.nextInt();
			}

			cabecalho_Livros();
			opções_switch_livro();

			break;

		case 3:

			deletarLivroPorId();

			break;

		case 4:

			editarLivroPorId();

			break;

		case 5:

			System.out.println("1. Buscar Livro por Id");
			System.out.println("2. Buscar Livro por Titulo");

			int opção_buscar = sc.nextInt();

			switch (opção_buscar) {

			case 1:

				buscarLivroPorId();

				break;

			case 2:

				buscarLivroPorTitulo();

				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");

			}

			break;

		case 6:

			Cabecalho_Principal();
			switch_inicial();

			break;

		default:
			System.out.println("Opção inválida. Tente novamente.");
		}

	}

	public static void InserirLivro() {

		System.out.print("Digite o título (ou 'cancelar' para sair): ");
		sc.nextLine();
		String titulo = sc.nextLine();
		if (titulo.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();

		}

		System.out.print("Digite o autor (ou 'cancelar' para sair): ");
		String autor = sc.nextLine();
		if (autor.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();

		}

		System.out.print("Digite o ISBN (ou 'cancelar' para sair): ");
		String isbn = sc.nextLine();
		if (isbn.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();

		}

		System.out.print("Digite o ano de publicação (ou 'cancelar' para sair): ");
		String anoPublicacao = sc.nextLine();
		if (anoPublicacao.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();

		}

		boolean inserido = Livro_Dao.inserirLivro(titulo, autor, isbn, anoPublicacao);
		if (inserido) {
			System.out.println("Livro inserido com sucesso");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova inserção? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				InserirLivro();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					cabecalho_Livros();
					opções_switch_livro();

				}
			}

		} else {
			System.out.println("Falha ao inserir o Livro.");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				InserirLivro();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					cabecalho_Livros();
					opções_switch_livro();

				}
			}

		}

	}

	public static void deletarLivroPorId() {

		System.out.print("Digite o ID do livro que deseja deletar (ou 'cancelar' para sair): ");
		sc.nextLine();
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();
		}

		try {
			int id = Integer.parseInt(valor_id);
			boolean deletado = Livro_Dao.deletarLivro(id);

			if (deletado) {
				System.out.println("Livro deletado com sucesso!");

				System.out.println();
				System.out.println();
				System.out.println();

				System.out.println("Deseja deletar mais algum livro? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					deletarLivroPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_Livros();
						opções_switch_livro();

					}
				}

			} else {
				System.out.println("Livro com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					deletarLivroPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_Livros();
						opções_switch_livro();

					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");

			System.out.println();
			deletarLivroPorId();
		}
	}

	public static void editarLivroPorId() {

		System.out.print("Digite o ID do livro que deseja editar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();
		}

		try {
			int id = Integer.parseInt(valor_id);

			System.out.print("Digite o novo título (ou 'cancelar' para sair): ");
			String titulo = sc.nextLine();
			if (titulo.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			System.out.print("Digite o novo autor (ou 'cancelar' para sair): ");
			String autor = sc.nextLine();
			if (autor.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			System.out.print("Digite o novo ISBN (ou 'cancelar' para sair): ");
			String isbn = sc.nextLine();
			if (isbn.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			System.out.print("Digite o novo ano de publicação (ou 'cancelar' para sair): ");
			String anoPublicacao = sc.nextLine();
			if (anoPublicacao.equalsIgnoreCase("cancelar")) {
				Cabecalho_Principal();
				return;
			}

			boolean editado = Livro_Dao.editarLivro(id, titulo, autor, isbn, anoPublicacao);

			if (editado) {
				System.out.println("Livro editado com sucesso!");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova edição? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					editarLivroPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_Livros();
						opções_switch_livro();

					}
				}

			} else {
				System.out.println("Livro com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					editarLivroPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_Livros();
						opções_switch_livro();

					}
				}

			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");

			System.out.println();
			editarLivroPorId();
		}
	}

	public static void buscarLivroPorId() {

		System.out.print("Digite o ID do livro que deseja buscar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			Cabecalho_Principal();
		}

		try {
			int id = Integer.parseInt(valor_id);
			Livros livro = Livro_Dao.buscarLivroPorId(id);

			if (livro != null) {
				System.out.println("Livro encontrado: " + livro);

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova busca? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					buscarLivroPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_Livros();
						opções_switch_livro();

					}
				}
			} else {
				System.out.println("Livro com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					buscarLivroPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_Livros();
						opções_switch_livro();

					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");

			System.out.println();
			buscarLivroPorId();
		}
	}

	public static void buscarLivroPorTitulo() {

		System.out.print("Digite o título do livro que deseja buscar: ");
		sc.nextLine();
		String titulo = sc.nextLine();

		List<Livros> livrosEncontrados = Livro_Dao.buscarLivrosPorTitulo(titulo);

		if (!livrosEncontrados.isEmpty()) {
			System.out.println("Livros encontrados:");
			for (Livros livro : livrosEncontrados) {
				System.out.println(livro);
			}

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova busca? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				buscarLivroPorTitulo();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					buscarLivroPorTitulo();
					opções_switch_livro();

				}
			}
		} else {
			System.out.println("Nenhum livro encontrado com o título especificado.");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				buscarLivroPorTitulo();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					buscarLivroPorTitulo();
					opções_switch_livro();

				}
			}
		}
	}
}
