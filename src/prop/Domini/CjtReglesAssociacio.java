package prop.Domini;

import java.io.Serializable;
import java.util.*;

/**
 * @author Muhammad Meraj Arshad
 */
public class CjtReglesAssociacio implements Serializable {

    /**
     *   Indentificador del Conjunt de Regles Associacio
     */
    private Integer idCjtRegles;

    /**
     *  Preproces dels Atributs
     */
    private CjtAtributs preproces;

    /**
     *  Data de la creacio del CtjReglesAssociacio
     */
    private Date data;

    /**
     *  Llistat de ReglaAssociacio
     */
    private  ArrayList<ReglaAssociacio> LlistRegAss;

    /**
     *
     */
    private static CjtReglesAssociacio instance = null;

    /**
     *
     * @return Conjunt de Regles Associciacio
     */
    public static CjtReglesAssociacio getInstance() {
        if (instance == null) {
            Random rand = new Random();
            instance = new CjtReglesAssociacio(rand.nextInt());
        }

        return instance;
    }

    /**
     * Constructora per defecte de la CtjReglaAssociacio
     */
    public CjtReglesAssociacio(Integer Cjtregla) {
        this.idCjtRegles = Cjtregla;
        this.data = new Date();
        this.LlistRegAss = new ArrayList<>();
    }

    /**
     * Donat un llistat de Regla Associacio crea una instancia de CtjReglaAssociacio amb el llistat pasat per parametre.
     * @param Cjtregla identificador del conjunt de la regles
     * @param preproc Conjunt Atributs
     * @param llistRegAss ArrayList de ReglaAssociacio
     */
    public CjtReglesAssociacio(Integer Cjtregla, CjtAtributs preproc, ArrayList<ReglaAssociacio> llistRegAss) {
        idCjtRegles = Cjtregla;
        preproces = preproc;
        data = new Date();
        LlistRegAss = llistRegAss;
    }

    /**
     *  Getter identificador del Conjunt de Regles Associacions
     * @return identificador del Conjunt de Regles Associacions
     */
    public Integer getIdCjtRegles() {
        return idCjtRegles;
    }

    /**
     *  Setter identificador del Conjunt de Regles Associacions
     * @param idCjtRegles identificador del Conjunt de Regles Associacions
     */
    public void setIdCjtRegles(Integer idCjtRegles) {
        this.idCjtRegles = idCjtRegles;
    }

    /**
     * Getter del Preproces del Conjunt de Regles Associacions
     * @return Conjunt de Atributs
     */
    public CjtAtributs getPreproces() {
        return preproces;
    }

    /**
     * Setter del Preproces del Conjunt de Regles Associacions
     * @param preproces Conjunt de Atributs
     */
    public void setPreproces(CjtAtributs preproces) {
        this.preproces = preproces;
    }

    /**
     * Getter de la Data del Conjunt de Regles Associacions
     * @return la data del Conjunt de Regles Associacions
     */
    public Date getData() {
        return data;
    }

    /**
     * Setter de la Data del Conjunt de Regles Associacions
     * @param data data de la creacio del Conjunt de Regles Associacions
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Donat un identificador de una regla Existent retorna la Regla d'Assocacio.
     * @param id_regla identificador de la Regla Associacio
     * @return una Regla d'Associacio
     * @throws Exception si no existeix una regla amb identificador id_regla llança una excepció
     */
    public  ReglaAssociacio getRegla(Integer id_regla) throws Exception{
        for (ReglaAssociacio regAss : LlistRegAss){
            if(regAss.getId_regla().equals(id_regla)) {
                return regAss;
            }
        }
        throw  new Exception("La Regla d'Associacio amb l'identificador donat no existeix dins del conjunt.");
    }

    /**
     * Getter llista de ReglaAssociacio
     * @return Retorna el llista de Regla Assocacio del CtjReglaAssociacio
     */
    public ArrayList<ReglaAssociacio> getLlistRegAss() {
        return LlistRegAss;
    }

    /**
     * Assigna una llista de Regles d'Associacio al CjtReglaAssociacio
     * @param llistRegAss Llista de Regles Associacions
     */
    public void setLlistRegAss(ArrayList<ReglaAssociacio> llistRegAss) {
        LlistRegAss = llistRegAss;
    }

    /**
     * Actualitza una ReglaAssociacio
     * @param id_regla identifiador de la Regla Associacio a Actualitzar
     * @param reglaAssociacio Regla Associacio
     */
    public  void updateRegla(Integer id_regla, ReglaAssociacio reglaAssociacio) throws Exception {
        getRegla(id_regla);
        for (int i = 0; i < LlistRegAss.size(); ++i) {
            if(LlistRegAss.get(i).getId_regla().equals(id_regla)) {
                LlistRegAss.set(i, reglaAssociacio);
            }
        }
    }

    /**
     * Elimina una Regla Associacio
     * @param id_regla identificador de la Regla Associacio a eliminar
     * @return True si s'ha eliminat la regla, altrament False
     */
    public boolean deleteRegla(Integer id_regla) throws Exception {
        boolean delete = false;
        getRegla(id_regla);
        if(LlistRegAss.removeIf(reg -> reg.getId_regla().equals(id_regla))){
            delete = true;
        }
        return  delete;
    }

    /**
     * Filtracio de regles per un suport minim, una confiança minima i un numero minim de condicions del present de la regla.
     * @param suport suport minim
     * @param confiaza confianza minima
     * @param MinimNumCondicions Minim numero de condicions del presedent de la regla
     * @return Un hashmap amb els atributs de cada Regla Associacions que compleixen el suport, confianza minima i el numero de condicions
     */
    public HashMap<Integer, ArrayList<Object>> filtrarRegles(float suport, float confiaza, int MinimNumCondicions){
        HashMap<Integer, ArrayList<Object>> result = new HashMap<>();
        for(ReglaAssociacio regla: LlistRegAss){
            if(regla.getSuport() >= suport && regla.getConfianca() >= confiaza){
                if(regla.getCondicions().size() >= MinimNumCondicions) {
                    ArrayList<Object> llista = AfegeixRegla(regla);
                    result.put(regla.getId_regla(), llista);
                }
            }
        }
        return result;
    }

    /**
     * Filtració de regles per condició o per consequent.
     * @param atribut Atribut Processat per consultar
     * @param condi Boolea quan es true indica que es vol consultar per condicio l'atribut atri, altrament es vol consultar per consequent.
     * @return Un Hashmap que conte per cada posicio tots els altributs de la Regles resultants.
     */
    public HashMap<Integer, ArrayList<Object>> FiltrarReglesPerCondicioConsequent(AtributProcessat atribut, boolean condi){
        HashMap<Integer, ArrayList<Object>> llistaReglas = new HashMap<>();
        for (ReglaAssociacio regla : LlistRegAss) {
            boolean condition = false;
            boolean conseq = false;
            if (condi){
                HashSet<AtributProcessat> atributs = regla.getCondicions();
                Iterator it = atributs.iterator();
                while(it.hasNext() && !condition){
                    condition = it.next().equals(atribut);
                }
            }
            else conseq = (regla.getConsequent().equals(atribut));
            if (condition || conseq) {
                ArrayList<Object> llista = AfegeixRegla(regla);
                llistaReglas.put(regla.getId_regla(), llista);
            }
        }
            return llistaReglas;
    }

    /**
     * Afeix tots els atributs de la Regla Associacio en un ArrayList
     * @param regla Regla Associacio
     * @return Un ArrayList amb tots els atributs de la Regla Associacio passada com a parametre
     */
    private ArrayList<Object> AfegeixRegla(ReglaAssociacio regla){
        ArrayList<Object> llista = new ArrayList<>();
        llista.add(regla.getSuport());
        llista.add(regla.getConfianca());
        HashSet<String> cond = new HashSet<>();
        HashSet<AtributProcessat> condicions = regla.getCondicions();
        for (AtributProcessat atri : condicions) {
            String rule = atri.getNom() + '=' + atri.getValor();
            cond.add(rule);
        }
        llista.add(cond);
        AtributProcessat consequent = regla.getConsequent();
        String consecuent = consequent.getNom() + '=' + consequent.getValor();
        llista.add(consecuent);
        return llista;
    }
    /**
     * Afegeix una Regla de Associacio en el conjunt de Regles Associacions.
     * @param regla Regla Associacio
     */
    public void afegirRegla(ReglaAssociacio regla) throws Exception {
        if (!LlistRegAss.contains(regla)) {
            LlistRegAss.add(regla);
        }
        else throw new Exception("Ja existeix aquesta Regla Associacio en el Conjunt de Regles.");
    }

}
