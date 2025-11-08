package valorReferencia;

public class Ejercicio05 {

    /*
     * EJERCICIO:
     * - Muestra ejemplos de asignación de variables "por valor" y "por referencia", según
     *   su tipo de dato.
     * - Muestra ejemplos de funciones con variables que se les pasan "por valor" y
     *   "por referencia", y cómo se comportan en cada caso en el momento de ser modificadas.
     * (Entender estos conceptos es algo esencial en la gran mayoría de lenguajes)
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea dos programas que reciban dos parámetros (cada uno) definidos como
     * variables anteriormente.
     * - Cada programa recibe, en un caso, dos parámetros por valor, y en otro caso, por referencia.
     *   Estos parámetros los intercambia entre ellos en su interior, los retorna, y su retorno
     *   se asigna a dos variables diferentes a las originales. A continuación, imprime
     *   el valor de las variables originales y las nuevas, comprobando que se ha invertido
     *   su valor en las segundas.
     *   Comprueba también que se ha conservado el valor original en las primeras.
     */

    public static void main(String[]args){

        //Asignacion mediante nombramiento primitivo

        int a = 10;
        int b = a;
        b=20;
        System.out.println("Por valor :");
        System.out.println(a);
        System.out.println(b);

        System.out.println();

        //Asignacion de valores por referencia

        int[] nums = {12,34,23}; // ingresamos los datos nosotros
        int[] nums1 = nums; // adjuntamos los valores de otro array.
        nums1[0]= 99;// modificar este valor afecta a las dos
        System.out.println("Por referencia: ");
        System.out.println("Numeros :");
        Imprimir(nums);
        System.out.println("Siguientes:");
        Imprimir(nums1);

        System.out.println();

        //Modificamos un valor por funcion

        int x = 3;
        System.out.println("Este es el valor original");
        System.out.println(x);
        System.out.println("Este es el valor modificado");
        System.out.println(ModificarValor(x));


        System.out.println();

       //Modificamos listas por funcion

        int[] chars = {1,2,3,4};
        int[] chars2 = {5,6,7,8};
        System.out.println("Esta es la lista original");
        Imprimir(chars);
        ModificarReferencia(chars);
        System.out.println("Esta es la lista modificada");
        Imprimir(chars);


        //Intercambio de valores

        int val1 = 30;
        int val2 = 40;
        int[] vals = {val1,val2};
        System.out.println("Esta es la array origianl");
        Imprimir(vals);

        int [] nVals = IntercambioValores(val1,val2);
        System.out.println("Estos son los valores intercambiados");
        Imprimir(nVals);

        //Intercambio valores por referencia

        int []arr = {2,3,5,6,7};
        int []arr1 = {2,5,6,7,8,9};
        int[][] neArr = IntercambioReferencias(arr,arr1);
         System.out.println("Estos son las listas sin modificar");
         Imprimir(arr);
         Imprimir(arr1);
        System.out.println();
        System.out.println("Estos son con las referencias modificadas");
        Imprimir(neArr[0]);
        Imprimir(neArr[1]);

























    }


    //Funciones para imprimir arreglos

    public static void Imprimir(int[]nums){
     for(int n : nums){
         System.out.println(n);
     }
    }

    //Funcion para modificar un valor
    public static int ModificarValor(int a){
      return a = 20;
    }

    public static int  ModificarReferencia(int[]chars){
       return chars[0] = 32;
    }

    public static int [] IntercambioValores(int a, int b){
        int temp = a;
        a=b;
        b=temp;

        return new int[]{a,b};
    }

    public static int[][] IntercambioReferencias(int[]arr, int[]arr2){
        int []temp = arr.clone();
        arr=arr2.clone();
        arr2=temp;

        return new int [] [] {arr, arr2};

    }


}
