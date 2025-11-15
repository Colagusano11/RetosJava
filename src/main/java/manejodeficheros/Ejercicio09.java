package manejodeficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio09 {

    /*
     * IMPORTANTE: Solo debes subir el fichero de código como parte del ejercicio.
     *
     * EJERCICIO:
     * Desarrolla un programa capaz de crear un archivo que se llame como
     * tu usuario de GitHub y tenga la extensión .txt.
     * Añade varias líneas en ese fichero:
     * - Tu nombre.
     * - Edad.
     * - Lenguaje de programación favorito.
     * Imprime el contenido.
     * Borra el fichero.
     */

    public static void escribirArchivo(File archivo,String contenido) {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("El archivo ha sido creado");

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void leerArchivo(File archivo){
        try(BufferedReader bw = new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea=bw.readLine())!=null){
                System.out.println(linea);
            }

        }catch (IOException e){
            System.out.println("El archivo no existe: "+e.getMessage());
        }


    }

    public static void eliminarArchivo(File archivo){
     if(archivo.delete()) {
         System.out.println("El archivo: "+archivo.getName()+" ha sido borrado");
     }else{
         System.out.println("El archivo no existe");
     }
    }


    public static void main(String[] args) {

        File archivo = new File ("Colagusano_11.txt");
        String contenido = "Nombre: Alvaro\nEdad: 35\nLenguaje: JavaPowa";

        escribirArchivo(archivo,contenido);
        System.out.println();
        leerArchivo(archivo);
        System.out.println();
        eliminarArchivo(archivo);
        System.out.println();
        leerArchivo(archivo);

        /*   * DIFICULTAD EXTRA (opcional):
         * Desarrolla un programa de gestión de ventas que almacena sus datos en un
         * archivo .txt.
         * - Cada producto se guarda en una línea del archivo de la siguiente manera:
         *   [nombre_producto], [cantidad_vendida], [precio].
         * - Siguiendo ese formato, y mediante terminal, debe permitir añadir, consultar,
         *   actualizar, eliminar productos y salir.
         * - También debe poseer opciones para calcular la venta total y por producto.
         * - La opción salir borra el .txt.
         */


        Scanner sc = new Scanner(System.in);
        int option;



        do {
            System.out.println("\n=== GESTOR DE VENTAS ===");
            System.out.println("1. Añadir producto");
            System.out.println("2. Consultar productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Calcular venta total");
            System.out.println("6. Calcular venta por producto");
            System.out.println("7. Salir del gestor (borra archivo)");
            System.out.print("Elige una opción: ");
            option = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (option) {
                case 1:
                    agregarArchivo(sc);
                    break;
                case 2:
                    consultarProductos();
                    break;
                case 3:
                    actualizarProducto(sc);
                    break;
                case 4:
                    eliminarProducto(sc);
                    break;
                case 5:
                    calcularVenta();
                    break;
                case 6:
                    calcularVentaProducto(sc);
                    break;
                case 7:
                    salirGestor();
                    break;
                default:
                    System.out.println("Escribe una opcion correcta");

            }
        } while (option != 7);

    }

    private static final String USUARIO_VENTAS = "ventas.txt";


    private static void agregarArchivo(Scanner sc) {
        try (FileWriter fw = new FileWriter(USUARIO_VENTAS, true)) {
            System.out.println("Nombre: ");
            String name = sc.nextLine();
            System.out.println("Cantidad: ");
            int cantidad = sc.nextInt();
            System.out.println("Precio: ");
            double precio = sc.nextDouble();
            sc.nextLine();

            fw.write("Nombre: " + name + ".\nCantidad: " + cantidad + ".\nPrecio: " + precio + "€\n");
            System.out.println("Producto añadido a la lista");

        } catch (IOException e) {
            System.out.println("Error al agregar el archivo " + e.getMessage());
        }
    }
    private static void consultarProductos() {

        try (BufferedReader rw = new BufferedReader(new FileReader(USUARIO_VENTAS))) {
            System.out.println("---PRODUCTOS---");
            String linea;
            while ((linea = rw.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void actualizarProducto(Scanner sc) {
        System.out.println("Nombre del producto a actualizar");
        String name = sc.nextLine();
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(USUARIO_VENTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Nombre: " + name)) {
                    encontrado = true;
                    System.out.println("Introduce nueva cantidad");
                    int cantidad = sc.nextInt();
                    System.out.println("Introduce nuevo precio");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    br.readLine();
                    br.readLine();

                    lineas.add("Nombre: " + name + ".\nCantidad: " + cantidad + ".\nPrecio: " + precio + "€\n");
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado");
        }
        try (FileWriter fw = new FileWriter(USUARIO_VENTAS)) {
            for (String l : lineas) {
                fw.write(l + "\n");
            }
            System.out.println("Producto actualizado");

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }


    }

    private static void eliminarProducto(Scanner sc) {
        System.out.println("Introduce el nombre del producto que desea eliminar");
        String name = sc.nextLine();
        List<String> lineas = new ArrayList<>();
        boolean eliminado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(USUARIO_VENTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Nombre: " + name)) {
                    br.readLine();
                    br.readLine();
                    eliminado = true;
                } else {
                    lineas.add(linea);
                }
            }
            if (!eliminado) {
                System.out.println("El producto no se ha encontrado");
            }
            try (FileWriter fw = new FileWriter(USUARIO_VENTAS)) {
                for (String l : lineas) {
                    fw.write(l + "\n");
                }
                System.out.println("Producto eliminado");

            } catch (IOException e) {
                System.out.println("Error escribiendo archivo: " + e.getMessage());
            }


        } catch(IOException e) {
        System.out.println("Producto no encontrado " + e.getMessage());
        }
}






 private static void calcularVenta(){
    double venta= 0;
    try(BufferedReader bw = new BufferedReader(new FileReader(USUARIO_VENTAS))){
        String linea;
        while((linea=bw.readLine())!=null){
            String lineaCantidad= bw.readLine(); // p.ej. "3"
            String lineaPrecio  = bw.readLine(); // p.ej. "23.50€"
            int c = Integer.parseInt(lineaCantidad.split(":", 2)[1].trim()
                    .replace(".",""));
            double p = Double.parseDouble(
                    lineaPrecio.split(":", 2)[1]   // " 23.50€"
                            .replace("€","")    // " 23.50"
                            .trim()
                            .replace(',', '.')  // por si viene "23,50"
            );
            venta +=c*p;
        }
        System.out.println("Venta total: "+ venta+"€");


    }catch (IOException e){
        System.out.println("Error: "+ e.getMessage());
    }


 }
 private static void calcularVentaProducto(Scanner sc){
     double venta = 0;
     System.out.println("Introduce el nombre del producto");
     String name = sc.nextLine();
     List<String> lineas = new ArrayList<>();

     try(BufferedReader bw = new BufferedReader(new FileReader(USUARIO_VENTAS))){
         String linea;
         while((linea=bw.readLine())!= null){
             if(linea.startsWith("Nombre: "+name)){
                 String lineaCantidad = bw.readLine(); // siguiente línea
                 String lineaPrecio = bw.readLine();   // siguiente línea
                 int c = Integer.parseInt(lineaCantidad.split(":",2)[1].trim()
                         .replace(".",""));
                 double p = Double.parseDouble(lineaPrecio.split(":",2)[1].trim()
                         .replace("€","")
                         .trim()
                         .replace(',','.'));
                 venta+=c*p;
                 break;
             }
         }
         System.out.println("La venta del producto: "+name+" es :"+ venta+"€");
     }catch (IOException e){
         System.out.println("Error: "+e.getMessage());
     }
 }

 private static void salirGestor() {
        File file = new File(USUARIO_VENTAS);
        if (file.exists() && file.delete()) {
            System.out.println(" Archivo de ventas borrado. ¡Hasta pronto!");
        } else {
            System.out.println("No había archivo que borrar.");
        }
    }


}

