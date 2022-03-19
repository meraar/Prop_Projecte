package prop;

import prop.Persistencia.CtrlPersistencia;

public class CtrlDomini {
    private CtrlPersistencia persistencia;

    private CtrlAtributs ctrlAtributs;
    private CtrlRegistres ctrlRegistres;
    private CtrlRegles ctrlRegles;

    public CtrlDomini() {
        this.persistencia = new CtrlPersistencia();

        this.ctrlAtributs = new CtrlAtributs();
        this.ctrlRegistres = new CtrlRegistres(this.persistencia);
        this.ctrlRegles = new CtrlRegles(this.persistencia);
    }

    public CtrlAtributs getCtrlAtributs() {
        return ctrlAtributs;
    }

    public CtrlRegistres getCtrlRegistres() {
        return ctrlRegistres;
    }

    public CtrlRegles getCtrlRegles() {
        return ctrlRegles;
    }
}
