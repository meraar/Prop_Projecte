package prop.Presentacio.utils.table;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Pere Grau
 */
public class RegistresTableModel extends DefaultTableModel {
    /**
     * Índex de la columna ID
     */
    public static final int ID_COLUMN = 1;

    /**
     * Índex de la columna amb el botó d'eliminar
     */
    public static final int ELIMINAR_COLUMN = 0;

    /**
     * Conté els índexs de les files marcades com a eliminades.
     */
    private ArrayList<Integer> filesEliminades;

    /**
     * Conté informació dels valors editats.
     */
    private ArrayList<ValorEditat> valorsEditats;

    /**
     * Conté el valor anterior a començar l'edició d'una cel·la.
     */
    private String currentEditing;

    /**
     * Constructora per defecte.
     */
    public RegistresTableModel() {
        super();

        filesEliminades = new ArrayList<>();
        valorsEditats = new ArrayList<>();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != ID_COLUMN;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == ELIMINAR_COLUMN) {
            return Boolean.class;
        }

        return String.class;
    }

    /**
     * Estableix el valor que s'està editant.
     *
     * @param valor Valor a editar.
     */
    public void setCurrentEditing(String valor) {
        currentEditing = valor;
    }

    /**
     * Retorna el nom de l'atribut que hi ha a la columna donada.
     *
     * @param column Índex de la columna.
     * @return Nom de l'atribut.
     */
    public String getNomAtribut(int column) {
        return this.columnIdentifiers.get(column).toString();
    }

    /**
     * Retorna l'identificador del registre que hi ha a la fila donada.
     *
     * @param row Índex de la fila.
     * @return Identificador del registre.
     */
    public int getIdRegistre(int row) {
        return Integer.parseInt(this.getValueAt(row, ID_COLUMN).toString());
    }

    /**
     * Comprova si una fila està eliminada.
     *
     * @param row Índex de la fila.
     * @return Bool indicant si la fila està eliminada.
     */
    public boolean filaEliminada(int row) {
        return filesEliminades.contains(row);
    }

    /**
     * Comprova si un valor està modificat.
     *
     * @param row Índex de la fila on es troba el valor.
     * @param column Índex de la columna on es troba el valor.
     * @return Bool indicant si el valor està modificat.
     */
    public boolean valorModificat(int row, int column) {
        return valorsEditats.contains(new ValorEditat(row, column));
    }

    /**
     * Comprova si la taula ha estat modificada (valors modificats o files eliminades).
     *
     * @return Bool indicant si la taula està modificada.
     */
    public boolean modificat() {
        return !valorsEditats.isEmpty() || !filesEliminades.isEmpty();
    }

    /**
     * Edita el valor d'una cel·la.
     *
     * @param row Índex de la fila on es troba la cel·la.
     * @param column Índex de la columna on es troba la cel·la.
     * @param nouValor Nou valor a establir a la cel·la.
     * @throws Exception El nou valor està buit.
     */
    public void editarValor(int row, int column, String nouValor) throws Exception {
        // Si hem clickat sobre el checkbox eliminar, no fem res
        if (column == ELIMINAR_COLUMN) {
            return;
        }

        if (nouValor.isEmpty()) {
            // Restaurem el valor anterior
            if (currentEditing != null) {
                setValueAt(currentEditing, row, column);
            }

            throw new Exception("El valor de l'atribut no pot estar buit!");
        }

        // Si el valor és el mateix, no el marquem com a modificat
        if (currentEditing != null && currentEditing.equals(nouValor)) {
            return;
        }

        // Borrem el valor previ si n'hi ha
        valorsEditats.remove(new ValorEditat(row, column));

        valorsEditats.add(new ValorEditat(nouValor, row, column));
    }

    /**
     * Marca com a eliminada/no eliminada una fila.
     *
     * @param row Índex de la fila a eliminar.
     * @param eliminar Indica si la fila s'ha d'eliminar o no.
     */
    public void eliminarFila(int row, boolean eliminar) {
        if (eliminar) {
            filesEliminades.add(row);
        }
        else {
            filesEliminades.remove(Integer.valueOf(row));
        }
    }

    /**
     * Informació de la taula.
     */
    public void printInfo() {
        System.out.println("Hi ha un total de " + filesEliminades.size() + " files eliminades:");
        for (int fila : filesEliminades) {
            System.out.println(fila);
        }

        System.out.println("Hi ha un total de " + valorsEditats.size() + " valors editats:");
        for (ValorEditat valor : valorsEditats) {
            System.out.println(valor.toString());
        }
    }

    /**
     * Consulta el llistat de registres eliminats.
     *
     * @return ArrayList amb els identificadors dels registres eliminats.
     */
    public ArrayList<Integer> getRegistresEliminats() {
        ArrayList<Integer> registres = new ArrayList<>();
        for (int row : filesEliminades) {
            registres.add(getIdRegistre(row));
        }

        return registres;
    }

    /**
     * Consulta el llistat d'atributs modificats.
     *
     * @return HashMap amb l'identificador del registre com a key i els valors modificats com a valor.
     */
    public HashMap<Integer, HashMap<String, String>> getAtributsModificats() {
        HashMap<Integer, HashMap<String, String>> atributs = new HashMap<Integer, HashMap<String, String>>();

        for (ValorEditat valor : valorsEditats) {
            int idRegistre = getIdRegistre(valor.getRow());
            String nomAtribut = getNomAtribut(valor.getColumn());
            String nouValor = valor.getNouValor();
            HashMap<String, String> valors;

            if (atributs.containsKey(idRegistre)) {
                valors = atributs.get(idRegistre);
            }
            else {
                valors = new HashMap<>();
            }

            valors.put(nomAtribut, nouValor);
            atributs.put(idRegistre, valors);
        }

        return atributs;
    }

    /**
     * @author Pere Grau
     */
    private static class ValorEditat {
        /**
         * Índex de la fila del valor editat.
         */
        private final int row;

        /**
         * Índex de la columna del valor editat.
         */
        private final int column;

        /**
         * Nou valor a establir.
         */
        private String nouValor;

        /**
         * Constructora amb el nou valor i els dos índexs.
         *
         * @param nouValor Nou valor.
         * @param row Índex de la fila.
         * @param column Índex de la columna.
         */
        public ValorEditat(String nouValor, int row, int column) {
            this.nouValor = nouValor;
            this.row = row;
            this.column = column;

            System.out.println(this.toString());
        }

        /**
         * Constructora sense nou valor.
         *
         * @param row Índex de la fila.
         * @param column Índex de la columna.
         */
        public ValorEditat(int row, int column) {
            this.row = row;
            this.column = column;
        }

        /**
         * Consulta el nou valor.
         *
         * @return El nou valor.
         */
        public String getNouValor() {
            return nouValor;
        }

        /**
         * Consulta la fila.
         *
         * @return Índex de la fila.
         */
        public int getRow() {
            return row;
        }

        /**
         * Consulta la columna.
         *
         * @return Índex de la columna.
         */
        public int getColumn() {
            return column;
        }

        @Override
        public String toString() {
            return String.format("S'ha editat la columna %d de la fila %d: %s", column, row, nouValor);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ValorEditat that = (ValorEditat) o;

            if (row != that.row) return false;
            return column == that.column;
        }
    }
}
