package UT01.Act2;

import java.io.FileWriter;
import java.io.IOException;

public class Act2_3 {
    private static final String RUTA_FICHERO = "datos/";
    private static final String NOMBRE_FICHERO = "fichero1.txt";

    static void main() {
        try (FileWriter escribirFichero = new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO)) {
            escribirFichero.write("2 DAM");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
        }
    }
}
