package prop.Domini;

import java.io.Serializable;

public class AtributProcessat implements Serializable {
    /**
     * Nom de l'atribut
     */
    private String nom;

    /**
     * Valor de l'atribut
     */
    private String valor;

    /**
     * Constructora per defecte.
     * Crea un atribut processat amb el nom i valor donats.
     *
     * @param nom Nom de l'atribut.
     * @param valor Valor de l'atribut.
     * @throws Exception El nom/valor de l'atribut no poden estar buits.
     */
    public AtributProcessat(String nom, String valor) throws Exception {
        this.setNom(nom);
        this.setValor(valor);
    }

    /**
     * Getter del nom.
     *
     * @return Nom de l'atribut.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter del valor.
     *
     * @return Valor de l'atribut.
     */
    public String getValor() {
        return valor;
    }

    /**
     * Setter del nom.
     *
     * @param nom Nom de l'atribut.
     * @throws Exception El nom de l'atribut no pot estar buit.
     */
    public void setNom(String nom) throws Exception {
        if (nom.equals("")) {
            throw new Exception("El nom de l'atribut no pot estar buit.");
        }

        this.nom = nom;
    }

    /**
     * Setter del valor.
     *
     * @param valor Valor de l'atribut.
     * @throws Exception El valor de l'atribut no pot estar buit.
     */
    public void setValor(String valor) throws Exception {
        if (valor.equals("")) {
            throw new Exception("El valor de l'atribut no pot estar buit.");
        }

        this.valor = valor;
    }

    /**
     * Mètode de comparació d'atributs.
     *
     * @param obj Atribut a comparar.
     * @return true o false segons si els atributs són iguals o no.
     */
    @Override
    public boolean equals(Object obj) {
        AtributProcessat atribut = (AtributProcessat)obj;

        return this.getNom().equals(atribut.getNom()) && this.getValor().equals(atribut.getValor());
    }

}
