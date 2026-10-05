package Act2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Act2_2 {
    private static final String RUTA_FICHERO = "datos/";
    private static final String NOMBRE_FICHERO = "fichero1.txt";

    static void main() {
        String nombreFichero = RUTA_FICHERO + NOMBRE_FICHERO;


        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }
}
