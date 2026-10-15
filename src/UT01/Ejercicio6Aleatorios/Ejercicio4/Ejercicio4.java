
package UT01.Ejercicio6Aleatorios.Ejercicio4;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4 {

    private static final String RUTA_FICHERO = "src/UT01/Ejercicio6Aleatorios/Ejercicio3/";
    private static final String NOMBRE_FICHERO = "juegos.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu edad: ");
        int edadIntroducida = sc.nextInt();

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {

            System.out.println("\nJuegos recomendados para tu edad:");

            while (fichero.getFilePointer() < fichero.length()) {

                int orden = fichero.readInt();

                char[] nombre = new char[10];
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = fichero.readChar();
                }

                int edadMinima = fichero.readInt();

                char[] clasificacion = new char[10];
                for (int i = 0; i < clasificacion.length; i++) {
                    clasificacion[i] = fichero.readChar();
                }

                if (edadIntroducida >= edadMinima) {
                    System.out.println("Juego: " + new String(nombre).trim()
                            + ", Temática: " + new String(clasificacion).trim());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

        sc.close();
    }
}
