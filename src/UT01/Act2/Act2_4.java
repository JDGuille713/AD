package UT01.Act2;

import java.io.*;

public class Act2_4 {
    private static final String RUTA_FICHERO = "datos/";
    private static final String FICHERO_ORIGEN = "datos.txt";
    private static final String FICHERO_DESTINO = "copia.txt";

    static void main() {
        try (BufferedReader leerFichero = new BufferedReader(new FileReader(RUTA_FICHERO + FICHERO_ORIGEN));
             BufferedWriter escribirFichero = new BufferedWriter(new FileWriter(RUTA_FICHERO + FICHERO_DESTINO))) {

            String linea;
            while ((linea = leerFichero.readLine()) != null) {
                escribirFichero.write(linea);
                escribirFichero.newLine();
            }
            System.out.println("Fichero copiado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al copiar el fichero.");
        }

    }
}
