package UT01.PracticaRepaso.Ejercicio2;

import java.io.*;
import java.util.Scanner;

public class NominasActualizadas {
    private static final String RUTA_FICHERO = "src/media/Ejercicio2/";
    private static final String FICHERO_ORIGEN = "nominas.dat";
    private static final String FICHERO_DESTINO = "nominasactualizado.dat";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        int eliminados = 0;

        do {
            System.out.println("***** ACTUALIZACIÓN DE NÓMINAS *****");
            System.out.println("1. Actualizar nóminas");
            System.out.println("2. Mostrar nóminas actualizadas");
            System.out.println("0. Salir");
            System.out.print("Seleccionar opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    eliminados = actualizarNominas();
                    break;
                case 2:
                    mostrarNominas(eliminados);
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

    // 1. Leer las nóminas, aplicar las actualizaciones y guardar los registros que correspondan
    public static int actualizarNominas() {
        int eliminados = 0;

        try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + FICHERO_ORIGEN));
             DataOutputStream escribir = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + FICHERO_DESTINO))) {


            while (true) {
                String nombre = leer.readUTF();
                int diasBaja = leer.readInt();
                double nomina = leer.readDouble();

                if (diasBaja == 0) {
                    nomina = nomina * 1.05;
                } else if (diasBaja >= 4 && diasBaja <= 10) {
                    nomina = nomina * 0.90;
                } else if (diasBaja > 10) {
                    eliminados++;
                }

                if (diasBaja <= 10) {
                    escribir.writeUTF(nombre);
                    escribir.writeInt(diasBaja);
                    escribir.writeDouble(nomina);
                }
            }
        } catch (EOFException e) {
            System.out.println("Nominas actualizadas");
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el archivo");
        }
        return eliminados;
    }

    // 2. Mostrar los empleados que permanecen e indicar cuántos han sido eliminados
    public static void mostrarNominas(int eliminados) {
        try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + FICHERO_DESTINO))) {

            while (true) {
                String nombre = leer.readUTF();
                int diasBajas = leer.readInt();
                double nomina = leer.readDouble();

                System.out.println("Nombre: " + nombre);
                System.out.println("Dias de baja: " + diasBajas);
                System.out.println("Nomina: " + nomina);
            }


        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        } finally {
            System.out.println("Empleados eliminados: " + eliminados);
        }
    }
}
