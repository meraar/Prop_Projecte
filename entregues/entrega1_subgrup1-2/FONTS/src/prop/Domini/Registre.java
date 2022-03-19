package prop.Domini;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Registre implements Serializable {
    /**
     * Identificador del registre
     */
    private int idRegistre;

    /**
     * Llistat d'atributs processats
     */
    private ArrayList<AtributProcessat> llistatAtributsProcessats;

    /**
     * Constructora per defecte.
     * Crea un registre buit.
     */
    public Registre() {
        this.idRegistre = 0;
        this.llistatAtributsProcessats = new ArrayList<AtributProcessat>();
    }

    /**
     * Getter de l'identificador del registre.
     *
     * @return Identificador del registre.
     */
    public int getId_reg() {
        return idRegistre;
    }

    /**
     * Setter de l'identificador del registre.
     *
     * @param idRegistre Identificador del registre.
     */
    public void setId_reg(int idRegistre) {
        this.idRegistre = idRegistre;
    }

    /**
     * Afegeix un atribut al registre.
     *
     * @param atribut Atribut a afegir.
     * @throws Exception Ja existeix un atribut amb el nom donat dins del conjunt.
     */
    public void afegirAtribut(AtributProcessat atribut) throws Exception {
        if (existeixAtribut(atribut.getNom())) {
            throw new Exception("Ja existeix un atribut amb aquest nom dins del registre.");
        }

        this.llistatAtributsProcessats.add(atribut);
    }

    /**
     * Comprova si existeix un atribut amb el nom donat dins del registre.
     *
     * @param nom Nom de l'atribut.
     * @return Bool indicant si l'atribut existeix o no dins del registre.
     */
    public boolean existeixAtribut(String nom) {
        for (AtributProcessat atribut : this.llistatAtributsProcessats) {
            if (atribut.getNom().equals(nom)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Getter del llistat d'atributs processats.
     *
     * @return Llistat d'atributs processats.
     */
    public ArrayList<AtributProcessat> getListAtribProc() {
        return this.llistatAtributsProcessats;
    }

    /**
     * Actualitza el valor d'un atribut processat dins del registre.
     *
     * @param nom Nom de l'atribut a actualitzar.
     * @param valor Nou valor de l'atribut.
     * @throws Exception No existeix cap atribut amb el nom donat dins del registre.
     */
    public void modificarAtributProcessat(String nom, String valor) throws Exception {
        for (AtributProcessat atribut : llistatAtributsProcessats) {
            if (atribut.getNom().equals(nom)) {
                atribut.setValor(valor);

                return;
            }
        }

        throw new Exception("No existeix cap atribut amb el nom donat dins del registre.");
    }

    /**
     * Elimina l'atribut processat amb el nom donat del registre.
     *
     * @param nom Nom de l'atribut a eliminar.
     * @throws Exception No existeix cap atribut amb el nom donat dins del registre.
     */
    public void eliminarAtributProcessat(String nom) throws Exception {
        if (!existeixAtribut(nom)) {
            throw new Exception("No existeix cap atribut amb el nom donat dins del registre.");
        }

        llistatAtributsProcessats.removeIf(atr -> atr.getNom().equals(nom));
    }

    /**
     * Comprova si el conjunt d'atributs donat es troba dins del registre.
     *
     * @param atributs Conjunt d'atributs a comrpovar.
     * @return true o false segons si el conjunt està dins del registre.
     */
    public boolean conteAtributs(Set<AtributProcessat> atributs) {
        for (AtributProcessat atribut : atributs) {
            if (!llistatAtributsProcessats.contains(atribut)) {
                return false;
            }
        }

        return true;
    }
}
