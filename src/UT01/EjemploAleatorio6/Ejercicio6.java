package UT01.EjemploAleatorio6;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio6 {
    private static final String RUTA_FICHERO = "src/UT01/EjemploAleatorio6/";
    private static final String NOMBRE_FICHERO = "empleados.dat";
    static void main() {
        int[] identificadores = {1, 2, 3, 4};
        String[] apellidos = {"Fernandez", "Lopez", "Garcia", "Sanchez"};
        int[] departamentos = {10, 20, 10, 30};
        double[] salarios = {1800.50, 2100.00, 1950.75, 2300.25};

        try(RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
            for (int i = 0; i < identificadores.length; i++) {
                fichero.writeInt(identificadores[i]);

                StringBuffer apellido = new StringBuffer(apellidos[i]);
                apellido.setLength(10);

                fichero.writeChars(apellido.toString());

                fichero.writeInt(departamentos[i]);

                fichero.writeDouble(salarios[i]);
            }
            System.out.println("Registros almacenados correctamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (RandomAccessFile fichero = new
                RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
            while (fichero.getFilePointer() < fichero.length()) {

                int identificador = fichero.readInt();

                char[] apellido = new char[10];
                for (int i = 0; i < apellido.length; i++) {
                    apellido[i] = fichero.readChar();
                }

                int departamento = fichero.readInt();
                // Lee salario
                double salario = fichero.readDouble();
                System.out.println("Id: " + identificador
                        + ", Apellido: " + new String(apellido).trim()
                        + ", Departamento: " + departamento
                        + ", Salario: " + salario);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }

    }
}
