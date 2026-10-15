
package UT01.Ejercicio6Aleatorios.Ejercicio6;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio6 {

    private static final String RUTA_FICHERO = "src/UT01/Ejercicio6Aleatorios/Ejercicio5/";
    private static final String NOMBRE_FICHERO = "practicas.dat";
    private static final String NOMBRE_FICHERO_PRECIO = "precio.dat";

    public static void main(String[] args) {

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r");
             RandomAccessFile ficheroPrecio = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO_PRECIO, "rw")) {

            ficheroPrecio.setLength(0);

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

                double precio = clases * 45.70;

                StringBuffer nombreAlumno = new StringBuffer(new String(nombre).trim());
                nombreAlumno.setLength(10);
                ficheroPrecio.writeChars(nombreAlumno.toString());

                StringBuffer dniAlumno = new StringBuffer(new String(dni).trim());
                dniAlumno.setLength(9);
                ficheroPrecio.writeChars(dniAlumno.toString());

                ficheroPrecio.writeInt(clases);
                ficheroPrecio.writeDouble(precio);
            }

            System.out.println("Fichero precio.dat creado correctamente.");

            ficheroPrecio.seek(0);

            System.out.println("\nContenido del fichero de precios:");

            while (ficheroPrecio.getFilePointer() < ficheroPrecio.length()) {

                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = ficheroPrecio.readChar();
                }

                char[] dni = new char[9];
                for (int i = 0; i < dni.length; i++) {
                    dni[i] = ficheroPrecio.readChar();
                }

                int clases = ficheroPrecio.readInt();

                double precio = ficheroPrecio.readDouble();

                System.out.println("Nombre: " + new String(nombre).trim()
                        + ", DNI: " + new String(dni).trim()
                        + ", Clases: " + clases
                        + ", Precio total: " + String.format("%.2f", precio) + " €");
            }

        } catch (IOException e) {
            System.out.println("Error al trabajar con los ficheros.");
        }
    }
}
