package prop.Domini;


import java.io.Serializable;

/**
 * @author Carles Capilla
 */
public abstract class Atribut implements Serializable {
    /**
     * Nom del atribut
     */
    protected String nom;
    /**
     * Tipus del atribut
     */
    protected Tipus tipus;
    /**
     * Indica si el atribut ha estat eliminat
     */
    protected boolean eliminat = false;

    /**
     * Constructora per defecte
     *
     * Crea un atribut buit
     */
    public Atribut(){
    }

    /**
     * Constructora amb assignacio de nom
     *
     * @param nom nom a assignar al atribut a crear
     */
    public Atribut(String nom) {
        this.nom = nom;
        this.tipus = Tipus.STRING;
    }

    /**
     * Constructora amb assignacio de nom i tipus
     *
     * @param nom nom a assignar al atribut a crear
     * @param tipus tipus a assignar al atribut a crear
     */
    public Atribut(String nom, Tipus tipus) {
        this.nom = nom;
        this.tipus = tipus;
    }

    /**
     * Getter del nom del atribut
     *
     * @return Nom del atribut
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter del nom del atribut
     *
     * @param nom Nom a assignar al atribut
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter del tipus del atribut
     *
     * @return tipus del atribut
     */
    public Tipus getTipus() {
        return tipus;
    }

    /**
     * Setter del tipus del atribut
     *
     * @param tipus Tipus a assignar al atribut
     */
    public void setTipus(Tipus tipus){

        this.tipus = tipus;
    }

    //public void modificarTipus(String tipus) { this.tipus = tipus; }

    /**
     * Metode abstracte a implementar en els seus fills on comprovar si un valor forma part d'un atribut
     * @param valor Valor a comprovar si pertany al atribut
     * @return true si el valor pertany al atribut i false en cas contrari
     */
    public abstract Boolean validarValor(String valor);

    /**
     * Metode abstracte a implementar en els seus fills on es processa el valor de l'atribut segons les condicions de l'usuari
     * @param valor valor del atribut a processar
     * @return el valor processat del atribut
     * @throws Exception Depenent del tipus de atribut llansa una excepcio diferent
     */
    public abstract String processar(String valor) throws Exception ;

    /**
     * Metode que canvia el estat de un atribut pel que fa a si esta eliminat o no
     * @param estat fals si volem deseliminar-lo i cert si volem eliminar-lo
     */
    public void setEliminat(boolean estat){
        this.eliminat = estat;
    }

    /**
     * Getter per obtenir el estat de un atribut
     * @return fals si no esta eliminat i cert en cas contrari
     */
    public boolean getEliminat(){
        return eliminat;
    }
}
