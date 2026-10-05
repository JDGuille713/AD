package UT01.Binarios5.Ejercicio7Binario;

import java.io.*;

public class Ejercicio7 {

    private static final String RUTA_FICHERO = "src/AccesoDatos/Ejercicio7/";
    private static final String FICHERO_ORIGINAL = "muchosdatos.bin";
    private static final String FICHERO_MENORES = "menores.dat";
    private static final String FICHERO_ADULTOS = "adultos.dat";
    private static final String FICHERO_MAYORES = "mayores.dat";

    public static void main(String[] args) {

        try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + FICHERO_ORIGINAL));

             DataOutputStream menores = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + FICHERO_MENORES));

             DataOutputStream adultos = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + FICHERO_ADULTOS));

             DataOutputStream mayores = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + FICHERO_MAYORES))) {

            while (true) {

                try {
                    String nombre = leer.readUTF();
                    String apellidos = leer.readUTF();
                    int edad = leer.readInt();
                    String telefono = leer.readUTF();
                    String email = leer.readUTF();
                    String ciudad = leer.readUTF();
                    String nacionalidad = leer.readUTF();
                    String profesion = leer.readUTF();

                    DataOutputStream fichero;

                    if (edad < 18) {
                        fichero = menores;
                    } else if (edad > 65) {
                        fichero = mayores;
                    } else {
                        fichero = adultos;
                    }

                    fichero.writeUTF(nombre);
                    fichero.writeUTF(apellidos);
                    fichero.writeInt(edad);
                    fichero.writeUTF(telefono);
                    fichero.writeUTF(email);
                    fichero.writeUTF(ciudad);
                    fichero.writeUTF(nacionalidad);
                    fichero.writeUTF(profesion);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al trabajar con los ficheros.");
            return;
        }

        mostrarFichero(FICHERO_MENORES);
        mostrarFichero(FICHERO_ADULTOS);
        mostrarFichero(FICHERO_MAYORES);
    }

    static void mostrarFichero(String nombreFichero) {

        System.out.println("\n" + nombreFichero);

        try (DataInputStream leer = new DataInputStream(new FileInputStream(RUTA_FICHERO + nombreFichero))) {

            while (true) {

                try {
                    String nombre = leer.readUTF();
                    String apellidos = leer.readUTF();
                    int edad = leer.readInt();
                    String telefono = leer.readUTF();
                    String email = leer.readUTF();
                    String ciudad = leer.readUTF();
                    String nacionalidad = leer.readUTF();
                    String profesion = leer.readUTF();

                    System.out.println("Nombre: " + nombre +  " Apellidos: " + apellidos + " Edad: " + edad + " Teléfono: " + telefono +
                            " Email: " + email + " Ciudad: " + ciudad + " Nacionalidad: " + nacionalidad +  " Profesion: " + profesion);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer " + nombreFichero);
        }
    }
}