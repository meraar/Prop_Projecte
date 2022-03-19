package prop.Domini;

import prop.Persistencia.CtrlPersistencia;

import java.util.*;
/**
 * @author Albert Coma Coma i Muhammad Meraj Arshad
 */
public class CtrlRegles {
    /**
     * Conjunt de regles d'associacio.
     */
    private CjtReglesAssociacio regles;

    /**
     * Controlador de persistència.
     */
    private CtrlPersistencia persistencia;

    /**
     * Controlador de Domini.
     */
    private CtrlDomini domini;

    /**
     *HashMap d'enter i ArrayList de Float on es guarda les regles validades.
     */
    private HashMap<Integer,ArrayList<Float>> ReglesValidades;

    /**
     * Constructora per defecte.
     *
     * @param persistencia Controlador de persistència.
     * @param dom Controladora del domini.
     */
    public CtrlRegles(CtrlPersistencia persistencia, CtrlDomini dom) {
        this.persistencia = persistencia;
        this.domini = dom;
        this.regles = new CjtReglesAssociacio(1);
    }

    /**
     * Constructora per defect
     *
     * @param persistencia Controladora de persistència.
     */
    public CtrlRegles(CtrlPersistencia persistencia) {

        this.persistencia = persistencia;
        this.regles = new CjtReglesAssociacio(1);
    }
    /**
     * Algoritme Apriori
     *
     *
     * @param minSuport Float del suport mínim (tant per 1).
     * @param minConfianca Float de la confiança mínima (tant per 1).
     * @param nRegles Enter del nombre màxim de regles a generar.
     * @throws Exception Error en el mínim suport i/o mínima confiança i/o nombre de regles.
     */
    public void apriori(float minSuport, float minConfianca, int nRegles) throws Exception {
        CjtRegistres registres = CjtRegistres.getInstance();
        if (minSuport>1 || minSuport<0) throw new Exception("El suport minim no es correcte");
        if (minConfianca>1 || minConfianca<0) throw new Exception("La confianca minima no es correcte");
        if (nRegles<=0) throw new Exception("El nombre de regles no es correcte.");

        int mida_reg = registres.getLlistReg().size();
        float minSup = minSuport*mida_reg;

        // Calculem el conjunt de candidats inicials
        ArrayList<LinkedHashSet<AtributProcessat>> candidats = calcularCandidatsInicials(registres);
        LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> suports = registres.calculaSuport(candidats);

        // Eliminar candidats que no tenen el suport minim
        eliminarElementsSuportMinim(suports, minSup);

        int k = 1;

        while (true) {
            ++k;

            LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> suportsAnteriors = new LinkedHashMap<>(suports);

            // Calculem els nous candidats
            candidats = calcularCandidats(k, suports);

            // Calculem els suports dels nous candidats
            suports = registres.calculaSuport(candidats);

            // Eliminem candidats que no arriben al suport mínim
            eliminarElementsSuportMinim(suports, minSup);

            if (suports.size() == 0) {
                generarRegles(registres,suportsAnteriors,minSuport,minConfianca,mida_reg,nRegles);
                return;
            }
        }
    }

    /**
     * Eliminació d'elements amb suport més baix que el suport mínim.
     *
     * @param suports LinkedHashMap de LinkedHashSet d'atributs processats i enter corresponent al suport.
     * @param minSup Enter del suport mínim.
     */
    private void eliminarElementsSuportMinim(LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> suports, float minSup) {
        Iterator<Map.Entry<LinkedHashSet<AtributProcessat>, Integer>> iter = suports.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<LinkedHashSet<AtributProcessat>, Integer> item = iter.next();
            float freq = item.getValue();
            if (freq < minSup || freq == 0) {
                iter.remove();
            }
        }
    }

    /**
     * Càlcul dels primers candidats per k=1.
     *
     * @param registres Conjunt de registres.
     * @return Llistat de LinkedHashSet d'atributs processats.
     */
    private ArrayList<LinkedHashSet<AtributProcessat>> calcularCandidatsInicials(CjtRegistres registres) {
        ArrayList<LinkedHashSet<AtributProcessat>> atributs = new ArrayList<>();

        for (AtributProcessat atribut : registres.getAtributsUnics()) {
            LinkedHashSet<AtributProcessat> atrs = new LinkedHashSet<>();
            atrs.add(atribut);

            atributs.add(atrs);
        }

        return atributs;
    }

    /**
     * Càlcul de candidats per a cada k.
     *
     * @param k Enter de la mida del conjunt d'items.
     * @param suports LinkedHashMap de LinkedHashSet d'atributs processats i enter corresponent al suport.
     * @return Llistat de LinkedHashSet d'atributs processats.
     */
    private ArrayList<LinkedHashSet<AtributProcessat>> calcularCandidats(int k, LinkedHashMap<LinkedHashSet<AtributProcessat>,Integer> suports) {
        ArrayList<LinkedHashSet<AtributProcessat>> nousCandidats = new ArrayList<>();
        for (Map.Entry<LinkedHashSet<AtributProcessat>, Integer> elemA : suports.entrySet()) {
            LinkedHashSet<AtributProcessat> candidatA = elemA.getKey();
            for (Map.Entry<LinkedHashSet<AtributProcessat>, Integer> elemB : suports.entrySet()) {
                LinkedHashSet<AtributProcessat> candidatB = elemB.getKey();
                LinkedHashSet<AtributProcessat> aux = new LinkedHashSet<>();
                if (candidatA != candidatB) {
                    aux.addAll(candidatA);
                    aux.addAll(candidatB);
                }
                if (!nousCandidats.contains(aux) && aux.size() == k) {
                    nousCandidats.add(aux);
                }
            }
        }
        return filtrarCandidats(nousCandidats,suports);
    }

    /**
     * Filtrat dels nous candidats.
     * Per cada candidat C(k) el seu subset també ho ha de ser.
     *
     * @param candidats Llistat de LinkedHashSet d'atributs processats.
     * @param suports LinkedHashMap de LinkedHashSet d'atributs processats i enter corresponent al suport.
     * @return Llistat de LinkedHashSet d'atributs processats.
     */
    private ArrayList<LinkedHashSet<AtributProcessat>> filtrarCandidats(ArrayList<LinkedHashSet<AtributProcessat>> candidats, LinkedHashMap<LinkedHashSet<AtributProcessat>,Integer> suports) {
        ArrayList<LinkedHashSet<AtributProcessat>> nousCandidats = new ArrayList<>();
        ArrayList<LinkedHashSet<AtributProcessat>> subsetCandidat;
        for (LinkedHashSet<AtributProcessat> candidatK : candidats) {
            subsetCandidat = trobarSubset(candidatK);
            boolean subset_freq = true;
            for (LinkedHashSet<AtributProcessat> subset : subsetCandidat) {
                if (!suports.containsKey(subset)) {
                    subset_freq = false;
                    break;
                }
            }
            if (subset_freq) nousCandidats.add(candidatK);
        }

        return nousCandidats;
    }

    /**
     * Trobar el subset d'una LinkedHashSet d'atributs processats.
     *
     * @param set LinkedHashSet d'atributs processats.
     * @return Llistat de LinkedHashSet d'atributs processats, corresponent els seus diferents subsets.
     */
    private ArrayList<LinkedHashSet<AtributProcessat>> trobarSubset(LinkedHashSet<AtributProcessat> set) {
        ArrayList<LinkedHashSet<AtributProcessat>> subset = new ArrayList<>();
        LinkedHashSet<AtributProcessat> aux;
        for (AtributProcessat atri : set) {
            aux = new LinkedHashSet<>(set);
            aux.remove(atri);
            subset.add(aux);
        }
        return subset;
    }

    /**
     * Generació de regles d'associació apartir de conjunt d'items freqüents.
     *
     * @param registres Conjunt de registres.
     * @param suports LinkedHashMap de LinkedHashSet d'atributs processats i enter corresponent al suport.
     * @param minSup Float del suport mínim (en tant per 1).
     * @param minConf Float de la confiança mínima (en tant per 1).
     * @param mida_reg Enter corresponent al nombre de registres.
     * @param nRegles Enter corresponent al nombre màxim de regles a generar.
     */
    private void generarRegles(CjtRegistres registres ,LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> suports, float minSup, float minConf, int mida_reg, int nRegles) {
        HashMap<Integer,HashMap<Float,Float>> MapRegla = new HashMap<>();
        regles = new CjtReglesAssociacio(1);
        regles.setData(new Date());
        ArrayList<ReglaAssociacio> llistaRegles = new ArrayList<>();
        Integer id_regla = 1;
        for (Map.Entry<LinkedHashSet<AtributProcessat>, Integer> elem : suports.entrySet()) {
            for (AtributProcessat atribut : elem.getKey()) {
                HashSet<AtributProcessat> condicions = new HashSet<>();
                for (AtributProcessat altreAtri : elem.getKey()) {
                    if (atribut != altreAtri) condicions.add(altreAtri);
                }
                float suport = (float) elem.getValue() / mida_reg;
                float suport_condi = registres.calculaSuportCondicio(condicions);
                float confianza = suport / (suport_condi/mida_reg);
                if (minSup <= suport && minConf <= confianza && nRegles != 0 && condicions.size() > 0) {
                    ReglaAssociacio regla = new ReglaAssociacio(id_regla,confianza,suport,atribut,condicions);
                    llistaRegles.add(regla);
                    ++id_regla;
                    --nRegles;
                    HashMap<Float,Float> SupConf = new HashMap<>();
                    SupConf.put(suport,confianza);
                    MapRegla.put(id_regla,SupConf);

                }
            }
        }
        regles.setLlistRegAss(llistaRegles);
    }

    public HashMap<Integer,ArrayList<Object>> ConsultarReglesGenerades(){
        HashMap<Integer,ArrayList<Object>> reglesgenerdes = new HashMap<>();
        for (ReglaAssociacio regla : regles.getLlistRegAss()){
            ArrayList<Object> llista = new ArrayList<>();
            llista.add(regla.getSuport());
            llista.add(regla.getConfianca());
            HashSet<String> cond = new HashSet<>();
            HashSet<AtributProcessat> condicions = regla.getCondicions();
            for(AtributProcessat atri : condicions){
                String rule = atri.getNom() + '=' + atri.getValor();
                cond.add(rule);
            }
            llista.add(cond);
            AtributProcessat conseq = regla.getConsequent();
            String consecuent = conseq.getNom() + '=' + conseq.getValor();
            llista.add(consecuent);
            reglesgenerdes.put(regla.getId_regla(), llista);
        }

        return reglesgenerdes;
    }


    /**
     *Comprovacio per cada regla d'associació compleixi que es troba en un registre. I que el suport i confiança obtinguts siguin majors que els mínims.
     *
     * @param minSup Float del suport mínim (en tant per 1).
     * @param minConf Float de confiança mínima (en tant per 1).
     * @throws Exception Error en el mínim suport i/o mínima confiança.
     */
    public void validarRegles(float minSup, float minConf) throws Exception {
        ReglesValidades = new HashMap<>();
        CjtRegistres dades = CjtRegistres.getInstance();

        if (minSup>1 || minSup<0) throw new Exception("El suport minim no es correcte"); // Aquesta comprovacio haurem de fer fora?
        if (minConf>1 || minConf<0) throw new Exception("La confianca minima no es correcte"); // Aquesta comprovacio haurem de fer fora?

        int midaRegistre = dades.getLlistReg().size();
        ArrayList<ReglaAssociacio> regAss = regles.getLlistRegAss(); // Agafo de la llista de ReglaAssociacio
        for (ReglaAssociacio regla : regAss) { // itero sobre la lista regAss
            int suportGeneral = 0;
            int suportCondicions = dades.calculaSuportCondicio(regla.getCondicions());

            // Iterem sobre cada registre per trobar el suport de tots els atributs que conté la regla
            for (Registre registre : dades.getLlistReg()) {
                if (regla.compleixRegla(registre)) ++suportGeneral;
            }
            // Calculem el suport i confiança en percentatges
            float suportNew = (float)suportGeneral/(float)midaRegistre;
            float confianzaNew = suportNew/((float)suportCondicions/(float)midaRegistre);

            if (confianzaNew >= minConf && suportNew >= minSup) {
                ArrayList<Float> aux = new ArrayList<>();
                aux.add(regla.getSuport());  //Suport de la regla
                aux.add(regla.getConfianca());  // Confianza de la regla
                aux.add(suportNew); // Suport nou de la regla per aquest conjunt
                aux.add(confianzaNew);  // Confianza nova de la regla per aquest conjunt
                ReglesValidades.put(regla.getId_regla(),aux); // ReglesValidades conte identificador de la Regla, Suport de la Regla,
                // Confianza de la Regla, Nou Suport de la Regla per nou conjunt, Nova Confianza de la Regla per nou conjunt.
            }
        }
    }

    /**
     * Consulta les regles validades
     *
     * @return HashMap de l'identificador de la regla i llista de corresponents per a cada regla suport i confiança de la regla, nou suport i nova confianza de la per al nou conjunt.
     */
    public HashMap<Integer,ArrayList<Float>> ConsultaReglesValidades() {
        return ReglesValidades;
    }

    /**
     * Exporta el conjunt de regles d'associació a un fitxer binari per posteriorment poder-lo importar.
     *
     * @param pathFitxerDesti Path del fitxer on exportar.
     * @throws Exception Errors d'arxius
     */
    public void exportarRegles(String pathFitxerDesti) throws Exception {
        ArrayList classes = new ArrayList();
        classes.add(regles);
        this.persistencia.escriureBinari(classes,pathFitxerDesti);
    }

    /**
     * Importa un fitxer binari donat per obtenir un nou conjunt de regles d'associació.
     *
     * @param pathFitxer Path del fitxer a importar.
     * @throws Exception Errors de format del fitxer d'importació.
     */
    public void importarRegles(String pathFitxer) throws Exception {
        ArrayList classes = this.persistencia.llegirBinari(pathFitxer);

        if (classes.size() != 1 || !(classes.get(0) instanceof CjtReglesAssociacio)) {
            throw new Exception("El fitxer d'importació de regles no és vàlid.");
        }

        this.regles = (CjtReglesAssociacio) classes.get(0);
    }

    /**
     * Consultar regles Associacions amb suport i confianza minima i un nombre minim de condicions
     * @param minsupot Minim Suport per el conjunt de regles a consultar
     * @param minconf Minima Confianza per el conjunt de regles a consultar
     * @param MinimNumCondicions Numero de condicions per a les regles a consultar
     * @return un hashmap amb tots els atributs de la regles que compleixen les condicions del parametres a consultar.
     */
    public HashMap<Integer, ArrayList<Object>> consultarRegles(float minsupot, float minconf, int MinimNumCondicions) {
        return regles.filtrarRegles(minsupot,minconf,MinimNumCondicions);
    }

    /**
     * Consultar Regles Associacions per una condicio o un consequent que ha de tenir la regla associacio
     * @param nomAtri Nom del Atribut
     * @param valorAtri Valor del Atribut
     * @param condicio bolea que indica si es true el nomAtri i valorAtri es una condicio, altrament es un consequent
     * @return un hashmap amb tots els atributs de la regles que tenen el atribut com a condicio o conquent depenent del bolea condicio
     */
    public HashMap<Integer, ArrayList<Object>> consultarReglesPerCondicioConsequent(String nomAtri, String valorAtri, boolean condicio) {
        AtributProcessat atri = null;
        try {
            atri = new AtributProcessat(nomAtri, valorAtri);
        } catch (Exception e){}
        return regles.FiltrarReglesPerCondicioConsequent(atri, condicio);
    }

    /**
     * Diu si hi ha regles d'associació al sistema.
     *
     * @return Bool a cert si hi ha regles, fals si no n'hi ha.
     */
    public boolean hihaRegles() {
        return !regles.getLlistRegAss().isEmpty();
    }
}
