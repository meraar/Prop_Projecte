package prop.Domini;
import prop.Persistencia.CtrlPersistencia;
import prop.Persistencia.FitxerDades;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Carles Capilla
 */
public class CtrlAtributs {
    /**
     * Controlador de persistencia
     */
    private CtrlPersistencia persistencia;

    /**
     * Path del fitxer de dades
     */
    private String path;


    public CtrlAtributs() { }


    /**
     * Constructora per defecte
     *
     * @param persistencia Controlador de persistencia
     */
    public CtrlAtributs(CtrlPersistencia persistencia) {
        this.persistencia = persistencia;
    }


    /**
     * Converteix el conjunt de atributs en un hasHMap de String String per poder consultar los al driver
     * @return HashMap de String String amb els nom i tipus del atribut
     */
    public LinkedHashMap<String,String> consultarAtributs(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();

        for(Atribut atribut : CjtAtributs.getInstance().getLlistAtri()){
           if(atribut.getEliminat())continue;

           String tipus =  atribut.getTipus().toString();
            map.put(atribut.getNom(),tipus);
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
     * @return
     */
    public ArrayList<String> generarAtributs(String pathFitxer) throws Exception {
        FitxerDades fitxer;
        fitxer = persistencia.llegirFitxerDades(pathFitxer);
        path = pathFitxer;
        ArrayList<String> llistAtriNom = fitxer.getHeader();

        /*en llistAtri tenemos un arrayList de String con cada uno
         de los atributos del fichero especificados en la cabecera
         */
        ArrayList<Atribut> llistAtri = new ArrayList<>();
        for (String nom : llistAtriNom) {
            AtributString atribut = new AtributString(nom);
            llistAtri.add(atribut);

        }
        CjtAtributs.getInstance().setLlistAtri(llistAtri);
        return llistAtriNom;
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
     * @throws Exception Error de lectura del fitxer
     */
    public void induirPreprocesDefecte(String pathFitxer) throws Exception {
        ArrayList<Preproces> defecte = new ArrayList<>();
        FitxerDades fitxer;
        fitxer = persistencia.llegirFitxerDades(pathFitxer);

        for (int i = 0; CjtAtributs.getInstance().getLlistAtri().size() > i; ++i) {
            Tipus tipus = CjtAtributs.getInstance().getLlistAtri().get(i).getTipus();

            switch (tipus) {
                case STRING:
                    defecte.add(new PreprocesString());
                    break;
                case FLOAT:
                    defecte.add(new PreprocesInt());
                    break;
                case BOOL:
                    defecte.add(new PreprocesBool());
                    break;
            }
        }
        ArrayList<String> valors = fitxer.llegirValors();
        while (!valors.isEmpty()) {

            for (int i = 0; CjtAtributs.getInstance().getLlistAtri().size() > i; ++i) {
                Atribut atr = CjtAtributs.getInstance().getLlistAtri().get(i); //agafem instància atribut de la posició i
                Preproces pre = defecte.get(i);//agafem instancia preproces a aplicar
                String valor = valors.get(i);//agafem valor per crear preproces

                if (atr.getTipus().equals(Tipus.FLOAT)) {
                    // el nostre atribut és un float
                    //atr = (AtributInt) atr;
                    float v = Float.parseFloat(valor);
                    ((PreprocesInt) pre).afegirValor(v);
                }
                // el nostre atribut és un string
                if (atr.getTipus().equals(Tipus.STRING)) ((AtributString) atr).afegirValorPossible(valor);

                // el nostre atribut és un bool
                if (atr.getTipus().equals(Tipus.BOOL)) ((PreprocesBool) pre).assignarBool(valor);
            }
            valors = fitxer.llegirValors();
        }
    }

    /**
     * Afegeix els valors possibles de un atribut de tipus bool i el atribut al conjunt de atributs posteriorment
     * @param valors son els dos valors que pot prendre el atribut
     * @param nom nom del atribut dins el fitxer
     * @throws Exception Error de creacio de nou atribut
     */
    public void generarPreprocesBool(ArrayList<String> valors,String nom) throws Exception {
        AtributBool atr = new AtributBool(nom,Tipus.BOOL);
        atr.setValorsB(valors);
        CjtAtributs.getInstance().updateAtri(nom,atr);
    }

    /**
     * Afegeix els valors possibles de un atribut de tipus String i el atribut al conjunt de atributs posteriorment
     * @param valString son tots els valors possibles que pot prendre el atribut
     * @param nom nom del atribut dins el fitxer
     */
   public void generarPreprocesString(HashSet<String> valString,String nom){
       AtributString atr = new AtributString(nom);
       atr.setValorsPos(valString);
       CjtAtributs.getInstance().updateAtri(nom,atr);
    }

    /**
     * Afegeix tots els intervals declarats per el usuari al atribut i posteriorment afegeix el atribut al conjunt de atributs
     * @param intervals intervals definits per el usuari
     * @param nom nom del atribut en el fitxer
     * @throws Exception Error en la creacio de un nou atribut Int o en afegir un interval al atribut
     */
    public void generarPreprocesInt(ArrayList<ArrayList<String>> intervals,String nom) throws Exception {
        AtributInt atr = new AtributInt(nom,Tipus.FLOAT);
        String nomI;
        boolean limInfInc,limSupInc;
        Float limInf,limSup;
        for(int i=0;i<intervals.size();++i){
            if(intervals.get(i).get(0).equals("INFP"))limSup = Interval.INFP;
            else limSup = Float.parseFloat(intervals.get(i).get(0));
            if(intervals.get(i).get(1).equals("INFN"))limInf = Interval.INFN;
            else limInf = Float.parseFloat(intervals.get(i).get(1));
            limInfInc = Boolean.parseBoolean(intervals.get(i).get(3));
            limSupInc = Boolean.parseBoolean(intervals.get(i).get(2));
            nomI = intervals.get(i).get(4);
            Interval inter = new Interval(limSup,limInf,limSupInc,limInfInc,nomI);
            atr.afegirInterval(inter);
        }

        CjtAtributs.getInstance().updateAtri(nom,atr);
    }
    //public void generarPreproces(String pathFitxer) throws Exception {
       // Atribut atr = atributs.getAtri();
   // }

    public void eliminarAtribut(String nomAtr){
        AtributString atr = new AtributString(nomAtr);
        CjtAtributs.getInstance().deleteAtri(nomAtr);
    }

    public HashSet<String> consultarAtributsEliminats() {

        HashSet<String> eliminats = new HashSet<>();
        for (int i = 0; i <  CjtAtributs.getInstance().getLlistAtri().size(); ++i) {
            if ( CjtAtributs.getInstance().getLlistAtri().get(i).getEliminat()) {
                eliminats.add( CjtAtributs.getInstance().getLlistAtri().get(i).getNom());
            }

        }
        return eliminats;
    }

    /**
     * Getter del path del fitxer de dades csv
     * @return el path del fitxer
     */
    public String getPath(){
        return path;
    }
}



