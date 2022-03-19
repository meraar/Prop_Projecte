package prop.Domini;

import java.io.Serializable;
import java.util.ArrayList;
public class CjtReglesAssociacio implements Serializable {
    /**
     *  Llistat de ReglaAssociacio
     */
    private  ArrayList<ReglaAssociacio> LlistRegAss;

    /**
     * Constructora per defecte de la CtjReglaAssociacio
     */
    public CjtReglesAssociacio() {
        this.LlistRegAss = new ArrayList<>();
    }
    /**
     * Donat un llistat de Regla Associacio crea una instancia de CtjReglaAssociacio amb el llistat pasat per parametre.
     * @param llistRegAss ArrayList de ReglaAssociacio
     */
    public CjtReglesAssociacio(ArrayList<ReglaAssociacio> llistRegAss) {
        LlistRegAss = llistRegAss;
    }

    /**
     * Donat un identificador de una regla Existent retorna la Regla d'Assocacio.
     * @param id_regla identificador de la Regla Associacio
     * @return una Regla d'Associacio
     * @throws Exception si no existeix una regla amb identificador id_regla llança una excepció
     */
    public  ReglaAssociacio getRegla(Integer id_regla) throws Exception{
        for (ReglaAssociacio regAss : LlistRegAss){
            if(regAss.getId_regla().equals(id_regla)) {
                return regAss;
            }
        }
        throw  new Exception("La Regla d'Associacio amb l'identificador donat no existeix dins del conjunt.");
    }

    /**
     * Getter llista de ReglaAssociacio
     * @return Retorna el llista de Regla Assocacio del CtjReglaAssociacio
     */
    public ArrayList<ReglaAssociacio> getLlistRegAss() {
        return LlistRegAss;
    }

    /**
     * Assigna una llista de Regles d'Associacio al CjtReglaAssociacio
     * @param llistRegAss Llista de Regles Associacions
     */
    public void setLlistRegAss(ArrayList<ReglaAssociacio> llistRegAss) {
        LlistRegAss = llistRegAss;
    }

    /**
     * Actualitza una ReglaAssociacio
     * @param id_regla identifiador de la Regla Associacio a Actualitzar
     * @param reglaAssociacio Regla Associacio
     */
    public  void updateRegla(Integer id_regla, ReglaAssociacio reglaAssociacio) throws Exception {
        getRegla(id_regla);
        for (int i = 0; i < LlistRegAss.size(); ++i) {
            if(LlistRegAss.get(i).getId_regla().equals(id_regla)) {
                LlistRegAss.set(i, reglaAssociacio);
            }
        }
    }

    /**
     * Elimina una Regla Associacio
     * @param id_regla identificador de la Regla Associacio a eliminar
     * @return True si s'ha eliminat la regla, altrament False
     */
    public boolean deleteRegla(Integer id_regla) throws Exception {
        boolean delete = false;
        getRegla(id_regla);
        if(LlistRegAss.removeIf(reg -> reg.getId_regla().equals(id_regla))){
            delete = true;
        }
        return  delete;
    }


}
