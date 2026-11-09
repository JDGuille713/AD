package UT01.PracticaRepaso.Ejercicio3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestionProductos {

    private static final String FICHERO = "productos.dat";
    private static final int LONG_NOMBRE = 15;
    // int (4) + 15 chars * 2 bytes (30) + double (8)
    private static final int TAM_REGISTRO = 4 + LONG_NOMBRE * 2 + 8;

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (!new File(FICHERO).exists()) {
            System.out.println("Aviso: no existe " + FICHERO + ", se creará al añadir productos.");
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1:
                        anadirProducto();
                        mostrarContenido();
                        break;
                    case 2:
                        modificarPrecio();
                        mostrarContenido();
                        break;
                    case 3:
                        eliminarProducto();
                        mostrarContenido();
                        break;
                    case 0:
                        System.out.println("Hasta pronto.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (IOException e) {
                System.out.println("Error de E/S: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
        System.out.println("1. Añadir un nuevo producto");
        System.out.println("2. Modificar el precio de un producto");
        System.out.println("3. Eliminar un producto (borrado lógico)");
        System.out.println("0. Salir");
    }

    // ---------- Operaciones ----------

    private static void anadirProducto() throws IOException {
        int codigo = leerEntero("Código: ");
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
            if (buscarPosicion(raf, codigo) != -1) {
                System.out.println("Aviso: el código " + codigo + " ya existe. No se añade.");
                return;
            }
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            double precio = leerDouble("Precio: ");

            raf.seek(raf.length()); // al final del fichero
            escribirRegistro(raf, codigo, nombre, precio);
            System.out.println("Producto añadido.");
        }
    }

    private static void modificarPrecio() throws IOException {
        int codigo = leerEntero("Código del producto: ");
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
            long pos = buscarPosicion(raf, codigo);
            if (pos == -1) {
                System.out.println("No existe ningún producto con código " + codigo + ".");
                return;
            }
            double nuevoPrecio = leerDouble("Nuevo precio: ");
            // Saltamos código (4) + nombre (30) para llegar al precio
            raf.seek(pos + 4 + LONG_NOMBRE * 2);
            raf.writeDouble(nuevoPrecio);
            System.out.println("Precio actualizado.");
        }
    }

    private static void eliminarProducto() throws IOException {
        int codigo = leerEntero("Código del producto: ");
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
            long pos = buscarPosicion(raf, codigo);
            if (pos == -1) {
                System.out.println("No existe ningún producto con código " + codigo + ".");
                return;
            }
            // Mantiene el código y la posición; solo cambia nombre y precio
            raf.seek(pos + 4);
            raf.writeChars(ajustarNombre("ELIMINADO"));
            raf.writeDouble(0.0);
            System.out.println("Producto eliminado (borrado lógico).");
        }
    }

    // ---------- Utilidades de fichero ----------

    /** Devuelve la posición (en bytes) del registro con ese código, o -1 si no existe. */
    private static long buscarPosicion(RandomAccessFile raf, int codigo) throws IOException {
        long total = raf.length() / TAM_REGISTRO;
        for (long i = 0; i < total; i++) {
            long pos = i * TAM_REGISTRO;
            raf.seek(pos);
            if (raf.readInt() == codigo) {
                return pos;
            }
        }
        return -1;
    }

    private static void escribirRegistro(RandomAccessFile raf, int codigo, String nombre, double precio)
            throws IOException {
        raf.writeInt(codigo);
        raf.writeChars(ajustarNombre(nombre));
        raf.writeDouble(precio);
    }

    /** Recorta o rellena con espacios hasta exactamente 15 caracteres. */
    private static String ajustarNombre(String nombre) {
        if (nombre.length() > LONG_NOMBRE) {
            return nombre.substring(0, LONG_NOMBRE); // recorta
        }
        String resultado = nombre;
        while (resultado.length() < LONG_NOMBRE) {
            resultado = resultado + " "; // rellena con espacios
        }
        return resultado;
    }

    private static void mostrarContenido() {
        File f = new File(FICHERO);
        if (!f.exists()) {
            System.out.println("\n(El fichero aún no existe)");
            return;
        }
        System.out.println("\nCódigo | Nombre | Precio");
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            long total = raf.length() / TAM_REGISTRO;
            for (long i = 0; i < total; i++) {
                raf.seek(i * TAM_REGISTRO);
                int codigo = raf.readInt();
                char[] buf = new char[LONG_NOMBRE];
                for (int j = 0; j < LONG_NOMBRE; j++) {
                    buf[j] = raf.readChar();
                }
                String nombre = new String(buf).trim();
                double precio = raf.readDouble();
                System.out.printf("%d | %s | %.2f%n", codigo, nombre, precio);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    // ---------- Lectura segura por teclado ----------

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido (ej. 12.50).");
            }
        }
    }
}