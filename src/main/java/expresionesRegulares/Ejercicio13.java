package expresionesRegulares;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio13 {
    /*
     * EJERCICIO:
     * Utilizando tu lenguaje, explora el concepto de expresiones regulares,
     * creando una que sea capaz de encontrar y extraer todos los números
     * de un texto.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea 3 expresiones regulares (a tu criterio) capaces de:
     * - Validar un email.
     * - Validar un número de teléfono.
     * - Validar una url.
     */

    public static void main(String[] args) {


        String texto = "Me llamo alvaro con DNI : 445311345T y vivo en la calle 76";

        Pattern txt = Pattern.compile("\\d+");
        Matcher find = txt.matcher(texto);

        while (find.find()) {
            System.out.println("Numero encontrado : " + find.group());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce email");
        String email = sc.nextLine();
        validarEmail(email);

        System.out.println("Introduce telefono");
        String telefono = sc.nextLine();
        validarTelefono(telefono);

        System.out.println("Introduce una URL");
        String url = sc.nextLine();
        validarUrl(url);


    }

    private static void validarEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)" +
                "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = emailPattern.matcher(email);
        if (matcher.matches()) {
            System.out.println("El email es valido");
        } else {
            System.out.println("El email es invalido");
            ;
        }
    }
    private static void validarTelefono(String telefono) {
        Pattern telefonoPattern = Pattern.compile("^\\d{9}$");
        Matcher matcher = telefonoPattern.matcher(telefono);
        if (matcher.matches()) {
            System.out.println("Telefono valido");
        } else {
            System.out.println("Telefono invalido");

        }
    }
    private static void validarUrl(String url) {
        Pattern urlPattern = Pattern.compile("^(https?://)?([\\w-]+\\.)+[\\w-]{2,}" +
                "(\\:[0-9]{1,5})?(/[\\w-./?%&=]*)?$");
        Matcher matcher = urlPattern.matcher(url);
        if (matcher.matches()) {
            System.out.println("URL valida");
        } else {
            System.out.println("URL INCORRECTA");
        }

    }

}


