package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
