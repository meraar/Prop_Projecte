package prop.Domini;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Carles Capilla
 */
public class CjtAtributs implements Serializable {
    /**
     * Llista de atributs pertanyents a el header del fitxer
     */
    private ArrayList<Atribut> LlistAtri;
    /**
     * Necessari per implementar singleton CjtAtributs
     */
    private static CjtAtributs instance = null;

    /**
     * Constructora per defecte
     *
     * Crea un conjunt d'atributs buit
     */
    public CjtAtributs() {
        this.LlistAtri = new ArrayList<>();

    }

    /**
     * Constructora amb assignacio de llista d'atributs
     *
     * @param llistAtri Llista de atributs a assignar
     */
    public CjtAtributs(ArrayList<Atribut> llistAtri) {
        this.LlistAtri = llistAtri;
    }


    /**
     * Getter de la llista de atributs de un conjunt de atributs
     *
     * @return Llista de atributs del conjunt
     */
    public ArrayList<Atribut> getLlistAtri() {

        return LlistAtri;
    }

    /**
     * Getter de la llista de atributs de un atribut
     *
     * @param nom nom del atribut a obtenir
     * @return Atribut del conjunt amb nom igual al del parametre
     */
    public Atribut getAtri (String nom) throws Exception{

        for(int i=0;i<LlistAtri.size();++i){
            if(LlistAtri.get(i).getNom().equals(nom)) return  LlistAtri.get(i);
        }throw new Exception("L'atribut amb nom: "+ nom +" no pertany al conjunt");

    }

    /**
     * Funcio que afegeix un atribut al conjunt de atributs
     * @param atri atribut a afegir
     */
    public void afegirAtri(Atribut atri){
        LlistAtri.add(atri);
    }
    /**
     * Setter de una llista de atributs al conjunt
     * @param llistAtri Llista de atributs a assignar
     */
    public void setLlistAtri (ArrayList<Atribut> llistAtri) {

        this.LlistAtri = llistAtri;
    }

    /**
     * Actualitza el atribut amb nom = nom de la llista de atributs
     * @param nom Nom del atribut a substituir o actualitzar
     * @param atribut atribut a introduir en la llista
     */
    public void updateAtri (String nom, Atribut atribut) {
        for(int i=0;i<LlistAtri.size();++i){
            if(LlistAtri.get(i).getNom().equals(nom)) LlistAtri.set(i, atribut);
        }

    }

    /**
     * Elimina un atribut de la llista de atributs del conjunt
     * @param nom nom del atribut a eliminar
     */
    public void deleteAtri (String nom) {
        for (int i = 0; i < LlistAtri.size(); ++i) {
            if (LlistAtri.get(i).getNom().equals(nom)) {
                LlistAtri.get(i).eliminat = true;
            }
        }
    }

    /**
     * Metode per obtenir la unica instancia de CjtAtributs
     * @return instancia singleton de la classe
     */
    public static CjtAtributs getInstance() {
        if(instance == null){
            instance = new CjtAtributs();
        }
        return instance;
    }

    /**
     * Metode per establir la unica instancia de CjtAtributs
     * @param atributs instancia de CjtAtributs
     */
    public static void setInstance(CjtAtributs atributs){
        instance = atributs;
    }
}
