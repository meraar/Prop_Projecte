package Drivers;

import prop.Domini.AtributInt;
import prop.Domini.Interval;
import prop.Domini.Tipus;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Carles Capilla
 */
public class Driver_AtributInt extends AbstractDriver{
    private static AtributInt atribut;

    private static void imprimeix() {
        System.out.println("*********************************************************************");
        System.out.println("* Benvingut! Estas al Driver_AtributInt. Selecciona una de les seguents opcions:");
        System.out.println("*	0. Sortir");
        System.out.println("*	1. Provar les Constructores");
        System.out.println("*	2. Consultar nom de l'atribut");
        System.out.println("*	3. Consultar tipus de l'atribut");
        System.out.println("*	4. Assignar nom a l'atribut");
        System.out.println("*	5. Assignar tipus a l'atribut");
        System.out.println("*	6. Provar si un valor forma part d'un atribut(validarValor)");
        System.out.println("*	7. Provar la obtencio del nom de l'interval al que pertany un valor(processarValor)");
        System.out.println("*	8. Provar d'afegir un interval dins d'un atribut");
        System.out.println("*	9. Provar d'afegir una llista d'intervals dins d'un atribut");
        System.out.println("*	10.Consultar la llista d'intervals d'un atribut");

        System.out.println("*********************************************************************");
    }

    public static void testConstructor() {
        System.out.println("*Constructora amb nom i tipus");
                try {
                    //System.out.println("Indiqui el nom de l'atributInt a crear:");
                    //String nom1 = lector.nextLine();
                    String nom1=llegeixString("Indiqui el nom de l'atributInt a crear:");
                    //System.out.println("Indiqui el tipus de l'atributInt a crear");
                    //String tipus1 = lector.nextLine();
                    String tipus1 = llegeixString("Indiqui el tipus de l'atributInt a crear");
                    Tipus tipuss1 = Tipus.valueOf(tipus1);
                    AtributInt atrIntNT = new AtributInt(nom1, tipuss1);
                    String nomA1 = atrIntNT.getNom();
                    Tipus tipusA1 = atrIntNT.getTipus();
                    System.out.println("Dades introduides -> Nom = " + nom1 + " tipus= " + tipus1);
                    System.out.println("Dades de l'atribut -> Nom = " + nomA1 + " tipus= " + tipusA1);
                }catch (Exception e) {
                    imprimeixError(e);
                }
    }

    public static void testGetNom() {
        try {
            String nom1 = llegeixString("Indiqui el nom de l'atributBool a crear:");
            AtributInt atr = new AtributInt(nom1, Tipus.FLOAT);
            System.out.println("El nom de l'atribut introduit es: " + nom1 + " i el nom de l'atribut es:" + atr.getNom());
        }catch (Exception e) {
            imprimeixError(e);
        }


    }

    public static void testGetTipus() {
        try {
            System.out.println("Es crea un AtributBool de tipus float:");
            AtributInt atr = new AtributInt("atri", Tipus.FLOAT);
            System.out.println("El tipus de l'atribut introduit es: " + atr.getTipus());
        }catch (Exception e) {
            imprimeixError(e);
        }
    }

    public static void testSetNom() {
        try {
            //System.out.println("Indiqui el nom de l'atributInt a crear de tipus float:");
            //String nom1 = lector.nextLine();
            String nom1 = llegeixString("Indiqui el nom de l'atributInt a crear de tipus float:");
            AtributInt atr = new AtributInt("nomCreadora",Tipus.FLOAT);
            atr.setNom(nom1);
            System.out.println("El nom de l'atribut introduit es: " + nom1 + " i el nom de l'atribut es:" + atr.getNom());

        }catch (Exception e) {
            imprimeixError(e);
        }

    }

    public static void testSetTipus() {
        try {
            System.out.println("Es crea un AtributInt on s'ha d'introduir el tipus Float:");
            AtributInt atr = new AtributInt("atri",Tipus.FLOAT);
            atr.setTipus(Tipus.FLOAT);
            System.out.println("El tipus de l'atribut introduit es: " + atr.getTipus());
        }catch (Exception e) {
            imprimeixError(e);
        }
    }

    public static void testValidarValor() {
        try {
            System.out.println("Es creara un atributInt anomenat atr amb interval entre 5 i 10 anomenat aprovat ");
            AtributInt atr = new AtributInt("atr", Tipus.FLOAT);
            Interval in = new Interval(10, 5, true, true, "aprovat");
            atr.afegirInterval(in);
            String valor = llegeixString("Introdueix un valor per veure si esta dins l'interval: ");
            System.out.println("El valor introduit esta dins l'interval aprovat(5<=valor<=10)? " + atr.validarValor(valor));
        }catch (Exception e) {
            imprimeixError(e);
        }

    }

    public static void testProcessar() {
       try {
           System.out.println("Es creara un atributInt anomenat atr amb interval entre 5 i 10 anomenat aprovat ");
           AtributInt atr = new AtributInt("atr", Tipus.FLOAT);
           Interval in = new Interval(10, 5, true, true, "aprovat");

           atr.afegirInterval(in);
           //System.out.println("Introduim un valor entre 5 i 10 per veure el nom de l'interval: ");
           //String valor = lector.nextLine();
           String valor = llegeixString("Introduim un valor entre 5 i 10 per veure el nom de l'interval: ");
           System.out.println("El valor introduit esta dins l'interval anomentat: " + atr.processar(valor));
       }catch (Exception e) {
           imprimeixError(e);
       }
    }



    public static void testAfegirInterval() {
        try {

            System.out.println("Es creara un atributInt anomenat atr de tipus float ");
            AtributInt atr = new AtributInt("atr", Tipus.FLOAT);
            System.out.println("Es creara uncinterval per atr anomenat aprovat ");
            Interval in = new Interval(10, 5, true, true, "aprovat");

            atr.afegirInterval(in);
            ArrayList<Interval> arrayInt = new ArrayList<>(atr.getLlistaInterval());
            System.out.println("L'interval afegit te per nom, limit inferior, limit superior, interval tancat per sobre i interval tancat per sota: " +arrayInt.get(0).getNom()+ " " +arrayInt.get(0).getLimSup() + " "+arrayInt.get(0).getLimInf() + " "+arrayInt.get(0).getIgualSup()+ " "+arrayInt.get(0).getIgualInf() );
        }catch (Exception e) {
            imprimeixError(e);
        }
    }
    public static void testAfegirLlistaInterval() {
        try {
            System.out.println("Es creara un atributInt anomenat atr amb interval entre 5 i 10 anomenat aprovat i un suspes entre 0 i 5 sense incloure aquest ultim");
            AtributInt atr = new AtributInt("atr", Tipus.FLOAT);
            Interval in = new Interval(10, 5, true, true, "aprovat");
            Interval in2 = new Interval(5, 0, false, true, "suspes");
            HashSet<Interval> intervals = new HashSet<>();
            intervals.add(in);
            intervals.add(in2);
            atr.afegirLlistaInterval(intervals);
            ArrayList<Interval> arrayInt = new ArrayList<>(intervals);
            System.out.println("Els intervals afegits son: "+arrayInt.get(0).getNom() + " i tambe " + arrayInt.get(1).getNom());
        }catch (Exception e) {
            imprimeixError(e);
        }
    }
    public static void testGetLlistaInterval(){
        try {
            System.out.println("Es creara un atributInt anomenat atr amb interval entre 5 i 10 anomenat aprovat i un suspes entre 0 i 5 sense incloure aquest ultim");
            AtributInt atr = new AtributInt("atr", Tipus.FLOAT);
            Interval in = new Interval(10, 5, true, true, "aprovat");
            Interval in2 = new Interval(5, 0, false, true, "suspes");
            atr.afegirInterval(in);
            atr.afegirInterval(in2);

            ArrayList<Interval> arrayInt = new ArrayList<>(atr.getLlistaInterval());
            System.out.println("El primer interval afegit te per nom, limit inferior, limit superior, interval tancat per sobre i interval tancat per sota: " + arrayInt.get(0).getNom() + " " + arrayInt.get(0).getLimSup() + " " + arrayInt.get(0).getLimInf() + " " + arrayInt.get(0).getIgualSup() + " " + arrayInt.get(0).getIgualInf());
            System.out.println("El segon interval afegit te per nom, limit inferior, limit superior, interval tancat per sobre i interval tancat per sota: " + arrayInt.get(1).getNom() + " " + arrayInt.get(1).getLimSup() + " " + arrayInt.get(1).getLimInf() + " " + arrayInt.get(1).getIgualSup() + " " + arrayInt.get(1).getIgualInf());

        }catch (Exception e) {
            imprimeixError(e);
        }

    }
    public static void main(String[] args){
        imprimeix();
        //lector = new Scanner(System.in);
        //int opcion = lector.nextInt();
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
                    System.out.println("Prova de validarValor:");
                    testValidarValor();
                    break;
                case 7:
                    System.out.println("Prova de processar:");
                    testProcessar();
                    break;
                case 8:
                    System.out.println("Prova d'afegir interval:");
                    testAfegirInterval();
                    break;
                case 9:
                    System.out.println("Prova d'afegir llista d'intervals:");
                    testAfegirLlistaInterval();
                    break;
                case 10:
                    System.out.println("Prova d'obtenir llista d'intervals:");
                    testGetLlistaInterval();
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