package Drivers;

import prop.CtrlAtributs;
import prop.Persistencia.CtrlPersistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver_CtrlAtributs extends AbstractDriver{

    private static CtrlPersistencia per;
    private static CtrlAtributs ctrlAtr;



    public static void testConstructor() {

        System.out.println("*	1. Constructora amb control de persistencia");
                    try {
                        per = new CtrlPersistencia();
                        CtrlPresentacioStub pres = new CtrlPresentacioStub(AbstractDriver.scanner);
                        ctrlAtr = new CtrlAtributs(per,pres);
                        System.out.println("Correcto");
                    } catch (Exception e) {
                        imprimeixError(e);
                    }
    }
    public static void testGenerarAtributs(String path){
        try{
            ctrlAtr.generarAtributs(path);
            for(Map.Entry<String,String> atribut: ctrlAtr.consultarAtributs().entrySet()){
                System.out.println(atribut.getKey());
            }
            System.out.println("Atributs generats");

        } catch (Exception e) {
            imprimeixError(e);
        }

    }
    public static void testDefinirTipus() {

        try {

            ctrlAtr.definirTipus();
            System.out.println("Llista d'atributs NOM i TIPUS");
            for(Map.Entry<String,String> atribut: ctrlAtr.consultarAtributs().entrySet()){
                System.out.println(atribut.getKey()+ " "+atribut.getValue());
            }
        }catch(Exception e){
            imprimeixError(e);
        }
    }

    public static void testGenerarPreproces(String path) {
        try {
            ctrlAtr.generarPreproces(path);
            System.out.println("Preproces generat");
            HashMap<String, ArrayList<String>> pre = ctrlAtr.consultarPreproces();

            for(Map.Entry<String,ArrayList<String>> atribut: ctrlAtr.consultarPreproces().entrySet()){
                System.out.println(atribut.getKey()+ " "+atribut.getValue());
            }

        }catch(Exception e){
            imprimeixError(e);
        }
    }


    public static void main(String[] args) {

        System.out.println("*********************************************************************");
        System.out.println("* Benvingut! Estas al Driver_CtrlAtributs.");
        System.out.println("*********************************************************************");

        System.out.println("Prova de les constructores");
        testConstructor();

        String path  = llegeixString("Introduce el path del fichero");



        System.out.println("Prova del generador d'atributs:");
        testGenerarAtributs(path);

        System.out.println("Prova de la definicio de tipus d'atributs");
        testDefinirTipus();

        System.out.println("Prova de la generacio del preprocés");
        testGenerarPreproces(path);


    }

}
