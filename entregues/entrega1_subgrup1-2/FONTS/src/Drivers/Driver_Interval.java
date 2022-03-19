package Drivers;

import prop.Domini.CjtAtributs;
import prop.Domini.Interval;

public class Driver_Interval extends AbstractDriver{
    private static Interval inter;

    private static void imprimeix() {
        System.out.println("*********************************************************************");
        System.out.println("* Benvingut! Estas al Driver_AtributString. Selecciona una de les seguents opcions:");
        System.out.println("*	0. Sortir");
        System.out.println("*	1. Provar les Constructores");
        System.out.println("*	2. Prova les consultores del interval");
        System.out.println("*	3. Provar si un valor es dins l'interval");
        System.out.println("*	4. Prova dels setters de l'interval");
        System.out.println("*********************************************************************");
    }
    public static void testConstructor() {

        System.out.println("Indiqui la creadora que vol testejar:");
        System.out.println("*	0. Constructora per defecte");
        System.out.println("*	1. Constructora amb nom i limits");
        int opcion =llegeixInt("");
        switch (opcion) {
            case 0:
                try {
                    inter = new Interval();
                    System.out.println("Correcto");
                } catch (Exception e) {
                    imprimeixError(e);
                }

                break;
            case 1:
                System.out.println("* Constructora amb nom i limits");
                try {

                    String nom1 = llegeixString("Indiqui el nom de l'interval a crear:");
                    float lims = llegeixFloat("Indiqui el limit superior de l'interval a crear:");
                    float limi = llegeixFloat("Indiqui el limit inferior de l'interval a crear:");
                    boolean igualsup = llegeixBool("Indiqui si el interval inclou el limit superior:(true/false)");
                    boolean igualinf = llegeixBool("Indiqui si el interval inclou el limit inferior:(true/false)");
                    Interval in = new Interval(lims, limi, igualsup, igualinf, nom1);
                    String nom2 = in.getNom();
                    float lims2 = in.getLimSup();
                    float limi2 = in.getLimInf();
                    boolean igualsup2 = in.getIgualSup();
                    boolean igualinf2 = in.getIgualInf();
                    System.out.println("Dades introduides -> Nom = " + nom1 + " limit superior= " + lims + " limit inferior= " + limi + " igual al limit superior= " + igualsup + " igual al limit inferior= " + igualinf);
                    System.out.println("Dades de l'atribut -> Nom = " + nom2 + " limit superior= " + lims2 + " limit inferior= " + limi2 + " igual al limit superior= " + igualsup2 + " igual al limit inferior= " + igualinf2);
                } catch (Exception e) {
                    imprimeixError(e);
                }
                break;
            default:
                System.out.println("La opcio escollida no existeix!");
                break;
        }
    }

    public static void testGetLimits(){
        System.out.println("Creem un interval anomenat aprovat de 5 a 10 ambdos inclosos");
        Interval in = new Interval(10,5,true,true,"aprovat");
        System.out.println("Ara obtenim tots el limits de l'interval: "+ in.getNom());
        System.out.println("Limit superior: " + in.getLimSup()+" Limit inferior: "+ in.getLimInf()+ " Limit superior inclos? "+ in.getIgualSup()+ " Limit inferior inclos? "+ in.getIgualInf());
    }
    public static void testSetLimits(){
        System.out.println("Creem un interval anomenat aprovat de 5 a 10 ambdos inclosos");
        Interval in = new Interval(10,5,true,true,"aprovat");
        Interval in2 = in;
        in2.setNom(llegeixString("Indiqui el nom a introduir"));
        in2.setLimSup(llegeixFloat("Indiqui el limit superior"));
        in2.setLimInf(llegeixFloat("Indiqui el limit inferior"));
        in2.setIgualSup(llegeixBool("Indiqui si el limit superior esta inclos"));
        in2.setIgualInf(llegeixBool("Indiqui si el limit inferior esta inclos"));
        System.out.println("Ara introduim els limits a afegir: "+ in.getNom());
        System.out.println("Limits inicials: Limit superior: " + in.getLimSup()+" Limit inferior: "+ in.getLimInf()+ " Limit superior inclos? "+ in.getIgualSup()+ " Limit inferior inclos? "+ in.getIgualInf());
        System.out.println("Limits actualitzats: Limit superior : " + in2.getLimSup()+" Limit inferior: "+ in2.getLimInf()+ " Limit superior inclos? "+ in2.getIgualSup()+ " Limit inferior inclos? "+ in2.getIgualInf());

    }

    public static void testEstaDins() {
        try{
            System.out.println("Creem un interval amb nom aprovat entre 5 i 10 ambdos inclosos");
            Interval in = new Interval(10,5,true,true,"aprovat");
            //System.out.println("Introdueix un valor per veure si hi és dins:");
            //String valor = lector.nextLine();
            String valor = llegeixString("Introdueix un valor per veure si hi es dins:");
            System.out.println("Esta "+ valor + " entre 5 i 10? "+in.estadins(valor));
        } catch (Exception e) {
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
                    System.out.println("Prova de la obtencio del nom i limits");
                    testGetLimits();
                    break;
                case 3:
                    System.out.println("Prova de si un valor es dins l'interval:");
                    testEstaDins();
                    break;
                case 4:
                    System.out.println("Prova d'afegir limits i nom de un interval");
                    testSetLimits();
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
