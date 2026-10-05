package UT01;

import java.io.File;

public class InformacionFichero {
    static void main() {
        File fichero = new File("hola.txt");

        System.out.println("Nombre: " + fichero.getName());
        System.out.println("Ruta: " + fichero.getPath());
        System.out.println("Ruta absoluta " + fichero.getAbsolutePath());
        System.out.println("Existe o no: " + fichero.exists());
        System.out.println("Se puede leer o no: " + fichero.canRead());
        System.out.println("Se puede escribir o no: " + fichero.canWrite());
        System.out.println("Tamaño: " + fichero.length());
        System.out.println("Es un directorio: " + fichero.isDirectory());
    }
}
