package prop.Domini;

import prop.Persistencia.CtrlPersistencia;

/**
 * @author Carles Capilla Canovas
 * @author Albert Coma Coma
 * @author Pere Grau Molina
 * @author Muhammad Meraj Arshad
 */
public class CtrlDomini {
    /**
     * Controlador de persistència.
     */
    private CtrlPersistencia persistencia;

    /**
     * Controlador d'atributs.
     */
    private CtrlAtributs ctrlAtributs;

    /**
     * Controlador de registres.
     */
    private CtrlRegistres ctrlRegistres;

    /**
     * Controlador de regles.
     */
    private CtrlRegles ctrlRegles;

    /**
     * Constructora per defecte.
     */
    public CtrlDomini() {
        this.persistencia = new CtrlPersistencia();

        this.ctrlAtributs = new CtrlAtributs(this.persistencia);
        this.ctrlRegistres = new CtrlRegistres(this.persistencia);
        this.ctrlRegles = new CtrlRegles(this.persistencia);
    }

    /**
     * Getter del controlador d'atributs.
     *
     * @return Controlador d'atributs.
     */
    public CtrlAtributs getCtrlAtributs() {
        return ctrlAtributs;
    }

    /**
     * Getter del controlador de registres.
     *
     * @return Controlador de registres.
     */
    public CtrlRegistres getCtrlRegistres() {
        return ctrlRegistres;
    }

    /**
     * Getter del controlador de regles.
     *
     * @return Controlador de regles.
     */
    public CtrlRegles getCtrlRegles() {
        return ctrlRegles;
    }
}
