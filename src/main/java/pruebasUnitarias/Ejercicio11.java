package pruebasUnitarias;

/*
 * EJERCICIO:
 * Crea una función que se encargue de sumar dos números y retornar
 * su resultado.
 * Crea un test, utilizando las herramientas de tu lenguaje, que sea
 * capaz de determinar si esa función se ejecuta correctamente.
 *
 * DIFICULTAD EXTRA (opcional):
 * Crea un diccionario con las siguientes claves y valores:
 * "name": "Tu nombre"
 * "age": "Tu edad"
 * "birth_date": "Tu fecha de nacimiento"
 * "programming_languages": ["Listado de lenguajes de programación"]
 * Crea dos test:
 * - Un primero que determine que existen todos los campos.
 * - Un segundo que determine que los datos introducidos son correctos.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;


import java.util.Arrays;
import java.util.HashMap;

//Peculiaridad de los "test" es que no se realizan en el main se realizan de manera paralela para ver si
// nuestas funciones estan bien escritas y nos adelantamos a si funcionan.

public class Ejercicio11 {
    public static void main(String[] arg) {

    }

    public static HashMap<String, Object> diccionario() {
        HashMap<String, Object> diccionario = new HashMap<String, Object>();
        diccionario.put("name", "Alvaro");
        diccionario.put("age", "34");
        diccionario.put("brith_date", "17-12-1990");
        diccionario.put("programming_lenguajes", Arrays.asList("Java", "Phyton", "C++"));

        return diccionario;
    }

    public static int sumar(int a, int b) {

        return a + b;
    }

    @Test
    public void sumarTest() {
        assertEquals(4, Ejercicio11.sumar(2, 2));
        assertEquals(10, Ejercicio11.sumar(5, 5));
        assertEquals(5, Ejercicio11.sumar(3, 2));
        assertEquals(7, Ejercicio11.sumar(4, 3));


    }

    @Test
    public void camposVacios() {

        assertFalse(diccionario().isEmpty());
        assertEquals("Alvaro", Ejercicio11.diccionario().get("name"));
        assertEquals("34", Ejercicio11.diccionario().get("age"));
        assertEquals("17-12-1990", Ejercicio11.diccionario().get("brith_date"));
        assertEquals(Arrays.asList("Java", "Phyton", "C++"), Ejercicio11.diccionario().get("programming_lenguajes"));


    }


}
