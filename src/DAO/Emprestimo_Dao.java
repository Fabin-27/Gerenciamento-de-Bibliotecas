package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

public class Emprestimo_Dao {
	
	public Emprestimo_Dao() {
		
	}
	
	public static boolean registrarEmprestimo(int livroId, int clienteId, String dataEmprestimo) {
		
        String sql = "INSERT INTO Emprestimos (LivroId, ClienteId, DataEmprestimo, Status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, livroId);
            stmt.setInt(2, clienteId);
            stmt.setString(3, dataEmprestimo);
            stmt.setString(4, "Emprestado");

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public void listarEmprestimos() {
	    String sql = "SELECT e.id, e.LivroId, e.ClienteId, e.dataEmprestimo, e.dataDevolucao, e.status, " +
	                 "l.titulo AS livroTitulo, c.nome AS clienteNome " +
	                 "FROM Emprestimos e " +
	                 "JOIN Livros l ON e.LivroId = l.id " +
	                 "JOIN Clientes c ON e.ClienteId = c.id";

	    try (Connection conn = DB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        System.out.printf("%-5s %-30s %-30s %-20s %-20s %-20s%n", "ID", "Livro", "Cliente", "Data Empréstimo", "Data Devolução", "Status");
	        System.out.println("---------------------------------------------------------------------------------------------------------");

	        while (rs.next()) {
	            System.out.printf("%-5d %-30s %-30s %-20s %-20s %-20s%n",
	                    rs.getInt("id"),
	                    rs.getString("livroTitulo"),
	                    rs.getString("clienteNome"),
	                    rs.getString("dataEmprestimo"),
	                    rs.getString("dataDevolucao") != null ? rs.getString("dataDevolucao") : "Não devolvido",
	                    rs.getString("status"));
	            System.out.println();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean devolverLivro(int emprestimoId, String dataDevolucao) {
	    String sql = "UPDATE Emprestimos SET dataDevolucao = ?, status = 'Disponível' WHERE id = ?";

	    try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, dataDevolucao);
	        stmt.setInt(2, emprestimoId);

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean verificarEmprestimosAtivos(int clienteId) {
	    String sql = "SELECT COUNT(*) FROM Emprestimos WHERE ClienteId = ? AND dataDevolucao IS NULL";

	    try (Connection conn = DB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, clienteId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0; // Retorna true se houver empréstimos ativos
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false; // Retorna false se não houver empréstimos ativos
	}
	
	public static boolean verificarDisponibilidadeLivro(int livroId) {
	    String sql = "SELECT status FROM Livros WHERE id = ?";

	    try (Connection conn = DB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, livroId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            String status = rs.getString("status");
	            return "disponivel".equalsIgnoreCase(status); // Retorna true se o status for 'disponivel'
	        } else {
	            System.out.println("Livro não encontrado.");
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static void atualizarStatusLivro(int livroId, String status) {
	    String sql = "UPDATE Livros SET status = ? WHERE id = ?";

	    try (Connection conn = DB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, status);
	        stmt.setInt(2, livroId);
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}




}