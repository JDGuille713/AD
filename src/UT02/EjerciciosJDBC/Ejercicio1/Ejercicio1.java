package UT02.EjerciciosJDBC.Ejercicio1;

import java.sql.*;

public class Ejercicio1 {


    public static void main(String[] args) {
        ConexionBD con = new ConexionBD();
        con.conectar();

        CrearTabla crearTabla = new CrearTabla();
        crearTabla.crearTabla();

    }




}