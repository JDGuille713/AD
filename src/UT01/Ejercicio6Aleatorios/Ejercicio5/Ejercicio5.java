
package UT01.Ejercicio6Aleatorios.Ejercicio5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5 {

    private static final String RUTA_FICHERO = "src/UT01/Ejercicio6Aleatorios/Ejercicio5/";
    private static final String NOMBRE_FICHERO = "practicas.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            fichero.setLength(0);

            System.out.print("¿Cuántos alumnos quieres introducir? ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < cantidad; i++) {

                System.out.print("Introduce el nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Introduce los apellidos: ");
                String apellidos = sc.nextLine();

                System.out.print("Introduce el DNI (8 dígitos y una letra): ");
                String dni = sc.nextLine();

                System.out.print("Introduce el vehículo de prácticas: ");
                int vehiculo = sc.nextInt();

                System.out.print("Introduce el número de clases prácticas: ");
                int clases = sc.nextInt();
                sc.nextLine();

                StringBuffer nombreAlumno = new StringBuffer(nombre);
                nombreAlumno.setLength(10);
                fichero.writeChars(nombreAlumno.toString());

                StringBuffer apellidosAlumno = new StringBuffer(apellidos);
                apellidosAlumno.setLength(35);
                fichero.writeChars(apellidosAlumno.toString());

                StringBuffer dniAlumno = new StringBuffer(dni);
                dniAlumno.setLength(9);
                fichero.writeChars(dniAlumno.toString());

                fichero.writeInt(vehiculo);

                fichero.writeInt(clases);
            }

            System.out.println("\nContenido del fichero:");

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {

                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = fichero.readChar();
                }

                char[] apellidos = new char[35];
                for (int i = 0; i < apellidos.length; i++) {
                    apellidos[i] = fichero.readChar();
                }

                char[] dni = new char[9];
                for (int i = 0; i < dni.length; i++) {
                    dni[i] = fichero.readChar();
                }

                int vehiculo = fichero.readInt();

                int clases = fichero.readInt();

                System.out.println("Nombre: " + new String(nombre).trim()
                        + ", Apellidos: " + new String(apellidos).trim()
                        + ", DNI: " + new String(dni).trim()
                        + ", Vehículo: " + vehiculo
                        + ", Clases prácticas: " + clases);
            }

        } catch (IOException e) {
            System.out.println("Error al trabajar con el fichero.");
        }

        sc.close();
    }
}
