package excepciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio08 {


    /*
     * EJERCICIO:
     * Explora el concepto de manejo de excepciones según tu lenguaje.
     * Fuerza un error en tu código, captura el error, imprime dicho error
     * y evita que el programa se detenga de manera inesperada.
     * Prueba a dividir "10/0" o acceder a un índice no existente
     * de un listado para intentar provocar un error.

     */

    public static void main(String[]args){
    // Inicio las variables con las que voy a trabajar
        int a1 =0;
        int b1=10;

        //Metemos todo el codigo que es susceptible de error dentro del try.

        try {
            int resultado = b1/a1;
            System.out.println(resultado);

        }catch (ArithmeticException e ){ // nombreamos el tipo de excepcion que puede salir y personalizamos el ms
          System.out.println("No se puede dividir un numero entre 0 "+ e.getMessage());
        } finally { // con esta palabra hacemos que el codigo no se detenga y continue.
            System.out.println("La operacion se ha completado");
        }
        try {

            ArrayList<String> ciudades = new ArrayList<>(List.of(
                    "Madrid", "Valencia", "Pontevedra", "Sevilla", "Cadiz"));

            System.out.println(ciudades.get(5));
            System.out.println(ciudades.get(2));
        }catch (IndexOutOfBoundsException e){
            System.out.println("Ingrese un indice dentro de los parametros "+e.getMessage());
        }finally {
            System.out.println("El proceso sigue su curso");
        }


       /*  * DIFICULTAD EXTRA (opcional):
     * Crea una función que sea capaz de procesar parámetros, pero que también
     * pueda lanzar 3 tipos diferentes de excepciones (una de ellas tiene que
     * corresponderse con un tipo de excepción creada por nosotros de manera
                * personalizada, y debe ser lanzada de manera manual) en caso de error.
     * - Captura todas las excepciones desde el lugar donde llamas a la función.
                * - Imprime el tipo de error.
     * - Imprime si no se ha producido ningún error.
                * - Imprime que la ejecución ha finalizado.
    */
        Scanner sc = new Scanner(System.in);
        int a,b;// Inicio las dos variables

                //pido por scanner las dos variables
        System.out.println("Introduce el primer numero");
        a = sc.nextInt();
        System.out.println("Introduce el segundo numero");
        b= sc.nextInt();


        //Inicio el try donde voy a meter todas las operaciones
            try{

        if(a<0 || b<0 ){ //Solicito que ambos numeros tienen que ser mayores que 0
            throw new numeroNegativo("El numero tiene que ser positivo");
        } else if (String.valueOf(a).length()==1 || String.valueOf(b).length()<1) { //Controlo el tamaño de los numeros
            throw new dosCifras(("El numero"+ a +" debe tener 2 cifras " +
                    "y el numero"+ b+" debe tener 1"));

        } else if (b==0) {// Controlo que no se puede dividir entre 0 por que ya se el resultado.
            throw new ArithmeticException("No se puede dividir entre 0");

        } else{ // una vez que tengo todas las validaciones hechas, ya puedo realizar la operacion.
            int producto= division(a,b);
            System.out.println(producto);
        }


        }catch (ArithmeticException e){ //El numero no se puede divir entre 0
            System.out.println(e.getMessage());
        }catch (numeroNegativo e1){// Si el numero no es positivo
           System.out.println(e1.getMessage());
        }catch (dosCifras e2){//El divisor puede tener mas de dos cifras y el divisor nada mas que 1
                System.out.println(e2.getMessage());
        }finally {
            System.out.println("El programa ha finalizado");
        }





    }
    //Inicio la funcion con la que voy hacer la division.
    public static int division(int a, int b){
        int resultado= a/b;
        return resultado;
    }

    //Preparo las dos excepciones personalizadas donde voy a controlar el tipo de datos que van a entrar.

    public static class numeroNegativo extends Exception {
        public numeroNegativo(String message) {
            super(message);
        }
    }

    public static class dosCifras extends Exception{
        public dosCifras (String message){
                super(message);
            }
     }





}
