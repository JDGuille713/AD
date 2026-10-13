
package UT01.Ejercicio6Aleatorios.Ejercicio1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {

    private static final String RUTA_FICHERO = "src/UT01/Ejercicio6Aleatorios/Ejercicio1/";
    private static final String NOMBRE_FICHERO = "enteros.dat";

    public static void main(String[] args) {
        {

            Scanner sc = new Scanner(System.in);

            try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

                fichero.setLength(0);

                for (int i = 0; i < 6; i++) {
                    fichero.writeInt(1);
                }

                for (int i = 0; i < 10; i++) {
                    fichero.writeInt(2);
                }

                System.out.println("Contenido del fichero:");

                fichero.seek(0);

                while (fichero.getFilePointer() < fichero.length()) {
                    System.out.println(fichero.readInt());
                }

                int numero;

                do {
                    System.out.print("Introduce un número entre 0 y 9: ");
                    numero = sc.nextInt();
                } while (numero < 0 || numero > 9);

                fichero.seek(0);

                while (fichero.getFilePointer() < fichero.length()) {

                    int valor = fichero.readInt();

                    if (valor == 2) {
                        fichero.seek(fichero.getFilePointer() - 4);
                        fichero.writeInt(numero);
                    }
                }

                System.out.println("Contenido del fichero modificado:");

                fichero.seek(0);

                while (fichero.getFilePointer() < fichero.length()) {
                    System.out.println(fichero.readInt());
                }

            } catch (IOException e) {
                System.out.println("Error al trabajar con el fichero.");
            }

            sc.close();
        }
    }
}
