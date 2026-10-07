package UT01.EjemploAleatorio6;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class BuscarEmpleado {
    private static final String RUTA_FICHERO = "src/UT01/EjemploAleatorio6/";
    private static final String NOMBRE_FICHERO = "empleados.dat";
    private static final int TAM_REGISTRO = 36;
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el identificador del empleado: ");
        int id = teclado.nextInt();
        // Busca el registro correspondiente al empleado
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            long posicion = (long) (id - 1) * TAM_REGISTRO;
            // Comprueba si el registro existe
            if (posicion >= fichero.length()) {
                System.out.println("El empleado no existe.");
                return;
            }
            // Sitúa el puntero al comienzo del registro
            fichero.seek(posicion);
            // Lee identificador
            int identificador = fichero.readInt();

            char[] apellido = new char[10];
            for (int i = 0; i < apellido.length; i++) {
                apellido[i] = fichero.readChar();
            }
            // Lee departamento
            int departamento = fichero.readInt();
            double salario = fichero.readDouble();
            System.out.println("Id: " + identificador);
            System.out.println("Apellido: " + new
                    String(apellido).trim());
            System.out.println("Departamento: " + departamento);
            System.out.println("Salario: " + salario);
        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }
        teclado.close();

    }
}
