package callbacks;


import java.util.Random;

public class Ejercicio16 {
    /*
     * EJERCICIO:
     * Explora el concepto de callback en tu lenguaje creando un ejemplo
     * simple (a tu elección) que muestre su funcionamiento.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea un simulador de pedidos de un restaurante utilizando callbacks.
     * Estará formado por una función que procesa pedidos.
     * Debe aceptar el nombre del plato, una callback de confirmación, una
     * de listo y otra de entrega.
     * - Debe imprimir un confirmación cuando empiece el procesamiento.
     * - Debe simular un tiempo aleatorio entre 1 a 10 segundos entre
     *   procesos.
     * - Debe invocar a cada callback siguiendo un orden de procesado.
     * - Debe notificar que el plato está listo o ha sido entregado.
     */
    //Definimos el interfaz funcional.
    interface MiCallback {
        void accionTerminada(String nombreTarea, String mensaje, long duracionMili);

    }

   public static void HacerAlgoImportante(long duracionMili,MiCallback callback) {
       System.out.println("Haciendo algo importante...");
       long time = TiempoEspera(duracionMili);
       callback.accionTerminada("Haciendo tarea", "Tarea Terminada", time);
   }

  public static long TiempoEspera(long duracionMili){
       try {
           Thread.sleep(duracionMili);
       }catch (InterruptedException e){
           System.out.println("Error en el proceso "+e.getMessage());
       }
        return duracionMili;
    }

    public static void main(String[] args) throws IllegalArgumentException {

        Ejercicio16 ejemplo = new Ejercicio16();

        ejemplo.HacerAlgoImportante(2000,(nombreTarea, mensaje, duracionMili) -> {
            System.out.println("PRIMER MENSAJE --> " + nombreTarea.toUpperCase() +" empezo despues de " + (duracionMili/1000) + " segundos");
        });


        ejemplo.HacerAlgoImportante(5000,(nombreTarea, mensaje, duracionMili) -> {
            // Segundo callback lo muestra en mayúsculas y con formato diferente
            System.out.println("SEGUNDO MENSAJE --> " + nombreTarea.toUpperCase() +
                    " terminó después de " + (duracionMili / 1000) + " segundos");
        });

        //Nombramos nuestra clase y le añadimos el nombre del restaurante
        MiRestaurante restaurante = new MiRestaurante();
        //Llamamos a nuestra funcion añadiendo el nombre del plato y haciendo las lambdas-
        restaurante.procesarPedidos("Potaje",
                plato -> System.out.println("OIDO COCINA: PROCESANDO PEDIDO DE "+ plato.toUpperCase()),
                plato -> System.out.println("PEDIDO LISTO PARA ENVIAR"),
                plato -> System.out.println("PEDIDO ENTREGADO ¡¡SATISFACTORIAMENTE!!")
                );



    }


     /* DIFICULTAD EXTRA (opcional):
            * Crea un simulador de pedidos de un restaurante utilizando callbacks.
            * Estará formado por una función que procesa pedidos.
            * Debe aceptar el nombre del plato, una callback de confirmación, una
     * de listo y otra de entrega.
            * - Debe imprimir un confirmación cuando empiece el procesamiento.
            * - Debe simular un tiempo aleatorio entre 1 a 10 segundos entre
     *   procesos.
     * - Debe invocar a cada callback siguiendo un orden de procesado.
            * - Debe notificar que el plato está listo o ha sido entregado.
     */

    //Creo la clase principal del donde vamos a realizar las funciones
    public static class MiRestaurante{

        //Nombro las 3 interfaces de los pedidos.
        interface ConfirmarPedido{
            void confirmar(String plato);
        }
        interface EntregarPedido{
            void entregar(String plato);
        }
        interface PedidoListo{
            void listo (String plato);
        }
        //Creo la funcion que va recoger el nombre del plato y va recoger los 3 callback que hemos creado
        public void procesarPedidos(String nombrePlato,ConfirmarPedido confirmar ,
                                     PedidoListo listo,EntregarPedido entregar){
            //Importo ramdom (para crear numeros aleatorios)
            Random rm = new Random();

            //Empiezo a nombrar a los calback añadiendo el nombre del plato
            confirmar.confirmar(nombrePlato);
            //Creo una variable que va guardar el resultado del numero aleatorio
            int timeListo= 1000 + rm.nextInt(9000);
            //En la funcion que creamos antes le agregamos el numero que resulte del random
            TiempoEspera(timeListo);
            listo.listo(nombrePlato);
            //En la funcion que creamos antes le agregamos el numero que resulte del random
            int timeEntrega = 1000 + rm.nextInt(9000);
            TiempoEspera(timeEntrega);
            entregar.entregar(nombrePlato);
        }




    }

}
