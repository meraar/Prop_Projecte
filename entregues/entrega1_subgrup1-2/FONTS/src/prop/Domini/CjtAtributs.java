package prop.Domini;

import java.util.ArrayList;

public class CjtAtributs {
    /**
     * Llista de atributs pertanyents a el header del fitxer
     */
    private ArrayList<Atribut> LlistAtri;

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
            if (LlistAtri.get(i).getNom().equals(nom)) LlistAtri.remove(i);
        }
    }
}
