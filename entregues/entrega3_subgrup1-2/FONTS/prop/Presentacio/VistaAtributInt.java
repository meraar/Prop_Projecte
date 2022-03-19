package prop.Presentacio;

import prop.Domini.Interval;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * @author Carles Capilla
 */
public class VistaAtributInt extends JDialog {
    /**
     * Contenidor de tots els elements de la vista
     */
    private JPanel panelMainInt;
    /**
     * Boto per confirmar el preproces definit
     */
    private JButton confirmarButton;
    /**
     * Boto per afegir l'interval definit i afegir el seguent
     */
    private JButton confirmarIAfegirButton;
    /**
     * Camp per al limit inferior de l'interval
     */
    private JTextField LimInfField;
    /**
     * Camp per al limit superior de l'interval
     */
    private JTextField LimSupField;
    /**
     * Seleccionable per a indicar si el limit inferior esta inclos
     */
    private JCheckBox límitInferiorInclòsCheckBox;
    /**
     * Seleccionable per a indicar si el limit superior esta inclos
     */
    private JCheckBox límitSuperiorInclòsCheckBox;
    /**
     * nom de l'interval a definir
     */
    private JTextField nomField;
    /**
     * camp que conte el nom de l'atribut del que s'esta definint el preproces
     */
    private JTextField nomAtributField;
    /**
     * Taula on apareixen els intervals definits
     */
    private JTable interTable;
    /**
     * Controladora de presentacio
     */
    private CtrlPresentacio presentacio;
    /**
     * nom de l'atribut del que s'esta definint el preproces
     */
    private String nomAtr;
    /**
     * Llista d'intervals definits per a l'atribut
     */
    ArrayList<String> LlistInt = new ArrayList<>();
    /**
     * limits inferior i superior del interval a afegir
     */
    private float LimInf,LimSup;
    /**
     * Valor infinit positiu per als limits dels intervals
     */
    private static final float INFP = Float.MAX_VALUE;
    /**
     * Valor infinit negatiu per als limits dels intervals
     */
    private static final float INFN = -Float.MAX_VALUE;

    /**
     * Constructora de la vista on definir el preproces d'un atribut de tipus Int
     * @param presentacio Controladora de presentacio
     */
    public VistaAtributInt(CtrlPresentacio presentacio) {
        super((Window)null);
        setModal(true);
        this.presentacio = presentacio;
        mostrarVistaInt(nomAtr);
    }

    /**
     * Metode per mostrar la VistaAtributInt des de una altre vista
     * @param nomAtr nom de l'atribut a editar el seu preproces
     */
    public void mostrarVistaInt(String nomAtr){
        this.nomAtr= nomAtr;
        nomAtributField.setText(nomAtr);
    }

    /**
     * Metode per tractar les accions produides a la vista
     */
    private void initListeners() {

        ArrayList<ArrayList<String>> intervals = new ArrayList<>();
        confirmarIAfegirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> interval = new ArrayList<>();
                interval.add(LimSupField.getText());
                interval.add(LimInfField.getText());

                if (límitSuperiorInclòsCheckBox.isSelected())
                    interval.add("true");
                else interval.add("false");

                if (límitInferiorInclòsCheckBox.isSelected())
                    interval.add("true");
                else interval.add("false");

                interval.add(nomField.getText());

                boolean jaExisteix = false;
                for (int i = 0; i < LlistInt.size(); ++i) {
                    if (LlistInt.get(i).equals(nomField.getText())) jaExisteix = true;
                }


                if (LimInfField.getText().equals("INFN")) {
                    LimInf = INFN;
                } else if (LimInfField.getText().equals("INFP")) {
                    LimInf = INFP;
                } else {
                    LimInf = Float.parseFloat(LimInfField.getText());
                }
                if (LimSupField.getText().equals("INFP")) {
                    LimSup = INFP;
                } else if (LimSupField.getText().equals("INFN")) {
                    LimSup = INFN;
                } else {
                    LimSup = Float.parseFloat(LimSupField.getText());
                }

                if (jaExisteix) {
                    JOptionPane.showMessageDialog(null, "Interval [" + nomField.getText() + "] no s'ha afegit ja que el seu nom és utilitzat en un altre interval.");
                } else if (LimInf > LimSup) {
                    JOptionPane.showMessageDialog(null, "Interval [" + nomField.getText() + "] no s'ha afegit ja que el seu límit inferior és més gran que el seu límit superior.");
                } else if(nomField.getText().equals("") || nomField.getText().equals(null)){
                    JOptionPane.showMessageDialog(null, "Interval [" + nomField.getText() + "] no s'ha afegit ja que el seu nom és buit.");
                }else {
                    LlistInt.add(nomField.getText());
                    intervals.add(interval);
                    JOptionPane.showMessageDialog(null, "Interval [" + nomField.getText() + "] afegit correctament.");


                    DefaultTableModel model = (DefaultTableModel) interTable.getModel();

                    model.setColumnIdentifiers(new Object[]{"Nom Intèrval","Límit Inf", "Límit Sup","Límit Inf inclòs","Límit Sup inclòs"});
                    interTable.setModel(model);
                    model.addRow(new Object[]{nomField.getText(),LimInfField.getText(),LimSupField.getText(),interval.get(3),interval.get(2)});

                    LimInfField.setText(LimSupField.getText());
                    LimInfField.setEditable(false);
                    if(límitSuperiorInclòsCheckBox.isSelected())
                        límitInferiorInclòsCheckBox.setSelected(false);
                    else límitInferiorInclòsCheckBox.setSelected(true);
                    límitInferiorInclòsCheckBox.setEnabled(false);

                    nomField.setText("");
                    LimSupField.setText("");
                    límitSuperiorInclòsCheckBox.setSelected(false);
                }

            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    presentacio.generarPreInt(intervals,nomAtr);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                VistaAtributInt.this.dispose();
            }
        });

    }

    /**
     * Metode per inicialitzar altres parametres de la vista
     */
    public void init() {
        setContentPane(panelMainInt);
        initListeners();
    }
}