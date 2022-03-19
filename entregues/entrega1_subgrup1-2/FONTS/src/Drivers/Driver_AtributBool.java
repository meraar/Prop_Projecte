package Drivers;

import prop.Domini.AtributBool;

import java.util.ArrayList;


public class Driver_AtributBool extends AbstractDriver {
    private static AtributBool atribut;

    private static void imprimeix() {
        System.out.println("*********************************************************************");
        System.out.println("* Benvingut! Estas al Driver_AtributBool. Selecciona una de les seguents opcions:");
        System.out.println("*	0. Sortir");
        System.out.println("*	1. Provar les Constructores");
        System.out.println("*	2. Consultar nom de l'atribut");
        System.out.println("*	3. Consultar tipus de l'atribut");
        System.out.println("*	4. Assignar nom a l'atribut");
        System.out.println("*	5.Assignar tipus a l'atribut");
        System.out.println("*	6.Assignar valors cert i fals a un atribut(setValorsB)");
        System.out.println("*	7. Provar si un valor forma part d'un atribut(validarValor)");
        System.out.println("*	8. Provar si un valor es considerat cert a l'atribut(processarValor)");
        System.out.println("*	9. Consultar els valors fals i cert d'un atribut");

        System.out.println("*********************************************************************");
    }

    public static void testConstructor() {

        System.out.println("Indiqui la creadora que vol testejar:");
        System.out.println("*	1. Constructora amb nom i tipus");
        System.out.println("*	2. Constructora amb nom, tipus, i valors");
        int opcion = llegeixInt("");
        switch (opcion) {
            case 1:
                try {
                    String nom1 = llegeixString("Indiqui el nom de l'atributBool a crear:");
                    String tipus1 = llegeixString("Indiqui el tipus de l'atributBool a crear");
                    AtributBool atrBoolNT = new AtributBool(nom1, tipus1);
                    String nomA1 = atrBoolNT.getNom();
                    String tipusA1 = atrBoolNT.getTipus();
                    System.out.println("Dades introduides -> Nom = " + nom1 + " tipus= " + tipus1);
                    System.out.println("Dades de l'atribut -> Nom = " + nomA1 + " tipus= " + tipusA1);
                }catch(Exception e) {
                    imprimeixError(e);
                }
                break;
            case 2:
                try {
                    String nom2 = llegeixString("Indiqui el nom de l'atributBool a crear:");
                    String tipus2 = llegeixString("Indiqui el tipus de l'atributBool a crear");
                    String valorF2 = llegeixString("Indiqui el valor fals de l'atribut");
                    String valorT2 = llegeixString("Indiqui el valor cert de l'atribut");
                    AtributBool atrBoolVal = new AtributBool(nom2, tipus2, valorF2, valorT2);
                    String nomA2 = atrBoolVal.getNom();
                    String tipusA2 = atrBoolVal.getTipus();
                    boolean valorAF2 = atrBoolVal.validarValor(valorF2);
                    boolean valorAT2 = atrBoolVal.validarValor(valorT2);
                    System.out.println("Dades introduides -> Nom = " + nom2 + " tipus= " + tipus2 + "valorFals= " + valorF2 + "valorTrue= " + valorT2);
                    System.out.println("Dades de l'atribut -> Nom = " + nomA2 + " tipus= " + tipusA2 + "Coincideix el valor introduit fals amb el de l'atribut?= " + valorAF2 + "Coincideix el valor introduit cert amb el de l'atribut?= " + valorAT2);
                }catch (Exception e){
                    imprimeixError(e);
                }
                break;
            default:
                System.out.println("La opcio escollida no existeix!");
                break;

        }

    }

    public static void testGetNom(){
        try {
            String nom1 = llegeixString("Indiqui el nom de l'atributBool a crear:");
            AtributBool atr = new AtributBool(nom1, "boolean");
            System.out.println("El nom de l'atribut introduit es: " + nom1 + " i el nom de l'atribut es:" + atr.getNom());
        }catch (Exception e) {
            imprimeixError(e);
        }


    }

    public static void testGetTipus() {
        try {


            System.out.println("Es crea un AtributBool de tipus boolean");
            AtributBool atr = new AtributBool("atri", "boolean");
            System.out.println("El tipus de l'atribut introduit es: " + atr.getTipus());
        }catch (Exception e) {
                imprimeixError(e);
            }
    }

    public static void testSetNom() {


        try {
            //System.out.println("Indiqui el nom de l'atributBool a crear de tipus boolean:");
            String nom1 = llegeixString("Indiqui el nom de l'atributBool a crear de tipus boolean amb nom a1 i farem setNom amb a2:");
            AtributBool atr = new AtributBool("a1","boolean");
            atr.setNom(nom1);
            System.out.println("El nom de l'atribut introduit es: "+ nom1 + " i el nom de l'atribut es: "+ atr.getNom());
        }
        catch (Exception e) {
            imprimeixError(e);
        }


    }

    public static void testSetTipus() {
        System.out.println("Es crea un AtributBool on s'introdueix el tipus boolean:");



        try {
            AtributBool atr = new AtributBool("atri","boolean");
            atr.setTipus("boolean");
            System.out.println("El tipus de l'atribut introduit es: "+ atr.getTipus());
        }
        catch (Exception e) {
            imprimeixError(e);
        }

    }

    public static void testValidarValor() {
        System.out.println("Es creara un atribut anomenat atr amb el valor fals: false i el valor cert: cierto ");

        try {
            AtributBool atr = new AtributBool("atr", "boolean", "false", "cierto");
            String valor = llegeixString("Introdueix un valor per veure si coincideix amb els valors de l'atribut: ");
            System.out.println("El valor introduit esta definit a l'atribut ja sigui cert o fals?: " + atr.validarValor(valor));
        }catch (Exception e){
            imprimeixError(e);
        }


    }

    public static void testProcessar() {
        System.out.println("Es creara un atribut anomenat atr. ");

        try {
            AtributBool atr = new AtributBool("atr", "boolean");
            ArrayList<String> valors = new ArrayList<>();
            valors.add(0,llegeixString("Introdueix el valor fals:"));
            valors.add(1,llegeixString("Introdueix el valor cert:"));
            atr.setValorsB(valors);
            String valorT = llegeixString("Introdueix un dels dos valors per veure si es considerat cert o fals: ");
            if(atr.processar(valorT).equals("true"))
                System.out.println("El valor introduit esta definit a l'atribut com cert.");
            else if(atr.processar(valorT).equals("false")) System.out.println("El valor introduit  esta definit a l'atribut com fals o be no pertany al atribut.");
            else System.out.println("El valor introduit NO esta definit a l'atribut.");
        }catch (Exception e){
            imprimeixError(e);
        }

    }

    public static void testSetValorsB() {

        try {
            System.out.println("Abans de tot es crea un atribut de tipus boolean");
            AtributBool atB = new AtributBool("atributBool","boolean");
            String valorF = llegeixString("Indiqui el valor fals de l'atribut");
            String valorT = llegeixString("Indiqui el valor cert de l'atribut");
            ArrayList<String> valors = new ArrayList<>();
            valors.add(0,valorF);
            valors.add(1,valorT);
            AtributBool atBDef = new AtributBool("atributBool","boolean",valors.get(0),valors.get(1));
            atB.setValorsB(valors);
            System.out.println("Atribut creadora -> Nom = " + atBDef.getNom() + " tipus= " + atBDef.getTipus() + " valorFals= " + valorF + " valorTrue= " + valorT);
            System.out.println("Atribut amb setValorsB -> Nom = " + atB.getNom() + " tipus= " + atB.getTipus()+ " Coincideix el valor fals de la creadora amb el de setValorsB?= " + atB.validarValor(valorF) + " Coincideix el valor cert de la creadora amb el de setValorsB?= " + atB.validarValor(valorT));

        }catch (Exception e){
            imprimeixError(e);
        }

    }

    public static void testGetValorsB() {

        try {
            System.out.println("Abans de tot es crea un atribut de tipus boolean");
            String valorF = llegeixString("Indiqui el valor fals de l'atribut");
            String valorT = llegeixString("Indiqui el valor cert de l'atribut");

            AtributBool atB = new AtributBool("atributBool","boolean",valorF,valorT);
            System.out.println("Atribut amb Nom = " + atB.getNom() + " tipus= " + atB.getTipus()+ "valor fals = "+ atB.getValorsB().get(0) +" i valor cert = "+ atB.getValorsB().get(1));

        }catch (Exception e){
            imprimeixError(e);
        }

    }


    public static void main(String[] args){
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
                    System.out.println("Prova de getNom:");
                    testGetNom();
                    break;
                case 3:
                    System.out.println("Prova de getTipus");
                    testGetTipus();
                    break;
                case 4:
                    System.out.println("Prova de setNom:");
                    testSetNom();
                    break;
                case 5:
                    System.out.println("Prova de setTipus:");
                    testSetTipus();
                    break;
                case 6:
                    System.out.println("Prova de setValorsB:");
                    testSetValorsB();
                    break;
                case 7:
                    System.out.println("Prova de validarValor:");
                    testValidarValor();
                    break;
                case 8:
                    System.out.println("Prova de processar:");
                    testProcessar();
                    break;
                case 9:
                    System.out.println("Prova de getValorsB:");
                    testGetValorsB();
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
