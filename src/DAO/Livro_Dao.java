package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

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

			System.out.printf("%-5s %-30s %-30s %-30s %-15s%n", "ID", "Titulo", "Autor", "ISBN", "Ano");
			System.out.println("---------------------------------------------------------------------------------------------------------");

			while (rs.next()) {

				System.out.printf("%-5d %-30s %-30s %-30s %-15s%n", rs.getInt("id"), rs.getString("titulo"),
						rs.getString("autor"), rs.getString("ISBN"), rs.getString("anoPublicacao"));
				
				System.out.println();
				System.out.println();
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


}
