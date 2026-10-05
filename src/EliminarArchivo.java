import java.io.File;

public class EliminarArchivo {
    static void main() {
        File fichero = new File("datos", "fichero2.txt");
        if (fichero.delete()) {
            System.out.println("Fichero eliminado correctamente.");
        } else {
            System.out.println("No se ha podido eliminar el fichero.");
        }
    }
}
