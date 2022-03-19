package prop;

import prop.Domini.*;
import prop.Persistencia.CtrlPersistencia;

import java.util.*;

public class CtrlRegles {
    /**
     * Conjunt de regles d'associació.
     */
    private CjtReglesAssociacio regles;

    /**
     * Controlador de persistència.
     */
    private CtrlPersistencia persistencia;

    /**
     * Constructora per defecte.
     *
     * @param persistencia Controlador de persistència.
     */
    public CtrlRegles(CtrlPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    /**
     * Algoritme Apriori
     *
     * @param registres Conjunt de registres.
     * @param minSuport Float del suport mínim (tant per 1).
     * @param minConfianca Float de la confiança mínima (tant per 1).
     * @param nRegles Enter del nombre màxim de regles a generar.
     * @return HashMap de l'identificador de la regla i HashMap de dos floats corresponents al suport i confiança de la regla.
     * @throws Exception Error en el mínim suport i/o mínima confiança i/o nombre de regles.
     */
    public HashMap<Integer,HashMap<Float,Float>> apriori(CjtRegistres registres, float minSuport, float minConfianca, int nRegles) throws Exception {

        if (minSuport>1 || minSuport<0) throw new Exception("El suport minim no es correcte");
        if (minConfianca>1 || minConfianca<0) throw new Exception("La confianca minima no es correcte");
        if (nRegles<=0) throw new Exception("El nombre de regles no es correcte.");

        int mida_reg = registres.getLlistReg().size();
        int minSup = (int) (minSuport*mida_reg);

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
                return generarRegles(registres,suportsAnteriors,minSuport,minConfianca,mida_reg,nRegles);
            }
        }
    }

    /**
     * Eliminació d'elements amb suport més baix que el suport mínim.
     *
     * @param suports LinkedHashMap de LinkedHashSet d'atributs processats i enter corresponent al suport.
     * @param minSup Enter del suport mínim.
     */
    private void eliminarElementsSuportMinim(LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> suports, int minSup) {
        Iterator<Map.Entry<LinkedHashSet<AtributProcessat>, Integer>> iter = suports.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<LinkedHashSet<AtributProcessat>, Integer> item = iter.next();
            float freq = item.getValue();
            if (freq < minSup) {
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
     * @return HashMap de l'identificador de la regla i HashMap de dos floats corresponents al suport i confiança de la regla.
     */
    private HashMap<Integer,HashMap<Float,Float>> generarRegles(CjtRegistres registres ,LinkedHashMap<LinkedHashSet<AtributProcessat>, Integer> suports, float minSup, float minConf, int mida_reg, int nRegles) {
        HashMap<Integer,HashMap<Float,Float>> MapRegla = new HashMap<>();
        regles = new CjtReglesAssociacio();
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
                float confianza = ((suport * mida_reg) / suport_condi) / mida_reg;
                if (minSup <= suport && minConf <= confianza && nRegles != 0) {
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
        return MapRegla;
    }


    /**
     *Comprovació per cada regla d'associació compleixi que es troba en un registre. I que el suport i confiança obtinguts siguin majors que els mínims.
     *
     * @param dades ArrayList de Registres.
     * @param minSup Float del suport mínim (en tant per 1).
     * @param minConf Float de confiança mínima (en tant per 1).
     * @return HashMap de l'identificador de la regla i HashMap de dos floats corresponents al suport i confiança de la regla.
     * @throws Exception Error en el mínim suport i/o mínima confiança.
     */
    public HashMap<Integer,HashMap<Float,Float>> validarRegles(CjtRegistres dades, float minSup, float minConf) throws Exception {
        HashMap<Integer,HashMap<Float,Float>> ReglesValidades = new HashMap<>();

        if (minSup>1 || minSup<0) throw new Exception("El suport minim no es correcte");
        if (minConf>1 || minConf<0) throw new Exception("La confianca minima no es correcte");

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
            float conf = ((float)suportGeneral/(float)suportCondicions)/(float)midaRegistre;
            float suport = (float)suportGeneral/(float)midaRegistre;

            if (conf >= minConf && suport >= minSup) {
                HashMap<Float,Float> aux = new HashMap<Float, Float>();
                aux.put(suport,conf);
                ReglesValidades.put(regla.getId_regla(),aux);
            }
        }
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
        this.persistencia.escriureBinari(classes,pathFitxerDesti,".regles");
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

    public void convertirRegles(CjtReglesAssociacio regles, String tipus, String pathFitxer) {

    }

    public CjtReglesAssociacio consultarRegles() {
        return new CjtReglesAssociacio(new ArrayList<>());
    }

}
