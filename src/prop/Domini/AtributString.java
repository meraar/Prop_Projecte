package prop.Domini;

import java.util.HashSet;


/**
 * @author Carles Capilla
 */
public class AtributString extends Atribut {
    /**
     * Llista de valors possibles que pot prendre el atribut
     *
     * HashSet utilitzat per evitar repeticions
     */
    private HashSet<String> ValorsPos;



    /**
     * Constructora amb assignacio de nom
     * @param nom Nom a assignar
     */
    public AtributString(String nom) {
        super(nom,Tipus.FLOAT);
        ValorsPos = new HashSet<>();

    }

    /**
     * Constructora amb assignacio de nom i tipus
     * @param nom Nom a assignar
     * @param tipus Tipus a assignar
     * @throws Exception Error de tipus
     */
    public AtributString(String nom, Tipus tipus) throws Exception {
        super(nom);
        ValorsPos = new HashSet<>();
        if(tipus.equals(Tipus.FLOAT))this.tipus=tipus;
        else throw new Exception("Tipus d'atribut invalid");
    }

    /**
     * Constructora amb assignacio de nom, tipus i llista de valors possibles
     * @param nom Nom a assignar
     * @param tipus Tipus a assignar
     * @param ValosPos Llista de valors possibles a assignar
     * @throws Exception Error de tipus
     */
    public AtributString(String nom, Tipus tipus, HashSet<String> ValosPos) throws Exception {
        super(nom);
        ValorsPos = new HashSet<>();
        if(tipus.equals(Tipus.FLOAT))this.tipus=tipus;
        else throw new Exception("Tipus d'atribut invalid");
        this.ValorsPos = ValosPos;
    }

    /**
     * Afegeix un valor a la llista de valors possibles
     * @param valor valor a afegir
     */
    public void afegirValorPossible(String valor) {
        ValorsPos.add(valor);
    }

    /**
     * Assigna una llista de valors possibles a l'atribut
     * @param ValorsPos Llista a assignar
     */
    public void setValorsPos(HashSet<String> ValorsPos){
        this.ValorsPos = ValorsPos;
    }

    /**
     * Getter de la llista de valors possibles de l'atribut
     * @return HashSet de String per tal de no haver repetits amb tots els valors que pot prendre el atribut
     */
    public HashSet<String> getValorsPos(){
        return ValorsPos;
    }

    /**
     * Comprova si un valor pertany a la llista de valors possibles
     * @param valor Valor a comprovar si pertany a l'atribut
     * @return true si el valor pertany a la llista de valors possibles del atribut i fals en cas contrari
     */
    @Override
    public Boolean validarValor(String valor) {
        boolean esta = false;
        for (String valorsPo : ValorsPos) {
            if (valorsPo.equals(valor)) {
                esta = true;
                break;
            }
        }
        return esta;
    }

    /**
     *Torna el mateix valor passat per parametre ja que tots els valors
     * son ben definits i es evaluen com possibles a validarValor
     * @param valor valor de l'atribut a processar
     * @return el mateix valor passat per parametre
     */
    public String processar(String valor)  {
        return valor;
    }
}
