package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import db.DB;
import entities.Funcionarios;

public class Funcionario_Dao {

	public Funcionario_Dao() {

	}

	public static int inserirFuncionario(String nome, String cpf, String cargo, double salario, String email,
			String telefone, String dataContratacao) {
		String sql = "INSERT INTO Funcionarios (nome, cpf, cargo, salario, email, telefone, dataContratacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DB.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, nome);
			stmt.setString(2, cpf);
			stmt.setString(3, cargo);
			stmt.setDouble(4, salario);
			stmt.setString(5, email);
			stmt.setString(6, telefone);
			stmt.setString(7, dataContratacao);

			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						return generatedKeys.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void listarFuncionarios() {
		String sql = "SELECT * FROM Funcionarios";

		try (Connection conn = DB.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			System.out.printf("%-5s %-20s %-15s %-20s %-10s %-30s %-15s %-15s%n", "ID", "Nome", "CPF", "Cargo",
					"Salário", "Email", "Telefone", "Data de Contratação");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.printf("%-5d %-20s %-15s %-20s %-10.2f %-30s %-15s %-15s%n", rs.getInt("id"),
						rs.getString("nome"), rs.getString("cpf"), rs.getString("cargo"), rs.getDouble("salario"),
						rs.getString("email"), rs.getString("telefone"), rs.getString("dataContratacao"));
			}

			System.out.println("\n\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean deletarFuncionario(int id) {

		String sql = "DELETE FROM Funcionarios WHERE id = ?";

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean editarFuncionario(int id, String nome, String cpf, String cargo, String dataContratacao,
			String email, String telefone, double salario) {

		String sql = "UPDATE Funcionarios SET nome = ?, cpf = ?, cargo = ?, salario = ?, email = ?, telefone = ?, dataContratacao = ? WHERE id = ?";

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, nome);
			stmt.setString(2, cpf);
			stmt.setString(3, cargo);
			stmt.setDouble(4, salario);
			stmt.setString(5, email);
			stmt.setString(6, telefone);
			stmt.setString(7, dataContratacao);
			stmt.setInt(8, id);

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Funcionarios buscarFuncionarioPorId(int id) {
		String sql = "SELECT * FROM Funcionarios WHERE id = ?";
		Funcionarios funcionario = null;

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					funcionario = new Funcionarios(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"),
							rs.getString("cargo"), rs.getDouble("salario"), rs.getString("email"),
							rs.getString("telefone"), rs.getString("dataContratacao"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}

	public static List<Funcionarios> buscarFuncionariosPorNome(String nome) {
		String sql = "SELECT * FROM Funcionarios WHERE nome LIKE ?";
		List<Funcionarios> funcionarios = new ArrayList<>();

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + nome + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Funcionarios funcionario = new Funcionarios(rs.getInt("id"), rs.getString("nome"),
							rs.getString("cpf"), rs.getString("cargo"), rs.getDouble("salario"), rs.getString("email"),
							rs.getString("telefone"), rs.getString("dataContratacao"));
					funcionarios.add(funcionario);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

	public static boolean autenticar(String login, String senha) {
		String query = "SELECT * FROM login WHERE login = ? AND senha = ?";

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, login);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery();

			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean inserirLogin(int funcionarioId, String login, String senha) {
		String sql = "INSERT INTO Login (funcionario_id, login, senha) VALUES (?, ?, ?)";

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, funcionarioId);
			stmt.setString(2, login);
			stmt.setString(3, senha);

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
