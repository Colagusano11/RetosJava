package asincronia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;


public class Ejercicio12 {

    /*
     * EJERCICIO:
     * Utilizando tu lenguaje, crea un programa capaz de ejecutar de manera
     * asíncrona una función que tardará en finalizar un número concreto de
     * segundos parametrizables. También debes poder asignarle un nombre.
     * La función imprime su nombre, cuándo empieza, el tiempo que durará
     * su ejecución y cuando finaliza.
     *
     * DIFICULTAD EXTRA (opcional):
     * Utilizando el concepto de asincronía y la función anterior, crea
     * el siguiente programa que ejecuta en este orden:
     * - Una función C que dura 3 segundos.
     * - Una función B que dura 2 segundos.
     * - Una función A que dura 1 segundo.
     * - Una función D que dura 1 segundo.
     * - Las funciones C, B y A se ejecutan en paralelo.
     * - La función D comienza su ejecución cuando las 3 anteriores han finalizado.
     */

    // Con esta funcion se ejecutan las el tiempo determinado que nosotros le marcamos.
    public static void ejecutarTarea(String name, int segundos) throws InterruptedException {
        System.out.println("--EJEMPLO--");

        LocalDateTime inicio = LocalDateTime.now();
        System.out.println(name + " empieza el " + LocalDateTime.now().getDayOfMonth() + " de " +
                LocalDateTime.now().getMonth() + " de " + LocalDateTime.now().getYear() +
                " a las " + LocalDateTime.now().toLocalTime());
        Thread.sleep(segundos * 1000);
        LocalDateTime fin = LocalDateTime.now();
        long diferencia = Duration.between(inicio, fin).getSeconds();
        System.out.println("El tiempo de espera ha sido " + diferencia);
        System.out.println(name + " termino el " + LocalDateTime.now().getDayOfMonth() + " de " +
                LocalDateTime.now().getMonth() + " de " + LocalDateTime.now().getYear() +
                " a las " + LocalDateTime.now().toLocalTime());

    }
    public static void A() throws InterruptedException {
        ejecutarTarea("A", 1);
    }
    public static void B() throws InterruptedException {
        ejecutarTarea("B", 2);
    }
    public static void C() throws InterruptedException {
        ejecutarTarea("C", 3);
    }
    public static void D() throws InterruptedException {
        ejecutarTarea("D", 1);
    }

    public static void main(String[] args) throws InterruptedException {

        ejecutarTarea("Ejemplo", 5);

        /*
        A traves de estos metodos vamos marcando las tareas que tienen que realizar.
         */
        CompletableFuture<Void> futureC = CompletableFuture.runAsync(() -> {
            try {
                C();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(() -> {
            try {
                B();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> futureA = CompletableFuture.runAsync(() -> {
            try {
                A();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //Con este decimos que se ejecuten todos a la vez.
        CompletableFuture<Void> all = CompletableFuture.allOf(futureC, futureB, futureA);
        // Con este decimos que cuando todos acaben se inicie el D.
        all.thenRun(() -> {
            try {
                D();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        all.join();


    }
}
