package clases;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Ejercicio06 {

    /*
     * EJERCICIO:
     * Explora el concepto de clase y crea un ejemplo que implemente un inicializador,
     * atributos y una función que los imprima (teniendo en cuenta las posibilidades
     * de tu lenguaje).
     * Una vez implementada, créala, establece sus parámetros, modifícalos e imprímelos
     * utilizando su función.
     */


    //Inicianilizamos la clase nombrandola y poniendo los atributos que queremos.

    static class Coche{

        //Si marcas las variables como "private" solo se pueden variar a traves del metodo set en el caso de
        // que se lo hagas , si no se hace el metodo no se podra modificar fuera de la clase.

        private String marca;
        private String modelo;
        private String age;

        //Constructo vacio
        public Coche(){

        }
        //Constructor
        public Coche(String marca, String modelo, String age){
            this.marca=marca;
            this.modelo=modelo;
            this.age=age;
        }

        //Acesos de informacion (getters and setters)

        public void setMarca(String marca){ //En los setters el metodo es void por que no devuelve nada
            this.marca=marca;
        }
        public String getMarca(){ //En los getters el metodo es un String por que nos va devolver un String
            return marca;
        }
        public void setModelo(String modelo){
            this.modelo=modelo;
        }
        public String getModelo(){
            return modelo;
        }
        public void setAge(String age){
            this.age=age;
        }
        public String getAge(){
            return age;
        }
        //Creamos un metodo para mostrar toda la informacion de la clase
        public String verInfo(){
            return "Este es el coche que has elegido "+ "\n"+
                    "Marca: "+ marca + " Modelo: "+ modelo+"\n"+
                    "Año: "+age;

        }

    }

    //Ejercicio de dificultad extra: * DIFICULTAD EXTRA (opcional):
    //     * Implementa dos clases que representen las estructuras de Pila y Cola (estudiadas
    //     * en el ejercicio número 7 de la ruta de estudio)
    //     * - Deben poder inicializarse y disponer de operaciones para añadir, eliminar,
    //     *   retornar el número de elementos e imprimir todo su contenido.



    static class Pila{

        // Es una buena practica "privatizar" nuestros atributos de una clase por
        // si en algun momento queremos que no se puedan modificar
       private Stack<String> pila;

        public Pila(){
            this.pila = new Stack<>();
        }
        //Push es el metodo con el que añadimos elementos a la pila
        public void agregarElemento(String elemento){
            pila.push(elemento);
        }
        //Pop es el metodo para eliminar un elemento en las pilas
        public void eliminarElemento(){
            pila.pop();
        }
        //Metodo con el que imprimimos todos los datos de la pila
        public void verInfo(){
            System.out.println("Estos son los elementos");
            for(String e : pila){
                System.out.println(e);
            }
        }
        //Metodo con el que imprimos el tamaño de la pila

        public void tamanoPila(){
            System.out.println("Este es el tamaño de la pila: "+ pila.size());
        }

    }

    static class Cola{

      //Inicializamos el atributo de nuestra clase
        private Queue<Integer> cola;

        public Cola(){
            this.cola=new LinkedList<>();
        }

        //Metodo para agregar el elemento
        public void agregarElemento(Integer elemeto){
            cola.add(elemeto);
        }

        //Metodo para eliminar elemento
        public void eliminarElemento(){
            cola.poll();
        }
        //Metodo para mostrar elementos
        public void verInfo(){
            System.out.println("Estos son los elementos");
            for(Integer e : cola){
                System.out.println(e);
            }
        }

        //Metodo para ver el tamaño
        public void tamanoCola(){
            System.out.println("Este es el tamaño de tu lista: "+ cola.size());
        }


    }



    public static void main(String[]args){

        //Implementamos la clase

        Coche coche01 = new Coche ("Seat", "Toledo", "1995");
        Coche coche02 = new Coche ("Audi","A4","2003");

        //Mostramos el coche por la consola a tra ves del metodo

        System.out.println(coche01.verInfo());
        System.out.println(coche02.verInfo());

        //Modificamos valores de la clase con nuestros metodos
        coche01.setAge("2003");
        coche02.setModelo("A7");
        System.out.println();
        //Imprimimos nuestra clase con los valores modificados
        System.out.println(coche01.verInfo());
        System.out.println(coche02.verInfo());
        System.out.println();
        //Atraves de nuestro metodo get podemos obtener el valor del atributo que nombremos
        System.out.println("Este es el modelo del primer coche: "+coche01.getModelo());
        System.out.println("Este es el año del segundo coche: "+coche02.getAge());



        System.out.println();
        System.out.println();

        //EJERCICIO EXTRA


        //PILA (principal caracteristica es que el ultimo en entrar es el primero en salir)
        Pila pila = new Pila();
        //Agregamos elementos a cada una atraves de los metodos que hemos creado
        pila.agregarElemento("Hola");
        pila.agregarElemento("Adios");
        pila.agregarElemento("Saludo");
        //Imprimimos la lista con los valores añadidos
        pila.verInfo();
        System.out.println();
        //Eliminamos con nuestro metodo RECUERDA que en la pila se elimina el ultimo elemento que añadiste
        pila.eliminarElemento();
        //Imprimimos la lista modificada
        pila.verInfo();
        System.out.println();


        //COLA (principal caracteristica es el que el primero en entrar es el primero en salir )
        Cola cola = new Cola();

        //Agregamos los elementos
        cola.agregarElemento(12);
        cola.agregarElemento(45);
        cola.agregarElemento(23);
        //Imprimimos lista inicial
        cola.verInfo();
        System.out.println();
        //Eliminamos elemento RECUERDA se borra el primer elemento en entrar en la lista
        cola.eliminarElemento();
        //Mostramos la lista modificada
        cola.verInfo();

    }
}
