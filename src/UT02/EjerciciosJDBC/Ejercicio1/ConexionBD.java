package UT02.EjerciciosJDBC.Ejercicio1;

import java.sql.*;

public class ConexionBD {
    public static final String URL = "jdbc:mysql://localhost:3306/tienda1?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("No se ha podido establecer la conexion");
            return null;
        }
    }
}
