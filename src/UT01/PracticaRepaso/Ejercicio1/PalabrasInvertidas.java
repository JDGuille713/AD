package UT01.PracticaRepaso.Ejercicio1;

import java.io.*;
import java.util.Scanner;

public class PalabrasInvertidas {
    private static final String RUTA_FICHERO = "src/media/Ejercicio1/";
    private static final String FICHERO_ORIGEN = "texto.txt";
    private static final String FICHERO_DESTINO = "textoinvertido.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("***** PALABRAS INVERTIDAS *****");
            System.out.println("1. Procesar fichero texto.txt");
            System.out.println("2. Mostrar textoinvertido.txt");
            System.out.println("0. Salir");
            System.out.print("Seleccionar opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    procesarFichero();
                    break;
                case 2:
                    mostrarFichero();
                    break;
                case 0:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            System.out.println();

        } while (opcion != 0);
        sc.close();
    }

    // 1. Leer texto.txt e invertir las palabras de cada línea
    public static void procesarFichero() {
        try(BufferedReader lector = new BufferedReader(new FileReader(RUTA_FICHERO + FICHERO_ORIGEN));
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_FICHERO + FICHERO_DESTINO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] palabras = linea.split(" ");

                for (int i = palabras.length - 1; i >= 0; i--) {
                    bw.write(palabras[i]);

                    if (i > 0) {
                        bw.write(" ");
                    }
                }

                bw.newLine();
            }

            System.out.println("Fichero procesado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el fichero");
        }
    }

    // 2. Mostrar el contenido de textoinvertido.txt
    public static void mostrarFichero() {
        try(BufferedReader br = new BufferedReader(new FileReader(RUTA_FICHERO + FICHERO_DESTINO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
    }
}
