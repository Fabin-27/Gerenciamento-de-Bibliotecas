package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import entities.Livros;

public class Livro_Dao {

	public Livro_Dao() {
	}

	public static boolean inserirLivro(String titulo, String autor, String isbn, String anoPublicacao) {

		String sql = "INSERT INTO Livros (titulo, autor, isbn, anoPublicacao) VALUES (?, ?, ?, ?)";

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, titulo);
			stmt.setString(2, autor);
			stmt.setString(3, isbn);
			stmt.setString(4, anoPublicacao);

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void listarLivros() {
	    String sql = "SELECT * FROM Livros";

	    try (Connection conn = DB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        System.out.printf("%-5s %-30s %-30s %-30s %-15s %-15s%n", "ID", "Título", "Autor", "ISBN", "Ano", "Status");
	        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

	        while (rs.next()) {
	            System.out.printf("%-5d %-30s %-30s %-30s %-15s %-15s%n", 
	                rs.getInt("id"), 
	                rs.getString("titulo"),
	                rs.getString("autor"), 
	                rs.getString("ISBN"), 
	                rs.getString("anoPublicacao"),
	                rs.getString("status"));
	            
	            System.out.println();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public static boolean deletarLivro(int id) {
	    String sql = "DELETE FROM Livros WHERE id = ?";

	    try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id);

	        int rowsDeleted = stmt.executeUpdate();
	        return rowsDeleted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static boolean editarLivro(int id, String titulo, String autor, String isbn, String anoPublicacao) {
	    String sql = "UPDATE Livros SET titulo = ?, autor = ?, isbn = ?, anoPublicacao = ? WHERE id = ?";

	    try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, titulo);
	        stmt.setString(2, autor);
	        stmt.setString(3, isbn);
	        stmt.setString(4, anoPublicacao);
	        stmt.setInt(5, id);

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Livros buscarLivroPorId(int id) {
	    String sql = "SELECT * FROM Livros WHERE id = ?";
	    Livros livro = null;

	    try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                livro = new Livros(
	                    rs.getInt("id"),
	                    rs.getString("titulo"),
	                    rs.getString("autor"),
	                    rs.getString("isbn"),
	                    rs.getString("anoPublicacao"),
	                    rs.getString("status")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return livro;
	}
	
	public static List<Livros> buscarLivrosPorTitulo(String titulo) {
		String sql = "SELECT * FROM Livros WHERE titulo LIKE ?";
		List<Livros> livros = new ArrayList<>();

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + titulo + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Livros livro = new Livros(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
							rs.getString("isbn"), rs.getString("anoPublicacao"), rs.getString("status"));
					livros.add(livro);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
	
	public static List<Livros> listarLivrosEmOrdemAlfabetica() {
        List<Livros> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livros ORDER BY titulo ASC";

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livros livro = new Livros();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setStatus(rs.getString("status"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }




}