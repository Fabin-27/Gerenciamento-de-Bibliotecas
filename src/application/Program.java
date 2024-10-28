package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.Cliente_Dao;
import DAO.Livro_Dao;
import db.DB;

public class Program {

	static Scanner sc = new Scanner(System.in);
	static Cliente_Dao clienteDAO = new Cliente_Dao();
	static Livro_Dao livroDAO = new Livro_Dao();

	public static void main(String[] args) {

		imprimirCabecalho();

	}
	
	public static void deletarClientePorId() {
		System.out.print("Digite o ID do cliente que deseja deletar (ou 'cancelar' para sair): ");
        sc.nextLine();
        String valor_id = sc.nextLine();

        if (valor_id.equalsIgnoreCase("cancelar")) {
            imprimirCabecalho();
            return;
        }

        try {
            int id = Integer.parseInt(valor_id);
            boolean deletado = Cliente_Dao.deletarCliente(id);

            if (deletado) {
                System.out.println("Cliente deletado com sucesso!");
                imprimirCabecalho();
            } 
            else {
                System.out.println("Cliente com o ID especificado não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, insira um número.");
        }
	}
	
	public static void deletarLivroPorId() {
        System.out.print("Digite o ID do livro que deseja deletar (ou 'cancelar' para sair): ");
        sc.nextLine();
        String valor_id = sc.nextLine();

        if (valor_id.equalsIgnoreCase("cancelar")) {
            imprimirCabecalho();
            return;
        }

        try {
            int id = Integer.parseInt(valor_id);
            boolean deletado = Livro_Dao.deletarLivro(id);

            if (deletado) {
                System.out.println("Livro deletado com sucesso!");
                imprimirCabecalho();
            } 
            else {
                System.out.println("Livro com o ID especificado não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, insira um número.");
        }
    }

	public static void switch_inicial() {

		int x = sc.nextInt();

		switch (x) {

		case 0:

			System.out.println("Saindo do sistema...");
			System.exit(0);

			break;

		case 1:

			System.out.println("=============================================");
			System.out.println("           Área de Gerenciamento           ");
			System.out.println("                de Clientes                     ");
			System.out.println("=============================================");
			System.out.println();
			System.out.println("1. Inserir Cliente");
			System.out.println("2. Listar Clientes");
			System.out.println("3. Deletar Cliente");
			System.out.println();

			opções_switch_cliente();

			break;

		case 2:

			System.out.println("=============================================");
			System.out.println("           Área de Gerenciamento           ");
			System.out.println("                de Livros                     ");
			System.out.println("=============================================");
			System.out.println();
			System.out.println("1. Inserir Livro");
			System.out.println("2. Listar Livros");
			System.out.println("3. Deletar Livro");
			System.out.println();

			opções_switch_livro();

			break;
		}

	}

	public static void opções_switch_livro() {
		int x = sc.nextInt();

		switch (x) {

		case 0:

			System.out.println("Saindo do sistema...");
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
			
			System.out.println("=============================================");
			System.out.println("           Área de Gerenciamento           ");
			System.out.println("                de Livros                     ");
			System.out.println("=============================================");
			System.out.println();
			System.out.println("1. Inserir Livro");
			System.out.println("2. Listar Livros");
			System.out.println("3. Deletar Livro");
			System.out.println();

			opções_switch_livro();

			break;
			
		case 3:
			
			deletarLivroPorId();
			
			break;

		default:
			System.out.println("Opção inválida. Tente novamente.");
		}

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

			System.out.println("=============================================");
			System.out.println("           Área de Gerenciamento           ");
			System.out.println("                de Clientes                     ");
			System.out.println("=============================================");
			System.out.println();
			System.out.println("1. Inserir Cliente");
			System.out.println("2. Listar Clientes");
			System.out.println("3. Deletar Cliente");
			System.out.println();

			opções_switch_cliente();

			break;
			
		case 3:
			
			deletarClientePorId();
			
			break;

		default:
			System.out.println("Opção inválida. Tente novamente.");
		}

		sc.close();
	}

	public static void imprimirCabecalho() {
		System.out.println("=============================================");
		System.out.println("           Sistema de Gerenciamento           ");
		System.out.println("                de Clientes                     ");
		System.out.println("=============================================");
		System.out.println();
		System.out.println("1. Área de Clientes");
		System.out.println("2. Área de Livros");
		System.out.println("0. Sair");
		System.out.println();
		switch_inicial();
	}

	public static void InserirCliente() {

		System.out.print("Digite o nome do cliente (ou 'cancelar' para sair): ");
		sc.nextLine();
		String nome = sc.nextLine();
		if (nome.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			imprimirCabecalho();
		}

		System.out.print("Digite o CPF do cliente (ou 'cancelar' para sair): ");
		String cpf = sc.nextLine();
		if (cpf.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			imprimirCabecalho();
		}

		System.out.print("Digite o email do cliente (ou 'cancelar' para sair): ");
		String email = sc.nextLine();
		if (email.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			imprimirCabecalho();
		}

		System.out.print("Digite o telefone do cliente (ou 'cancelar' para sair): ");
		String telefone = sc.nextLine();
		if (telefone.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			imprimirCabecalho();
		}

		System.out.print("Digite o endereço do cliente (ou 'cancelar' para sair): ");
		String endereco = sc.nextLine();
		if (endereco.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			imprimirCabecalho();
		}

		boolean inserido = Cliente_Dao.inserirCliente(nome, cpf, email, telefone, endereco);
		if (inserido) {
			System.out.println("Cliente inserido com sucesso!");
			imprimirCabecalho();
		} else {
			System.out.println("Falha ao inserir o cliente.");
			imprimirCabecalho();
		}
	}

	public static void InserirLivro() {

		System.out.print("Digite o título (ou 'cancelar' para sair): ");
		sc.nextLine();
		String titulo = sc.nextLine();
		if (titulo.equalsIgnoreCase("cancelar")) {
			imprimirCabecalho();

		}

		System.out.print("Digite o autor (ou 'cancelar' para sair): ");
		String autor = sc.nextLine();
		if (autor.equalsIgnoreCase("cancelar")) {
			imprimirCabecalho();

		}

		System.out.print("Digite o ISBN (ou 'cancelar' para sair): ");
		String isbn = sc.nextLine();
		if (isbn.equalsIgnoreCase("cancelar")) {
			imprimirCabecalho();

		}

		System.out.print("Digite o ano de publicação (ou 'cancelar' para sair): ");
		String anoPublicacao = sc.nextLine();
		if (anoPublicacao.equalsIgnoreCase("cancelar")) {
			imprimirCabecalho();

		}

		boolean inserido = Livro_Dao.inserirLivro(titulo, autor, isbn, anoPublicacao);
		if (inserido) {
			System.out.println("Livro inserido com sucesso");
			System.out.println();
			imprimirCabecalho();
		} else {
			System.out.println("Falha ao inserir o cliente.");
		}

	}

	public static void TestarConexão() {

		try {
			Connection conn = DB.getConnection();
			if (conn != null) {
				System.out.println("Conexão com o banco de dados realizada com sucesso!");
			} else {
				System.out.println("Falha na conexão com o banco de dados.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar conectar ao banco de dados: " + e.getMessage());
		}

	}

}
