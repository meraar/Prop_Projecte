package prop.Domini;

import java.util.ArrayList;

public class AtributInt extends Atribut {
    /**
     * Llista dels intervals del atribut
     */
    private ArrayList<Interval> LlistaInterval;


    /**
     * Constructora amb assignacio de nom i tipus
     * @param nom nom a assignar al atribut
     * @param tipus tipus a assignar al atribut
     * @throws Exception Error de tipus
     */
    public AtributInt(String nom, String tipus) throws Exception {

        super(nom);
        LlistaInterval = new ArrayList<>();
        if(tipus.equals("float"))this.tipus=tipus;
        else throw new Exception("Tipus d'atribut invalid");
    }

    /**
     *Comprova si valor pertany a algun dels intervals del atribut
     * @param valor Valor a comprovar
     * @return true si el valor pertany a algun interval i fals en cas contrari
     */
    @Override
    public Boolean validarValor(String valor) {
        boolean dins = false;
        for (Interval interval : LlistaInterval) {
            dins = interval.estadins(valor);
            if(dins) return true;
        }
        return dins;
    }

    /**
     * Afegeix el interval a la llista de intervals del atribut
     * @param interval Interval a afegir
     */
    public void afegirInterval(Interval interval) {
        LlistaInterval.add(interval);
    }

    /**
     * Afegeix una llista de intervals al atribut
     * @param llista Llista a afegir
     */
    public void afegirLlistaInterval(ArrayList<Interval> llista) {
        this.LlistaInterval = llista;
    }

    /**
     * Getter de la llista de intervals de un atribut de tipus float
     * @return Un array de intervals amb tots els intervals de un atribut
     */
    public ArrayList<Interval> getLlistaInterval(){
        return LlistaInterval;
    }
    /**
     * Retorna el nom del interval al que pertany el valor
     * @param valor valor del atribut a processar
     * @return nom del interval al que pertany el valor
     * @throws Exception No existeix cap interval al que pugui pertanyer el atribut
     */
    public String processar(String valor) throws Exception {
        for (Interval interval : LlistaInterval) {
            if (interval.estadins(valor)) return interval.getNom();
        }
        throw new Exception("L'atribut no pertany a cap interval");
    }
}