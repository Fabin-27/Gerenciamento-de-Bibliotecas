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
		opções_switch();

	}

	public static void opções_switch() {
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

			break;

		case 3:
			InserirLivro();

			break;

		case 4:

			livroDAO.listarLivros();
			
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
		System.out.println("1. Inserir Cliente");
		System.out.println("2. Listar Clientes");
		System.out.println("3. Inserir Livro");
		System.out.println("4. Listar Livros");
		System.out.println("0. Sair");
		System.out.println();
	}

	public static void InserirCliente() {

		System.out.print("Digite o nome do cliente: ");
		String nome = sc.nextLine();

		System.out.print("Digite o CPF do cliente: ");
		String cpf = sc.nextLine();

		System.out.print("Digite o email do cliente: ");
		String email = sc.nextLine();

		System.out.print("Digite o telefone do cliente: ");
		String telefone = sc.nextLine();

		System.out.print("Digite o endereço do cliente: ");
		String endereco = sc.nextLine();

		boolean inserido = Cliente_Dao.inserirCliente(nome, cpf, email, telefone, endereco);
		if (inserido) {
			System.out.println("Cliente inserido com sucesso!");
		} else {
			System.out.println("Falha ao inserir o cliente.");
		}
	}

	public static void InserirLivro() {

		System.out.print("Digite o titulo: ");
		String titulo = sc.nextLine();

		System.out.print("Digite o autor: ");
		String autor = sc.nextLine();

		System.out.print("Digite o isbn: ");
		String isbn = sc.nextLine();

		System.out.print("Digite o ano de publicação: ");
		String anoPublicacao = sc.nextLine();

		boolean inserido = Livro_Dao.inserirLivro(titulo, autor, isbn, anoPublicacao);
		if (inserido) {
			System.out.println("Livro inserido com sucesso");
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
