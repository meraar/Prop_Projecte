package Drivers;

import prop.Domini.AtributProcessat;
import prop.Domini.Registre;

import java.util.HashSet;

public class Driver_Registre extends AbstractDriver {
    private static Registre registre;

    private static void imprimeixRegistre(Registre registre) {
        if (registre.getListAtribProc().isEmpty()) {
            System.out.println("No hi ha cap atribut dins del registre.");

            return;
        }

        System.out.println("Llistat d'atributs pel registre amb identificador " + registre.getId_reg());

        for (AtributProcessat atribut : registre.getListAtribProc()) {
            System.out.println(String.format(" - %s: %s", atribut.getNom(), atribut.getValor()));
        }
    }

    private static void testConstructora() {
        titolTest("Test constructora per defecte.");
        descripcioTest("Crea un registre buit.");

        registre = new Registre();

        acabarTest();
    }

    private static void testGetterIdentificador() {
        titolTest("Test getter identificador.");
        descripcioTest("Consulta l'identificador del registre.");

        System.out.println("Identificador del registre: " + registre.getId_reg());

        acabarTest();
    }

    private static void testSetterIdentificador() {
        titolTest("Test setter identificador.");
        descripcioTest("Estableix l'identificador del registre.");

        int idRegistre = llegeixInt("Introdueix el nou identificador:");
        registre.setId_reg(idRegistre);

        acabarTest();
    }

    private static void testAfegirAtribut() {
        titolTest("Test afegir atribut.");
        descripcioTest("Afegeix un nou atribut al registre.");

        String nom = llegeixString("Introdueix el nom de l'atribut:");
        String valor = llegeixString("Introdueix el valor de l'atribut:");

        try {
            registre.afegirAtribut(new AtributProcessat(nom, valor));
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testGetterLlistatAtributs() {
        titolTest("Test getter del llistat d'atributs.");
        descripcioTest("Consulta el llistat d'atributs del registre.");

        imprimeixRegistre(registre);

        acabarTest();
    }

    private static void testModificarAtribut() {
        titolTest("Test modificar atribut.");
        descripcioTest("Modifica un atribut dins del registre.");

        String nom = llegeixString("Introdueix el nom de l'atribut a modificar:");
        String valor = llegeixString("Introdueix el nou valor de l'atribut:");

        try {
            registre.modificarAtributProcessat(nom, valor);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testEliminarAtribut() {
        titolTest("Test eliminar atribut.");
        descripcioTest("Elimina un atribut del registre.");

        String nom = llegeixString("Introdueix el nom de l'atribut a eliminar:");

        try {
            registre.eliminarAtributProcessat(nom);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testExisteixAtribut() {
        titolTest("Test existeix atribut.");
        descripcioTest("Comprova si existeix un atribut dins del conjunt.");

        String nom = llegeixString("Introdueix el nom de l'atribut a consultar:");

        if (registre.existeixAtribut(nom)) {
            System.out.println("SI que existeix un atribut amb aquest nom dins del registre.");
        }
        else {
            System.out.println("NO existeix un atribut amb aquest nom dins del registre.");
        }

        acabarTest();
    }

    private static void testConteAtributs() {
        titolTest("Test conte atributs");
        descripcioTest("Comprova si un conjunt d'atributs donat existeix dins del registre");

        int length = llegeixInt("Introdueix el nombre d'atributs que tindra el conjunt:");
        HashSet<AtributProcessat> set = new HashSet<>();

        for (int i = 0; i < length; ++i) {
            String nom = llegeixString(String.format("Introdueix el nom de l'atribut #%d:", i));
            String valor = llegeixString(String.format("Introdueix el valor de l'atribut #%d:", i));

            try {
                set.add(new AtributProcessat(nom, valor));
            }
            catch (Exception e) {
                imprimeixError(e);
                --i;
            }
        }

        if (registre.conteAtributs(set)) {
            System.out.println("El conjunt donat SI que es troba dins del registre.");
        }
        else {
            System.out.println("El conjunt donat NO es troba dins del registre.");
        }

        acabarTest();
    }

    private static int menu() {
        System.out.println();
        System.out.println(" === Menu del driver ===");
        System.out.println("[-1] Sortir del driver");
        System.out.println("[0] Test constructora");
        System.out.println("[1] Test getter identificador del registre");
        System.out.println("[2] Test setter identificador del registre");
        System.out.println("[3] Test afegir atribut processat");
        System.out.println("[4] Test getter llistat d'atributs");
        System.out.println("[5] Test modificar atribut processat");
        System.out.println("[6] Test eliminar atribut processat");
        System.out.println("[7] Test existeix atribut processat");
        System.out.println("[8] Test conte conjunt d'atributs");
        System.out.println();

        return llegeixInt("Especifica l'opcio a provar:");
    }

    public static void main(String[] args) {
        titolDriver("Driver Registre");

        System.out.println("Inicialitzant el registre amb la constructora per defecte...");
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
                    testGetterIdentificador();
                    break;

                case 2:
                    testSetterIdentificador();
                    break;

                case 3:
                    testAfegirAtribut();
                    break;

                case 4:
                    testGetterLlistatAtributs();
                    break;

                case 5:
                    testModificarAtribut();
                    break;

                case 6:
                    testEliminarAtribut();
                    break;

                case 7:
                    testExisteixAtribut();
                    break;

                case 8:
                    testConteAtributs();
                    break;

                default:
                    imprimeixError("Opcio no valida");
                    break;
            }
        }
    }
}
