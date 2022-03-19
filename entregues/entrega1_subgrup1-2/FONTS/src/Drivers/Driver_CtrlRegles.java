package Drivers;

import prop.CtrlRegles;
import prop.Domini.*;
import prop.Persistencia.CtrlPersistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver_CtrlRegles extends AbstractDriver {
    private static CtrlRegles Regles;
    private static boolean generatRegles = false;


    private static Registre crearRegistre() {
        int idRegistre = llegeixInt("Introdueix identificador del registre:");

        return crearRegistre(idRegistre);
    }

    private static Registre crearRegistre(int idRegistre) {
        int length = llegeixInt("Introdueix el nombre de atributs que conte el registre:");

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

    private static void testConstructora(){
        System.out.println(" Test Constructura per defecte");
        Regles = new CtrlRegles(new CtrlPersistencia());
    }
    private  static void imprimir_Map(HashMap<Integer, HashMap<Float, Float>> vali){
        for (Map.Entry<Integer, HashMap<Float,Float>> reg : vali.entrySet()) {
            HashMap<Float,Float> mapa;
            mapa = reg.getValue();
            Map.Entry<Float,Float> entry = mapa.entrySet().iterator().next();
            Float sup = entry.getKey();
            Float conf = entry.getValue();
            System.out.println("Identificador regla : "+reg.getKey()+" ");
            System.out.println("Suport : "+sup+", Confianca : "+conf+"");
        }
    }
    private static CjtRegistres crearCtjRegistre(){
        int num_Registres = llegeixInt("Introdueix el nombre de Registres que vols utilizar per generar regles: ");
        CjtRegistres registres = new CjtRegistres();
        ArrayList<Registre> llistReg = new ArrayList<>();
        for (int i = 0; i < num_Registres; ++i){
            System.out.println(String.format("Introduccio de dades pel registre #%d:", i+1));
            llistReg.add(crearRegistre());
            System.out.println();
        }
        registres.setLlistReg(llistReg);
        return registres;
    }

    private static void testApriori(){
        System.out.println(" Test Apriori  i generar regles");
        CjtRegistres registres  = crearCtjRegistre();
        float minSuport = llegeixFloat("Introdueix el minim Suport: ");
        float minConfianza = llegeixFloat("Introdueix el minim Confianca: ");
        int nRegles = llegeixInt("Introdueix el nombre maxim de Regles a generar: ");
        try {
            HashMap<Integer, HashMap<Float, Float>> regles_generades = Regles.apriori(registres, minSuport, minConfianza, nRegles);
            generatRegles = true;
            imprimir_Map(regles_generades);
        } catch (Exception e) {
            imprimeixError(e);
        }
    }

    private static void testvalidarRegles(){
        if(generatRegles) {
            System.out.println(" Test Validar Regles ");
            CjtRegistres dades = crearCtjRegistre();
            float minSup = llegeixFloat("Introdueix el minim Suport: ");
            float minConf = llegeixFloat("Introdueix la minima Confianca: ");
            try {
                HashMap<Integer, HashMap<Float, Float>> val = Regles.validarRegles(dades, minSup, minConf);
                imprimir_Map(val);
            } catch (Exception e) {
                imprimeixError(e);
            }
        }
        else System.out.println(" Primer has de generar regles. ");
        System.out.println();

    }

    private static void testExportarRegles() {
        titolTest("Test exportar regles d'associacio");
        descripcioTest("Exporta les regles d'associacio a un fitxer");

        String path = llegeixString("Introdueix el path del fitxer desti: ");

        try {
            Regles.exportarRegles(path);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();

    }

    private static void testImportarRegles() {
        titolTest("Test importar regles d'associacio");
        descripcioTest("Importa les regles d'associacio previament exportats a un fitxer");

        String path = llegeixString("Introdueix el path del fitxer a importar: ");

        try {
            Regles.importarRegles(path);
        }
        catch (Exception e) {
            imprimeixError(e);
        }

        acabarTest();

    }


    private static int menu() {
        System.out.println();
        System.out.println(" *** Menu del driver CtrlRegles *** ");
        System.out.println("[-1] Sortir del driver");
        System.out.println("[0] Test constructora");
        System.out.println("[1] Generar Regles");
        System.out.println("[2] Validar Regles");
        System.out.println("[3] Exportar Regles");
        System.out.println("[4] Importar Regles");
        System.out.println();

        return llegeixInt("Especifica l'opcio a provar:");

    }

    public  static  void main (String[] args){
        titolDriver("Driver CtrlRegles");

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
                    testApriori();
                    break;

                case 2:
                    testvalidarRegles();
                    break;

                case 3:
                    testExportarRegles();
                    break;

                case 4:
                    testImportarRegles();
                    break;

                default:
                    imprimeixError("Opcio no valida");
                    break;
            }
        }
    }
}

