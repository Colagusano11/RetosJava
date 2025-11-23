package ordenSuperior;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionesOrdenSuperior {
    /*
     * EJERCICIO:
     * Explora el concepto de funciones de orden superior en tu lenguaje
     * creando ejemplos simples (a tu elección) que muestren su funcionamiento.
     *
     * DIFICULTAD EXTRA (opcional):
     * Dada una lista de estudiantes (con sus nombres, fecha de nacimiento y
     * lista de calificaciones), utiliza funciones de orden superior para
     * realizar las siguientes operaciones de procesamiento y análisis:
     * - Promedio calificaciones: Obtiene una lista de estudiantes por nombre
     *   y promedio de sus calificaciones.
     * - Mejores estudiantes: Obtiene una lista con el nombre de los estudiantes
     *   que tienen calificaciones con un 9 o más de promedio.
     * - Nacimiento: Obtiene una lista de estudiantes ordenada desde el más joven.
     * - Mayor calificación: Obtiene la calificación más alta de entre todas las
     *   de los alumnos.
     * - Una calificación debe estar comprendida entre 0 y 10 (admite decimales).
     */

    public static void main(String[] args) {

        //Principales funciones de orden superior

        //1. For each : Ejecuta una acción para cada elemento de una colección.
        List<String> colores = Arrays.asList("Rojo", "Verde", "Azul", "Marron");
        colores.forEach(color -> System.out.println(color));
        //2. Map : Transforma cada elemento de una colección según la función que pasas.
        List<Integer> numeros = Arrays.asList(1, 2, 3);
        List<Integer> alCuadrado = numeros.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(alCuadrado);
        //3. Filter: Elimina elementos que no cumplen una condición (predicado).
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(pares);
        //4. Reduce : "Resume" (acumula) los elementos de una colección en un único valor aplicando una función.
        int suma = numeros.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(suma);
        //5. findFrist/findAny: Devuelven el primer/algún elemento que cumple la condición.
        Optional<Integer> par = numeros.stream()
                .filter(n -> n % 2 == 0)
                .findAny();
        par.ifPresent(System.out::println);

        //Enumero las distintas calificaciones de las diferentes asignaturas.
        EnumMap<Asignaturas, Integer> notasAlvaro = new EnumMap<>(Asignaturas.class);
        notasAlvaro.put(Asignaturas.LEN, 7);
        notasAlvaro.put(Asignaturas.ING, 8);
        notasAlvaro.put(Asignaturas.MAT, 5);
        EnumMap<Asignaturas, Integer> notasGuadalupe = new EnumMap<>(Asignaturas.class);
        notasGuadalupe.put(Asignaturas.LEN, 10);
        notasGuadalupe.put(Asignaturas.ING, 9);
        notasGuadalupe.put(Asignaturas.MAT, 9);
        EnumMap<Asignaturas, Integer> notasGonzalo = new EnumMap<>(Asignaturas.class);
        notasGonzalo.put(Asignaturas.LEN, 9);
        notasGonzalo.put(Asignaturas.ING, 2);
        notasGonzalo.put(Asignaturas.MAT, 6);
        //Implemento la clase con sus atributos.
        List<Estudiante> estudiantes = new ArrayList<>(Arrays.asList(

                new Estudiante("Alvaro Martinez", LocalDate.parse("1990-12-17"), notasAlvaro),
                new Estudiante("Guadalupe Romero", LocalDate.parse("1987-05-31"), notasGuadalupe),
                new Estudiante("Gonzalo Martinez", LocalDate.parse("2018-01-04"), notasGonzalo)

        ));

        //Promedio de sus calificaciones atra vez de un foreach.
        for (Estudiante estudiante : estudiantes) {
            double promedio = estudiante.getCalifiaciones().values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0);
            System.out.println("La nota promedio de " + estudiante.getName() + " es: " + promedio);
        }
        //Mostrar alumnos con un promedio mas alto de 9
        estudiantes.stream()
                .filter(e -> e.getCalifiaciones().values().stream()
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0) >= 9)
                .forEach(e -> System.out.println("El mejor alumn@ es: " + e.getName()));
        //Mostramos los alumnos ordenados de menor a mayor.
        List<Estudiante> orden = estudiantes.stream()
                .sorted(Comparator.comparing(Estudiante::getFechaNacimiento).reversed())
                .collect(Collectors.toList());

        orden.forEach(estudiante -> System.out.println(estudiante.getName()));

        //Calificacion mas alta de todos los alumnos.

        int notaMax = estudiantes.stream().flatMap(e -> e.getCalifiaciones()
                        .values()
                        .stream())
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        estudiantes.stream().filter(e -> e.getCalifiaciones().values().contains(notaMax))
                .forEach(e -> System.out.println("La nota maxima del curso es : " + notaMax +
                        " obtenida por " + e.getName()));
    }

    /* DIFICULTAD EXTRA (opcional):
     * Dada una lista de estudiantes (con sus nombres, fecha de nacimiento y
     * lista de calificaciones), utiliza funciones de orden superior para
     * realizar las siguientes operaciones de procesamiento y análisis:
     * - Promedio calificaciones: Obtiene una lista de estudiantes por nombre
     *   y promedio de sus calificaciones.
     * - Mejores estudiantes: Obtiene una lista con el nombre de los estudiantes
     *   que tienen calificaciones con un 9 o más de promedio.
     * - Nacimiento: Obtiene una lista de estudiantes ordenada desde el más joven.
     * - Mayor calificación: Obtiene la calificación más alta de entre todas las
     *   de los alumnos.
     * - Una calificación debe estar comprendida entre 0 y 10 (admite decimales).
     */
//Creo un enum con las diferentes asignaturas del curso
    private enum Asignaturas {
        LEN, ING, MAT
    }

    //Creo la clase principal donde se va apoyar toda la practica.
    public static class Estudiante {
        private String name;
        private LocalDate fechaNacimiento;
        private EnumMap<Asignaturas, Integer> califiaciones;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public EnumMap<Asignaturas, Integer> getCalifiaciones() {
            return califiaciones;
        }

        public void setCalifiaciones(EnumMap<Asignaturas, Integer> califiaciones) {
            this.califiaciones = califiaciones;
        }

        public Estudiante(String name,
                          LocalDate fechaNacimiento,
                          EnumMap<Asignaturas, Integer> califiaciones) {
            this.name = name;
            this.fechaNacimiento = fechaNacimiento;
            this.califiaciones = califiaciones;

        }
    }
}
