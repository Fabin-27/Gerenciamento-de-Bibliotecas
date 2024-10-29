package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static String url;
    private static Properties props;

    static {
        try {
            props = loadProperties();
            url = props.getProperty("dburl");
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, props);
    }

    private static Properties loadProperties() throws IOException {
        try (FileInputStream fs = new FileInputStream("db.Properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
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


