package Drivers;


import prop.Domini.AtributString;


import java.util.HashSet;


public class Driver_AtributString extends AbstractDriver{

    private static AtributString atribut;

    private static void imprimeix() {
        System.out.println("*********************************************************************");
        System.out.println("* Benvingut! Estas al Driver_AtributString. Selecciona una de les seguents opcions:");
        System.out.println("*	0. Sortir");
        System.out.println("*	1. Provar les Constructores");
        System.out.println("*	2. Consultar nom de l'atribut");
        System.out.println("*	3. Consultar tipus de l'atribut");
        System.out.println("*	4. Assignar nom a l'atribut");
        System.out.println("*	5.Assignar tipus a l'atribut");
        System.out.println("*	6. Provar si un valor forma part d'un atribut(validarValor)");
        System.out.println("*	7. Provar del processat d'un atribut(processarValor)");
        System.out.println("*	8.Provar d'establir un valor com a possible en un atribut");
        System.out.println("*	9.Provar d'establir una llista de valors com a possibles en un atribut");
        System.out.println("*	10.Provar de la obtencio de llista de valors possibles");

        System.out.println("*********************************************************************");
    }

    public static void testConstructor() {
        //lector = new Scanner(System.in);
        System.out.println("Indiqui la creadora que vol testejar:");
        System.out.println("*	1. Constructora amb nom i tipus");
        System.out.println("*	2. Constructora amb nom, tipus i llista de valors possibles");

        //int opcion = lector.nextInt();
        int opcion =llegeixInt("");
        switch (opcion) {

            case 1:
                try {
                    //System.out.println("Indiqui el nom de l'atributInt a crear:");
                    //String nom1 = lector.nextLine();
                    String nom1 = llegeixString("Indiqui el nom de l'atributString a crear:");
                    //System.out.println("Indiqui el tipus de l'atributInt a crear");
                    //String tipus1 = lector.nextLine();
                    String tipus1 = llegeixString("Indiqui el tipus de l'atributString a crear:");
                    AtributString atrStringNT = new AtributString(nom1, tipus1);
                    String nomA1 = atrStringNT.getNom();
                    String tipusA1 = atrStringNT.getTipus();
                    System.out.println("Dades introduides -> Nom = " + nom1 + " tipus= " + tipus1);
                    System.out.println("Dades de l'atribut -> Nom = " + nomA1 + " tipus= " + tipusA1);
                }catch (Exception e) {
                imprimeixError(e);
            }

                break;
            case 2:
                try{
                String nom2 = llegeixString("Indiqui el nom de l'atributIntString a crear:");
                String tipus2 = llegeixString("Indiqui el tipus de l'atributIntString a crear:");
                String val1 = llegeixString("Indiqui el primer valor possible de l'atribut:");
                String val2 = llegeixString("Indiqui el segon valor possible de l'atribut:");

                HashSet<String> valors = new HashSet<>();
                valors.add(val1);
                valors.add(val2);
                AtributString atrStringNTV = new AtributString(nom2, tipus2, valors);
                String nomA2 = atrStringNTV.getNom();
                String tipusA2 = atrStringNTV.getTipus();
                System.out.println("Dades introduides -> Nom = " + nom2 + " tipus= " + tipus2 + "valors possibles: "+ val1+ " " + val2);
                System.out.println("Dades de l'atribut -> Nom = " + nomA2 + " tipus= " + tipusA2 + "valors possibles:" + atrStringNTV.getValorsPos());
                } catch (Exception e) {
                    imprimeixError(e);
                }

                break;
            default:
                System.out.println("La opcio escollida no existeix!");
                break;

        }

    }

    public static void testGetNom() {
        try {
            String nom1 = llegeixString("Indiqui el nom de l'atributString a crear:");
            AtributString atr = new AtributString(nom1, "String");
            System.out.println("El nom de l'atribut introduit es: " + nom1 + "i el nom de l'atribut es:" + atr.getNom());
        }catch (Exception e) {
            imprimeixError(e);
        }
    }

    public static void testGetTipus() {
        try {
            System.out.println("Es crea un AtributString de tipus String");
            AtributString atr = new AtributString("atri", "String");
            System.out.println("El tipus de l'atribut introduit es: " + atr.getTipus());
        }catch (Exception e) {
            imprimeixError(e);
        }
    }

    public static void testSetNom() {
        try {
            //System.out.println("Indiqui el nom de l'atributInt a crear de tipus String:");
            //String nom1 = lector.nextLine();
            String nom1 = llegeixString("Indiqui el nom de l'atributInt a crear de tipus String:");
            AtributString atr = new AtributString("nomCreadora");
            atr.setNom(nom1);
            atr.setTipus("String");
            System.out.println("El nom de l'atribut introduit es: " + nom1 + " i el nom de l'atribut es:" + atr.getNom());

        }catch (Exception e) {
        imprimeixError(e);
    }

}

    public static void testSetTipus() {
        try{
        System.out.println("Es crea un AtributInt on s'ha d'introduir el tipus String:");
        AtributString atr = new AtributString("atri");
        atr.setTipus("String");
        System.out.println("El tipus de l'atribut introduit es: "+ atr.getTipus());
        } catch (Exception e) {
            imprimeixError(e);
        }
    }

    public static void testValidarValor() {
        try{
        System.out.println("Es creara un atributString anomenat atr amb valors possibles Malalt i Sa ");
        AtributString atr = new AtributString("atr", "String");
        HashSet<String> valors = new HashSet<>();
        valors.add("Sa");
        valors.add("Malalt");
        atr.setValorsPos(valors);
        //System.out.println("Introdueix un valor per veure si és un dels valors possibles: ");
        //String valor = lector.nextLine();
        String valor = llegeixString("Introdueix un valor per veure si es un dels valors possibles: ");
        System.out.println("El valor introduit pertany a atr? "+ atr.validarValor(valor));
        } catch (Exception e) {
        imprimeixError(e);
         }

    }

    public static void testProcessar(){
        System.out.println("La funcio processar al ser metode abstracte, per la classe Atribut String nomes retorna el valor que se li passa per parametre. ");

    }



    public static void testAfegirValorPossible() {
        try{
        System.out.println("Es creara un atributInt anomenat atr de tipus String ");
        AtributString atr = new AtributString("atr", "String");
        //System.out.println("Indiqui el valor possible a afegir ");
        //String valor = lector.nextLine();
        String valor = llegeixString("Indiqui el valor possible a afegir ");
        atr.afegirValorPossible(valor);
        System.out.println("Dades de l'atribut -> Nom = " + atr.getNom() + " tipus= " + atr.getTipus() + "valor possible:" + atr.getValorsPos());
        } catch (Exception e) {
            imprimeixError(e);
        }

    }
    public static void testSetValorsPos() {
        try{
        System.out.println("Es creara un atributInt anomenat atr de tipus String ");
        AtributString atr = new AtributString("atr", "String");
        //System.out.println("Indiqui 3 valors possible a assignar ");
        //String valor = lector.nextLine();
        String valor = llegeixString("Indiqui el primer valor possible a assignar ");
       //String valor1 = lector.nextLine();
        //String valor2 = lector.nextLine();
        String valor1 = llegeixString("Indiqui el segon valor possible a assignar ");
        String valor2 = llegeixString("Indiqui el tercer valor possible a assignar ");
        HashSet<String> valors = new HashSet<>();
        valors.add(valor);
        valors.add(valor1);
        valors.add(valor2);
        atr.setValorsPos(valors);
        System.out.println("Dades de l'atribut -> Nom = " + atr.getNom() + " tipus= " + atr.getTipus() + "valors possibles:" + atr.getValorsPos());
        } catch (Exception e) {
            imprimeixError(e);
        }

    }
    public static void testgetValorsPos() {
        try {
            System.out.println("Es creara un atributInt anomenat atr de tipus String ");
            AtributString atr = new AtributString("atr", "String");
            System.out.println("S'afegiran els valors Alt, Mitja i Baix ");
            String valor = "Alt";
            String valor1 = "Mitja";
            String valor2 = "Baix";
            HashSet<String> valors = new HashSet<>();
            valors.add(valor);
            valors.add(valor1);
            valors.add(valor2);
            atr.setValorsPos(valors);
            System.out.println("Dades de l'atribut -> Nom = " + atr.getNom() + " tipus= " + atr.getTipus() + "valors possibles:" + atr.getValorsPos());
        } catch (Exception e) {
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
                    System.out.println("Prova d'afegir valor possible:");
                    testAfegirValorPossible();
                    break;
                case 9:
                    System.out.println("Prova d'afegir llista de valors possibles:");
                    testSetValorsPos();
                    break;
                case 10:
                    System.out.println("Prova d'obtenir llista de valors possibles:");
                    testgetValorsPos();
                    break;


                default:
                    System.out.println("La opcio escollida no existeix!");
                    break;

            }
            if(opcion!= 0) {
                imprimeix();
                opcion = llegeixInt("Selecciona la nova funcionalitat a provar");
            }
        }

    }
}