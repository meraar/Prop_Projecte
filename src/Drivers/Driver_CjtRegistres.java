package Drivers;

import prop.Domini.AtributProcessat;
import prop.Domini.CjtRegistres;
import prop.Domini.Registre;

import java.util.*;

public class Driver_CjtRegistres extends AbstractDriver {
    private static CjtRegistres conjunt;

    private static void imprimeixRegistre(Registre registre) {
        System.out.println(" - Registre " + registre.getId_reg());

        for (AtributProcessat atribut : registre.getListAtribProc()) {
            System.out.println(String.format("   - %s: %s", atribut.getNom(), atribut.getValor()));
        }
    }

    private static Registre crearRegistre() {
        int idRegistre = llegeixInt("Introdueix l'identificador del registre:");

        return crearRegistre(idRegistre);
    }

    private static Registre crearRegistre(int idRegistre) {
        int length = llegeixInt("Introdueix el nombre d'atributs que conte el registre:");

        Registre registre = new Registre();
        registre.setId_reg(idRegistre);

        for (int i = 0; i < length; ++i) {
            String nom = llegeixString(String.format("Introdueix el nom de l'atribut %d:", i + 1));
            String valor = llegeixString(String.format("Introdueix el valor de l'atribut %d:", i + 1));

            try {
                AtributProcessat atribut = new AtributProcessat(nom, valor);
                registre.afegirAtribut(atribut);
            } catch (Exception e) {
                imprimeixError(e);
                --i;
            }
        }

        return registre;
    }

    private static void testConstructora() {
        titolTest("Test constructora");
        descripcioTest("Crea un conjunt utilitzant la constructora per defecte.");

        conjunt = new CjtRegistres();
        acabarTest();
    }

    private static void testGetLlistatRegistres() {
        titolTest("Test getter del llistat de registres");
        descripcioTest("Retorna el llistat de registres i els imprimeix per pantalla.");

        if (conjunt.getLlistReg().isEmpty()) {
            System.out.println("No hi ha cap registre dins del conjunt.");

            return;
        }

        for (Registre reg : conjunt.getLlistReg()) {
            imprimeixRegistre(reg);
        }

        acabarTest();
    }

    private static void testSetLlistatRegistres() {
        titolTest("Test setter del llistat de registres");
        descripcioTest("Estableix el llistat de registres donat un nou arraylist de registres.");

        int length = llegeixInt("Introdueix la quantitat de registres del nou llistat:");
        ArrayList<Registre> llistat = new ArrayList<Registre>();

        for (int i = 0; i < length; ++i) {
            System.out.println(String.format("Introduccio de dades pel registre #%d:", i));
            llistat.add(crearRegistre());
            System.out.println();
        }

        conjunt.setLlistReg(llistat);

        acabarTest();
    }

    private static void testBorrarRegistre() {
        titolTest("Test eliminar registre del llistat");
        descripcioTest("Elimina un registre del llistat donat el seu identificador.");

        int idRegistre = llegeixInt("Introdueix l'identificador del registre a eliminar:");

        try {
            conjunt.borrarRegistre(idRegistre);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testUpdateRegistre() {
        titolTest("Test actualitzar registre dins del conjunt");
        descripcioTest("Actualitza un registre del conjunt donat un identificador i un nou registre.");

        int idRegistre = llegeixInt("Introdueix l'identificador del registre a actualitzar:");

        System.out.println("Introdueix les dades del nou registre:");

        Registre registre = crearRegistre(idRegistre);

        try {
            conjunt.modificarRegistre(idRegistre, registre);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testAfegirRegistre() {
        titolTest("Test afegir registre dins del conjunt");
        descripcioTest("Afegeix un registre al conjunt");

        System.out.println("Introdueix les dades del nou registre:");

        Registre registre = crearRegistre();

        try {
            conjunt.afegirRegistre(registre);
        } catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testConsultarRegistre() {
        titolTest("Test consultar registre");
        descripcioTest("Consulta un registre del conjunt");

        int idRegistre = llegeixInt("Introdueix l'identificador del registre:");

        try {
            Registre registre = conjunt.consultarRegistre(idRegistre);

            imprimeixRegistre(registre);
        } catch (Exception e) {
            imprimeixError(e);
        }
    }

    private static void testGetAtributsUnics() {
        titolTest("Test get atributs unics");
        descripcioTest("Obte un conjunt amb els atributs unics que hi ha dins del conjunt de registres ordenats lexicograficament");

        if (conjunt.getLlistReg().isEmpty()) {
            System.out.println("El conjunt esta buit.");

            return;
        }

        System.out.println("El llistat d'AtributsProcessats unics dins del conjunt es:");

        for (AtributProcessat atribut : conjunt.getAtributsUnics()) {
            System.out.println(String.format(" - %s: %s", atribut.getNom(), atribut.getValor()));
        }

        acabarTest();
    }

    private static void testOrdena() {
        titolTest("Test ordenacio");
        descripcioTest("Ordena un conjunt d'atributs en ordre lexicografic i sense repetits");

        System.out.println("S'ordenaran tots els atributs de dins del CjtRegistres:");

        if (conjunt.getLlistReg().isEmpty()) {
            System.out.println("El conjunt esta buit.");

            return;
        }

        LinkedHashSet<AtributProcessat> atributs = new LinkedHashSet<>();

        for (Registre registre : conjunt.getLlistReg()) {
            atributs.addAll(registre.getListAtribProc());
        }

        atributs = CjtRegistres.ordena(atributs);

        for (AtributProcessat atribut : atributs) {
            System.out.println(String.format(" - %s: %s", atribut.getNom(), atribut.getValor()));
        }

        acabarTest();
    }

    private static void testCalculaSuport() {
        titolTest("Test calcul de suport");
        descripcioTest("Calcula el suport del conjunt d'atributs donat dins del conjunt de registres");

        System.out.println("Primer es demanara l'entrada del conjunt d'Atributs del qual es vol comprovar el suport");
        int length = llegeixInt("Introdueix el nombre d'atributs que tindra el conjunt:");

        ArrayList<LinkedHashSet<AtributProcessat>> atributs = new ArrayList<>();

        for (int i = 0; i < length; ++i) {
            LinkedHashSet<AtributProcessat> set = new LinkedHashSet<>();

            String nom = llegeixString(String.format("Introdueix el nom de l'atribut #%d:", i));
            String valor = llegeixString(String.format("Introdueix el valor de l'atribut #%d:", i));

            try {
                set.add(new AtributProcessat(nom, valor));
                atributs.add(set);
            }
            catch (Exception e) {
                imprimeixError(e);
                --i;
            }
        }

        LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> map = conjunt.calculaSuport(atributs);

        System.out.println();
        System.out.println("Llistat de suports pels atributs especificats:");

        for (Map.Entry<LinkedHashSet<AtributProcessat>, Integer> entry : map.entrySet()) {
            HashSet<AtributProcessat> atrs = entry.getKey();

            for (AtributProcessat atribut : atrs) {
                System.out.println(String.format(" - %s=%s: %d", atribut.getNom(), atribut.getValor(), entry.getValue()));
            }
        }

        acabarTest();
    }

    private static int menu() {
        System.out.println();
        System.out.println(" === Menu del driver ===");
        System.out.println("[-1] Sortir del driver");
        System.out.println("[0] Test constructora");
        System.out.println("[1] Test getter llistat de registres");
        System.out.println("[2] Test setter llistat de registres");
        System.out.println("[3] Test eliminar registre del conjunt");
        System.out.println("[4] Test actualitzar registre del conjunt");
        System.out.println("[5] Test afegir registre al conjunt");
        System.out.println("[6] Test consultar registre");
        System.out.println("[7] Test get atributs unics");
        System.out.println("[8] Test ordena llistat atributs");
        System.out.println("[9] Test calcul de suport");
        System.out.println();

        return llegeixInt("Especifica l'opcio a provar:");
    }

    public static void main(String[] args) {
        titolDriver("Driver CjtRegistres");

        System.out.println("Inicialitzant el conjunt amb la constructora per defecte...");
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
                    testGetLlistatRegistres();
                    break;

                case 2:
                    testSetLlistatRegistres();
                    break;

                case 3:
                    testBorrarRegistre();
                    break;

                case 4:
                    testUpdateRegistre();
                    break;

                case 5:
                    testAfegirRegistre();
                    break;

                case 6:
                    testConsultarRegistre();
                    break;

                case 7:
                    testGetAtributsUnics();
                    break;

                case 8:
                    testOrdena();
                    break;

                case 9:
                    testCalculaSuport();
                    break;

                default:
                    imprimeixError("Opcio no valida");
                    break;
            }
        }
    }
}
