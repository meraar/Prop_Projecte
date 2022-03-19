package prop.Domini;

import java.io.Serializable;
import java.util.*;

/**
 * @author Pere Grau
 */
public class CjtRegistres implements Serializable {
    /**
     * Instància Singleton de la classe.
     */
    private static CjtRegistres instance = null;

    /**
     * Mètode per obtenir la instància Singleton.
     *
     * @return Instància de la classe.
     */
    public static CjtRegistres getInstance() {
        if (instance == null) {
            instance = new CjtRegistres();
        }

        return instance;
    }

    /**
     * Setter per la instància Singleton.
     *
     * @param registres Nova instància de la classe.
     */
    public static void setInstance(CjtRegistres registres) {
        instance = registres;
    }

    /**
     * Llistat de registres
     */
    private ArrayList<Registre> llistatRegistres;

    /**
     * Constructora per defecte.
     * Inicialitza el conjunt buit.
     */
    public CjtRegistres(){
        this.llistatRegistres = new ArrayList<Registre>();
    }

    /**
     * Obtenir l'ArrayList de registres.
     *
     * @return ArrayList que conté els registres del conjunt.
     */
    public ArrayList<Registre> getLlistReg() {
        return llistatRegistres;
    }

    /**
     * Estableix el llistat de registres del conjunt.
     *
     * @param llistat Nou llistat a establir pel conjunt.
     */
    public void setLlistReg(ArrayList<Registre> llistat) {
        this.llistatRegistres = llistat;
    }

    /**
     * Elimina un registre del conjunt donat el seu identificador.
     *
     * @param idRegistre Identificador del registre a eliminar
     * @throws Exception El registre no existeix dins del conjunt.
     */
    public void borrarRegistre(int idRegistre) throws Exception {
        // Consultem el registre per comprovar si existeix
        consultarRegistre(idRegistre);

        llistatRegistres.removeIf(reg -> reg.getId_reg() == idRegistre);
    }

    /**
     * Actualitza el registre amb l'identificador donat.
     *
     * @param idRegistre Identificador del registre.
     * @param registre Nova instància del registre a actualitzar.
     * @throws Exception El registre no existeix dins del conjunt.
     */
    public void modificarRegistre(int idRegistre, Registre registre) throws Exception {
        for (int i = 0; i < llistatRegistres.size(); ++i) {
            if (llistatRegistres.get(i).getId_reg() == idRegistre) {
                llistatRegistres.set(i, registre);

                return;
            }
        }

        throw new Exception("El registre amb la ID especificada no existeix al conjunt");
    }

    /**
     * Afegeix un registre al conjunt.
     *
     * @param registre Registre a afegir.
     * @throws Exception Ja existeix un registre amb aquest identificador.
     */
    public void afegirRegistre(Registre registre) throws Exception {
        try {
            consultarRegistre(registre.getId_reg());
        } catch (Exception e) {
            // No existeix el registre, per tant l'afegim
            llistatRegistres.add(registre);

            return;
        }

        throw new Exception("Ja existeix un registre amb aquest identificador dins del conjunt");
    }

    /**
     * Consulta un registre del conjunt.
     *
     * @param idRegistre Identificador del registre a consultar.
     * @return Instància del registre demanat.
     * @throws Exception El registre amb l'identificador donat no existeix dins del conjunt.
     */
    public Registre consultarRegistre(int idRegistre) throws Exception {
        for (Registre registre : llistatRegistres) {
            if (registre.getId_reg() == idRegistre) {
                return registre;
            }
        }

        throw new Exception("El registre amb l'identificador donat no existeix dins del conjunt.");
    }

    /**
     * Consulta els atributs únics que hi ha en tots els registres del conjunt ordenats per ordre lexicogràfic.
     *
     * @return Conjunt d'atributs únics ordenats.
     */
    public ArrayList<AtributProcessat> getAtributsUnics() {
        TreeSet<AtributProcessat> atributs = new TreeSet<>(new AtributsComparator());

        for (Registre registre : llistatRegistres) {
            atributs.addAll(registre.getListAtribProc());
        }

        return new ArrayList<AtributProcessat>(atributs);
    }

    /**
     * Ordena el set donat per ordre lexicogràfic.
     *
     * @param atributs Conjunt d'atributs a ordenar.
     * @return Conjunt d'atributs ordenats.
     */
    public static LinkedHashSet<AtributProcessat> ordena(Set<AtributProcessat> atributs) {
        TreeSet<AtributProcessat> set = new TreeSet<>(new AtributsComparator());
        set.addAll(atributs);

        return new LinkedHashSet<>(set);
    }

    /**
     * Calcula el suport del conjunt d'atributs donat dins del CjtRegistres.
     *
     * @param conjunt Conjunt de conjunts de registres.
     * @return Mapa que conté el suport de cada conjunt.
     */
    public LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> calculaSuport(ArrayList<LinkedHashSet<AtributProcessat>> conjunt) {
        LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> map = new LinkedHashMap<>();

        for (Set<AtributProcessat> atributs : conjunt) {
            int count = 0;

            for (Registre reg : getLlistReg()) {
                if (reg.conteAtributs(atributs)) {
                    ++count;
                }
            }

            map.put(new LinkedHashSet<>(atributs), count);
        }

        return map;
    }

    /**
     * Calcula el suport del set d'atributs donat.
     *
     * @param condicions Set d'atributs.
     * @return Suport del conjunt d'atributs donat.
     */
    public Integer calculaSuportCondicio(HashSet<AtributProcessat> condicions) {
       int count = 0;
       for (Registre reg : getLlistReg()) {
           if (reg.conteAtributs(condicions)) {
               ++count;
           }
       }
       return count;
    }
}

/**
 * @author Pere Grau
 */
class AtributsComparator implements Comparator<AtributProcessat> {

    @Override
    public int compare(AtributProcessat atr1, AtributProcessat atr2) {
        if (!atr1.getNom().equals(atr2.getNom())) {
            return atr1.getNom().compareTo(atr2.getNom());
        }

        if (!atr1.getValor().equals(atr2.getValor())) {
            return atr1.getValor().compareTo(atr2.getValor());
        }

        return 0;
    }
}