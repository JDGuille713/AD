package UT01.EjerciciosXMLconDOM9.Ejercicio3Alumnos;

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

public class CalcularNotas {

    private static final String RUTA_FICHERO = "src/UT01/EjerciciosXMLconDOM9/Ejercicio3Alumnos/";
    private static final String NOMBRE_FICHERO = "alumnos.xml";
    private static final String NOMBRE_NUEVO = "notas.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            documento.getDocumentElement().normalize();

            String modulo = documento.getElementsByTagName("modulo").item(0).getTextContent();

            NodeList alumnos = documento.getElementsByTagName("alumno");

            Document documentoNuevo = builder.newDocument();

            Element notas = documentoNuevo.createElement("Notas");
            documentoNuevo.appendChild(notas);

            notas.setAttribute("modulo", modulo);

            double sumaNotas = 0;

            for (int i = 0; i < alumnos.getLength(); i++) {

                Element alumno = (Element) alumnos.item(i);

                String dni = alumno.getAttribute("dni");

                double practica1 = Double.parseDouble(alumno.getElementsByTagName("practica1").item(0).getTextContent());
                double practica2 = Double.parseDouble(alumno.getElementsByTagName("practica2").item(0).getTextContent());
                double examen = Double.parseDouble(alumno.getElementsByTagName("examen").item(0).getTextContent());
                double practicaFinal = Double.parseDouble(alumno.getElementsByTagName("final").item(0).getTextContent());

                double practicas = (practica1 + practica2) / 2;

                double notaMedia = practicas * 0.3 + examen * 0.4 + practicaFinal * 0.3;

                sumaNotas += notaMedia;

                System.out.println(dni + " = " + String.format(java.util.Locale.US, "%.1f", notaMedia));

                Element alumnoNuevo = documentoNuevo.createElement("Alumno");
                alumnoNuevo.setAttribute("dni", dni);

                Element nota = documentoNuevo.createElement("NotaMedia");
                nota.setTextContent(String.valueOf(notaMedia));

                alumnoNuevo.appendChild(nota);
                notas.appendChild(alumnoNuevo);
            }

            double mediaTotal = sumaNotas / alumnos.getLength();

            System.out.println("Media total del modulo " + modulo + " = " + String.format(java.util.Locale.US, "%.2f", mediaTotal));

            Element media = documentoNuevo.createElement("MediaTotal");
            media.setTextContent(String.valueOf(mediaTotal));

            notas.appendChild(media);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(new DOMSource(documentoNuevo), new StreamResult(new File(RUTA_FICHERO + NOMBRE_NUEVO)));

            System.out.println("Fichero notas.xml creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al procesar los ficheros.");
        }

    }
}