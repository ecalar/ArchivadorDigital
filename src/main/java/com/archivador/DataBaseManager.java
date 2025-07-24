package com.archivador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {
    private static final String DB_URL = "jdbc:h2:file:./data/archivadordb;DB_CLOSE_ON_EXIT=FALSE";
    private static final String USER = "admin";
    private static final String PASS = "admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Crear tabla de usuarios si no existe
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) UNIQUE NOT NULL," +
                    "password VARCHAR(100) NOT NULL," +
                    "rol VARCHAR(20) NOT NULL CHECK (rol IN ('ADMIN', 'SECRETARIO', 'CONSULTOR'))" +
                    ")");

            // Verificar si el usuario admin existe
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM usuarios WHERE username = 'admin'");
            rs.next();
            if (rs.getInt(1) == 0) {
                // Crear usuario admin con contraseña encriptada
                String hashedPwd = BCrypt.hashpw("admin123", BCrypt.gensalt());
                stmt.executeUpdate("INSERT INTO usuarios (username, password, rol) VALUES " +
                        "('admin', '" + hashedPwd + "', 'ADMIN')");
                System.out.println("Usuario admin creado con éxito");
            }

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos:");
            e.printStackTrace();
        }
    }

}