package UT01.PracticaRepaso.EjercicioRepasoRAF;/*
 * ENUNCIADO
 * ---------
 * Crea un programa llamado Notas.java que trabaje con un fichero de acceso
 * aleatorio llamado notas.dat.
 *
 * Cada registro contendra los siguientes campos:
 *   - id   (int)
 *   - nota (double)
 *
 * El programa realizara, en este orden y sin menu, las siguientes acciones:
 *
 *   1. Crear el fichero y escribir 5 registros fijos definidos en el codigo:
 *        (1, 5.5), (2, 7.0), (3, 4.2), (4, 9.1), (5, 6.8)
 *
 *   2. Mostrar el numero de registros que contiene el fichero, calculandolo
 *      a partir de la longitud del fichero y el tamano de cada registro.
 *
 *   3. Leer y mostrar unicamente el registro numero 3 (el cuarto, contando
 *      desde 0), posicionandose en el con seek.
 *
 *   4. Cambiar la nota del registro numero 1 a 10.0, posicionandose
 *      directamente en el campo nota con seek.
 *
 *   5. Mostrar todo el contenido del fichero para comprobar el cambio.
 *
 * Resultado esperado:
 *
 *   Registros: 5
 *   Registro 3 -> id: 4, nota: 9.1
 *   1 | 5.5
 *   2 | 10.0
 *   3 | 4.2
 *   4 | 9.1
 *   5 | 6.8
 */

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Notas {

    private static final int TAM_REGISTRO = 4 + 8; // int + double = 12 bytes
    private static final String RUTA_FICHERO = "src/UT01/PracticaRepaso/EjercicioRepasoRAF/";
    private static final String NOMBRE_FICHERO = "notas.dat";

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {

            // 1. Crear el fichero con 5 registros
            raf.setLength(0); // vacia el fichero por si ya existia
            int[] ids = {1, 2, 3, 4, 5};
            double[] notas = {5.5, 7.0, 4.2, 9.1, 6.8};
            for (int i = 0; i < ids.length; i++) {
                raf.writeInt(ids[i]);
                raf.writeDouble(notas[i]);
            }

            // 2. Numero de registros
            long total = raf.length() / TAM_REGISTRO;
            System.out.println("Registros: " + total);

            // 3. Leer solo el registro numero 3
            long pos = 3 * TAM_REGISTRO; // inicio del registro 3
            raf.seek(pos);
            int id = raf.readInt();
            double nota = raf.readDouble();
            System.out.println("Registro 3 -> id: " + id + ", nota: " + nota);

            // 4. Cambiar la nota del registro 1 a 10.0
            pos = 1 * TAM_REGISTRO;
            raf.seek(pos + 4); // salta el id (4 bytes) y cae en la nota
            raf.writeDouble(10.0);

            // 5. Mostrar todo el fichero
            for (int i = 0; i < total; i++) {
                raf.seek(i * TAM_REGISTRO);
                int idLeido = raf.readInt();
                double notaLeida = raf.readDouble();
                System.out.println(idLeido + " | " + notaLeida);

            }
            System.out.println();

            try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
                while (true) {
                    int id2 = leer.readInt();
                    double nota2 = leer.readDouble();
                    System.out.println(id2 + " | " + nota2);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}