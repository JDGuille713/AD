package UT01.EjerciciosXMLconDOM9.Ejercicio1Zoo;

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

public class ZooNuevo {

    private static final String RUTA_FICHERO = "src/UT01/EjerciciosXMLconDOM9/Ejercicio1Zoo/";
    private static final String NOMBRE_FICHERO = "zoo.xml";
    private static final String NOMBRE_FICHERO_NUEVO = "zoo2.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            documento.getDocumentElement().normalize();

            NodeList felinos = documento.getElementsByTagName("Felino");

            for (int i = 0; i < felinos.getLength(); i++) {

                Element felino = (Element) felinos.item(i);

                Element peso = (Element) felino.getElementsByTagName("Peso").item(0);

                int pesoActual = Integer.parseInt(peso.getTextContent());

                peso.setTextContent(String.valueOf(pesoActual - 5));
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO_NUEVO)));

            System.out.println("Fichero creado correctamente.");
            System.out.println("Contenido de Zoo2.xml:");

            for (int i = 0; i < felinos.getLength(); i++) {

                Element felino = (Element) felinos.item(i);

                String nombre = felino.getElementsByTagName("Nombre").item(0).getTextContent();
                String origen = felino.getElementsByTagName("Origen").item(0).getTextContent();
                String peso = felino.getElementsByTagName("Peso").item(0).getTextContent();

                System.out.println("Nombre: " + nombre);
                System.out.println("Origen: " + origen);
                System.out.println("Peso: " + peso + " kg");
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error al modificar el fichero");
        }

    }
}