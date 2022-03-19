package prop.Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Carles Capilla
 */
public class VistaAtributBool extends JDialog {

    /**
     * Contenidor de tots els elements de la vista
     */
    private JPanel panelMainBool;
    /**
     * Camp de text on introduir el valor a considerar fals
     */
    private JTextField AtributFalsText;
    /**
     * Camp de text on introduir el valor a considerar cert
     */
    private JTextField AtributCertText;
    /**
     * Boto per confirmar els valors introduits
     */
    private JButton confirmarButton;
    /**
     * Camp on apareix el nom de l'atribut del que s'esta definint el preproces
     */
    private JTextField nomAtributField;
    /**
     * Controladora de presentacio
     */
    private CtrlPresentacio presentacio;
    /**
     * Nom de l'atribut del que s'esta editant el preproces
     */
    private String nomAtr;

    /**
     * Constructora de la vista on definir el preproces d'un atribut de tipus Bool
     * @param presentacio controlador de presentacio
     */
    public VistaAtributBool(CtrlPresentacio presentacio) {
        super((Window)null);
        this.presentacio = presentacio;
        setContentPane(panelMainBool);
        initListeners();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setModal(true);



    }

    /**
     * Metode per mostrar la vistaAtributBool desde una altre
     * @param nomAtr nom del atribut a definir el seu preproces
     */
    public void mostrarVistaBool(String nomAtr){
        this.nomAtr= nomAtr;
        nomAtributField.setText(nomAtr);
        AtributFalsText.setText("");
        AtributCertText.setText("");


        setVisible(true);

    }

    /**
     * Metode per tractar les accions produides a la vista
     */
    private void initListeners() {

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AtributCertText.getText().equals("") || AtributFalsText.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Nombre de valors incorrecte");
                else if(AtributFalsText.getText().equals(AtributCertText.getText())){
                    JOptionPane.showMessageDialog(null, "Valors fals i cert iguals");
                }
                else {
                    ArrayList<String> valors = new ArrayList<>();
                    valors.add(0, AtributFalsText.getText());
                    valors.add(1, AtributCertText.getText());

                    try {
                        presentacio.generarPreBool(valors, nomAtr);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Valor[" + valors.get(0) + "] afegit correctament com a fals i valor[" + valors.get(1) + "] afegit correctament com a cert.");
                    VistaAtributBool.this.setVisible(false);
                }
            }
        });
    }
}
