package Act2;

import java.io.FileReader;
import java.io.IOException;

public class Act2_1 {
    private static final String RUTA_FICHERO = "datos/";
    private static final String NOMBRE_FICHERO = "fichero1.txt";

    static void main() {
        try (FileReader lector = new FileReader(RUTA_FICHERO + NOMBRE_FICHERO)) {
            int caracter;
            while ((caracter = lector.read()) != -1) {
                System.out.print((char) caracter);
        }
    } catch (IOException e) {
            System.out.println("Error al leer el texto");
        }
    }
}
