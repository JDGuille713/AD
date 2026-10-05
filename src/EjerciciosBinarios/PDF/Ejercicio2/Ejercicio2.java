package EjerciciosBinarios.PDF.Ejercicio2;

import java.io.*;
import java.util.Scanner;

public class Ejercicio2 {
    private final static String RUTA_FICHERO = "src/EjerciciosBinarios/PDF/Ejercicio2/";
    private static final String NOMBRE_FICHERO = "coches.bin";

    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuántos coches quieres guardar?");
        int cantCoches = sc.nextInt();

        try (DataOutputStream escribir = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (int i = 0; i < cantCoches; i++) {
                System.out.print("Escribe la matrícula del coche: ");
                String matricula = sc.nextLine();

                System.out.print("Escribe la marca del coche: ");
                String marca = sc.nextLine();

                System.out.print("Escribe el tamaño del depósito: ");
                double deposito = Double.parseDouble(sc.nextLine());

                System.out.print("Escribe el modelo del coche: ");
                String modelo = sc.nextLine();

                escribir.writeUTF(matricula);
                escribir.writeUTF(marca);
                escribir.writeDouble(deposito);
                escribir.writeUTF(modelo);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }

        try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            while (true) {
                String matricula = leer.readUTF();
                String marca = leer.readUTF();
                double deposito = leer.readDouble();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }

    }
}
