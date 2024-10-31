package application;

import java.util.List;
import java.util.Scanner;

import DAO.Cliente_Dao;
import DAO.Funcionario_Dao;
import DAO.Livro_Dao;
//import db.DB;
import entities.Clientes;
import entities.Funcionarios;
import entities.Livros;

public class Program {

	static Scanner sc = new Scanner(System.in);
	static Cliente_Dao clienteDAO = new Cliente_Dao();
	static Livro_Dao livroDAO = new Livro_Dao();

	public static void main(String[] args) {

		// DB.TestarConexão();
		login();
		Cabecalho_Principal();
		switch_inicial();

	}

	public static void Cabecalho_Principal() {
		System.out.println("=============================================");
		System.out.println("           Sistema de Gerenciamento           ");
		System.out.println("                de Biblioteca                     ");
		System.out.println("=============================================");
		System.out.println();
		switch_inicial();
	}

	public static void switch_inicial() {
		while (true) {
			System.out.println("Digite uma opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Menu de Clientes");
			System.out.println("2 - Menu de Livros");
			System.out.println("3 - Menu de Funcionários");

			int x = sc.nextInt();

			switch (x) {
			case 0:
				System.out.println("Até a próxima :)");
				System.exit(0);
				break;

			case 1:
				cabecalho_clientes();
				opções_switch_cliente();
				return;

			case 2:
				cabecalho_Livros();
				opções_switch_livro();
				return;

			case 3:
				cabecalho_funcionarios();
				opções_switch_funcionario();

				return;

			default:
				System.out.println("Opção inválida. Por favor, digite 0, 1 ou 2.");
			}
		}
	}

	
	 public static void login() {
	        
	        System.out.print("Digite o login: ");
	        String login = sc.nextLine();

	        System.out.print("Digite a senha: ");
	        String senha = sc.nextLine();

	        if (Funcionario_Dao.autenticar(login, senha)) {
	            System.out.println("Login bem-sucedido!");
	        } else {
	            System.out.println("Login ou senha incorretos. Tente novamente.");
	            login();
	        }
	    }


	// ÁREA DOS FUNCIONÁRIOS
	public static void cabecalho_funcionarios() {

		System.out.println("=============================================");
		System.out.println("           Área de Gerenciamento           ");
		System.out.println("              de Funcionários              ");
		System.out.println("=============================================");
		System.out.println();

	}

	public static void opções_switch_funcionario() {
		while (true) {
			System.out.println("Digite uma opção:");
			System.out.println("0 - Sair do sistema");
			System.out.println("1 - Inserir Funcionário");
			System.out.println("2 - Listar Funcionários");
			System.out.println("3 - Deletar Funcionário por ID");
			System.out.println("4 - Editar Funcionário por ID");
			System.out.println("5 - Buscar Funcionário");
			System.out.println("6 - Voltar ao Menu Principal");

			int x = sc.nextInt();

			switch (x) {
			case 0:
				System.out.println("Saindo do sistema...");
				System.exit(0);
				break;

			case 1:
				InserirFuncionario();
				return;

			case 2:
				Funcionario_Dao.listarFuncionarios();
				System.out.println("Digite '9' para voltar para a área de Gerenciamento de Funcionários");

				int z = sc.nextInt();
				while (z != 9) {
					System.out.println(
							"Comando inválido. Digite '9' para voltar para a área de Gerenciamento de Funcionários");
					z = sc.nextInt();
				}
				cabecalho_funcionarios();
				continue;

			case 3:
				deletarFuncionarioPorId();
				return;

			case 4:
				editarFuncionarioPorId();
				return;

			case 5:
				System.out.println("1 - Buscar Funcionário por Id");
				System.out.println("2 - Buscar Funcionário por Nome");

				int opcao_buscar = sc.nextInt();
				switch (opcao_buscar) {
				case 1:
					buscarFuncionarioPorId();
					break;
				case 2:
					buscarFuncionarioPorNome();
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					System.out.println();
					continue;
				}
				return;

			case 6:
				Cabecalho_Principal();
				switch_inicial();
				return;

			default:
				System.out.println("Opção inválida. Tente novamente.");
				System.out.println();
			}
		}
	}

	public static void InserirFuncionario() {
        sc.nextLine();
        System.out.print("Digite o nome do funcionário (ou 'cancelar' para sair): ");
        String nome = sc.nextLine();
        if (nome.equalsIgnoreCase("cancelar")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Digite o CPF do funcionário (ou 'cancelar' para sair): ");
        String cpf = sc.nextLine();
        if (cpf.equalsIgnoreCase("cancelar")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Digite o cargo do funcionário (ou 'cancelar' para sair): ");
        String cargo = sc.nextLine();
        if (cargo.equalsIgnoreCase("cancelar")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Digite o salário do funcionário (ou 'cancelar' para sair): ");
        String salarioStr = sc.nextLine();
        if (salarioStr.equalsIgnoreCase("cancelar")) {
            System.out.println("Operação cancelada.");
            return;
        }
        double salario = Double.parseDouble(salarioStr);

        System.out.print("Digite o email do funcionário (ou 'cancelar' para sair): ");
        String email = sc.nextLine();
        if (email.equalsIgnoreCase("cancelar")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Digite o telefone do funcionário (ou 'cancelar' para sair): ");
        String telefone = sc.nextLine();
        if (telefone.equalsIgnoreCase("cancelar")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Digite a data de contratação do funcionário (ou 'cancelar' para sair): ");
        String dataContratacao = sc.nextLine();
        if (dataContratacao.equalsIgnoreCase("cancelar")) {
            System.out.println("Operação cancelada.");
            return;
        }

        int funcionarioId = Funcionario_Dao.inserirFuncionario(nome, cpf, cargo, salario, email, telefone, dataContratacao);
        if (funcionarioId != -1) {
            System.out.print("Digite o login(e-mail) (ou 'cancelar' para sair): ");
            String login = sc.nextLine();
            if (login.equalsIgnoreCase("cancelar")) {
                System.out.println("Operação cancelada.");
                return;
            }

            System.out.print("Digite a senha (ou 'cancelar' para sair): ");
            String senha = sc.nextLine();
            if (senha.equalsIgnoreCase("cancelar")) {
                System.out.println("Operação cancelada.");
                return;
            }

            boolean loginInserido =Funcionario_Dao.inserirLogin(funcionarioId, login, senha);
            if (loginInserido) {
                System.out.println("Funcionário e login inseridos com sucesso!");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova inserção? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {
				InserirFuncionario();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {
					cabecalho_funcionarios();
					opções_switch_funcionario();
				}
			}

		} else {
			System.out.println("Falha ao inserir o funcionário.");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {
				InserirFuncionario();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {
					cabecalho_funcionarios();
					opções_switch_funcionario();
				}
			}
		}}
	}

	public static void deletarFuncionarioPorId() {
		sc.nextLine();
		System.out.print("Digite o ID do funcionário que deseja deletar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			cabecalho_funcionarios();
			opções_switch_funcionario();
		}

		try {
			int id = Integer.parseInt(valor_id);
			boolean deletado = Funcionario_Dao.deletarFuncionario(id);

			if (deletado) {
				System.out.println("Funcionário deletado com sucesso!");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja deletar mais algum funcionário? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {
					deletarFuncionarioPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {
						cabecalho_funcionarios();
						opções_switch_funcionario();
					}
				}

			} else {
				System.out.println("Funcionário com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {
					deletarFuncionarioPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {
						cabecalho_funcionarios();
						opções_switch_funcionario();
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
			System.out.println();
			deletarFuncionarioPorId();
		}
	}

	public static void editarFuncionarioPorId() {
		System.out.print("Digite o ID do funcionário que deseja editar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			cabecalho_funcionarios();
			opções_switch_funcionario();
		}

		try {
			int id = Integer.parseInt(valor_id);

			System.out.print("Digite o novo nome (ou 'cancelar' para sair): ");
			String nome = sc.nextLine();
			if (nome.equalsIgnoreCase("cancelar")) {
				cabecalho_funcionarios();
				opções_switch_funcionario();
			}

			System.out.print("Digite o novo CPF (ou 'cancelar' para sair): ");
			String cpf = sc.nextLine();
			if (cpf.equalsIgnoreCase("cancelar")) {
				cabecalho_funcionarios();
				opções_switch_funcionario();
			}

			System.out.print("Digite o novo email (ou 'cancelar' para sair): ");
			String email = sc.nextLine();
			if (email.equalsIgnoreCase("cancelar")) {
				cabecalho_funcionarios();
				opções_switch_funcionario();
			}

			System.out.print("Digite o novo telefone (ou 'cancelar' para sair): ");
			String telefone = sc.nextLine();
			if (telefone.equalsIgnoreCase("cancelar")) {
				cabecalho_funcionarios();
				opções_switch_funcionario();
			}

			System.out.print("Digite o novo endereço (ou 'cancelar' para sair): ");
			String endereco = sc.nextLine();
			if (endereco.equalsIgnoreCase("cancelar")) {
				cabecalho_funcionarios();
				opções_switch_funcionario();
			}

			System.out.print("Digite o novo cargo (ou 'cancelar' para sair): ");
			String cargo = sc.nextLine();
			if (cargo.equalsIgnoreCase("cancelar")) {
				cabecalho_funcionarios();
				opções_switch_funcionario();
			}

			System.out.print("Digite o novo salário (ou 'cancelar' para sair): ");
			String salarioStr = sc.nextLine();
			if (salarioStr.equalsIgnoreCase("cancelar")) {
				cabecalho_funcionarios();
				opções_switch_funcionario();
			}

			double salario = Double.parseDouble(salarioStr);

			boolean editado = Funcionario_Dao.editarFuncionario(id, nome, cpf, email, telefone, endereco, cargo,
					salario);

			if (editado) {
				System.out.println("Funcionário editado com sucesso!");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja alterar mais algum funcionário? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {
					editarFuncionarioPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {
						cabecalho_funcionarios();
						opções_switch_funcionario();
					}
				}
			} else {
				System.out.println("Funcionário com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {
					editarFuncionarioPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {
						cabecalho_funcionarios();
						opções_switch_funcionario();
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
			System.out.println();
			editarFuncionarioPorId();
		}
	}

	public static void buscarFuncionarioPorId() {
		sc.nextLine();
		System.out.print("Digite o ID do funcionário que deseja buscar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			cabecalho_funcionarios();
			opções_switch_funcionario();
		}

		try {
			int id = Integer.parseInt(valor_id);
			Funcionarios funcionario = Funcionario_Dao.buscarFuncionarioPorId(id);

			if (funcionario != null) {
				System.out.println("Funcionário encontrado: " + funcionario);

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja buscar mais algum funcionário? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {
					buscarFuncionarioPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {
						cabecalho_funcionarios();
						opções_switch_funcionario();
					}
				}

			} else {
				System.out.println("Funcionário com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja buscar mais algum funcionário? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {
					buscarFuncionarioPorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {
						cabecalho_funcionarios();
						opções_switch_funcionario();
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
			System.out.println();
			buscarFuncionarioPorId();
		}
	}

	public static void buscarFuncionarioPorNome() {
		sc.nextLine();
		System.out.print("Digite o nome do funcionário que deseja buscar (ou 'cancelar' para sair): ");
		String nome = sc.nextLine();

		if (nome.equalsIgnoreCase("cancelar")) {
			cabecalho_funcionarios();
			opções_switch_funcionario();
		}

		List<Funcionarios> funcionariosEncontrados = Funcionario_Dao.buscarFuncionariosPorNome(nome);

		if (!funcionariosEncontrados.isEmpty()) {
			System.out.println("Funcionários encontrados:");
			for (Funcionarios funcionario : funcionariosEncontrados) {
				System.out.println(funcionario);
			}

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja buscar mais algum funcionário? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {
				buscarFuncionarioPorNome();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {
					cabecalho_funcionarios();
					opções_switch_funcionario();
				}
			}
		} else {
			System.out.println("Nenhum funcionário encontrado com o nome especificado.");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {
				buscarFuncionarioPorNome();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {
					cabecalho_funcionarios();
					opções_switch_funcionario();
				}
			}
		}
	}

	// ÁREA DOS CLIENTES
	public static void cabecalho_clientes() {

		System.out.println("=============================================");
		System.out.println("           Área de Gerenciamento           ");
		System.out.println("                de Clientes                     ");
		System.out.println("=============================================");
		System.out.println();

	}

	public static void opções_switch_cliente() {
		while (true) {

			System.out.println("Digite uma opção:");
			System.out.println("0 - Sair do sistema");
			System.out.println("1 - Inserir Cliente");
			System.out.println("2 - Listar Clientes");
			System.out.println("3 - Deletar Cliente por ID");
			System.out.println("4 - Editar Cliente por ID");
			System.out.println("5 - Buscar Cliente");
			System.out.println("6 - Voltar ao Menu Principal");

			int x = sc.nextInt();

			switch (x) {
			case 0:
				System.out.println("Saindo do sistema...");
				System.exit(0);
				break;

			case 1:
				InserirCliente();
				return;

			case 2:
				clienteDAO.listarClientes();
				System.out.println("Digite '9' para voltar para a área de Gerenciamento de clientes");

				int z = sc.nextInt();
				while (z != 9) {
					System.out.println(
							"Comando inválido. Digite '9' para voltar para a área de Gerenciamento de clientes");
					z = sc.nextInt();
				}
				cabecalho_clientes();
				continue;

			case 3:
				deletarClientePorId();
				return;

			case 4:
				editarClientePorId();
				return;

			case 5:
				System.out.println("1 - Buscar Cliente por Id");
				System.out.println("2 - Buscar Cliente por Nome");

				int opcao_buscar = sc.nextInt();
				switch (opcao_buscar) {
				case 1:
					buscarClientePorId();
					break;
				case 2:
					buscarClientePorNome();
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					System.out.println();
					continue;
				}
				return;

			case 6:
				Cabecalho_Principal();
				switch_inicial();
				return;

			default:
				System.out.println("Opção inválida. Tente novamente.");
				System.out.println();
			}
		}
	}

	public static void InserirCliente() {

		sc.nextLine();
		System.out.print("Digite o nome do cliente (ou 'cancelar' para sair): ");

		String nome = sc.nextLine();
		if (nome.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			cabecalho_clientes();
			opções_switch_cliente();
		}

		System.out.print("Digite o CPF do cliente (ou 'cancelar' para sair): ");
		String cpf = sc.nextLine();
		if (cpf.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			cabecalho_clientes();
			opções_switch_cliente();
		}

		System.out.print("Digite o email do cliente (ou 'cancelar' para sair): ");
		String email = sc.nextLine();
		if (email.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			cabecalho_clientes();
			opções_switch_cliente();
		}

		System.out.print("Digite o telefone do cliente (ou 'cancelar' para sair): ");
		String telefone = sc.nextLine();
		if (telefone.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			cabecalho_clientes();
			opções_switch_cliente();
		}

		System.out.print("Digite o endereço do cliente (ou 'cancelar' para sair): ");
		String endereco = sc.nextLine();
		if (endereco.equalsIgnoreCase("cancelar")) {
			System.out.println("Operação cancelada.");
			cabecalho_clientes();
			opções_switch_cliente();
		}

		boolean inserido = Cliente_Dao.inserirCliente(nome, cpf, email, telefone, endereco);
		if (inserido) {
			System.out.println("Cliente inserido com sucesso!");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova inserção? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				InserirCliente();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					cabecalho_clientes();
					opções_switch_cliente();

				}
			}

		} else {
			System.out.println("Falha ao inserir o cliente.");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				InserirCliente();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					cabecalho_clientes();
					opções_switch_cliente();

				}
			}

		}
	}

	public static void deletarClientePorId() {

		sc.nextLine();
		System.out.print("Digite o ID do cliente que deseja deletar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			cabecalho_clientes();
			opções_switch_cliente();
		}

		try {
			int id = Integer.parseInt(valor_id);
			boolean deletado = Cliente_Dao.deletarCliente(id);

			if (deletado) {
				System.out.println("Cliente deletado com sucesso!");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja deletar mais algum cliente? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					deletarClientePorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_clientes();
						opções_switch_cliente();

					}
				}

			} else {
				System.out.println("Cliente com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					deletarClientePorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_clientes();
						opções_switch_cliente();

					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
			System.out.println();
			deletarClientePorId();
		}
	}

	public static void editarClientePorId() {
		System.out.print("Digite o ID do cliente que deseja editar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			cabecalho_clientes();
			opções_switch_cliente();
		}

		try {
			int id = Integer.parseInt(valor_id);

			System.out.print("Digite o novo nome (ou 'cancelar' para sair): ");
			String nome = sc.nextLine();
			if (nome.equalsIgnoreCase("cancelar")) {
				cabecalho_clientes();
				opções_switch_cliente();
			}

			System.out.print("Digite o novo CPF (ou 'cancelar' para sair): ");
			String cpf = sc.nextLine();
			if (cpf.equalsIgnoreCase("cancelar")) {
				cabecalho_clientes();
				opções_switch_cliente();
			}

			System.out.print("Digite o novo email (ou 'cancelar' para sair): ");
			String email = sc.nextLine();
			if (email.equalsIgnoreCase("cancelar")) {
				cabecalho_clientes();
				opções_switch_cliente();
			}

			System.out.print("Digite o novo telefone (ou 'cancelar' para sair): ");
			String telefone = sc.nextLine();
			if (telefone.equalsIgnoreCase("cancelar")) {
				cabecalho_clientes();
				opções_switch_cliente();
			}

			System.out.print("Digite o novo endereço (ou 'cancelar' para sair): ");
			String endereco = sc.nextLine();
			if (endereco.equalsIgnoreCase("cancelar")) {
				cabecalho_clientes();
				opções_switch_cliente();
			}

			boolean editado = Cliente_Dao.editarCliente(id, nome, cpf, email, telefone, endereco);

			if (editado) {
				System.out.println("Cliente editado com sucesso!");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja alterar mais algum cliente? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					editarClientePorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_clientes();
						opções_switch_cliente();

					}
				}
			} else {
				System.out.println("Cliente com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					editarClientePorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_clientes();
						opções_switch_cliente();

					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
			System.out.println();
			editarClientePorId();
		}
	}

	public static void buscarClientePorId() {
		System.out.print("Digite o ID do cliente que deseja buscar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			cabecalho_clientes();
			opções_switch_cliente();
		}

		try {
			int id = Integer.parseInt(valor_id);
			Clientes cliente = Cliente_Dao.buscarClientePorId(id);

			if (cliente != null) {
				System.out.println("Cliente encontrado: " + cliente);

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja buscar mais algum cliente? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					buscarClientePorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_clientes();
						opções_switch_cliente();

					}
				}

			} else {
				System.out.println("Cliente com o ID especificado não encontrado.");

				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Deseja buscar mais algum cliente? (Digite 'sim' ou 'nao')");

				String resposta_busca = sc.nextLine();

				while (resposta_busca.equalsIgnoreCase("sim")) {

					buscarClientePorId();

					System.out.println();
					System.out.println();
					System.out.println();

					if (resposta_busca.equalsIgnoreCase("nao")) {

						cabecalho_clientes();
						opções_switch_cliente();

					}
				}

			}
		} catch (NumberFormatException e) {
			System.out.println("ID inválido. Por favor, insira um número.");
			System.out.println();
			buscarClientePorId();

		}
	}

	public static void buscarClientePorNome() {

		sc.nextLine();
		System.out.print("Digite o nome do cliente que deseja buscar (ou 'cancelar' para sair): ");
		String nome = sc.nextLine();

		if (nome.equalsIgnoreCase("cancelar")) {
			cabecalho_Livros();
			opções_switch_livro();
		}

		List<Clientes> clientesEncontrados = Cliente_Dao.buscarClientesPorNome(nome);

		if (!clientesEncontrados.isEmpty()) {
			System.out.println("Clientes encontrados:");
			for (Clientes cliente : clientesEncontrados) {
				System.out.println(cliente);
			}

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja buscar mais algum cliente? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				buscarClientePorNome();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					cabecalho_clientes();
					opções_switch_cliente();

				}
			}
		} else {
			System.out.println("Nenhum cliente encontrado com o nome especificado.");

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Deseja fazer uma nova tentativa? (Digite 'sim' ou 'nao')");

			String resposta_busca = sc.nextLine();

			while (resposta_busca.equalsIgnoreCase("sim")) {

				buscarClientePorNome();

				System.out.println();
				System.out.println();
				System.out.println();

				if (resposta_busca.equalsIgnoreCase("nao")) {

					cabecalho_clientes();
					opções_switch_cliente();

				}
			}
		}
	}

	// ÁREA DOS LIVROS
	public static void cabecalho_Livros() {

		System.out.println("=============================================");
		System.out.println("           Área de Gerenciamento           ");
		System.out.println("                de Livros                     ");
		System.out.println("=============================================");
		System.out.println();

	}

	public static void opções_switch_livro() {
		while (true) {

			System.out.println("Digite uma opção:");
			System.out.println("0 - Sair do sistema");
			System.out.println("1 - Inserir Livro");
			System.out.println("2 - Listar Livros");
			System.out.println("3 - Deletar Livro por ID");
			System.out.println("4 - Editar Livro por ID");
			System.out.println("5 - Buscar Livro");
			System.out.println("6 - Voltar ao Menu Principal");

			int x = sc.nextInt();

			switch (x) {
			case 0:
				System.out.println("Até a próxima :)");
				System.exit(0);
				break;

			case 1:
				InserirLivro();
				return;

			case 2:
				livroDAO.listarLivros();
				System.out.println("Digite '9' para voltar para a área de Gerenciamento de livros");

				int z = sc.nextInt();
				while (z != 9) {
					System.out
							.println("Comando inválido. Digite '9' para voltar para a área de Gerenciamento de livros");
					z = sc.nextInt();
				}
				cabecalho_Livros();
				continue;

			case 3:
				deletarLivroPorId();
				return;

			case 4:
				editarLivroPorId();
				return;

			case 5:
				System.out.println("1 - Buscar Livro por Id");
				System.out.println("2 - Buscar Livro por Título");

				int opcao_buscar = sc.nextInt();
				switch (opcao_buscar) {
				case 1:
					buscarLivroPorId();
					break;
				case 2:
					buscarLivroPorTitulo();
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					System.out.println();
					continue;
				}
				return;

			case 6:
				Cabecalho_Principal();
				switch_inicial();
				return;

			default:
				System.out.println("Opção inválida. Tente novamente.");
				System.out.println();
			}
		}
	}

	public static void InserirLivro() {

		sc.nextLine();
		System.out.print("Digite o título (ou 'cancelar' para sair): ");

		String titulo = sc.nextLine();
		if (titulo.equalsIgnoreCase("cancelar")) {
			cabecalho_Livros();
			opções_switch_livro();

		}

		System.out.print("Digite o autor (ou 'cancelar' para sair): ");
		String autor = sc.nextLine();
		if (autor.equalsIgnoreCase("cancelar")) {
			cabecalho_Livros();
			opções_switch_livro();

		}

		System.out.print("Digite o ISBN (ou 'cancelar' para sair): ");
		String isbn = sc.nextLine();
		if (isbn.equalsIgnoreCase("cancelar")) {
			cabecalho_Livros();
			opções_switch_livro();

		}

		System.out.print("Digite o ano de publicação (ou 'cancelar' para sair): ");
		String anoPublicacao = sc.nextLine();
		if (anoPublicacao.equalsIgnoreCase("cancelar")) {
			cabecalho_Livros();
			opções_switch_livro();

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

		sc.nextLine();
		System.out.print("Digite o ID do livro que deseja deletar (ou 'cancelar' para sair): ");
		String valor_id = sc.nextLine();

		if (valor_id.equalsIgnoreCase("cancelar")) {
			cabecalho_Livros();
			opções_switch_livro();
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
			cabecalho_Livros();
			opções_switch_livro();
		}

		try {
			int id = Integer.parseInt(valor_id);

			System.out.print("Digite o novo título (ou 'cancelar' para sair): ");
			String titulo = sc.nextLine();
			if (titulo.equalsIgnoreCase("cancelar")) {
				cabecalho_Livros();
				opções_switch_livro();
			}

			System.out.print("Digite o novo autor (ou 'cancelar' para sair): ");
			String autor = sc.nextLine();
			if (autor.equalsIgnoreCase("cancelar")) {
				cabecalho_Livros();
				opções_switch_livro();
			}

			System.out.print("Digite o novo ISBN (ou 'cancelar' para sair): ");
			String isbn = sc.nextLine();
			if (isbn.equalsIgnoreCase("cancelar")) {
				cabecalho_Livros();
				opções_switch_livro();
			}

			System.out.print("Digite o novo ano de publicação (ou 'cancelar' para sair): ");
			String anoPublicacao = sc.nextLine();
			if (anoPublicacao.equalsIgnoreCase("cancelar")) {
				cabecalho_Livros();
				opções_switch_livro();
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
			cabecalho_Livros();
			opções_switch_livro();
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

		sc.nextLine();
		System.out.print("Digite o título do livro que deseja buscar: ");

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
