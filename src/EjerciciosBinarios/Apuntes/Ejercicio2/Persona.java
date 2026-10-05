package EjerciciosBinarios.Apuntes.Ejercicio2;

import java.io.Serializable;

public class Persona implements Serializable {
    String nombre = "";
    Double notaMedia;

    public Persona() {
    }

    public Persona(String nombre, Double notaMedia) {
        this.nombre = nombre;
        this.notaMedia = notaMedia;
    }

    @Override
    public String toString() {
        return "Persona: " + "nombre = " + nombre + ", notaMedia = " + notaMedia;
    }
}
