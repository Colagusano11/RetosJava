package patronesSingleton;

public class Ejercicio18 {

    /*
     * EJERCICIO:
     * Explora el patrón de diseño "singleton" y muestra cómo crearlo
     * con un ejemplo genérico.
     *
     */

    public static class Singleton {

        // 1. Instancia estática privada
        private static Singleton instancia;

        // 2. Constructor privado
        private Singleton() {
        }

        // 3. Método de acceso público y estático
        public static Singleton getInstance() {
            if (instancia == null) {
                instancia = new Singleton();
            }
            return instancia;
        }
    }


    public static void main(String[] args) {

        Usuario usuario1 = new Usuario(1, "Rockefeler_09", "Alvaro9", "amarlo8@hotmail.com");

        SesionUsuario sesionUsuario = SesionUsuario.getInstance();
        try {
            sesionUsuario.iniciarSesion(usuario1);
            System.out.println(sesionUsuario.getUsuario().toString());

            sesionUsuario.cerrarSesion();
        } catch (NullPointerException e) {
            System.out.println("Usuario no encontrado " + e.getMessage());
        }
    }
    /* DIFICULTAD EXTRA (opcional):
     * Utiliza el patrón de diseño "singleton" para representar una clase que
     * haga referencia a la sesión de usuario de una aplicación ficticia.
     * La sesión debe permitir asignar un usuario (id, username, nombre y email),
     * recuperar los datos del usuario y borrar los datos de la sesión.
     */

    //Creo la clase donde va ir la instancia Singleton.
    public static class SesionUsuario {
        private static SesionUsuario instance;

        private Usuario usuario; //le añado el atributo Usuario que he creado

        //Constructor vacio
        private SesionUsuario() {
        }

        //Metodo de acceso a la instancia
        public static SesionUsuario getInstance() {
            if (instance == null) {
                instance = new SesionUsuario();
            }
            return instance;
        }

        //Metodo para iniciar sesion con una validacion para que no contenga numeros el nombre.
        public void iniciarSesion(Usuario usuario) {

            if (usuario.name.matches(".*\\d.*")) {
                System.out.println("No se ha podido iniciar sesion");
            } else {
                this.usuario = usuario;
            }
        }

        //Metodo para devolver todos los datos del usuario
        public Usuario getUsuario() {
            return usuario;
        }

        //Metodo para borrar el usuario.
        public void cerrarSesion() {
            usuario = null;
            System.out.println("Usuario eliminado!!!");
        }

    }

    //Clase publica en la que nos vamos apoyar para realiar el ejercicio
    public static class Usuario {
        private int id;
        private String username;
        private String name;
        private String email;


        public Usuario(int id, String username, String name, String email) {
            this.id = id;
            this.username = username;
            this.name = name;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        // sobreescribo el metodo toString para cuando haga el metodo get, muestre los datos.
        @Override
        public String toString() {
            return "Usuario{" + "\n" +
                    "    \"id\": " + id + ",\n" +
                    "    \"username\": " + username + ",\n" +
                    "    \"name\": " + name + ",\n" +
                    "    \"email\": " + email + "\n" +
                    '}';
        }
    }

}
