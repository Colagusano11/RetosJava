package herenciaYPolimorfismo;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio07 {
    /*
     * EJERCICIO:
     * Explora el concepto de herencia según tu lenguaje. Crea un ejemplo que
     * implemente una superclase Animal y un par de subclases Perro y Gato,
     * junto con una función que sirva para imprimir el sonido que emite cada Animal.
     *
     */

    //Creamos la super clase de la que van a depender el resto de clases con sus atributos

    public static class Animal {
        private String name;
        private int age;

        public Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void sonido() {
            System.out.println("Sonido animal");
        }

    }

    //Aplicamos la herencia a traves de la palabra "extends" donde va a heredar los atributos de "Animal".

    public static class Perro extends Animal {

        Perro(String name, int age) {
            super(name, age);
        }

        //Atraves del polimorfismo podemos modificar el metodo para imprimir la caracteristica de la clase nueva.

        @Override
        public void sonido() {
            System.out.println("Soy un perro");

        }
    }

    public static class Gato extends Animal {

        public Gato(String name, int age) {
            super(name, age);
        }

        @Override
        public void sonido() {
            System.out.println("Soy un gato");
        }


    }


    public static void main(String[] args) {

        //Nombramos las dos subclases que hemos creado (La CLASE es Animal)
        Gato gato = new Gato("Thor", 13);
        Perro perro = new Perro("Nala", 45);
        //Implantamos los metodos que hemos creado para cada clase.
        perro.sonido();
        gato.sonido();


        /* * DIFICULTAD EXTRA (opcional):
         * Implementa la jerarquía de una empresa de desarrollo formada por Empleados que
         * pueden ser Gerentes, Gerentes de Proyectos o Programadores.
         * Cada empleado tiene un identificador y un nombre.
         * Dependiendo de su labor, tienen propiedades y funciones exclusivas de su
         * actividad, y almacenan los empleados a su cargo.

         */


        //Creamos nuestra empresa a traves de nuestras clases

        Gerente gerente = new Gerente("23","Pepe");
        GerentesProyecto gerentesProyecto = new GerentesProyecto("12","Pascual","App Movil");
        Programador programador = new Programador("03","Alvaro","Java");
        Programador programador1 = new Programador("04","Pepe","Phyton");

        //Agregamos los diferentes empleados que hemos creado
        gerente.agregarEmpleado(gerentesProyecto);
        gerente.agregarEmpleado(programador);
        gerente.agregarEmpleado(programador1);
        //Usamos el metodo de gerente para ver lo que hace el gerente y la gente que tiene a cargo.
        gerente.cargo();
        System.out.println();
        gerentesProyecto.cargo();
        System.out.println();
        programador.cargo();
        programador1.cargo();



    }

    //Creamos la "Super clase " en este caso empleado

    public static class Empleado {
        String nif;
        String name;

        public Empleado(String nif, String name) {
            this.nif = nif;
            this.name = name;
        }

        public String getNif() {
            return nif;
        }

        public void setNif(String nif) {
            this.nif = nif;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void cargo() {
            System.out.println("Soy" +getName()+" un empleado normal");
        }
    }

    //Clase gerente que va extenderse de Empleado y es donde vamos a guardar todos los empleados.

    public static class Gerente extends Empleado {
        ArrayList<Empleado> empleadoCargo = new ArrayList<>(); //Creamos array para guardar empleados

        public Gerente(String nif, String name) {
            super(nif, name);

        }
        //Metodo para guardar los empleados
        public void agregarEmpleado(Empleado e){
            empleadoCargo.add(e);
            System.out.println("Empleado: "+ e.getName() +" añadido a la lista de empleados");

        }

        @Override
        public void cargo() {
            System.out.println("Soy "+ getName()+ " un Gerente con estos Empleados a cargo");
            int i =1 ;
            for(Empleado e : empleadoCargo){ //Mediante un for each mostramos los empleados guardados
                System.out.println(i+". "+e.getName());
              i++;
            }
        }

    }
    //Creamos la clase gerente de proyecto con el atributo particular del proyecto al que se dedica.
    public static class GerentesProyecto extends Empleado {
        String proyecto;

        public GerentesProyecto(String nif, String name,String proyecto) {
            super(nif, name);
            this.proyecto=proyecto;
        }
    //Hacemos el polimorfismo del metodo "cargo" donde imprimmos las caracteristicas de gerente de proyecto
        public void cargo() {
            System.out.println("Soy "+ getName()+" un Gerente de: "+this.proyecto);

        }
    }
        //Creamos la clase de programador con la particularidad del lenguaje que usa.
        public static class Programador extends Empleado{

        String lenguaje;
            public Programador(String nif, String name,String lenguaje) {
                super(nif, name);
                this.lenguaje=lenguaje;
            }
            //Usamos polimorfismo para imprimir las particularidades de la clase.
            public void cargo() {
                System.out.println("Soy " + getName()+ " EL PROGRAMADOR y mi lenguaje es: "+this.lenguaje);


            }


        }
    }
