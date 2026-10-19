package UT01.FicherosXML8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String RUTA_FICHERO = "src/UT01/FicherosXML8/";
    private static final String NOMBRE_FICHERO = "peliculas.xml";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Crear documento XML");
            System.out.println("2. Mostrar películas");
            System.out.println("3. Modificar título");
            System.out.println("4. Añadir película");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearDocumento();
                    break;
                case 2:
                    mostrarPeliculas();
                    break;
                case 3:
                    modificarTitulo(scanner);
                    break;
                case 4:
                    anadirPelicula(scanner);
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void crearDocumento() {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.newDocument();

            Element peliculas = documento.createElement("peliculas");
            documento.appendChild(peliculas);

            Element catalogo = documento.createElement("catalogo");
            peliculas.appendChild(catalogo);

            String[] años = {"1999", "2014", "2000"};
            String[] titulos = {"Matrix", "Interstellar", "Gladiator"};
            String[] directores = {"Lana y Lilly Wachowski", "Christopher Nolan","Ridley Scott"};


            for (int i = 0; i < titulos.length; i++) {

                Element pelicula = documento.createElement("pelicula");
                pelicula.setAttribute("año", años[i]);
                catalogo.appendChild(pelicula);

                Element titulo = documento.createElement("titulo");
                titulo.setTextContent(titulos[i]);
                pelicula.appendChild(titulo);

                Element director = documento.createElement("director");
                director.setTextContent(directores[i]);
                pelicula.appendChild(director);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO)));

            System.out.println("Documento XML creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear el documento XML.");
        }
    }

    private static void mostrarPeliculas() {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            NodeList peliculas = documento.getElementsByTagName("pelicula");

            for (int i = 0; i < peliculas.getLength(); i++) {

                Element pelicula = (Element) peliculas.item(i);

                String año = pelicula.getAttribute("año");

                String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();

                String director = pelicula.getElementsByTagName("director").item(0).getTextContent();

                System.out.println("Año: " + año);
                System.out.println("Título: " + titulo);
                System.out.println("Director: " + director);
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error al leer el documento XML.");
        }
    }

    private static void modificarTitulo(Scanner sc) {

        System.out.print("Título de la película que quieres modificar: ");
        String tituloBuscar = sc.nextLine();

        System.out.print("Nuevo título: ");
        String nuevoTitulo = sc.nextLine();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            NodeList peliculas = documento.getElementsByTagName("pelicula");

            boolean encontrada = false;

            for (int i = 0; i < peliculas.getLength(); i++) {

                Element pelicula = (Element) peliculas.item(i);

                Element titulo = (Element) pelicula.getElementsByTagName("titulo").item(0);

                if (titulo.getTextContent().equalsIgnoreCase(tituloBuscar)) {
                    titulo.setTextContent(nuevoTitulo);
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("La película no existe.");
                return;
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO)));

            System.out.println("Título modificado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al modificar el documento XML.");
        }
    }

    private static void anadirPelicula(Scanner sc) {

        System.out.print("Año de la película: ");
        String año = sc.nextLine();

        System.out.print("Título de la película: ");
        String titulo = sc.nextLine();

        System.out.print("Director de la película: ");
        String director = sc.nextLine();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            Element catalogo = (Element) documento.getElementsByTagName("catalogo").item(0);

            Element pelicula = documento.createElement("pelicula");
            pelicula.setAttribute("año", año);

            Element tituloPelicula = documento.createElement("titulo");
            tituloPelicula.setTextContent(titulo);
            pelicula.appendChild(tituloPelicula);

            Element directorPelicula = documento.createElement("director");
            directorPelicula.setTextContent(director);
            pelicula.appendChild(directorPelicula);

            catalogo.appendChild(pelicula);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO)));

            System.out.println("Película añadida correctamente.");

        } catch (Exception e) {
            System.out.println("Error al añadir la película.");
        }
    }
}
