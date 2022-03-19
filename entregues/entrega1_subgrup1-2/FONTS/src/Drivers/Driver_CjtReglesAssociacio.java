package Drivers;

import prop.Domini.AtributProcessat;
import prop.Domini.CjtReglesAssociacio;
import prop.Domini.ReglaAssociacio;

import java.util.ArrayList;
import java.util.HashSet;

public class Driver_CjtReglesAssociacio extends AbstractDriver {
    private static CjtReglesAssociacio CRA;

    private static ReglaAssociacio crearRegla(){
        int id_Regla = llegeixInt("Introdueix l identificador de la Regla:");

        float suport = llegeixFloat("Introdueix el suport per la regla: ");
        float confianza = llegeixFloat("Introdueix la confianza per la regla: ");

        HashSet<AtributProcessat> condicions = new HashSet<>();
        int num_condicions = llegeixInt("Introdueix el numero de condicions que te la Regla: ");
        for(int i = 0; i < num_condicions; i++) {
            String nom = llegeixString("Introdueix el nom de l atribut del conjunt de condicions de la Regla: ");
            String valor = llegeixString("Introdueix el valor de l atribut del conjunt de condicions de la Regla: ");
            try {
                AtributProcessat atri = new AtributProcessat(nom, valor);
                condicions.add(atri);
            } catch (Exception e) {
                imprimeixError(e);
            }

        }

        String nom_cons = llegeixString("Introdueix el nom de l atribut Consequent de la Regla: ");
        String valor_cons = llegeixString("Introdueix el valor de l atribut Consequent de la Regla: ");
        AtributProcessat consequent = null;
        try {
            consequent = new AtributProcessat(nom_cons, valor_cons);
        } catch (Exception e) {
            imprimeixError(e);
        }

        ReglaAssociacio regla = new ReglaAssociacio(id_Regla, suport, confianza, consequent, condicions);
        return regla;

    }
    public  static  void  testConstructor(){

        System.out.println("Escull lopcio que vols :");
        System.out.println(" 1. Constructura per defecte. ");
        System.out.println(" 2. Constructora amb un Conjunt de ReglesAssociacio. ");
        Integer op = llegeixInt("Escull 1 o 2");
        if(op == 1){
            CRA = new CjtReglesAssociacio();
            System.out.println("Sha creat un CjtReglaAssociacio amb un Conjunt de ReglaAsscocio buit.");
        }
        else if(op == 2){
            int tam = llegeixInt("Introdueix el nombre de Regles dAssociacions que conte el CjtReglesAssociacio:");
            ArrayList<ReglaAssociacio> listReg = new ArrayList<ReglaAssociacio>();
            for (int i = 0; i < tam; ++i){
                ReglaAssociacio reg = crearRegla();
                listReg.add(reg);
            }
            CRA = new CjtReglesAssociacio(listReg);
            System.out.println("S ha creat un CjtReglaAssociacio amb el Conjunt de ReglaAsscocio");
        }
        else{
            System.out.println("ERROR!!!!!!!!! Opcio INCORRECTE");
        }

    }

    public static void testgetRegla() {
        if(!CRA.getLlistRegAss().isEmpty()){
            Integer idregla = llegeixInt("Introdueix identificador de la Regla que vols consultar");
            try {
                ReglaAssociacio reg = CRA.getRegla(idregla);
                System.out.println(" Regla Associacio  identificador = " + reg.getId_regla());
                System.out.println(" Regla Associacio suport = " + reg.getSuport());
                System.out.println(" Regla Associacio confianza = " + reg.getConfianca());
                for (AtributProcessat atri : reg.getCondicions()) {
                    System.out.println(" Regla Associacio Condicio : NomAtribut " + atri.getNom() + " valorAtribut = " + atri.getValor());
                }
                System.out.println(" Regla Associacio Consequent : NomAtribut " + reg.getConsequent().getNom() + " valorAtribut = " + reg.getConsequent().getValor());
                System.out.println();

            } catch (Exception e) {
                imprimeixError(e);
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Primer has de creat un Conjunt de Regles amb les Regles Asociacions");
            return;
        }
    }
    public static void testgetLlistRegAss(){
        if(CRA.getLlistRegAss().isEmpty()){
            System.out.println("No hi ha cap LLista de Regla Associacio");
            return;
            }

        else{
            ArrayList<ReglaAssociacio> res = CRA.getLlistRegAss();
            for(ReglaAssociacio reg : res){
                System.out.println(" Regla Associacio  identificador = " + reg.getId_regla());
                System.out.println(" Regla Associacio suport = " + reg.getSuport());
                System.out.println(" Regla Associacio confianza = " + reg.getConfianca());
                for (AtributProcessat atri : reg.getCondicions()) {
                    System.out.println(" Regla Associacio Condicio : NomAtribut " + atri.getNom() + " valorAtribut = " + atri.getValor());
                }
                System.out.println(" Regla Associacio Consequent : Nom " + reg.getConsequent().getNom() + " valor = " + reg.getConsequent().getValor());
                System.out.println();
            }
        }
    }
    public static void testsetLlistRegAss(){

        int tam = llegeixInt("Introdueix la quantitat de Regles d Associacio del nou llistat:");
        ArrayList<ReglaAssociacio> llistReg = new ArrayList<ReglaAssociacio>();

        for (int i = 0; i < tam; ++i) {
            System.out.println(String.format("Introduccio de dades per la Regla Associacio #%d:", i));
            llistReg.add(crearRegla());
        }

        CRA.setLlistRegAss(llistReg);

        System.out.println("La llista de ReglaAsscciacio esta actualitzada");
    }
    public static void testupdateRegla(){
        Integer id_reg = llegeixInt("Introdueix el identificador de la Regla Assocoiacio a modificar");

        ReglaAssociacio reg_new = crearRegla();

        try {
            CRA.updateRegla(id_reg, reg_new);
            System.out.println("La Regla Associacio esta actualitzada");
        }
        catch (Exception e) {
            imprimeixError(e);
        }
    }
    public static void testdeleteRegla() {
        Integer id_reg = llegeixInt("Introdueix el identificador de la Regla Assocoiacio a eliminar");
        if(CRA.getLlistRegAss().isEmpty()) {
            System.out.println("El conjunt esta buit");
            return;
        }
        else {
            ReglaAssociacio as = new ReglaAssociacio();
            try {
                CRA.deleteRegla(id_reg);
                System.out.println("La Regla Associacio amb idetificador " + id_reg + " esta eliminat");
            } catch (Exception e) {
                imprimeixError(e);
            }
        }

    }
    private static void imprimeix() {
        System.out.println("*********************************************************************");
        System.out.println("* Benvingut! Estas al Driver_CtjReglesAssociacio. Selecciona una de les seguents opcions:");
        System.out.println("*  -1. Sortir");
        System.out.println("*	0. Provar les Constructores");
        System.out.println("*	1. Set una llista de ReglesAssociacio. ");
        System.out.println("*	2. Consultar llista de ReglesAssociacio");
        System.out.println("*	3. Consultar una Regla de Associacio");
        System.out.println("*	4. Actualitzar una Regla d'Associacio");
        System.out.println("*	5. Eliminar una Regla d'Associacio");
        System.out.println("*********************************************************************");
    }
    public static void main(String[] args)  {
        boolean sortir = false;
        CRA = new CjtReglesAssociacio();
        while(!sortir) {
            imprimeix();
            int opcion = llegeixInt("Especifica la opcio a provar:");
            switch (opcion) {
                case -1:
                    sortir = true;
                    acabarTest();
                    System.out.println();
                    break;
                case 0:
                    testConstructor();
                    acabarTest();
                    System.out.println();
                    break;
                case 1:
                    testsetLlistRegAss();
                    acabarTest();
                    System.out.println();
                case 2:
                    testgetLlistRegAss();
                    acabarTest();
                    System.out.println();
                    break;
                case 3:
                    testgetRegla();
                    acabarTest();
                    System.out.println();
                    break;
                case 4:
                    testupdateRegla();
                    acabarTest();
                    System.out.println();
                    break;
                case 5:
                    try {
                        testdeleteRegla();
                    } catch (Exception e) {
                        System.out.println("ERROR en la eliminacio de la Regla");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("La opcio elegida no existe!");
                    System.out.println();
                    break;
            }
        }

    }
}
