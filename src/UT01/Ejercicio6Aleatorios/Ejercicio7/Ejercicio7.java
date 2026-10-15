
package UT01.Ejercicio6Aleatorios.Ejercicio7;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio7 {

    private static final String RUTA_FICHERO = "src/UT01/Ejercicio6Aleatorios/Ejercicio5/";
    private static final String NOMBRE_FICHERO = "precio.dat";
    private static final String NOMBRE_FICHERO_DESCUENTO = "descuento.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el porcentaje de descuento: ");
        double descuento = sc.nextDouble();

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r");
             RandomAccessFile ficheroDescuento = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO_DESCUENTO, "rw")) {

            ficheroDescuento.setLength(0);

            while (fichero.getFilePointer() < fichero.length()) {

                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = fichero.readChar();
                }

                char[] dni = new char[9];
                for (int i = 0; i < dni.length; i++) {
                    dni[i] = fichero.readChar();
                }

                int clases = fichero.readInt();

                double precioAnterior = fichero.readDouble();

                double precioDescuento = precioAnterior - (precioAnterior * descuento / 100);

                StringBuffer dniAlumno = new StringBuffer(new String(dni).trim());
                dniAlumno.setLength(9);
                ficheroDescuento.writeChars(dniAlumno.toString());

                ficheroDescuento.writeDouble(precioAnterior);
                ficheroDescuento.writeDouble(precioDescuento);
            }

            ficheroDescuento.seek(0);

            System.out.println("\nContenido del fichero de descuentos:");

            while (ficheroDescuento.getFilePointer() < ficheroDescuento.length()) {

                char[] dni = new char[9];
                for (int i = 0; i < dni.length; i++) {
                    dni[i] = ficheroDescuento.readChar();
                }

                double precioAnterior = ficheroDescuento.readDouble();

                double precioDescuento = ficheroDescuento.readDouble();

                System.out.println("DNI: " + new String(dni).trim()
                        + ", Precio anterior: " + String.format("%.2f", precioAnterior) + " €"
                        + ", Precio con descuento: " + String.format("%.2f", precioDescuento) + " €");
            }

        } catch (IOException e) {
            System.out.println("Error al trabajar con los ficheros.");
        }

        sc.close();
    }
}
