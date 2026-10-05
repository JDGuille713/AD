package EjerciciosBinarios.Apuntes.Ejercicio2;

import java.io.Serializable;

public class Alumno implements Serializable {
    String nombre = "";
    Double notaMedia;

    public Alumno() {
    }

    public Alumno(String nombre, Double notaMedia) {
        this.nombre = nombre;
        this.notaMedia = notaMedia;
    }

    @Override
    public String toString() {
        return "Persona: " + "nombre = " + nombre + ", notaMedia = " + notaMedia;
    }
}
