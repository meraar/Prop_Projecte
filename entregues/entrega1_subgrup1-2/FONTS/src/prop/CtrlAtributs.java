package prop;

import prop.Domini.*;
import prop.Persistencia.CtrlPersistencia;
import prop.Persistencia.FitxerDades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CtrlAtributs {
    /**
     * Conjunt de atributs del sistema
     */
    private CjtAtributs atributs;
    /**
     * Controlador de persistencia
     */
    private CtrlPersistencia persistencia;
    private CtrlPresentacio presentacio;


    public CtrlAtributs() {
    }

    /**
     * Constructora per defecte
     *
     * @param persistencia Controlador de persistencia
     */
    public CtrlAtributs(CtrlPersistencia persistencia,CtrlPresentacio presentacio) {
        this.presentacio = presentacio;
        this.persistencia = persistencia;
    }

    /**
     * Llegeix el tipus que vol assignar el usuari a un atribut
     * @return Tipus del atribut del fitxer indicat pel usuari
     * @throws IOException Error de entrada de dades
     */
    private String llegirTipus() throws IOException {

        String tipus = this.presentacio.llegeixString("");
        while (!tipus.equals("float") & !tipus.equals("String") & !tipus.equals("boolean"))
            tipus = this.presentacio.llegeixString("TIPUS D'ATRIBUT INCORRECTE\nIntrodueix de nou el tipus de l'atribut(float/String/boolean):");

        return tipus;
    }

    /**
     * Llegeix els valors possibles dun atribut de tipus String
     * @param nomAtr nom del atribut del que es volen rebre els valors possibles
     * @return Llista sense repetits de valors possibles que ha de prendre el atribut
     * @throws IOException Error de entrada de dades
     */
    private HashSet<String> llegirValorsString(String nomAtr) throws IOException {
        HashSet<String> valors = new HashSet<>();
        String line;
        System.out.println("Introdueix els valors possibles per l'atribut " + nomAtr + " separats per intro i END per indicar que no en queden:");

        while (!(line = this.presentacio.llegeixString("")).equals("END")) {
            valors.add(line);
        }

        return valors;
    }

    /**
     * Llegeix els intervals que vol definir el usuari en un atribut tipus float
     * @param nomAtr Nom del atribut en el que vol definir la llista de intervals
     * @return Llista de intervals a assignar al atribut
     * @throws IOException Error de entrada sortida
     */
    private ArrayList<Interval> llegirValorsInt(String nomAtr) throws IOException {
        float limS, limI;
        boolean igualS, igualI;
        String nom;
        System.out.println("Introdueix el nom de l'interval a afegir per l'atribut " + nomAtr + ":");
        nom = this.presentacio.llegeixString("");
        ArrayList<Interval> intervals = new ArrayList<>();

        while (!nom.equals("END")) {
            Interval inter = new Interval();
            inter.setNom(nom);

            System.out.println("Introdueix el limit superior:");
            limS = this.presentacio.llegeixFloat("");
            inter.setLimSup(limS);

            System.out.println("Introdueix el limit inferior:");
            limI = this.presentacio.llegeixFloat("");
            inter.setLimInf(limI);


            System.out.println("Valor <= limit superior(true/false):");
            igualS = this.presentacio.llegeixBool("");
            inter.setIgualSup(igualS);

            System.out.println("Valor >= limit inferior(true/false):");
            igualI = this.presentacio.llegeixBool("");
            inter.setIgualInf(igualI);

            intervals.add(inter);
            System.out.println("Si no vols afegir un nou interval escriu END, en cas contrari introdueix el seu nom:");
            nom = this.presentacio.llegeixString("");
        }
        return intervals;
    }

    /**
     * Llegeix els valors a considerar fals i cert per un atribut de tipus boolean
     * @param nomAtr Nom del atribut del qual obtenir els valors
     * @return Llista de dos elements amb el valor a considerar fals seguit del cert
     * @throws IOException Error de entrada sortida
     */
    private ArrayList<String> llegirValorsBool(String nomAtr) throws IOException {
        System.out.println("Introdueix el valor a considerar fals i despres el cert per l'interval " + nomAtr + ":");// fi lectura incerta
        ArrayList<String> val = new ArrayList<>();

        val.add(this.presentacio.llegeixString(""));
        val.add(this.presentacio.llegeixString(""));
        return val;
    }

    /**
     * Converteix el conjunt de atributs en un hasHMap de String String per poder consultar los al driver
     * @return HashMap de String String amb els nom i tipus del atribut
     */
    public HashMap<String,String> consultarAtributs(){
        HashMap<String,String> map = new HashMap<>();
        for(Atribut atribut : atributs.getLlistAtri()){
            map.put(atribut.getNom(),atribut.getTipus());
        }
        return map;
    }

    /**
     * Converteix el preproces dels atributs en un hasHMap de String String per poder consultar los al driver
     * @return HashMap de String String amb els nom i preproces del atribut
     * @throws Exception Error en la obtencio de valors boolean
     */
    public HashMap<String, ArrayList<String>> consultarPreproces() throws Exception {
        HashMap<String,ArrayList <String>> map = new HashMap<>();

        for (Atribut atr : atributs.getLlistAtri()) {
            ArrayList<String> preproces = new ArrayList<>();

            switch (atr.getTipus()) {
                case "String":
                   AtributString atributS = (AtributString)atr;
                    preproces.addAll(atributS.getValorsPos());
                    break;

                case "float":
                    AtributInt atributI = (AtributInt) atr;
                    for(Interval inter : atributI.getLlistaInterval()) {
                        preproces.add("Nom=" + inter.getNom() + " Limit superior=" + Float.toString(inter.getLimSup()) + " Limit inferior=" + Float.toString(inter.getLimInf()) + " Limit superior inclos=" + Boolean.toString(inter.getIgualSup()) + " Limit inferior inclos=" + Boolean.toString(inter.getIgualInf()));
                    }
                    break;

                case "boolean":
                    AtributBool atributB = (AtributBool) atr;
                    ArrayList<String> vals= atributB.getValorsB();
                    preproces.add("Valor fals: " +vals.get(0)+ " valor cert: "+vals.get(1));

                    break;
            }

            map.put(atr.getNom(), preproces);
        }

        return map;
    }

    /**
     * Classe auxiliar per a tenir operacions pels preprocessos per defecte
     */
    abstract class Preproces {
    }

    /**
     * Classe filla de preproces utilitzable pels processos de atributs de tipus boolean
     */
    private class PreprocesBool extends Preproces{
        /**
         * Valor a considerar cert en un preproces de atribut boolean
         */
        private String ValorTrue;
        /**
         * Valor a considerar fals en un preproces de atribut boolean
         */
        private String ValorFalse;

        /**
         * Assigna el valor passat per parametre com a valor considerat cert o fals entre una serie de possibles valors
         * si el valor no es igual a cap, es assigna de manera random
         * @param valor valor a assignar com a cert o fals
         */
        public void assignarBool(String valor) {

            if (valor.equals("Yes") || valor.equals("YES") || valor.equals("yes") || valor.equals("Si") || valor.equals("SI") || valor.equals("si") ||valor.equals("True") || valor.equals("TRUE") || valor.equals("true")||  valor.equals("Verdadero") || valor.equals("VERDADERO") || valor.equals("verdadero") || valor.equals("Cert") || valor.equals("CERT") || valor.equals("cert"))
                this.ValorTrue = valor;
            else if (valor.equals("No") || valor.equals("NO") || valor.equals("no") || valor.equals("False") || valor.equals("FALSE") || valor.equals("false") || valor.equals("Falso") || valor.equals("FALSO") || valor.equals("falso") || valor.equals("Fals") || valor.equals("FALS") || valor.equals("fals")) {
                this.ValorFalse = valor;
            } else {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 1);
                if (randomNum == 0) this.ValorFalse = valor;
                else this.ValorTrue = valor;
            }
        }
    }

    /**
     * Classe filla de preproces utilitzable pels processos de atributs de tipus String
     */
    private class PreprocesString extends Preproces {
    }

    /**
     * Classe filla de preproces utilitzable pels processos de atributs de tipus float
     */
    private class PreprocesInt extends Preproces{

        /**
         * Suma de tots els valors del atribut de tipus float del fitxer
         */
        private float sumaTotal;
        /**
         * Nombre de elements de un atribut de tipus float
         */
        private float numElemnts;

        /**
         * Suma tots els valors de un atribut de un fitxer i a la vegada compta els cops que fa la suma que seran el nombre de valors
         * @param valor valor a sumar
         */
        public void afegirValor(float valor) {
            sumaTotal += valor;

            numElemnts++;
        }

        /**
         * Fa la mitja aritmetica de el atribut al que es aplica el preproces
         * @return mitjana aritmetica de un atribut en el fitxer
         * @throws Exception Nombre de elements es 0
         */
        public float getMitja() throws Exception {
            if(numElemnts==0) throw new Exception("Nombre d'elements es 0");
            return sumaTotal/numElemnts;
        }
    }

    /**
     * Genera un conjunt de atributs a partir del header del fitxer
     * @param pathFitxer path del fitxer des del que es volen treure els atributs
     * @throws Exception Error de lectura del fitxer
     */
    public void generarAtributs(String pathFitxer) throws Exception {
        FitxerDades fitxer;
        fitxer = persistencia.llegirFitxerDades(pathFitxer);
        ArrayList<String> llistAtriNom = fitxer.getHeader();

        /*en llistAtri tenemos un arrayList de String con cada uno
         de los atributos del fichero especificados en la cabecera
         */
        ArrayList<Atribut> llistAtri = new ArrayList<>();
        for (String nom : llistAtriNom) {
            AtributString atribut = new AtributString(nom);
            llistAtri.add(atribut);

        }
        this.atributs = new CjtAtributs(llistAtri);
    }

    /**
     * Es defineixen els tipus dels atributs generats a la funcio generarAtributs
     * @throws IOException Error de lectura de llegirTipus
     */
    public void definirTipus() throws Exception {
        int i = 0;
        ArrayList<Atribut> listAtr = atributs.getLlistAtri();
        ArrayList<Atribut> listAtraux = new ArrayList<>();
        while (i < atributs.getLlistAtri().size()) {
            //demanar a usuari tipus
            System.out.println("Introdueix el tipus de l'atribut: " + listAtr.get(i).getNom() + "\n(float/String/boolean)");
            String tipus = llegirTipus();
            switch (tipus) {
                case "String":
                    listAtraux.add(new AtributString(listAtr.get(i).getNom(), tipus));
                   break;
                case "boolean":
                    listAtraux.add(new AtributBool(listAtr.get(i).getNom(), tipus));
                    break;
                case "float":
                    listAtraux.add(new AtributInt(listAtr.get(i).getNom(), tipus));
                    break;
            }
            ++i;
        }
        atributs.setLlistAtri(listAtraux);
    }

    /**
     * (OPC)Per cada atribut del CjtAtributs es indueix un Preprocés automàtic (NO IMPLEMENTAT ENCARA)
     *      segons el contingut de les dades.
     *      Si String-> tots valors=possibles
     *      Si float-> fer mitja i definir dos intervals
     *      Si boolean-> es plantejen una serie de valors possibles com a cert o fals,
     *                   si els valors no coincideixen amb cap dells s assignen de manera aleatoria
     *
     * @param pathFitxer Path del fitxer a analitzar
     * @param atributs Conjunt de atributs amb nom i tipus definits anteriorment
     * @throws Exception Error de lectura del fitxer
     */
    public void induirPreprocesDefecte(String pathFitxer, CjtAtributs atributs) throws Exception {
        ArrayList<Preproces> defecte = new ArrayList<>();
        FitxerDades fitxer;
        fitxer = persistencia.llegirFitxerDades(pathFitxer);

        for (int i = 0; atributs.getLlistAtri().size() > i; ++i) {
            String tipus = atributs.getLlistAtri().get(i).getTipus();

            switch (tipus) {
                case "String":
                    defecte.add(new PreprocesString());
                    break;
                case "float":
                    defecte.add(new PreprocesInt());
                    break;
                case "boolean":
                    defecte.add(new PreprocesBool());
                    break;
            }
        }
        ArrayList<String> valors = fitxer.llegirValors();
        while (!valors.isEmpty()) {

            for (int i = 0; atributs.getLlistAtri().size() > i; ++i) {
                Atribut atr = atributs.getLlistAtri().get(i); //agafem instància atribut de la posició i
                Preproces pre = defecte.get(i);//agafem instancia preproces a aplicar
                String valor = valors.get(i);//agafem valor per crear preproces

                if (atr.getTipus().equals("float")) {
                    // el nostre atribut és un float
                    atr = (AtributInt) atr;
                    pre = (PreprocesInt) pre;
                    Float v = Float.valueOf(valor);
                    ((PreprocesInt) pre).afegirValor(v);
                }
                if (atr.getTipus().equals("String")) {
                    // el nostre atribut és un string
                    atr = (AtributString) atr;
                    //abans de afegir comprobar que no sigui duplicat
                    ((AtributString) atr).afegirValorPossible(valor);
                }

                if (atr.getTipus().equals("boolean")) {
                    // el nostre atribut és un bool
                    atr = (AtributBool) atr;
                    pre = (PreprocesBool) pre;
                    ((PreprocesBool) pre).assignarBool(valor);
                }
            }
            valors = fitxer.llegirValors();
        }
    }

    /**
     *Genera el preproces de cada atribut
     * si atribut de tipus float llavors introdueix tos els intervals
     * si atribut de tipus String llavors introdueix tots els valors possibles
     * si atribut de tipus boolean llavors assigna valors com a fals i cert
     * @param pathFitxer Path del fitxer a analitzar
     * @throws Exception Errors de lectura
     */
    public void generarPreproces(String pathFitxer) throws Exception {


        FitxerDades fitxer;
        fitxer = persistencia.llegirFitxerDades(pathFitxer);
        //String val1 = null, val2 = null;
        for (int i = 0; atributs.getLlistAtri().size() > i; ++i) {
            Atribut atr = atributs.getLlistAtri().get(i);
            String tipus = atr.getTipus();
            String nom = atr.getNom();
            switch (tipus) {
                case "String":
                    AtributString atrS = (AtributString) atr;
                    HashSet<String> valString = new HashSet<>();
                    valString = llegirValorsString(nom);
                    atrS.setValorsPos(valString);

                    break;
                case "float":
                    AtributInt atrI = (AtributInt) atr;
                    atrI.afegirLlistaInterval(llegirValorsInt(nom));

                    break;
                case "boolean":
                    AtributBool atrB = (AtributBool) atr;
                    atrB.setValorsB(llegirValorsBool(nom));

                    break;
            }
        }
    }

}



