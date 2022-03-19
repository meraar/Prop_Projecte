package prop.Domini;

import prop.Persistencia.CtrlPersistencia;
import prop.Persistencia.FitxerDades;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Pere Grau
 */
public class CtrlRegistres {
    /**
     * Conjunt de registres del sistema.
     */
    private CjtRegistres registres;

    /**
     * Controlador de persistència.
     */
    private CtrlPersistencia persistencia;

    /**
     * Constructora per defecte.
     *
     * @param persistencia Controlador de persistència
     */
    public CtrlRegistres(CtrlPersistencia persistencia) {
        this.persistencia = persistencia;
        this.registres = CjtRegistres.getInstance();
    }

    /**
     * Constructora per defecte.
     *
     * @param persistencia Controlador de persistència
     * @param registres Conjunt de registres
     */
    public CtrlRegistres(CtrlPersistencia persistencia, CjtRegistres registres) {
        this.persistencia = persistencia;
        this.registres = registres;
    }

    /**
     * Consulta els atributs processats que conté el registre
     *
     * @param idRegistre Identificador del registre a consultar.
     * @return Map que conté els atributs del registre.
     * @throws Exception El registre especificat no existeix
     */
    public HashMap<String, String> consultarRegistre(int idRegistre) throws Exception {
        Registre registre = registres.consultarRegistre(idRegistre);
        HashMap<String, String> atributs = new HashMap<String, String>();

        for (AtributProcessat atribut : registre.getListAtribProc()) {
            atributs.put(atribut.getNom(), atribut.getValor());
        }

        return atributs;
    }

    /**
     * Consulta el llistat de registres que conté el conjunt.
     *
     * @return Hashmap que conté l'identificador del registre com a clau i els atributs com a valor.
     */
    public HashMap<Integer, ArrayList<String>> consultarRegistres() {
        HashMap<Integer, ArrayList<String>> dades = new HashMap<>();

        for (Registre registre : registres.getLlistReg()) {
            ArrayList<String> valors = new ArrayList<>();

            for (AtributProcessat atribut : registre.getListAtribProc()) {
                valors.add(atribut.getValor());
            }

            dades.put(registre.getId_reg(), valors);
        }

        return dades;
    }

    /**
     * Processa el fitxer de dades donat amb la configuració de preprocés proporcionada.
     *
     * @param pathFitxer Path on es troba el fitxer de dades
     * @param avortar Indica si s'ha d'avortar el preprocés en cas de trobar un atribut no vàlid
     * @throws Exception Errors de format
     * @return Possibles errors que hi ha hagut durant el preprocés
     */
    public ArrayList<String> processarAtributs(String pathFitxer, boolean avortar) throws Exception {
        CjtAtributs atributs = CjtAtributs.getInstance();
        ArrayList<String> errors = new ArrayList<>();

        ArrayList<Registre> regs = new ArrayList<>();
        ArrayList<Atribut> atrs = atributs.getLlistAtri();
        int idRegistre = 1;

        // Llegim el fitxer de dades per obtenir els valors
        FitxerDades dades = this.persistencia.llegirFitxerDades(pathFitxer);
        ArrayList<String> valors = dades.llegirValors();

        // Iterem per cada fila del fitxer
        while (!valors.isEmpty()) {
            Registre registre = new Registre();
            registre.setId_reg(idRegistre);
            boolean err = false;

            // Iterem per cada atribut i
            for (int i = 0; i < atrs.size(); ++i) {
                Atribut atribut = atrs.get(i);

                // Si l'atribut està marcat com a eliminat, no el processem
                if (atribut.getEliminat() || valors.size() <= i) {
                    continue;
                }

                if (!atribut.validarValor(valors.get(i))) {
                    // Avortem si l'usuari ho ha escollit
                    if (avortar) {
                        throw new Exception(String.format("El format de les dades d'entrada no correspon al format de preprocés per l'atribut \"%s\" del registre %d", atribut.getNom(), idRegistre));
                    }

                    errors.add(String.format("Valor inesperat per l'atribut \"%s\" del registre %d: %s", atribut.getNom(), idRegistre, valors.get(i)));
                    err = true;
                    break;
                }

                String valorProcessat = atribut.processar(valors.get(i));

                registre.afegirAtribut(new AtributProcessat(atribut.getNom(), valorProcessat));
            }

            // Només afegim el registre si no hi ha hagut cap error
            if (!err) {
                regs.add(registre);
            }

            ++idRegistre;
            valors = dades.llegirValors();
        }

        registres.setLlistReg(regs);

        return errors;
    }

    /**
     * Exporta el conjunt de registres a un fitxer binari per posteriorment poder-lo importar.
     *
     * @param pathFitxerDesti Path del fitxer on exportar.
     * @throws Exception Errors d'arxius
     */
    public void exportarRegistres(String pathFitxerDesti) throws Exception {
        ArrayList classes = new ArrayList();
        classes.add(registres);
        classes.add(CjtAtributs.getInstance());

        this.persistencia.escriureBinari(classes, pathFitxerDesti);
    }

    /**
     * Importa un fitxer binari donat per obtenir un nou conjunt de registres.
     *
     * @param pathFitxer Path del fitxer a importar.
     * @throws Exception Errors de format del fitxer d'importació.
     */
    public void importarRegistres(String pathFitxer) throws Exception {
        ArrayList classes = this.persistencia.llegirBinari(pathFitxer);

        if (classes.size() != 2 || !(classes.get(0) instanceof CjtRegistres) || !(classes.get(1) instanceof CjtAtributs)) {
            throw new Exception("El fitxer d'importació de registres no és vàlid.");
        }

        CjtRegistres.setInstance((CjtRegistres)classes.get(0));
        CjtAtributs.setInstance((CjtAtributs) classes.get(1));
        this.registres = CjtRegistres.getInstance();
    }

    /**
     * Modifica el valor de l'atribut donat del registre donat.
     *
     * @param idRegistre Identificador del registre.
     * @param nomAtribut Nom de l'atribut a modificar.
     * @param nouValor Nou valor a establir.
     * @throws Exception L'atribut no existeix dins del registre.
     */
    public void modificarAtributRegistre(int idRegistre, String nomAtribut, String nouValor) throws Exception {
        Registre registre = registres.consultarRegistre(idRegistre);

        registre.modificarAtributProcessat(nomAtribut, nouValor);
    }

    /**
     * Elimina un registre del conjunt del programa.
     *
     * @param idRegistre Identificador del registre a eliminar.
     * @throws Exception El registre no existeix al conjunt.
     */
    public void eliminarRegiste(int idRegistre) throws Exception {
        registres.borrarRegistre(idRegistre);
    }
}
