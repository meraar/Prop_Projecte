package Drivers;

import prop.Domini.Atribut;
import prop.Domini.AtributInt;
import prop.Domini.AtributString;
import prop.Domini.CjtAtributs;

import java.util.ArrayList;


public class Driver_CjtAtributs extends AbstractDriver {
    private static CjtAtributs cjt = new CjtAtributs();

    private static void imprimeix() {
        System.out.println("*********************************************************************");
        System.out.println("* Benvingut! Estas al Driver_AtributString. Selecciona una de les seguents opcions:");
        System.out.println("*	0. Sortir");
        System.out.println("*	1. Provar les Constructores");
        System.out.println("*	2. Provar l'actualitzacio d'un atribut en concret");
        System.out.println("*	3. Provar l'eliminacio d'un atribut en concret");
        System.out.println("*********************************************************************");
    }

    public static void testConstructor() {
        System.out.println("Indiqui la creadora que vol testejar:");
        System.out.println("*	0. Constructora per defecte");
        System.out.println("*	1. Constructora amb atributs al conjunt");

        int opcion = llegeixInt("");
        switch (opcion) {
            case 0:
                try {
                   cjt = new CjtAtributs();
                   System.out.println("Correcto");
                } catch (Exception e) {
                    imprimeixError(e);
                }


                break;
            case 1:
                try {
                    System.out.println("Es crea una llista d'atributs amb un de tipus Float i l'altre String ");
                    AtributInt atr = new AtributInt("atr", "float");
                    ArrayList<Atribut> atributs = new ArrayList<>();
                    atributs.add(0, atr);
                    AtributString atr2 = new AtributString("atr2", "String");
                    atributs.add(1, atr2);
                    System.out.println("Es crea un conjunt d'atributs amb la llista especificada ");
                    cjt = new CjtAtributs(atributs);
                    for (int i = 0; i < cjt.getLlistAtri().size(); ++i)
                        System.out.println("Dades de l'atribut que pertany al conjunt d'atributs despres del canvi a la posicio " + i + "-> Nom = " + cjt.getLlistAtri().get(i).getNom() + " tipus= " + cjt.getLlistAtri().get(i).getTipus());
                }catch (Exception e) {
                    imprimeixError(e);
                }

        break;
            default:
                System.out.println("La opcio escollida no existeix!");
                break;

        }


    }
    public static void testUpdateAtribut(){
        try{
            System.out.println("Es creara un conjunt d'atributs amb un atributInt anomenat atr de tipus float ");
            CjtAtributs cjt = new CjtAtributs();
            AtributInt atr = new AtributInt("atr", "float");
            ArrayList<Atribut> atributs = new ArrayList<>();
            atributs.add(0,atr);
            cjt.setLlistAtri(atributs);
            System.out.println("Dades de l'atribut que pertany al conjunt d'atributs-> Nom = " + cjt.getLlistAtri().get(0).getNom()+ " tipus= " + cjt.getLlistAtri().get(0).getTipus());
            System.out.println("Ara se substitueix per un atributString anomenat atr2 de tipus String ");
            AtributString atr2 = new AtributString("atr2", "String");
            cjt.updateAtri("atr",atr2);
            System.out.println("Dades de l'atribut que pertany al conjunt d'atributs despres del canvi-> Nom = " + cjt.getLlistAtri().get(0).getNom()+ " tipus= " + cjt.getLlistAtri().get(0).getTipus());
        } catch (Exception e) {
            imprimeixError(e);
        }




    }
    public static void testDeleteAtribut() {
        try {
            System.out.println("Es creara un conjunt d'atributs amb un atributInt anomenat atr de tipus Float i un atributString anomenat atr2 de tipus String");

            cjt = new CjtAtributs();
            AtributInt atr = new AtributInt("atr", "float");
            AtributString atr2 = new AtributString("atr2", "String");
            ArrayList<Atribut> atributs = new ArrayList<>();
            atributs.add(0, atr);
            atributs.add(1, atr2);
            cjt.setLlistAtri(atributs);
            System.out.println("Dades de l'atribut que pertany al conjunt d'atributs a la posicio 0-> Nom = " + cjt.getLlistAtri().get(0).getNom() + " tipus= " + cjt.getLlistAtri().get(0).getTipus());
            System.out.println("Dades de l'atribut que pertany al conjunt d'atributs a la posicio 1-> Nom = " + cjt.getLlistAtri().get(1).getNom() + " tipus= " + cjt.getLlistAtri().get(1).getTipus());
            System.out.println("Ara eliminem el segon atribut del conjunt ");
            cjt.deleteAtri("atr2");
            System.out.println("Ara obtenim tots els atributs del conjunt ");
            for (int i = 0; i < cjt.getLlistAtri().size(); ++i)
                System.out.println("Dades de l'atribut que pertany al conjunt d'atributs despres del canvi a la posicio " + i + "-> Nom = " + cjt.getLlistAtri().get(i).getNom() + " tipus= " + cjt.getLlistAtri().get(i).getTipus());
            System.out.println("Fi del conjunt");
        }catch(Exception e){
            imprimeixError(e);
        }
    }

    public static void main(String[] args) {
        imprimeix();
        int opcion = llegeixInt("");
        System.out.println(opcion);
        boolean sortir = false;
        while(!sortir) {
            switch (opcion) {
                case 0:
                    sortir = true;
                    break;
                case 1:
                    System.out.println("Prova de les constructores");
                    testConstructor();
                    break;
                case 2:
                    System.out.println("Prova de updateAtribut d'un conjunt d'atributs:");
                    testUpdateAtribut();
                    break;
                case 3:
                    System.out.println("Prova de deleteAtribut d'un conjunt d'atributs");
                    testDeleteAtribut();
                    break;
                default:
                    System.out.println("La opcio escollida no existeix!");
                    break;

            }if(opcion!= 0) {
                imprimeix();
                opcion = llegeixInt("Selecciona la nova funcionalitat a provar");
            }
        }

    }
}
