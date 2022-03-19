package Drivers;

import java.util.Scanner;

public abstract class AbstractDriver {
    /**
     * Títol del driver
     */
    private static String titolDriver;

    /**
     * Scanner de lectura
     */
    public static Scanner scanner = new Scanner(System.in);

    protected static void titolDriver(String titol) {
        titolDriver = titol;
    }

    protected static void titolTest(String titol) {
        System.out.println(String.format("----- [%s] %s -----", titolDriver, titol));
    }

    protected static void descripcioTest(String descripcio) {
        System.out.println(String.format("--- %s", descripcio));
    }

    protected static int llegeixInt(String text) {
        String valor = llegeixString(text);

        try {
            return Integer.parseInt(valor);
        }
        catch (NumberFormatException e) {
            return llegeixInt(text);
        }
    }

    protected static String llegeixString(String text) {
        System.out.println(text + " ");
        return scanner.nextLine();
    }

    protected static Boolean llegeixBool(String text) {
        String valor = llegeixString(text);

        if (valor.equals("true")) {
            return true;
        }
        else if (valor.equals("false")) {
            return false;
        }

        return llegeixBool(text);
    }

    protected static float llegeixFloat(String text) {
        String valor = llegeixString(text);

        try {
            return Float.parseFloat(valor);
        }
        catch (NumberFormatException e) {
            return llegeixFloat(text);
        }
    }

    protected static void imprimeixError(Exception error) {
        System.out.println(" * ERROR: " + error.getMessage());
    }

    protected static void imprimeixError(String error) {
        System.out.println(" * ERROR: " + error);
    }

    protected static void acabarTest() {
        System.out.println("--- Test finalitzat");
    }
}
