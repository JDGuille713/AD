
package UT01.Ejercicio6Aleatorios.Ejercicio2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {

    private static final String RUTA_FICHERO = "src/UT01/Ejercicio6Aleatorios/Ejercicio1/";
    private static final String NOMBRE_FICHERO = "enteros.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            int numero;

            do {
                System.out.print("Introduce un número entre 0 y 9: ");
                numero = sc.nextInt();
            } while (numero < 0 || numero > 9);

            System.out.print("¿Cuántas veces quieres añadirlo? ");
            int cantidad = sc.nextInt();

            fichero.seek(fichero.length());

            for (int i = 0; i < cantidad; i++) {
                fichero.writeInt(numero);
            }

            System.out.println("Números añadidos correctamente.");

            fichero.seek(0);

            System.out.println("Contenido del fichero:");

            while (fichero.getFilePointer() < fichero.length()) {
                System.out.println(fichero.readInt());
            }

        } catch (IOException e) {
            System.out.println("Error al trabajar con el fichero.");
        }

        sc.close();
    }
}
