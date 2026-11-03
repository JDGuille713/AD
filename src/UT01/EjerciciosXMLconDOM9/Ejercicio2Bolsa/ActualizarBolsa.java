package UT01.EjerciciosXMLconDOM9.Ejercicio2Bolsa;

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

public class ActualizarBolsa {

    private static final String RUTA_FICHERO = "src/UT01/EjerciciosXMLconDOM9/Ejercicio2Bolsa/";
    private static final String NOMBRE_FICHERO = "bolsa.xml";
    private static final String NOMBRE_DATOS = "datos.xml";
    private static final String NOMBRE_NUEVO = "bolsaactualizada.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document bolsa = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));
            Document datos = builder.parse(new File(RUTA_FICHERO + NOMBRE_DATOS));

            bolsa.getDocumentElement().normalize();
            datos.getDocumentElement().normalize();

            NodeList operaciones = datos.getElementsByTagName("Operacion");


            for (int i = 0; i < operaciones.getLength(); i++) {

                Element operacion = (Element) operaciones.item(i);

                String id = operacion.getAttribute("id");
                String nombre = operacion.getElementsByTagName("Nombre").item(0).getTextContent();

                // Buscar la empresa
                NodeList empresas = bolsa.getElementsByTagName("Empresa");

                Element empresaEncontrada = null;

                for (int j = 0; j < empresas.getLength(); j++) {

                    Element empresa = (Element) empresas.item(j);

                    String nombreEmpresa = empresa.getElementsByTagName("Nombre").item(0).getTextContent();

                    if (nombreEmpresa.equalsIgnoreCase(nombre)) {
                        empresaEncontrada = empresa;
                        break;
                    }
                }

                // Alta
                if (id.equals("A")) {

                    if (empresaEncontrada != null) {
                        System.out.println("Error: La empresa " + nombre + " ya existe.");
                    } else {

                        Element empresa = bolsa.createElement("Empresa");
                        empresa.setAttribute("indice", operacion.getElementsByTagName("Indice").item(0).getTextContent());

                        Element nombreNuevo = bolsa.createElement("Nombre");
                        nombreNuevo.setTextContent(nombre);
                        empresa.appendChild(nombreNuevo);

                        Element simbolo = bolsa.createElement("Simbolo");
                        simbolo.setTextContent(operacion.getElementsByTagName("Simbolo").item(0).getTextContent());
                        empresa.appendChild(simbolo);

                        Element precio = bolsa.createElement("Precio");
                        precio.setTextContent(operacion.getElementsByTagName("Precio").item(0).getTextContent());
                        empresa.appendChild(precio);

                        bolsa.getDocumentElement().appendChild(empresa);

                        System.out.println("Empresa " + nombre + " añadida correctamente.");
                    }
                }

                // Baja
                else if (id.equals("B")) {

                    if (empresaEncontrada == null) {
                        System.out.println("Error: La empresa " + nombre + " no existe.");
                    } else {
                        bolsa.getDocumentElement().removeChild(empresaEncontrada);

                        System.out.println("Empresa " + nombre + " eliminada correctamente.");
                    }
                }

                // Modificacion
                else if (id.equals("M")) {

                    if (empresaEncontrada == null) {
                        System.out.println("Error: La empresa " + nombre + " no existe.");
                    } else {

                        String nuevoPrecio = operacion.getElementsByTagName("Precio").item(0).getTextContent();

                        Element precio = (Element) empresaEncontrada.getElementsByTagName("Precio").item(0);
                        precio.setTextContent(nuevoPrecio);

                        System.out.println("Precio de " + nombre + " modificado correctamente.");
                    }
                }
            }

            // Crear el nuevo fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(new DOMSource(bolsa), new StreamResult(new File(RUTA_FICHERO + NOMBRE_NUEVO)));

            System.out.println("Fichero bolsaactualizada.xml creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al actualizar el fichero.");
        }

    }
}