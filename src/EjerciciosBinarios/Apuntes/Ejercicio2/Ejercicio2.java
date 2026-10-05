package EjerciciosBinarios.Apuntes.Ejercicio2;

import java.io.*;

public class Ejercicio2 {
    private final static String RUTA_FICHERO = "src/EjerciciosBinarios/Apuntes/Ejercicio2/";
    private final static String NOMBRE_FICHERO = "personas.dat";
    static void main() {
        Alumno[] personas = {
                new Alumno("Ana", 7.5),
                new Alumno("Gabriel", 4.5),
                new Alumno("Cristian", 9.0),
                new Alumno("Samuel", 6.0),
                new Alumno("Hector", 7.25)
        };
            try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
                for (int i = 0; i < personas.length; i++) {
                    escribir.writeObject(personas[i]);
                }
            } catch (IOException e) {
                System.out.println("Error");
            }

            try (ObjectInputStream leer = new ObjectInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
                for (int i = 0; i < 5; i++) {
                    Alumno personaLeida = (Alumno) leer.readObject();
                    System.out.println(personaLeida);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error");
            }
    }
}
