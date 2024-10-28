package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

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

		} catch (SQLException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	

	

}


