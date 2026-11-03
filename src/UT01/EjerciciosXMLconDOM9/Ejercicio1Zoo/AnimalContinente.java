package UT01.EjerciciosXMLconDOM9.Ejercicio1Zoo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class AnimalContinente {

    private static final String RUTA_FICHERO = "src/UT01/EjerciciosXMLconDOM9/Ejercicio1Zoo/";
    private static final String NOMBRE_FICHERO = "zoo.xml";

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Introduce un continente: ");
            String continente = sc.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            documento.getDocumentElement().normalize();

            NodeList felinos = documento.getElementsByTagName("Felino");

            boolean encontrado = false;

            for (int i = 0; i < felinos.getLength(); i++) {

                Element felino = (Element) felinos.item(i);

                String nombre = felino.getElementsByTagName("Nombre").item(0).getTextContent();
                String origen = felino.getElementsByTagName("Origen").item(0).getTextContent();
                String peso = felino.getElementsByTagName("Peso").item(0).getTextContent();

                if (origen.equalsIgnoreCase(continente)) {

                    System.out.println("Nombre: " + nombre);
                    System.out.println("Origen: " + origen);
                    System.out.println("Peso: " + peso + " kg");
                    System.out.println();

                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No hay animales de ese continente.");
            }

        } catch (Exception e) {
            System.out.println("Error al leer el fichero");
        }

    }
}