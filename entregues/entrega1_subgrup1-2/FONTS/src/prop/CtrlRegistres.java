package prop;

import prop.Domini.*;
import prop.Persistencia.CtrlPersistencia;
import prop.Persistencia.FitxerDades;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.registres = new CjtRegistres();
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
     * Processa el fitxer de dades donat amb la configuració de preprocés proporcionada.
     *
     * @param pathFitxer Path on es troba el fitxer de dades
     * @param atributs Conjunt d'atributs amb el preprocés a aplicar
     * @throws Exception Errors de format
     */
    public void processarAtributs(String pathFitxer, CjtAtributs atributs) throws Exception {
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

            // Iterem per cada atribut i
            for (int i = 0; i < atrs.size(); ++i) {
                Atribut atribut = atrs.get(i);

                if (!atribut.validarValor(valors.get(i))) {
                    throw new Exception("El format de les dades d'entrada no correspon al format de preprocés.");
                }

                String valorProcessat = atribut.processar(valors.get(i));

                registre.afegirAtribut(new AtributProcessat(atribut.getNom(), valorProcessat));
            }

            regs.add(registre);
            ++idRegistre;
            valors = dades.llegirValors();
        }

        CjtRegistres cjt = new CjtRegistres();
        cjt.setLlistReg(regs);

        this.registres = cjt;
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

        this.persistencia.escriureBinari(classes, pathFitxerDesti, ".registres");
    }

    /**
     * Importa un fitxer binari donat per obtenir un nou conjunt de registres.
     *
     * @param pathFitxer Path del fitxer a importar.
     * @throws Exception Errors de format del fitxer d'importació.
     */
    public void importarRegistres(String pathFitxer) throws Exception {
        ArrayList classes = this.persistencia.llegirBinari(pathFitxer);

        if (classes.size() != 1 || !(classes.get(0) instanceof CjtRegistres)) {
            throw new Exception("El fitxer d'importació de registres no és vàlid.");
        }

        this.registres = (CjtRegistres)classes.get(0);
    }
}
