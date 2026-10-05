package UT01;

import java.io.File;
import java.io.IOException;

public class CrearArchivos {
    static void main() throws IOException {
        File directorio = new File("datos");

        if (directorio.mkdir()) {
            System.out.println("Directorio creado.");
        } else {
            System.out.println("No se ha creado el directorio.");
        }

        File fichero1 = new File(directorio, "fichero1.txt");
        fichero1.createNewFile();

        File fichero2 = new File(directorio, "fichero2.txt");
        fichero2.createNewFile();
    }
}
