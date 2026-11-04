package UT02;

import java.sql.*;

public class Metadatos {
    public static final String URL="jdbc:mysql://localhost:3306/";
    public static final String USER="root";
    public static final String PASSWORD="root";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexion ok");
        } catch (SQLException e) {
            System.out.println("Error");
        }

    }
}

