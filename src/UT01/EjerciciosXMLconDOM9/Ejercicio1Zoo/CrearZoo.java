package UT01.EjerciciosXMLconDOM9.Ejercicio1Zoo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CrearZoo {

    private static final String RUTA_FICHERO = "src/UT01/EjerciciosXMLconDOM9/Ejercicio1Zoo/";
    private static final String NOMBRE_FICHERO = "zoo.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.newDocument();

            Element zoo = documento.createElement("Zoo");
            documento.appendChild(zoo);

            Element felinos = documento.createElement("Felinos");
            zoo.appendChild(felinos);

            // Leon
            Element felino1 = documento.createElement("Felino");
            felinos.appendChild(felino1);

            Element nombre1 =  documento.createElement("Nombre");
            nombre1.setTextContent("Leon");
            felino1.appendChild(nombre1);

            Element origen1 = documento.createElement("Origen");
            origen1.setTextContent("Africa");
            felino1.appendChild(origen1);

            Element peso1 = documento.createElement("Peso");
            peso1.setTextContent("155");
            felino1.appendChild(peso1);

            // Tigre
            Element felino2 = documento.createElement("Felino");
            felinos.appendChild(felino2);

            Element nombre2 =  documento.createElement("Nombre");
            nombre2.setTextContent("Tigre");
            felino2.appendChild(nombre2);

            Element origen2 = documento.createElement("Origen");
            origen2.setTextContent("Asia");
            felino2.appendChild(origen2);

            Element peso2 = documento.createElement("Peso");
            peso2.setTextContent("215");
            felino2.appendChild(peso2);

            // Lince
            Element felino3 = documento.createElement("Felino");
            felinos.appendChild(felino3);

            Element nombre3 = documento.createElement("Nombre");
            nombre3.setTextContent("Lince");
            felino3.appendChild(nombre3);

            Element origen3 = documento.createElement("Origen");
            origen3.setTextContent("Europa");
            felino3.appendChild(origen3);

            Element peso3 = documento.createElement("Peso");
            peso3.setTextContent("20");
            felino3.appendChild(peso3);

            // PanteraTigris
            Element felino4 = documento.createElement("Felino");
            felinos.appendChild(felino4);

            Element nombre4 = documento.createElement("Nombre");
            nombre4.setTextContent("PanteraTigris");
            felino4.appendChild(nombre4);

            Element origen4 = documento.createElement("Origen");
            origen4.setTextContent("America");
            felino4.appendChild(origen4);

            Element peso4 = documento.createElement("Peso");
            peso4.setTextContent("95");
            felino4.appendChild(peso4);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO)));

            System.out.println("Fichero creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear el fichero");
        }

    }
}
