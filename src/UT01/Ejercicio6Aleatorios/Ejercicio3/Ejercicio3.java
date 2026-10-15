
package UT01.Ejercicio6Aleatorios.Ejercicio3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3 {

    private static final String RUTA_FICHERO = "src/UT01/Ejercicio6Aleatorios/Ejercicio3/";
    private static final String NOMBRE_FICHERO = "juegos.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            fichero.setLength(0);

            int orden;

            do {
                System.out.print("Introduce el número de orden (0 para terminar): ");
                orden = sc.nextInt();
                sc.nextLine();

                if (orden != 0) {

                    System.out.print("Introduce el nombre del juego: ");
                    String nombre = sc.nextLine();

                    System.out.print("Introduce la edad mínima recomendada: ");
                    int edad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Introduce la clasificación temática: ");
                    String clasificacion = sc.nextLine();

                    fichero.writeInt(orden);

                    StringBuffer nombreJuego = new StringBuffer(nombre);
                    nombreJuego.setLength(10);
                    fichero.writeChars(nombreJuego.toString());

                    fichero.writeInt(edad);

                    StringBuffer temaJuego = new StringBuffer(clasificacion);
                    temaJuego.setLength(10);
                    fichero.writeChars(temaJuego.toString());
                }

            } while (orden != 0);

            System.out.println("\nContenido del fichero:");

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {

                int numeroOrden = fichero.readInt();

                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = fichero.readChar();
                }

                int edad = fichero.readInt();

                char[] clasificacion = new char[10];
                for (int i = 0; i < clasificacion.length; i++) {
                    clasificacion[i] = fichero.readChar();
                }

                System.out.println("Orden: " + numeroOrden + ", Juego: " + new String(nombre).trim() + ", Edad mínima: " + edad + ", Clasificación: " + new String(clasificacion).trim());
            }

        } catch (IOException e) {
            System.out.println("Error al trabajar con el fichero.");
        }

        sc.close();
    }
}
