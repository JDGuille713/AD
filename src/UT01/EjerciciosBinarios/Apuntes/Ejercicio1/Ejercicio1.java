package UT01.EjerciciosBinarios.Apuntes.Ejercicio1;

import java.io.*;

public class Ejercicio1 {
    private static final String RUTA_FICHERO = "src/UT01.EjerciciosBinarios/Apuntes/Ejercicio1/";
    private static final String NOMBRE_FICHERO = "1_50.dat";
    static void main() {
        try (DataOutputStream escribir = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (int i = 1; i < 51; i++) {
                escribir.writeInt(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (int i = 1; i < 51; i++) {
                System.out.println(leer.readInt());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
