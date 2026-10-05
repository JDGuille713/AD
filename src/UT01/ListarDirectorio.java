package UT01;

import java.io.File;
public class ListarDirectorio {
    public static void main(String[] args) {
        File directorio = new File(".");
        String[] elementos = directorio.list();
        if (elementos != null) {
            for (String nombre : elementos) {
                File elemento = new File(directorio, nombre);
                if (elemento.isDirectory()) {
                    System.out.println("Directorio: " + nombre);
                } else {
                    System.out.println("Fichero: " + nombre);
                }
            }
        }
    }
}