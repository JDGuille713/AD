package UT02.EjerciciosJDBC.Ejercicio1;

import java.sql.*;

public class Ejercicio1 {
    public static final String URL = "jdbc:mysql://localhost:3306/tienda1?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) {
        conectar();
    }

    public static void conectar() {
        String sql = "SELECT CLART, Nombre, Precio, CLFAB FROM Articulos";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("Conexion correcta.");
            while (rs.next()) {
                System.out.println(rs.getInt("CLART") + " | " + rs.getString("Nombre")
                        + " | " + rs.getInt("Precio") + " | " + rs.getInt("CLFAB"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}