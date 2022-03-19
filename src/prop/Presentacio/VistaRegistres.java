package prop.Presentacio;

import prop.Presentacio.utils.table.RegistresTableCellEditor;
import prop.Presentacio.utils.table.RegistresTableCellRenderer;
import prop.Presentacio.utils.table.RegistresTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author Pere Grau
 */
public class VistaRegistres extends JFrame {
    /**
     * Contenidor principal de la vista.
     */
    private JPanel mainPanel;

    /**
     * Contenidor de la vista general.
     */
    private JPanel viewPanel;

    /**
     * Menú de navegació.
     */
    private MenuNavegacio menu;

    /**
     * Taula que conté els regitres.
     */
    private JTable registresTable;

    /**
     * Contenidor del missatge de "No hi ha dades".
     */
    private JPanel noDadesPanel;

    /**
     * ScrollPane per la taula.
     */
    private JScrollPane taulaScrollPane;

    /**
     * Botó de carregar un nou arxius de dades.
     */
    private JButton carregaUnNouArxiuButton;

    /**
     * Contenidor per la taula.
     */
    private JPanel taulaPanel;

    /**
     * Contenidor pels botons.
     */
    private JPanel botonsPanel;

    /**
     * Botó de restablir canvis.
     */
    private JButton restablirCanvisButton;

    /**
     * Botó de guardar canvis.
     */
    private JButton guardarCanvisButton;

    /**
     * Controlador de presentació.
     */
    CtrlPresentacio presentacio;

    /**
     * Constructora per defecte amb el controlador de presentació.
     *
     * @param presentacio Controlador de presentació.
     */
    public VistaRegistres(CtrlPresentacio presentacio) {
        this.presentacio = presentacio;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        menu.setPresentacio(presentacio);

        initListeners();
    }

    /**
     * Mostra la vista.
     */
    public void mostrarVista() {
        initData();

        // Recalculem el tamany de la pantalla perquè es vegin tots els elements correctament
        revalidate();
        pack();
        repaint();

        setVisible(true);
    }

    /**
     * Inicialitza les dades de la taula.
     */
    private void initData() {
        HashMap<Integer, ArrayList<String>> registres = presentacio.domini.getCtrlRegistres().consultarRegistres();

        // No hi ha registres, mostrem un missatge indicant-ho
        if (!registres.entrySet().iterator().hasNext()) {
            // Amaguem la taula
            taulaPanel.setVisible(false);

            return;
        }

        taulaPanel.setVisible(true);

        // Amaguem el missatge de no elements
        noDadesPanel.setVisible(false);

        RegistresTableModel tableModel = new RegistresTableModel();

        registresTable.setModel(tableModel);
        registresTable.setDefaultRenderer(String.class, new RegistresTableCellRenderer());
        registresTable.setDefaultEditor(String.class, new RegistresTableCellEditor(new JTextField()));

        // Centrem els headers de la taula
        ((DefaultTableCellRenderer)registresTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        LinkedHashMap<String, String> nomsAtributs = presentacio.domini.getCtrlAtributs().consultarAtributs();

        // Afegim la columna pel botó de borrar
        tableModel.addColumn("Eliminar");

        // Afegim la columna per l'identificador
        tableModel.addColumn("ID");

        HashSet<String> atributsEliminats = presentacio.domini.getCtrlAtributs().consultarAtributsEliminats();

        // Afegim les columnes a la taula
        for (Map.Entry<String, String> atribut : nomsAtributs.entrySet()) {
            // Només afegim la columna si no està marcada com eliminada
            if (!atributsEliminats.contains(atribut.getKey())) {
                tableModel.addColumn(atribut.getKey());
            }
        }

        // Afegim les dades a la taula
        for (Map.Entry<Integer, ArrayList<String>> registre : registres.entrySet()) {
            ArrayList<Object> valors = new ArrayList<>();

            // Afegim el botó
            valors.add(false);

            // Afegim l'identificador
            valors.add(registre.getKey().toString());

            // Afegim els valors dels atributs
            valors.addAll(registre.getValue());

            tableModel.addRow(valors.toArray());
        }

        listenerEditarTaula();
    }

    /**
     * Inicialitza els listeners pels botons i la taula.
     */
    public void initListeners() {
        restablirCanvisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(VistaRegistres.this, "Estàs segur/a que vols borrar els canvis establerts?");

                if (input == 0) {
                    VistaRegistres.this.initData();

                    restablirCanvisButton.setEnabled(false);
                    guardarCanvisButton.setEnabled(false);
                }
            }
        });

        guardarCanvisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(VistaRegistres.this, "Estàs segur/a que vols guardar els canvis establers?");

                if (input == 0) {
                    // Guardem els canvis
                    try {
                        VistaRegistres.this.guardarCanvis();

                        VistaRegistres.this.initData();

                        restablirCanvisButton.setEnabled(false);
                        guardarCanvisButton.setEnabled(false);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(VistaRegistres.this, exception.getMessage(), "Hi ha hagut un error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        carregaUnNouArxiuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaRegistres.this.dispose();
                VistaRegistres.this.presentacio.mostrarVistaGestorFitxerDades();
            }
        });
    }

    /**
     * Guarda els canvis de la taula al sistema.
     *
     * @throws Exception El registre no existeix.
     */
    private void guardarCanvis() throws Exception {
        RegistresTableModel model = (RegistresTableModel) registresTable.getModel();

        // Apliquem els canvis dels valors modificats
        for (Map.Entry<Integer, HashMap<String, String>> valor : model.getAtributsModificats().entrySet()) {
            for (Map.Entry<String, String> atribut : valor.getValue().entrySet()) {
                presentacio.domini.getCtrlRegistres().modificarAtributRegistre(valor.getKey(), atribut.getKey(), atribut.getValue());
            }
        }

        // Eliminem els registres marcats com a eliminats
        for (int idRegistre : model.getRegistresEliminats()) {
            presentacio.domini.getCtrlRegistres().eliminarRegiste(idRegistre);
        }
    }

    /**
     * Inicialitza els listeners de la taula.
     */
    private void listenerEditarTaula() {
        registresTable.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                RegistresTableModel model = (RegistresTableModel)registresTable.getModel();
                int column = e.getColumn();
                int row = e.getFirstRow();
                String nouValor = model.getValueAt(row, column).toString();

                if (column == RegistresTableModel.ELIMINAR_COLUMN) {
                    Boolean checked = (Boolean) model.getValueAt(row, column);
                    model.eliminarFila(row, checked);
                }
                else {
                    try {
                        model.editarValor(row, column, nouValor);
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(VistaRegistres.this, exception.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Activem el botó de guardar i restablir canvis si tenim valors editats
                if (model.modificat()) {
                    guardarCanvisButton.setEnabled(true);
                    restablirCanvisButton.setEnabled(true);
                }
            }
        });
    }
}