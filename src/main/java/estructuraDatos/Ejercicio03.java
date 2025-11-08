package estructuraDatos;

import java.util.*;

public class Ejercicio03 {
    /*
     * EJERCICIO:
     * - Muestra ejemplos de creación de todas las estructuras soportadas por defecto
     *   en tu lenguaje.
     * - Utiliza operaciones de inserción, borrado, actualización y ordenación.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea una agenda de contactos por terminal.
     * - Debes implementar funcionalidades de búsqueda, inserción, actualización
     *   y eliminación de contactos.
     * - Cada contacto debe tener un nombre y un número de teléfono.
     * - El programa solicita en primer lugar cuál es la operación que se quiere realizar,
     *   y a continuación los datos necesarios para llevarla a cabo.
     * - El programa no puede dejar introducir números de teléfono no numéricos y con más
     *   de 11 dígitos (o el número de dígitos que quieras).
     * - También se debe proponer una operación de finalización del programa.
     */

    public static void main(String[] args) {

        //Arrays de numeros enteros añadiendo los datos mediante variables

        int[] numeros = new int[5];

        numeros[0] = 1;
        numeros[1] = 2;
        numeros[2] = 3;
        numeros[3] = 4;
        numeros[4] = 5;

        System.out.println(numeros);


        //Arrays de numeros enteros añandiendo los datos en la inicializacion del array, habria que mostrarlo atraves
        //de un bucle for.

        int[] enteros = {23, 34, 45, 12};
        for (int num : enteros) {
            System.out.print(num + " ");
        }

        System.out.println();

        /*
       ArrayList de tipo dinamica se pueden agregar, modificar y eliminar datos mediante los metodos propios de
       la array
         */

        ArrayList<String> nombres = new ArrayList<>();

        nombres.add("Pepe");
        nombres.add("Pedro");
        nombres.add("Josefina");
        nombres.add("Guadalupe");
        nombres.remove("Pepe");


        System.out.println(nombres);


        //Linkelist

        LinkedList<String> nombres2 = new LinkedList<>();

        nombres2.add("Sergio");
        nombres2.add("Miguel");
        nombres2.add("Manuel");

        System.out.println(nombres2);

        //Hashmap

        HashMap<String, Integer> listaTelefonica = new HashMap<>();

        listaTelefonica.put("Alvaro", 621243458);
        listaTelefonica.put("Guadalupe", 62838430);


        System.out.println(listaTelefonica);// toda la lista
        System.out.println(listaTelefonica.get("Alvaro"));

        for (String name : listaTelefonica.keySet()) {
            System.out.println(name + " --> " + listaTelefonica.get(name));

        }

        //Agenda de contactos

        HashMap<String, String> contactos = new HashMap<>();

        //Sacamos el escanner a peticion para agregar los contactos

        Scanner entrada = new Scanner(System.in);

        boolean Menu=true;

        while (Menu){

            System.out.println("""
                     /*/*/*/*/ AGENDA TELEFÓNICA /*/*/*/
                    
                     1.Buscar contacto
                     2.Añadir contacto
                     3.Modificar contacto
                     4.Borrar contacto
                     5.Ver todos
                     6.Salir
                    
                    """);
            String elegirMenu = entrada.nextLine();

            switch (elegirMenu.toLowerCase()) {
                case "buscar":
                    BuscarContacto(contactos);
                    break;
                case "añadir":
                    AnadirContacto(contactos, entrada);
                    break;
                case "modificar":
                    ModificarContacto(contactos, entrada);
                    break;
                case "borrar":
                    BorrarContacto(contactos, entrada);
                    break;
                case "ver":
                    VerContactos(contactos);
                    break;
                case "salir":
                    System.out.println("NO ME ENTERO DE NA COMPADRE XD");
                    Menu = false;
                    break;

                default:
                    System.out.println("Indica un numero correcto");


            }


        }


    }

    public static void BuscarContacto(Map<String, String> contactos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre del contacto");
        String nombreBuscado = entrada.nextLine();

        if (contactos.containsKey(nombreBuscado)) {
            System.out.println(nombreBuscado + ": " + contactos.get(nombreBuscado));

        } else {
            System.out.println("El nombre: " + nombreBuscado + " no se encuentra en la agenda");

        }
    }

    public static void AnadirContacto(Map<String,String> contactos, Scanner entrada) {

        System.out.println("Indica el nombre que desea añadir a la lista");
        String nombreAnadido = entrada.nextLine();
        System.out.println("Indica el numero que desea añadir a la lista");
        String numeroAnadido = entrada.nextLine();
        //Hago un bucle while para que mientras los parametros sean erroenos me los vuelvan a pedir.

        while (numeroAnadido.length()<9 || contactos.containsValue(numeroAnadido)){

            if (numeroAnadido.length() < 9) {
                System.out.println("El numero debe tener 9 cifras");
                numeroAnadido = entrada.nextLine();
            }
            if (contactos.containsValue(numeroAnadido)) {
                System.out.println("El numero ya se encuentra en la agenda");
                numeroAnadido = entrada.nextLine();

            }

        }
        contactos.put(nombreAnadido, numeroAnadido);
        System.out.println("El contacto "+ nombreAnadido + "ha sido añadido");


    }


    public static void ModificarContacto(Map<String, String> contactos, Scanner entrada) {
        String name;
        System.out.println("Indica el nombre que desea modificar");
        name = entrada.nextLine();

        if (!contactos.containsValue(name)) {
            System.out.println("El nombre"+name+" no se encuentra en la aganda");
            name = entrada.nextLine();
        } else {
            contactos.remove(name);
        }

        AnadirContacto(contactos,entrada);


    }

    public static void BorrarContacto(Map<String, String> contactos, Scanner entrada){
        String name;
        System.out.println("Indica el nombre que desea borrar");
        name = entrada.nextLine();

        if(!contactos.containsKey(name)){
            System.out.println("El nombre no se encuentra en la agenda");
            name= entrada.nextLine();
        }else{
            contactos.remove(name);
        }

        System.out.println("El contacto "+ name+ "ha sido borrado con exito");


    }


    public static void VerContactos (Map<String,String>contactos){
        for (Map.Entry<String, String> entry : contactos.entrySet()) {
            System.out.println("Nombre: " + entry.getValue() + ", Número: " + entry.getKey());
        }


    }








}
