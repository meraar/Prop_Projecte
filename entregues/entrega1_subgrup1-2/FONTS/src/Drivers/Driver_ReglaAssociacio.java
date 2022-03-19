package Drivers;

import prop.Domini.AtributProcessat;
import prop.Domini.Registre;
import prop.Domini.ReglaAssociacio;

import java.util.HashSet;

public class Driver_ReglaAssociacio extends AbstractDriver {
    private static ReglaAssociacio regla;

    private static void imprimeixCondicioRegla(ReglaAssociacio regla) {
        if (regla.getCondicions().isEmpty()) {
            System.out.println("Les condicions estan buides.");
            return;
        }

        for (AtributProcessat atri : regla.getCondicions()) {
            System.out.println(String.format(" - %s: %s", atri.getNom(), atri.getValor()));
        }
    }

    private static void imprimeixConcequentRegla(ReglaAssociacio regla) {
        if (regla.getConsequent().getValor().isEmpty()) {
            System.out.println("El consequent esta buit.");
            return;
        }
        System.out.println("" + regla.getConsequent().getNom() + ": " + regla.getConsequent().getValor() + "");
    }

    private static void testConstructora() throws Exception {
        titolTest("Test constructora per defecte.");
        descripcioTest("Crea una regla d'associacio buida.");

        int ident = llegeixInt("Introdueix l'identificador de la regla: ");
        float conf = llegeixFloat("Introdueix la confianca de la regla: ");
        float suport = llegeixFloat("Introdueix el suport de la regla: ");

        int num_condi = llegeixInt("Introdueix el nombre de condicions: ");
        HashSet<AtributProcessat> condicions = new HashSet<>();
        for(int i = 0; i < num_condi; i++) {
            String nomcondi = llegeixString(String.format("Introdueix el nom de la condicio %d:", i + 1));
            String valorcondi = llegeixString(String.format("Introdueix el valor de la condicio %d:", i + 1));
            AtributProcessat condicio = new AtributProcessat(nomcondi,valorcondi);
            condicions.add(condicio);
        }

        String nomconse = llegeixString("Introdueix el nom del consquent de la regla: ");
        String valorconse = llegeixString("Introdueix el valor del consquent de la regla: ");
        AtributProcessat consequent = new AtributProcessat(nomconse,valorconse);

        regla = new ReglaAssociacio(ident,conf,suport,consequent,condicions);

        acabarTest();
    }

    private static void testGetterIdentificador() {
        titolTest("Test getter identificador.");
        descripcioTest("Consulta l'identificador de la regla d'associacio.");

        System.out.println("Identificador de la regla d'associacio: " + regla.getId_regla());

        acabarTest();
    }

    private static void testSetterIdentificador() {
        titolTest("Test setter identificador.");
        descripcioTest("Estableix l'identificador de la regla d'associacio.");

        int idRegla = llegeixInt("Introdueix el nou identificador:");
        regla.setId_regla(idRegla);

        acabarTest();
    }

    private static void testGetterConfianca() {
        titolTest("Test getter confianca.");
        descripcioTest("Consulta la confinaca de la regla d'associacio.");

        System.out.println("Confianca de la regla d'associacio: " + regla.getConfianca());

        acabarTest();
    }

    private static void testSetterConfianca() {
        titolTest("Test setter confianca.");
        descripcioTest("Estableix la confiança de la regla d'associacio.");

        float confianca = llegeixFloat("Introdueix la nova confianca:");
        regla.setConfianca(confianca);

        acabarTest();
    }

    private static void testGetterSuport() {
        titolTest("Test getter suport.");
        descripcioTest("Consulta el suport de la regla d'associacio.");

        System.out.println("Suport de la regla d'associacio: " + regla.getSuport());

        acabarTest();
    }

    private static void testSetterSuport() {
        titolTest("Test setter suport.");
        descripcioTest("Estableix el suport de la regla d'associacio.");

        float suport = llegeixFloat("Introdueix el nou suport:");
        regla.setSuport(suport);

        acabarTest();
    }

    private static void testGetterCondicions() {
        titolTest("Test getter del llistat de condicions.");
        descripcioTest("Consulta el llistat de condicions de la regla d'associacio.");

        imprimeixCondicioRegla(regla);

        acabarTest();
    }

    private static void testSetterCondicions() throws Exception {
        titolTest("Test setter del llistat de condicions.");
        descripcioTest("Estableix el llistat de condicions de la regla d'associacio.");

        int mida = llegeixInt("Introdueix quantes condicions vols:");
        HashSet<AtributProcessat> set = new HashSet<>();

        for (int i = 0; i < mida; i++) {
            String nom = llegeixString(String.format("Introdueix el nom de la condicio %d:", i + 1));
            String valor = llegeixString(String.format("Introdueix el valor de la condicio %d:", i + 1));
            AtributProcessat atri = new AtributProcessat(nom,valor);
            set.add(atri);
        }
        regla.setCondicions(set);

        acabarTest();
    }

    private static void testGetterConsequent() {
        titolTest("Test getter del consequent de la regla.");
        descripcioTest("Consulta el consequent de la regla d'associacio.");

        imprimeixConcequentRegla(regla);

        acabarTest();
    }

    private static void testSetterConsequent() throws Exception {
        titolTest("Test setter del consquent de la regla.");
        descripcioTest("Estableix el consequent de la regla d'associacio.");

        String nom = llegeixString("Introdueix el nom de l'atribut consequent:");
        String valor = llegeixString("Introdueix el valor de l'atribut consequent:");
        AtributProcessat atri = new AtributProcessat(nom,valor);
        regla.setConsequent(atri);


        acabarTest();
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

    private static void testEsCompleixLaRegla() {
        titolTest("Test es compleix la regla.");
        descripcioTest("Comprova si tots els atributs d'una regla es troben al registre.");

        Registre regi = crearRegistre();

        if (regla.compleixRegla(regi)) {
            System.out.println("SI compleix la regla.");
        }
        else {
            System.out.println("NO compleix la regla.");
        }

        acabarTest();
    }


    private static int menu() {
        System.out.println();
        System.out.println(" === Menu del driver ===");
        System.out.println("[-1] Sortir del driver");
        System.out.println("[0] Test constructora");
        System.out.println("[1] Test setter identificador de la regla d'associacio");
        System.out.println("[2] Test getter identificador de la regla d'associacio");
        System.out.println("[3] Test setter confianca de la regla d'associacio");
        System.out.println("[4] Test getter confianca de la regla d'associacio");
        System.out.println("[5] Test setter suport de la regla d'associacio");
        System.out.println("[6] Test getter suport de la regla d'associacio");
        System.out.println("[7] Test setter del llistat de condicions de la regla d'associacio");
        System.out.println("[8] Test getter del llistat de condicions de la regla d'associacio");
        System.out.println("[9] Test setter del consequent de la regla d'associacios");
        System.out.println("[10] Test getter del consequent de la regla d'associacios");
        System.out.println("[11] Test es compleix la regla d'associacio");
        System.out.println();

        return llegeixInt("Especifica l'opcio a provar:");
    }

    public static void main(String[] args) throws Exception {
        titolDriver("Driver Regla d'associacio");

        System.out.println("Inicialitzant la regla d'associacio amb la constructora per defecte...");
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
                    testSetterIdentificador();
                    break;

                case 2:
                    testGetterIdentificador();
                    break;

                case 3:
                    testSetterConfianca();
                    break;

                case 4:
                    testGetterConfianca();
                    break;

                case 5:
                    testSetterSuport();
                    break;

                case 6:
                    testGetterSuport();
                    break;

                case 7:
                    testSetterCondicions();
                    break;

                case 8:
                    testGetterCondicions();
                    break;

                case 9:
                    testSetterConsequent();
                    break;

                case 10:
                    testGetterConsequent();
                    break;

                case 11:
                    testEsCompleixLaRegla();
                    break;

                default:
                    imprimeixError("Opció no valida");
                    break;
            }
        }
    }
}


