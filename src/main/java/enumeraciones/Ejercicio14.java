package enumeraciones;

public class Ejercicio14 {

    /*
     * EJERCICIO:
     * Empleando tu lenguaje, explora la definición del tipo de dato
     * que sirva para definir enumeraciones (Enum).
     * Crea un Enum que represente los días de la semana del lunes
     * al domingo, en ese orden. Con ese enumerado, crea una operación
     * que muestre el nombre del día de la semana dependiendo del número entero
     * utilizado (del 1 al 7).
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea un pequeño sistema de gestión del estado de pedidos.
     * Implementa una clase que defina un pedido con las siguientes características:
     * - El pedido tiene un identificador y un estado.
     * - El estado es un Enum con estos valores: PENDIENTE, ENVIADO, ENTREGADO y CANCELADO.
     * - Implementa las funciones que sirvan para modificar el estado:
     *   - Pedido enviado
     *   - Pedido cancelado
     *   - Pedido entregado
     *   (Establece una lógica, por ejemplo, no se puede entregar si no se ha enviado, etc...)
     * - Implementa una función para mostrar un texto descriptivo según el estado actual.
     * - Crea diferentes pedidos y muestra cómo se interactúa con ellos.
     */

    public static void main(String[] args) {

        for (int i = 1; i <= 7; i++) {
            mostrarDia(i);
        }
        Pedido n1 = new Pedido(01);
        Pedido n2 = new Pedido(02);
        Pedido n3 = new Pedido(03);
        Pedido n4 = new Pedido(04);


        n1.enviarPedido();
        n2.pedidoEntregado();
        n3.cancelarPedido();
        System.out.println(n4.getReferencia());
        System.out.println("El pedido esta: "+n4.getEstado());
    }

    //Creo la clase pedido con sus dos atributos principales
    static class Pedido {
        private int id;
        private Estado estado;
        //Creo el constructor de la clase e indico el pedido como "pendiente".
        public Pedido(int id) {
            this.id = id;
            this.estado = Estado.PENDIENTE;
        }
        //getters & setters
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public Estado getEstado() {
            return estado;
        }
        public void setEstado(Estado estado) {
            this.estado = estado;
        }
        //Metodo para enviar el pedido, aplicando un cambio de estado de pedido
        public void enviarPedido() {
            boolean pedido = false;
            if (estado == Estado.ENVIADO) {
                System.out.println("El pedido " + id + " ya se ha enviado");

            } else if (estado == Estado.ENTREGADO) {
                System.out.println("El pedido " + id + " ya esta entregado");

            } else if (estado == Estado.PENDIENTE) {
                estado = Estado.ENVIADO;
                pedido = true;
                System.out.println("El pedido " + id + " se ha enviado");
            }
        }
        //Metodo para cancelar el pedido, aplicando un cambio de estado de pedido
        public void cancelarPedido() {
            if (estado == Estado.ENVIADO) {
                estado = Estado.CANCELADO;
                System.out.println("El pedido " + id + " se ha CANCECLADO");
            } else if (estado == Estado.ENTREGADO) {
                System.out.println("El pedido " + id + " ya esta entregado no se puede cancelar");
            } else if (estado == Estado.PENDIENTE) {
                estado = Estado.CANCELADO;
                System.out.println("El pedido " + id + "estaba pendiente y se ha cancelado");
            }
        }
        //Metodo para entregar el pedido, aplicando un cambio de estado de pedido
        public void pedidoEntregado() {
            if (estado == Estado.ENVIADO) {
                estado = Estado.ENTREGADO;
                System.out.println("El pedido " + id + " se ha ENTREGADO A SU DESTINATARIO");
            } else if (estado == Estado.ENTREGADO) {
                System.out.println("El pedido " + id + " no se puede volver a entregar");
            } else if (estado == Estado.PENDIENTE) {
                System.out.println("El pedido " + id + "estaba pendiente de envio");
            }
        }
        //Creo un metodo que devuelve un String para ver a traves del estado del pedido me devuelva un
        // mensaje a modo informacion de como esta el pedido en ese momento
        public String getReferencia(){

            switch (estado){
                case ENVIADO ->{ return "El pedido se ha enviado";}
                case ENTREGADO -> {return "El pedido esta entreago";}
                case CANCELADO -> {return "El pedido se ha cancelado";}
                case PENDIENTE -> {return "El pedido esta pendiente";}
                default -> {return "Estado DESCONOCIDO";}
            }
        }
    }
    //Creo la enum. donde voy a poner los diferentes estados en los que puede estar el pedido.
    private enum Estado {
        PENDIENTE, ENVIADO, ENTREGADO, CANCELADO

    }
    private enum DiaSemana {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }
    public static void mostrarDia(int numero) {
        if (numero < 1 || numero > 7) {
            System.out.println("Numero invalido");
            return;
        }
        DiaSemana dia = DiaSemana.values()[numero - 1];
        System.out.println("Dia: " + numero + ": " + dia);
    }
}
