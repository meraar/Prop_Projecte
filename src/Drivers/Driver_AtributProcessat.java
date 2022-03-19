package Drivers;

import prop.Domini.AtributProcessat;

public class Driver_AtributProcessat extends AbstractDriver {
    private static AtributProcessat atribut;

    private static void testConstructora() {
        titolTest("Test constructora");
        descripcioTest("Crea un atribut utilitzant la constructora per defecte");

        String nom = llegeixString("Introdueix el nom de l'atribut:");
        String valor = llegeixString("Introdueix el valor de l'atribut:");

        try {
            atribut = new AtributProcessat(nom, valor);
        } catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testGetterNom() {
        titolTest("Test getter nom");
        descripcioTest("Obtenir el nom de l'atribut");

        System.out.println("Nom de l'atribut: " + atribut.getNom());

        acabarTest();
    }

    private static void testGetterValor() {
        titolTest("Test getter valor");
        descripcioTest("Obtenir el valor de l'atribut");

        System.out.println("Valor de l'atribut: " + atribut.getValor());

        acabarTest();
    }

    private static void testSetterNom() {
        titolTest("Test setter nom");
        descripcioTest("Estableix el nom de l'atribut");

        String nom = llegeixString("Introdueix el nom de l'atribut:");

        try {
            atribut.setNom(nom);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testSetterValor() {
        titolTest("Test setter valor");
        descripcioTest("Estableix el valor de l'atribut");

        String nom = llegeixString("Introdueix el valor de l'atribut:");

        try {
            atribut.setValor(nom);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testIgualtatAtributs() {
        titolTest("Test igualtat d'atributs");
        descripcioTest("Comprova si dos atributs son iguals");

        String nom1 = llegeixString("Introdueix el nom del primer atribut:");
        String valor1 = llegeixString("Introdueix el valor del primer atribut:");
        String nom2 = llegeixString("Introdueix el nom del segon atribut:");
        String valor2 = llegeixString("Introdueix el valor del segon atribut:");

        try {
            AtributProcessat atribut1 = new AtributProcessat(nom1, valor1);
            AtributProcessat atribut2 = new AtributProcessat(nom2, valor2);

            if (atribut1.equals(atribut2)) {
                System.out.println("Els atributs SI que son iguals");
            }
            else {
                System.out.println("Els atributs NO son iguals.");
            }
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static int menu() {
        System.out.println();
        System.out.println(" === Menu del driver ===");
        System.out.println("[-1] Sortir del driver");
        System.out.println("[0] Test constructora");
        System.out.println("[1] Test getter del nom de l'atribut");
        System.out.println("[2] Test getter del valor de l'atribut");
        System.out.println("[3] Test setter del nom de l'atribut");
        System.out.println("[4] Test setter del valor de l'atribut");
        System.out.println("[5] Test igualtat d'atributs");
        System.out.println();

        return llegeixInt("Especifica l'opcio a provar:");
    }

    public static void main(String[] args) {
        titolDriver("Driver AtributProcessat");

        System.out.println("Inicialitzant l'atribut amb la constructora per defecte...");
        testConstructora();

        while (true) {
            switch (menu()) {
                case -1:
                    System.exit(0);
                    break;

                case 0:
                    testConstructora();
                    break;

                case 1:
                    testGetterNom();
                    break;

                case 2:
                    testGetterValor();
                    break;

                case 3:
                    testSetterNom();
                    break;

                case 4:
                    testSetterValor();
                    break;

                case 5:
                    testIgualtatAtributs();
                    break;
                default:
                    imprimeixError("Opcio no valida");
                    break;
            }
        }
    }
}
