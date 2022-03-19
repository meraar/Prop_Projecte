package prop.Domini;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author Albert Coma Coma
 */
public class ReglaAssociacio implements Serializable {
    /**
     * Identificador de la regla d'associació.
     */
    private Integer id_regla;

    /**
     * Tant per 1 de confiança de la regla d'assoaciació.
     */
    private float confianca;

    /**
     * Tant per 1 del suport de la regla d'assoaciació.
     */
    private float suport;

    /**
     * HashSet d'atributs processats que corresponen a les condicions de la regla.
     */
    private HashSet<AtributProcessat> condicions;

    /**
     * AtributProcessat que correspon al conseqüent de la regla.
     */
    private AtributProcessat consequent;

    /**
     * Identificador de la regla d'associació.
     */
    public ReglaAssociacio() {
    }

    /**
     * Constructora.
     * Crea una regla d'associació.
     * @param id_regla Integer identificador.
     * @param confianca Float confiança (en tant per 1).
     * @param suport Float suport (en tant per 1).
     * @param consequent AtributProcessat corresponent al conseqüent de la regla.
     * @param condicions HashSet d'atributs processats corresponents a les condicions de la regla.
     */
    public ReglaAssociacio(Integer id_regla, float confianca, float suport, AtributProcessat consequent, HashSet<AtributProcessat> condicions) {
        this.id_regla = id_regla;
        this.confianca = confianca;
        this.suport = suport;
        this.consequent = consequent;
        this.condicions = condicions;
    }

    /**
     * Getter de l'identificador de la regla d'associació.
     *
     * @return Identificador de la regla d'associació.
     */
    public Integer getId_regla() {
        return id_regla;
    }

    /**
     * Setter de l'identificador de la regla d'associació.
     *
     * @param id_regla Enter que identifica a la regla d'associació.
     */
    public void setId_regla(Integer id_regla) {
        this.id_regla = id_regla;
    }

    /**
     * Getter de les condicions de la regla d'associació.
     *
     * @return HashSet d'atributs processats (condicions de la regla).
     */
    public HashSet<AtributProcessat> getCondicions() {
        return condicions;
    }

    /**
     * Setter de les condicions de la regla d'associació.
     *
     * @param condicions HashSet d'atributs processats.
     */
    public void setCondicions(HashSet<AtributProcessat> condicions) {
        this.condicions = condicions;
    }

    /**
     * Getter del conseqüent de la regla d'associació.
     *
     * @return AtributProcessat corresponent al conseqüent de la regla.
     */
    public AtributProcessat getConsequent() {
        return consequent;
    }

    /**
     * Setter del conseqüent de la regla d'associació.
     *
     * @param consequent AtributProcessat corresponent al conseqüent de la regla.
     */
    public void setConsequent(AtributProcessat consequent) {
        this.consequent = consequent;
    }

    /**
     * Getter de la confiança de la regla d'associació.
     *
     * @return Float entre 0 i 1 que correspon al tant per 1 de la confiança de la regla.
     */
    public float getConfianca() {
        return confianca;
    }

    /**
     * Setter de la confiança de la regla d'associació.
     *
     * @param confianca Float entre 0 i 1 que correspon al tant per 1 de la confiança de la regla.
     */
    public void setConfianca(float confianca) {
        this.confianca = confianca;
    }

    /**
     * Getter del suport de la regla d'associació.
     *
     * @return Float entre 0 i 1 que correspon al tant per 1 del suport de la regla.
     */
    public float getSuport() {
        return suport;
    }

    /**
     * Setter del suport de la regla d'associació.
     *
     * @param suport Float entre 0 i 1 que correspon al tant per 1 del suport de la regla.
     */
    public void setSuport(float suport) {
        this.suport = suport;
    }


    /**
     * Comprova que tots els atributs de la regla es troben al registre.
     *
     * @param registre Llista d'atributs processats.
     * @return Bool indicant si es compleix o no la regla amb aquest registre.
     */
    public boolean compleixRegla(Registre registre) {
        LinkedHashSet<AtributProcessat> atributs = new LinkedHashSet<>();

        atributs.add(consequent);
        atributs.addAll(condicions);

        return registre.conteAtributs(atributs);
    }
}

