package Drivers;

import prop.CtrlRegistres;
import prop.Domini.*;
import prop.Persistencia.CtrlPersistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver_CtrlRegistres extends AbstractDriver {
    private static CtrlRegistres ctrl;
    private static CjtAtributs atributs;

    private static void testProcessarAtribus() {
        titolTest("Test processar atributs");
        descripcioTest("Processa els atributs donat un fitxer de dades i el seu preproces");

        String path = llegeixString("Introdueix el path del fitxer de dades: ");

        try {
            ctrl.processarAtributs(path, atributs);
        } catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testConsultarRegistre() {
        titolTest("Test consultar registre");
        descripcioTest("Consulta un registre del conjunt");

        int id = llegeixInt("Introdueix l'identificador del registre a consultar: ");

        try {
            HashMap<String, String> registre = ctrl.consultarRegistre(id);

            System.out.println(String.format("El registre amb ID %d conte els seguents atributs:", id));

            for (Map.Entry<String, String> atribut : registre.entrySet()) {
                System.out.println(String.format(" - %s = %s", atribut.getKey(), atribut.getValue()));
            }

        } catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testExportarRegistres() {
        titolTest("Test exportar registres");
        descripcioTest("Exporta els registres d'atributs processats a un fitxer");

        String path = llegeixString("Introdueix el path del fitxer desti: ");

        try {
            ctrl.exportarRegistres(path);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testImportarRegistres() {
        titolTest("Test importar registres");
        descripcioTest("Importa els registres previament exportats a un fitxer");

        String path = llegeixString("Introdueix el path del fitxer a importar: ");

        try {
            ctrl.importarRegistres(path);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();
    }

    private static void testBuidarCjtRegistres() {
        titolTest("Test buidar conjunt");
        descripcioTest("Buida el conjunt de registres actual");

        ctrl = new CtrlRegistres(new CtrlPersistencia());

        acabarTest();
    }

    private static int menu() {
        System.out.println();
        System.out.println(" === Menu del driver ===");
        System.out.println("[-1] Sortir del driver");
        System.out.println("[0] Processar atributs");
        System.out.println("[1] Consultar registre");
        System.out.println("[2] Exportar registres");
        System.out.println("[3] Importar registres");
        System.out.println("[4] Buidar CjtRegistres");
        System.out.println();

        return llegeixInt("Especifica l'opcio a provar:");
    }

    public static void main(String[] args) {
        titolDriver("Driver CtrlRegistres");

        ctrl = new CtrlRegistres(new CtrlPersistencia());
        try {
            atributs = cjtAtributsProva();
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        System.out.println("Aquest driver prova les funcions de la classe CtrlRegistres.");
        System.out.println("El conjunt d'atributs i els seus preprocessos definits per aquesta prova son:");
        imprimirAtributs();

        while (true) {
            switch (menu()) {
                case -1:
                    System.exit(0);
                    break;

                case 0:
                    testProcessarAtribus();

                    break;

                case 1:
                    testConsultarRegistre();

                    break;

                case 2:
                    testExportarRegistres();

                    break;

                case 3:
                    testImportarRegistres();

                    break;

                case 4:
                    testBuidarCjtRegistres();

                    break;

                default:
                    imprimeixError("Opcio no valida");
                    break;
            }
        }
    }

    private static void imprimirAtributs() {
        for (Atribut atribut : atributs.getLlistAtri()) {
            System.out.println(String.format("Atribut %s:", atribut.getNom()));

            switch (atribut.getTipus()) {
                case "String":
                    AtributString atr1 = (AtributString)atribut;
                    System.out.print(" - Valors possibles: ");

                    for (String valor : atr1.getValorsPos()) {
                        System.out.print(valor + " ");
                    }

                    System.out.println();

                    break;

                case "float":
                    AtributInt atr2 = (AtributInt)atribut;

                    for (Interval interval : atr2.getLlistaInterval()) {
                        System.out.println(String.format(" - Interval %s: limInf: %f, limSup: %f, limSupIgual: %b, limInfIgual: %b",
                                interval.getNom(), interval.getLimInf(), interval.getLimSup(), interval.getIgualSup(), interval.getIgualInf()));
                    }

                    break;

                case "boolean":
                    AtributBool atr3 = (AtributBool)atribut;
                    try {
                        ArrayList<String> valors = atr3.getValorsB();

                        System.out.println(String.format(" - Valor true: %s, valor false: %s", valors.get(1), valors.get(0)));
                    } catch (Exception e) {
                        imprimeixError(e);
                    }

                    break;
            }
        }
    }

    private static CjtAtributs cjtAtributsProva() throws Exception {
        CjtAtributs conjunt = new CjtAtributs();
        ArrayList<Atribut> atributs = new ArrayList<>();

        AtributString atr1 = new AtributString("Nom", "String");
        atr1.afegirValorPossible("Pere");
        atr1.afegirValorPossible("Meraj");
        atr1.afegirValorPossible("Albert");
        atr1.afegirValorPossible("Carles");
        atr1.afegirValorPossible("Miquel");
        atributs.add(atr1);

        AtributInt atr2 = new AtributInt("NotaParcial1", "float");
        intervalsNotes(atr2);
        atributs.add(atr2);

        AtributBool atr3 = new AtributBool("EntregaTreb", "boolean", "No", "Si");
        atributs.add(atr3);

        AtributInt atr4 = new AtributInt("NotaFinal", "float");
        intervalsNotes(atr4);
        atributs.add(atr4);

        conjunt.setLlistAtri(atributs);

        return conjunt;
    }

    private static void intervalsNotes(AtributInt atribut) {
        atribut.afegirInterval(new Interval(5, 0, false, true, "suspes"));
        atribut.afegirInterval(new Interval(6.5f, 5, true, true, "suficient"));
        atribut.afegirInterval(new Interval(8.5f, 6.5f, true, false, "notable"));
        atribut.afegirInterval(new Interval(10, 8.5f, true, false, "excelent"));
    }
}
