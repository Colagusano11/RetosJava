package manejodeficheros;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio09_1 {


    private static String fichero = "notas.txt";


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

      //  crearFichero(fichero);
        agregarDatos(sc);

    }
    //Creamos la funcion que crea el fichero
    public static void crearFichero(String fichero) {
        try {
            File file = new File(fichero);
            if (file.createNewFile()) {
                System.out.println("Se ha creado el fichero");
            } else {
                System.out.println("No se ha creado el fichero");
            }

        } catch (IOException ex) {
            System.out.println("No se ha podido crear el fichero " + ex.getStackTrace());
        }

    }

    public static void agregarDatos(Scanner sc){
       try (FileWriter fw = new FileWriter("notas.txt", true)){

           System.out.println("Que quieres añadir al fichero");
           String contenido = sc.nextLine();
           fw.write(contenido+"\n");
           System.out.println("Se ha añadido el contenido al fichero");

       }catch (IOException e){
           System.out.println("No se ha podido añadir el contenido "+ e.getMessage());
       }
    }





}
