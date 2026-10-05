package EjerciciosBinarios.Apuntes.Ejercicio2;

import java.io.*;

public class Ejercicio2 {
    private final static String RUTA_FICHERO = "src/EjerciciosBinarios/Apuntes/Ejercicio2/";
    private final static String NOMBRE_FICHERO = "personas.dat";
    static void main() {
        Persona[] personas = {
                new Persona("Ana", 7.5),
                new Persona("Gabriel", 4.5),
                new Persona("Cristian", 9.0),
                new Persona("Samuel", 6.0),
                new Persona("Hector", 7.25)
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
                    Persona personaLeida = (Persona) leer.readObject();
                    System.out.println(personaLeida);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error");
            }
    }
}
