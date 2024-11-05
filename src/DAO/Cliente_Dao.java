package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import entities.Clientes;

public class Cliente_Dao {

	public Cliente_Dao() {
	}
	
	public static boolean inserirCliente(String nome, String cpf, String email, String telefone, String endereco) {
		
		// Chama o comando que você quer dentro de um String
		String sql = "INSERT INTO Clientes (nome, cpf, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)";

		// Dentro do try você instancia a conexão e cria o PreparedStatement
		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			// Nesse ponto você define o que cada '?' vai ser
			stmt.setString(1, nome);
			stmt.setString(2, cpf);
			stmt.setString(3, email);
			stmt.setString(4, telefone);
			stmt.setString(5, endereco);

			// teste para retornar se a conexão foi bem sucedida 
			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLIntegrityConstraintViolationException e) {
	        System.out.println("Erro: O cliente já está registrado. Verifique se o CPF ou e-mail já existe no sistema.");
	        return false;

	    }catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void listarClientes() {
        String sql = "SELECT * FROM Clientes";

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.printf("%-5s %-20s %-15s %-30s %-15s %-30s%n", "ID", "Nome", "CPF", "Email", "Telefone", "Endereço");
            System.out.println("-------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-30s %-15s %-30s%n",
                                  rs.getInt("id"),
                                  rs.getString("nome"),
                                  rs.getString("cpf"),
                                  rs.getString("email"),
                                  rs.getString("telefone"),
                                  rs.getString("endereco"));
            }
            
			System.out.println();
			System.out.println();
			System.out.println();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static boolean deletarCliente(int id) {
		
		String sql = "DELETE FROM Clientes WHERE id = ?";

	    try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id);

	        int rowsDeleted = stmt.executeUpdate();
	        return rowsDeleted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		
	}
	
	public static boolean editarCliente(int id, String nome, String cpf, String email, String telefone, String endereco) {
		
		String sql = "UPDATE clientes SET nome = ?, cpf = ?, email = ?, telefone = ?, endereco = ? WHERE id = ?";
		
		try(Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, nome);
	        stmt.setString(2, cpf);
	        stmt.setString(3, email);
	        stmt.setString(4, telefone);
	        stmt.setString(5, endereco);
	        stmt.setInt(6, id);
			
			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static Clientes buscarClientePorId(int id) {
	    String sql = "SELECT * FROM Clientes WHERE id = ?";
	    Clientes cliente = null;

	    try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                cliente = new Clientes(
	                    rs.getInt("id"),
	                    rs.getString("nome"),
	                    rs.getString("cpf"),
	                    rs.getString("email"),
	                    rs.getString("telefone"),
	                    rs.getString("endereco")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cliente;
	}
	
	public static List<Clientes> buscarClientesPorNome(String nome) {
		String sql = "SELECT * FROM Clientes WHERE nome LIKE ?";
		List<Clientes> clientes = new ArrayList<>();

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + nome + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Clientes cliente = new Clientes(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"),
							rs.getString("email"), rs.getString("telefone"), rs.getString("endereco"));
					clientes.add(cliente);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public static List<Clientes> listarClientesEmOrdemAlfabetica() {
	        List<Clientes> clientes = new ArrayList<>();
	        String sql = "SELECT * FROM Clientes ORDER BY nome ASC";

	        try (Connection conn = DB.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Clientes cliente = new Clientes();
	                cliente.setId(rs.getInt("id"));
	                cliente.setNome(rs.getString("nome"));
	                cliente.setCpf(rs.getString("cpf"));
	                cliente.setEmail(rs.getString("email"));
	                cliente.setTelefone(rs.getString("telefone"));
	                cliente.setEndereco(rs.getString("endereco"));
	                clientes.add(cliente);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return clientes;
	    }
	}


	
	

	




