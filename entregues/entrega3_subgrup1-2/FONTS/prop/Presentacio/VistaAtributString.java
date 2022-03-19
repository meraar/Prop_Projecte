package prop.Presentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * @author Carles Capilla
 */
public class VistaAtributString extends JDialog {
    /**
     * Contenidor de tots els elements de la vista
     */
    private JPanel panelMainString;
    /**
     * Boto per finalitzar preproces i afegir tots els valors possibles
     */
    private JButton confirmarButton;
    /**
     * Controladora de presentacio
     */
    private CtrlPresentacio presentacio;
    /**
     * Boto per afegir un interval i poder afegir-ne un altre
     */
    private JButton confirmarIAfegirButton;
    /**
     * Camp on introduir el valor possible a prendre per l'atribut
     */
    private JTextField valorField;
    /**
     * Nom de l'atribut del que s'esta definint el preproces
     */
    private JTextField nomAtributField;
    /**
     * Taula on apareixen tots els valors que es van definint com a possibles
     */
    private JTable valorsPTable;
    /**
     * Nom de l'atribut del que s'esta definint el preproces
     */
    private String nomAtr;
    /**
     * Constructora de la vista on definir el preproces d'un atribut de tipus String
     * @param presentacio Controladora de presentacio
     */
    public VistaAtributString(CtrlPresentacio presentacio) {
        super((Window)null);
        setModal(true);
        this.presentacio = presentacio;
        mostrarVistaString(nomAtr);

    }

    /**
     * Metode per mostrar la VistaAtributInt des de una altre vista
     * @param nomAtr nom del atribut a definir el preproces
     */
    public void mostrarVistaString(String nomAtr){
        this.nomAtr= nomAtr;
        nomAtributField.setText(nomAtr);
    }

    /**
     * Metode per tractar les accions produides a la vista
     */
    private void initListeners() {
        HashSet<String> valors = new HashSet<>();
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    presentacio.generarPreString(valors,nomAtr);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                VistaAtributString.this.dispose();
            }

        });
        confirmarIAfegirButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(valorField.getText()!= null && !valorField.getText().equals("")) {
                    valors.add(valorField.getText());
                    JOptionPane.showMessageDialog(null, "Valor[" + valorField.getText() + "] afegit correctament.");

                    DefaultTableModel model = (DefaultTableModel) valorsPTable.getModel();
//                    ArrayList<String> valT = new ArrayList<>();
//                    valT.add(valorField.getText());
                    model.setColumnIdentifiers(new Object[]{"Valors Possibles"});
                    valorsPTable.setModel(model);
                    model.addRow(new Object[]{valorField.getText()});
                    valorField.setText("");
                }else
                    JOptionPane.showMessageDialog(null, "Un valor buit no pot ser un valor possible.");
            }
        });
    }

    /**
     * Metode per inicialitzar altres parametres de la vista
     */
    public void init(){
        setContentPane(panelMainString);
        initListeners();
    }
}
