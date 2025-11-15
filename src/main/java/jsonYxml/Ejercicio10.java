package jsonYxml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio10 {

    /*
     * IMPORTANTE: Solo debes subir el fichero de código como parte del ejercicio.
     *
     * EJERCICIO:
     * Desarrolla un programa capaz de crear un archivo XML y JSON que guarde los
     * siguientes datos (haciendo uso de la sintaxis correcta en cada caso):
     * - Nombre
     * - Edad
     * - Fecha de nacimiento
     * - Listado de lenguajes de programación
     * Muestra el contenido de los archivos.
     * Borra los archivos.
     *
     * DIFICULTAD EXTRA (opcional):
     * Utilizando la lógica de creación de los archivos anteriores, crea un
     * programa capaz de leer y transformar en una misma clase custom de tu
     * lenguaje los datos almacenados en el XML y el JSON.
     * Borra los archivos.
     */

    private static final String ARCHIVO_XML = "Colagusano11_data.xml";
    private static final String ARCHIVO_JSON = "Colagusano11_data.json";

    public static void main(String[] args) {
        try {


            //Instamos la clase que hemos creado de donde vamos a coger los datos para crear los archivos

            Persona p = new Persona("Julio",
                    35,
                    "12-12-1998",
                    Arrays.asList("Java", "Django", "Angular"));

            //Crear archivo XML (Aqui imprimimos una lista de 2 personas)
            archivoXml(p, ARCHIVO_XML);
            //Crear archivo Json
            archivoJson(p, ARCHIVO_JSON);

            //imprimimos ambos archivos por consola
            imprimirArchivo(ARCHIVO_XML);
            imprimirArchivo(ARCHIVO_JSON);

            //revertimos archivo XML en clase
            Persona desdeXML = docXML(ARCHIVO_XML);
            System.out.println("Clase desde XML: "+desdeXML);

            //revertimos archico JSON en clase
            Persona desdeJSON = docJSON(ARCHIVO_JSON);
            System.out.println("Clase desde JSON: "+desdeJSON);

            //Borrado de archivos
            borrarArchivo(ARCHIVO_XML);
            borrarArchivo(ARCHIVO_JSON);


        } catch (Exception e) {

            System.out.println("Ocurrio un error: " + e.getMessage());
        }

    }

    //++++METODOS PARA CREAR ARCHIVOS+++++

    //Creamos la funcion para crear el archivo XML ( CONSEJO: ver formato de archivo XML)
    private static void archivoXml(Persona p, String ruta) throws Exception {

        try {
            //Creamos el DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Documento
            Document documento = builder.newDocument();

            //<Persona>
            Element rootElement = documento.createElement("personas");
            documento.appendChild(rootElement);


            //Nombre
            Element name = documento.createElement("nombre");
            name.appendChild(documento.createTextNode(p.name));
            rootElement.appendChild(name);

            //Edad
            Element edad = documento.createElement("edad");
            edad.appendChild(documento.createTextNode(String.valueOf(p.edad)));
            rootElement.appendChild(edad);
            //Fecha de nacimiento
            Element fecha = documento.createElement("fechaNacimiento");
            fecha.appendChild(documento.createTextNode(p.fechaNac));
            rootElement.appendChild(fecha);

            //Lenguajes
            Element lenguajes = documento.createElement("lenguajes");
            for (String lang : p.lenguajes) {
                Element langElem = documento.createElement("lenguaje");
                langElem.appendChild(documento.createTextNode(lang));
                lenguajes.appendChild(langElem);
            }
            rootElement.appendChild(lenguajes);


            //Escribir el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //Lineas genericas para ver mejor el archivo
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


            //Una vez que tenemos todas las etiquetas con los valores, creamos el archivo.

            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(ruta));
            transformer.transform(source, result);


            System.out.println("El archivo: " + ruta + " ha sido creado");


        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }


    }

    //Creamos el archivo JSON ( es importante que tengas claro el formato de un archivo JSON)

    private static void archivoJson(Persona p, String ruta) throws IOException {
        StringBuilder sb = new StringBuilder();
        //Construimos el JSon de manera manual en el formato que corresponde.
        sb.append("{\n");
        sb.append("   \"nombre\": ").append(scapeJson(p.name)).append(",\n");
        sb.append("   \"edad\": ").append(scapeJson(String.valueOf(p.edad))).append(",\n");
        sb.append("   \"fechaNacimiento\": ").append(scapeJson(p.fechaNac)).append(",\n");
        sb.append("   \"lenguajes\": [\n");
        for (int i = 0; i < p.lenguajes.size(); i++) {
            sb.append("      ").append(scapeJson(p.lenguajes.get(i)));
            if (i < p.lenguajes.size() - 1) {
                sb.append(",");
                sb.append("\n");
            }
        }
        sb.append("  ]\n");
        sb.append("}\n");

        //Una vez que tenemos todos los datos los escribimos en el archivo.
        Files.write(Paths.get(ruta), sb.toString().getBytes());
        System.out.println("El archivo: " + ruta + " ha sido creado");
    }

    //Metodo para limpiar el texto que introducimos en el archivo JSON
    private static String scapeJson(String s) {
        String scaped = s.replace("\\", "\\\"").replace("\"", "\\\"");

        return "\"" + scaped + "\"";
    }

    //Creamos metodo para imprimir las lineas de los archivos por consola
    private static void imprimirArchivo(String ruta) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(ruta));//lista donde se iran guardando las lineas
            for (String l : lines) { //creamos un foreach para ir imprimiendo linea por linea
                System.out.println(l);
            }


        } catch (Exception e) {
            System.out.println("El mensaje no se ha podido leer " + e.getMessage());

        }
    }
    //Creamos la funcion que nos va devolver los datos de un archivo XML en una clase persona.
    private static Persona docXML(String ruta) {
        try {

            //Primero creamos el archivo donde lo vamos a buscar y lo guardamos en una variable
            File xml = new File(ruta);
            //Creamos los metodos para generar el documento
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document dc = dbBuilder.parse(ruta);
            //Buena practica para normalizar el texto que hay dentro del XML
            dc.getDocumentElement().normalize();

            //Iniciamos las variables donde vamos a guardar los datos que vamos a ir obteniendo
            String name = getTagValue("nombre", dc.getDocumentElement());
            int edad = Integer.parseInt(getTagValue("edad", dc.getDocumentElement()));
            String fechaNac = getTagValue("fechaNacimiento", dc.getDocumentElement());

            List<String> lenguajes = new ArrayList<>();
            NodeList ndl = dc.getElementsByTagName("lenguaje");
            for (int i = 0; i < ndl.getLength(); i++) {
                Node node = ndl.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    lenguajes.add(node.getTextContent());

                }

            }
            //Una vez obtenidos todos los datos los guardamos en una nueva clase persona.
            return new Persona(name, edad, fechaNac, lenguajes);

        } catch (Exception e) {
            System.out.println("No se ha podido convertir " + e.getMessage());
        }
        return null;
    }
    //Creamos una funcion para coger los valores del archivo XML( lo que queremos buscar , con lo que hay en el texto)
    private static String getTagValue(String tag, Element element) {
        //A traves de "Nodelist", podemos ingresar la palabra que queremos buscar.
        NodeList nodeList = element.getElementsByTagName(tag);
        //Creamos un if , para ver si lo que estamos buscando no esta o esta "vacio"
        if (nodeList != null && nodeList.getLength() > 0) {
            //Creamos una variable para que guarde lo que estamos buscando
            Node node = nodeList.item(0);
            //Una vez que lo ha encontrado lo devuelve
            return node.getTextContent();
        }
        //Si no lo encuentra devuelve una cadena de textp vacia
        return "";
    }
    //Creamos la funcion que va retornar una "Persona" de la clase que hemos generado.
    private static Persona docJSON(String ruta){

        try{
            //Lo primero creamos una variable que va contener todo el texto del JSON
            String contenido =  new String(Files.readAllBytes(Paths.get(ruta)));
            //Creamos la variables donde se van almacenar los datos que queremos extraer.
            String name = extracDocJson(contenido,"\"nombre\"\\s*:\\s*\"([^\"]*)\"");
            //Como la edad en el JSON es como si fuera texto primero lo dectectamos
            String edadStr = extracDocJson(contenido,"\"edad\"\\s*:\\s*\"(\\d+)");
            //Segundo una vez detectado creamos la variable el numero entero
            int edad = edadStr.isEmpty() ? 0 : Integer.parseInt(edadStr);
            //Creamos la variable de la fecha de nacimiento
            String fechaNac = extracDocJson(contenido,"\"fechaNacimiento\"\\s*:\\s*\"([^\"]*)\"");

            //Como los lenguajes estan en una lista, tenemos que crear la variable de la lista
            List<String> lenguajes = new ArrayList<>();
            //Creamos la variable donde almacenamos el texto que BUSCAR
            Pattern p = Pattern.compile("\"lenguajes\"\\s*:\\s*\\[(.*?)\\]",Pattern.DOTALL);
            //Creamos la variable donde esta el texto que queremos ENCONTRAR
            Matcher m = p.matcher(contenido);
            //Usamos el metodo if para indicarle que si coinciden las dos variables anteriores
            if(m.find()){
                //Guardamos en una variable lo que coincide del primer grupo
                String inside = m.group(1);
                //Creamos la coincidencia atraves de compile para ver lo que queremos buscar
                Pattern strPat = Pattern.compile("\"([^\"]*)\"");
                //Creamos la variable para ver si lo que buscamos coincide con lo que hay en group (1)
                Matcher mm = strPat.matcher(inside);
                //Creamos un bucle que mientras coincida la parte que buscamos con algo del contenido lo añade al array.
                while (mm.find()){
                    lenguajes.add(mm.group(1));
                }
            }
            //Una vez que ya tenemos todos los datos captados en las variables los retornamos en una nueva clase.
            return new Persona(name,edad,fechaNac,lenguajes);

        }catch(IOException e) {
            System.out.println("No se ha podido leer: "+e.getMessage());
            return null;
        }
    }

    //Funcion para localizar el dato que queremos en el Json
    private static String extracDocJson(String text, String regex){
        Pattern p = Pattern.compile(regex);//Variable para buscar el texto que introduciremos
        Matcher m = p.matcher(text);//Variable para ver si coincide el texto
        if(m.find()){// creamos un if para ver si ha coincididos los dos parametros anteriores
            return m.group(1);//devolvemos el valor que ha coincidido
        }
        return "";// si no ha coincidido lo que hace es devolver una cadena de texto vacia.
    }

    //Creamos la funcion que va borrar los archivos generados a traves de insertar el nombre.
    private static void borrarArchivo(String ruta) {
        try {
            Files.deleteIfExists(Paths.get(ruta));
            System.out.println("Archivo borrado: " + ruta);
        } catch (IOException e) {
            System.out.println("No se pudo borrar " + ruta + ": " + e.getMessage());
        }
    }

    //Creamos la clase persona que es donde va girar toda la tematica de este ejercicio.
    public static class Persona {
        String name;
        int edad;
        String fechaNac;
        List<String> lenguajes;

        public Persona(String name, int edad, String fechaNac, List<String> lenguajes) {
            this.name = name;
            this.edad = edad;
            this.fechaNac = fechaNac;
            this.lenguajes = new ArrayList<>(lenguajes);
        }


        public String toString() {
            return "Persona{" +
                    "nombre='" + name + '\'' +
                    ", edad=" + edad +
                    ", fechaNacimiento='" + fechaNac + '\'' +
                    ", lenguajes=" + lenguajes +
                    '}';
        }


    }


}
