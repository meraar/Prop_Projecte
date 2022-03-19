package prop.Domini;

import java.util.ArrayList;


/**
 * @author Carles Capilla
 */
public class AtributBool extends Atribut {
    /**
     * Valor a considerar cert de un atribut binari
     */
    private String ValorTrue;
    /**
     * Valor a considerar fals de un atribut binari
     */
    private String ValorFalse;


    /**
     * Constructora amb assignacio de nom i tipus
     *
     * @param nom Nom a assignar al atribut
     * @param tipus tipus a assignar al atribut
     * @throws Exception Error de tipus
     */
    public AtributBool(String nom, Tipus tipus) throws Exception{
            super(nom);
            if(tipus.equals(Tipus.BOOL))this.tipus=tipus;
            else throw new Exception("Tipus d'atribut invalid");
    }


    /**
     * Constructura amb assignacio de nom,tipus i valors cert i fals
     *
     * @param nom nom Nom a assignar al atribut
     * @param tipus tipus Tipus a assignar al atribut
     * @param ValorFalse ValorFalse Valor a assignar com fals al atribut
     * @param ValorTrue  ValorTrue Valor a assignar com cert al atribut
     * @throws Exception Error de tipus
     */
    public AtributBool(String nom, Tipus tipus, String ValorFalse, String ValorTrue) throws Exception {
        super(nom);
        if(tipus.equals("boolean"))this.tipus=tipus;
        else throw new Exception("Tipus d'atribut invalid");
        if(!ValorFalse.equals(""))
        this.ValorFalse = ValorFalse;
        else throw new Exception("Valor fals invalid");
        if(!ValorTrue.equals("") && !ValorTrue.equals(ValorFalse))
        this.ValorTrue = ValorTrue;
        else throw new Exception("Valor cert invalid");
    }

    /**
     * Comprova si el valor passat per parametre pertany al atribut ja sigui com a cert o com a fals
     * @param valor Valor a comprovar si pertany al atribut
     * @return Cert si valor es cert o fals al atribut i fals en cas contrari
     */
    @Override
    public Boolean validarValor(String valor) {
        return valor.equals(ValorTrue) || valor.equals(ValorFalse);
    }

    /**
     * Comprova si el valor del parametre es el considerat true, si no fos aixi el considerarem
     * fals ja que estem segurs que al fitxer nomes hi haura dos valors possibles
     * @param valor valor del atribut a processar
     * @return "true" si es tractar del atribut considerat cert i "false" en cas contrari
     */
    public String processar(String valor){
        if (valor.equals(ValorTrue)) return "true";
        else return "false";
    }

    /**
     * Assigna la llista de valors passada per parametre com a fals i cert en el ordre que apareguin a la llista
     * @param valorsB Llista de dos elements on la primera posicio es el element fals i a la segona el cert
     * @throws Exception El nombre de valors no es el correcte
     */
    public void setValorsB(ArrayList<String> valorsB) throws Exception{

        if(valorsB.size()!=2) throw new Exception("Nombre de valors invalid");
            else{
            this.ValorFalse = valorsB.get(0);
            this.ValorTrue = valorsB.get(1);
        }
    }

    /**
     * Getter dels valors fals i cert de un atribut de tipus boolean en forma de array
     * @return un array amb els valors fals i cert a les posicions 0 i 1
     * @throws Exception Valors no assignats al atribut
     */
    public ArrayList<String> getValorsB() throws Exception{
        ArrayList<String> val = new ArrayList<>();
        if(ValorFalse.equals("") || ValorTrue.equals(""))throw new Exception("L'atribut no te assignats els valors true i false");
        val.add(0,ValorFalse);
        val.add(1,ValorTrue);
        return val;
    }

}
