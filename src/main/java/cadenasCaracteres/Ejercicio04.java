package cadenasCaracteres;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Ejercicio04 {
    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * Muestra ejemplos de todas las operaciones que puedes realizar con cadenas de caracteres
         * en tu lenguaje. Algunas de esas operaciones podrían ser (busca todas las que puedas):
         * - Acceso a caracteres específicos, subcadenas, longitud, concatenación, repetición,
         *   recorrido, conversión a mayúsculas y minúsculas, reemplazo, división, unión,
         *   interpolación, verificación...
         *
         * DIFICULTAD EXTRA (opcional):
         * Crea un programa que analice dos palabras diferentes y realice comprobaciones
         * para descubrir si son:
         * - Palíndromos
         * - Anagramas
         * - Isogramas
         */
        //Vamos a poner todas las opciones de cadenas de caracteres y los metodos que nos ofrece java
        //para operar con ellas.

        String cadena = "Hola me llamo Alvaro";
        String cadena2 = "Hola me llamo Guadalupe";
        // Imprimimos de manera sencilla la cadena entera.
        System.out.println(cadena);
        //Imprimimos la longuitud de la cadena
        System.out.println(cadena.length());
        //Imprimimos algunos caractereres de la cadena de texto.
        System.out.println(cadena.charAt(1));
        //Podemos decir si una cadena contiene algún caracter en especial nos devuelva true/false
        System.out.println(cadena.contains("a"));
        //También podemos cambiar algún carácter de la cadena de texto
        System.out.println(cadena.replace('o', 'O'));
        //Si queremos concatenar algo nuevo a la cadena
        System.out.println(cadena.concat(" y vivo en madrid"));
        // Si queremos recorrer nuesta cadena podemos hacerlo mediante bucles
        for (int i = 0; i <= cadena.length() - 1; i++) {
            System.out.print(cadena.charAt(i));

        }
        System.out.println("");
        //También podemos pasar toda la cadena de texto a mayusculas
        System.out.println(cadena.toLowerCase());
        //También podemos pasar toda la cadena de texto a minusculas
        System.out.println(cadena.toUpperCase());
        //También podemos hacer verificaciones para comprobar entradas de texto
        Scanner sc = new Scanner(System.in);

        String dni = "";
        int lar = 9;
        boolean cont = true;
        while (cont) {
            System.out.println("Introduce DNI");
            dni = sc.nextLine();
            if (dni.isEmpty()) {
                System.out.println("El DNI no puede estar vacio");
            } else if (dni.length() != lar) {
                System.out.println("El DNI tiene que tener 9 digitos");

            } else if (!dni.matches(".*[A-Z]$")) {
                System.out.println("El DNI tiene que terminar con una letra");

            } else {
                System.out.println("El dni " + dni + " es correcto");
                cont = false;
            }

        }

        //Tambien podemos dar la vuelta a una palabra
        Scanner nm = new Scanner(System.in);
        String palabra1;
        String palabraAlre = "";
        System.out.println("Introduce la palabra");
        palabra1 = nm.nextLine();

        for (int i = palabra1.length() - 1; i >= 0; i--) {
            palabraAlre += palabra1.charAt(i);
        }
        System.out.println(palabraAlre);


        //Ejercicios de palindromos,anagramas e isogramas, realizamos el ejercicio

        /*Vamos a crear una estructura que nos va servir para activar cualquiera de los 3 funciones que queramos
        usar */


        /*Vamos hacer un menu tipo para interactuar con dependiendo de la accion que queramos hacer
        un palindromo , anagrama o isograma
         */
        Scanner e = new Scanner(System.in);
        boolean menu= true;
        do {
            System.out.println("+++MENU++++");
            System.out.println("1.Palindromo");
            System.out.println("2.Anagrama");
            System.out.println("3.Isograma");
            System.out.println("4.Salir");
            System.out.println("");
            System.out.println("Elige la opcion que quieras");
            String opcion = e.nextLine();
            switch (opcion.toLowerCase()){
                case "palindromo":
                    System.out.println("Introduce la palabra");
                    String palabra = e.nextLine();
                    Palindromo(palabra);
                    break;
                case "anagrama":
                    System.out.println("Introduce dos palabras");
                    String p1 = e.nextLine();
                    String p2 = e.nextLine();
                    Anagrama(p1,p2);
                    break;
                case"isograma":
                    System.out.println("Introduce la palabra");
                    String p3 = e.nextLine();
                    Isograma(p3);
                    break;
                case "salir":
                    System.out.println("Hasta pronto");
                    menu=false;
                    break;
                default:
                    System.out.println("Introduce una opcion correcta");
                    break;

            }

        }while(menu);


    }


    /*PALINDROMO: palabra que se lee igual hacia delante que hacia atras, ejemplos: radar, reconocer, oso
     *  */
    public static void Palindromo(String palabra) {
        // como vamos hacer una comparacion necesitamos una variable nueva con la que comparar
        String palindromo = "";
        //Primero tenemos que dar la vuelta a la palabra que hemos introducido
        for (int i = palabra.length() - 1; i >= .0; i--) {
            //Inicializamos el bucle y vamos guardando los caracteres en la nueva variable
            palindromo += palabra.charAt(i);
        }
        /*
        Una vez creada la variable la ponemos toda en mayusculas para evitar inconcruencias y la comparamos con la
        palabra que hemos introducido al principio
                */
        if (palindromo.toLowerCase().equals(palabra)) {
            System.out.println("Esta palabra es un palindromo");
        } else {
            System.out.println("Esta palabra NO es un palindromo");
        }
    }
    /*ANAGRAMA : son palabras que con las mismas letras de la palabra se forma otra palabra diferente por ejemplo
    roma-amor , lacteo-coleta.
     */
    public static void Anagrama(String palabra, String palabra1) {
        //Primero necesitamos saber los caracteres que tiene la palabra
        // que le indicamos por scanner y para ello necesitaremos inicializar dos variables y las pasamos
        // todas a minisculas
        String palabrax = palabra.toLowerCase();
        String palabray = palabra1.toLowerCase();

        if(palabrax.length()!=palabray.length()){
            System.out.println("No son anagramas");
        }
        char[] a = palabrax.toCharArray();
        char[] b = palabray.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);

        if(Arrays.equals(a,b)){
            System.out.println("Estas palabras son anagramas");
        }else{
            System.out.println("Estas palabras NO son anagramas");
        }
    }


    /*ISOGRAMA: es una palabra donde las letras no se repiten

     */

    public static void Isograma(String palabra){
        //Creamos la variable que va contener la palabra en minusculas
        String palabrax = palabra.toLowerCase();
        boolean mod = true;
        //Creamos una lista donde se van a ir almacenando caracteres
        HashSet<Character> letras = new HashSet<>();
        //Con el bucle for, primero la palabra la transformamos en un array y luego la recorremos buscando los
        // caracteres, vamos extrayendo los caracteres y en el momento en el que dos sean iguales retorna falso
        for(char c : palabrax.toCharArray() ){
            if(letras.contains(c)){
                mod = false;
            }
            letras.add(c);
        }
        // Hacemos un print de los resultados
        if(!mod){
            System.out.println("No es un isograma");
        }else{
            System.out.println("Es un isograma");
        }




    }
}