package peticionesHTTP;

import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;


public class Ejercicio15 {
    /*
     * EJERCICIO:
     * Utilizando un mecanismo de peticiones HTTP de tu lenguaje, realiza
     * una petición a la web que tú quieras, verifica que dicha petición
     * fue exitosa y muestra por consola el contenido de la web.
     *
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/todos/1";

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = cliente.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println("Estado: " + response.statusCode());

        if (response.statusCode() == 200) {
            System.out.println("Contenido:");
            System.out.println(response.body());
        } else {
            System.out.println("Petición no exitosa");
        }


        /* DIFICULTAD EXTRA (opcional):
         * Utilizando la PokéAPI (https://pokeapi.co), crea un programa por
         * terminal al que le puedas solicitar información de un Pokémon concreto
         * utilizando su nombre o número.
         * - Muestra el nombre, id, peso, altura y tipo(s) del Pokémon
         * - Controla posibles errores
         */

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Intoduce el nombre del pokemon");
            String nombrePokemon = sc.nextLine().toLowerCase();
            String url1 = "https://pokeapi.co/api/v2/pokemon/" + nombrePokemon;

            HttpClient cliente1 = HttpClient.newHttpClient();
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create(url1))
                    .GET()
                    .build();
            HttpResponse<String> response1 = cliente1.send(request1,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("Estado: " + response1.statusCode());
            System.out.println("Contenido");
            //Metemos el resultado de la peticion en una cadena de texto
            String jsonPokemon = response1.body();
            //Llamamos a la clase con la que vamos a extraer la info de un Json
            Gson gson = new Gson();
            //Nombramos la clase que hemos creado previamente con los atributos que queremos extraer.
            Pokemon pokemon = gson.fromJson(jsonPokemon, Pokemon.class);
            //Imprimimos el resultado nombrando la variable que hemos creado
            System.out.println("ID: " + pokemon.getId());
            System.out.println("Nombre: " + pokemon.getName());
            System.out.println("Altura: " + pokemon.getHeight());
            System.out.println("Peso: " + pokemon.getWeight());
            //Como los tipos a veces son varios tenemos que crear una lista que guarde los tipos para imprimirlos
            List<String> tipos = new ArrayList<>();
            //Con un for each recorremos los tipos de la clase y con add los añadimos a la lista nueva.
            for (TypeSlot s : pokemon.getTypes()) {
                tipos.add(s.getType().getName());// nombramos el atb de la primera y le añadimos el de la segunda
            }
            System.out.println("Tipo");
            //Con nuesyta lista llena de tipos , lo unico que tenemos que hacer es imprimirlos.
            for (String ti : tipos) {
                System.out.println("-" + ti);
            }

        } catch (Exception e) {
            System.out.println("Pokemon no encontrado ");

        }

    }

    // En los tipos de pokemon vienen separados por dos listas la primera define el tipo
    public static class TypeSlot {
        private int slot;
        private PokemonType type;

        public int getSlot() {
            return slot;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }

        public PokemonType getType() {
            return type;
        }

        public void setType(PokemonType type) {
            this.type = type;
        }
    }

    //La segunda nombra el tipo de pokemon.
    public static class PokemonType {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    //Clase principal en la cual vamos a trabajar
    public static class Pokemon {
        private String name;
        private int id;
        private double weight;
        private double height;
        List<TypeSlot> types;// aqui añadimos la lista con su getter y setter

        public Pokemon(String name, int id, double weight, double height, List<TypeSlot> types) {
            this.name = name;
            this.id = id;
            this.weight = weight;
            this.height = height;
            this.types = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public List<TypeSlot> getTypes() {
            return types;
        }

        public void setTypes(List<TypeSlot> types) {
            this.types = types;
        }
    }

}

