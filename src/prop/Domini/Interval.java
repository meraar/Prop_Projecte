package prop.Domini;

import java.io.Serializable;

/**
 * @author Carles Capilla
 */
public class Interval implements Serializable {

    /**
     * Limit superior de un interval
     */
    private float lim_superior;
    /**
     * Limit inferior de un interval
     */
    private float lim_inferior;
    /**
     * Boolea que es cert si el limit superior pertany al interval i fals en cas contrari
     */
    private boolean igual_superior;
    /**
     * Boolea que es cert si el limit inferior pertany al interval i fals en cas contrari
     */
    private boolean igual_inferior;
    /**
     * Nom del interval
     */
    private String nom;

    public static final float INFP = Float.MAX_VALUE;
    public static final float INFN = -Float.MAX_VALUE;
    /**
     * Constructora per defecte
     *
     * Crea un interval buit
     */
    public Interval(){
    }

    /**
     * Constructora amb assignacio de nom i limits
     * @param lim_superior Limit superior a assignar al interval
     * @param lim_inferior Limit inferior a assignar al interval
     * @param igual_superior Assignar si es tracta de interval obert o tancat pel limit superior
     * @param igual_inferior Assignar si es tracta de interval obert o tancat pel limit inferior
     * @param nom Nom a assignar al interval
     * @throws Exception Error en limits del interval
     */
    public Interval(float lim_superior, float lim_inferior, boolean igual_superior, boolean igual_inferior, String nom) throws Exception {
        if(lim_inferior<lim_superior) {
            this.lim_superior = lim_superior;
            this.lim_inferior = lim_inferior;
            this.igual_superior = igual_superior;
            this.igual_inferior = igual_inferior;
            this.nom = nom;
        }else if(lim_inferior==lim_superior){
            this.lim_superior = lim_superior;
            this.lim_inferior = lim_inferior;
            this.igual_superior = true;
            this.igual_inferior = true;
            this.nom = nom;
        }else {
            throw new Exception("Límit superior més petit que límit inferior");
//            this.lim_superior = lim_inferior;
//            this.lim_inferior = lim_superior;
//            this.igual_superior = igual_superior;
//            this.igual_inferior = igual_inferior;
//            this.nom = nom;

        }
    }


    /**
     * Getter del limit superior
     * @return Retorna el limit superior del interval
     */
    public float getLimSup() {
        return lim_superior;
    }

    /**
     * Setter del limit superior
     *
     * Assigna el limit superior del parametre al interval
     *
     * @param lim_superior limit superior a assignar al interval
     */
    public void setLimSup(float lim_superior) {
        this.lim_superior = lim_superior;
    }

    /**
     * Getter del limit inferior
     * @return Retorna el limit inferior del interval
     */
    public float getLimInf() {
        return lim_inferior;
    }

    /**
     * Setter del limit inferior
     *
     * Assigna el limit inferior del parametre al interval
     *
     * @param lim_inferior limit inferior a assignar al interval
     */
    public void setLimInf(float lim_inferior) {
        this.lim_inferior = lim_inferior;
    }

    /**
     * Geter del tipus de interval pel que fa al limit superior
     * @return false si limit superior no pertany al interval i true en cas contrari
     */
    public boolean getIgualSup() {
        return igual_superior;
    }

    /**
     * Setter del tipus de interval pel que fa al limit superior
     * @param igual_superior cert si interval tancat i false si obert pel que fa al limit superior
     */
    public void setIgualSup(boolean igual_superior) {
        this.igual_superior = igual_superior;
    }

    /**
     * Geter del tipus de interval pel que fa al limit inferior
     * @return false si limit inferior no pertany al interval i true en cas contrari
     */
    public boolean getIgualInf() {
        return igual_inferior;
    }

    /**
     * Setter del tipus de interval pel que fa al limit inferior
     * @param igual_inferior cert si interval tancat i false si obert pel que fa al limit inferior
     */
    public void setIgualInf(boolean igual_inferior) {
        this.igual_inferior = igual_inferior;
    }

    /**
     * Getter del nom del interval
     * @return Nom del interval
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter del nom del interval
     * @param nom Nom a assignar al interval
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Comprova si un valor pertany al interval o no
     *
     * @param valor valor a analitzar
     * @return true si el valor pertany al interval i false en cas contrari
     */
    public boolean estadins(String valor) {
        float val = Float.parseFloat(valor);
        boolean dins = false;
        if (getLimSup() > val && getLimInf() < val) dins = true;
        else if (getIgualSup() && getLimSup() == val) dins = true;
        else if (getIgualInf() && getLimInf() == val) dins = true;
        return dins;
    }
}
