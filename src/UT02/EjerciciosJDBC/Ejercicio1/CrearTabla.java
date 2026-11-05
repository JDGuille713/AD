package UT02.EjerciciosJDBC.Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTabla {
    public static final String URL = "jdbc:mysql://localhost:3306/tienda1?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public void crearTabla() {
        String borrar = "DROP TABLE IF EXISTS ArtFab";

        String crearTabla = """
                CREATE TABLE ArtFab (
                    nombre_articulo VARCHAR(30),
                    nombre_fabricante VARCHAR(30),
                    precio INT,
                    iva DECIMAL(10,2)
                )
                """;

        String insertarTabla = """
                INSERT INTO ArtFab (nombre_articulo, nombre_fabricante, precio, iva)
                SELECT
                    a.Nombre,
                    f.Nombre,
                    a.Precio,
                    CASE
                        WHEN a.Precio < 200 THEN a.Precio * 0.10
                        WHEN a.Precio < 500 THEN a.Precio * 0.08
                        WHEN a.Precio < 700 THEN a.Precio * 0.06
                        ELSE 0
                    END
                FROM Articulos a
                JOIN Fabricantes f ON a.CLFAB = f.CLFAB
                """;

        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {

            st.executeUpdate(borrar);

            st.executeUpdate(crearTabla);

            st.executeUpdate(insertarTabla);

            st.executeUpdate("SELECT * FROM ArtFab");
            System.out.println("Conexion correcta.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
