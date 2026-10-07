package UT01.EjemploAleatorio6;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ModificarSalario {
    private static final String RUTA_FICHERO = "src/UT01/EjemploAleatorio6/";
    // Nombre del fichero de acceso aleatorio
    private static final String NOMBRE_FICHERO = "empleados.dat";
    // Tamaño de cada registro (4 + 20 + 4 + 8 = 36 bytes)
    private static final int TAM_REGISTRO = 36;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introducir el identificador del empleado: ");
        int id = sc.nextInt();

        System.out.print("Introducir el nuevo salario: ");
        double nuevoSalario = sc.nextDouble();

        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            long posicion = (long) (id - 1) * TAM_REGISTRO;

            if (posicion >= fichero.length()) {
                System.out.println("El empleado no existe.");
                return;
            }

            fichero.seek(posicion + 28);

            fichero.writeDouble(nuevoSalario);

            System.out.println("Salario actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }
        sc.close();
    }
}
