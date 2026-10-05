package UT01.EjerciciosBinarios.PDF.Ejercicio1;

import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
    private static final String RUTA_FICHERO = "src/UT01.EjerciciosBinarios/PDF/Ejercicio1/";
    private static final String NOMBRE_FICHERO = "num_aleat.bin";
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuántos números quieres almacenar?");
        int cantNumeros = sc.nextInt();

        System.out.println("¿Entre qué rango? (Límites incluidos)");
        System.out.print("Primer número del límite: ");
        int rango1 = sc.nextInt();

        System.out.print("Segundo número del  límite: ");
        int rango2 = sc.nextInt();

        int rango = (rango2 - rango1) + 1;
        if ((rango1 > 0 && rango2 > 0) && rango1 < rango2) {
            if (rango >= cantNumeros) {
                try (DataOutputStream escribir = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {
                    for (int i = 0; i < cantNumeros; i++) {
                       int numero = (int) (Math.random() * rango) + rango1;
                       escribir.writeInt(numero);
                    }
                } catch (IOException e) {
                    System.out.println("Error al escribir el fichero.");
                    return;
                }

                try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
                   /* for (int i = 0; i < cantNumeros; i++) {
                        System.out.print(leer.readInt() + " ");
                    }*/

                    while(true) {
                        System.out.print(leer.readInt() + " ");
                    }

                }catch (EOFException e) {
                    System.out.println("Fin del fichero");
                }
                catch (IOException e) {
                    System.out.println("Error al leer el fichero.");
                }
            }
        } else {
            System.out.println("Error");
        }
    }
}
