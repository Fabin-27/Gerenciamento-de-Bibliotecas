package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.Cliente_Dao;
import db.DB;

public class Program {

	static Scanner sc = new Scanner(System.in);
	static Cliente_Dao clienteDAO = new Cliente_Dao();

	public static void main(String[] args) {

		imprimirCabecalho();
		opções_switch();

		

		
	}
	
	public static void opções_switch() {
		int x = sc.nextInt();

		switch (x) {

		case 1:

			InserirCliente();

			break;

		case 2:

			clienteDAO.listarClientes();

			break;

		case 3:
			
			System.out.println("Saindo do sistema...");
			System.exit(0);
			
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
		System.out.println("3. Sair");
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
