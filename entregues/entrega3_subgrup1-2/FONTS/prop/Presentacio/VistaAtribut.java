package prop.Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Carles Capilla
 */
public class VistaAtribut extends JDialog{
    /**
     * Contenidor de tots els elements de la vista
     */
    private JPanel panelMainAtr;
    /**
     * Camp de text on apareix el nom de l'atribut
     */
    private JTextField nomAtributField;
    /**
     * Boto per comensar a editar el preproces de l'atribut
     */
    private JButton edPrepButton;
    /**
     * Combo box per seleccionar el tipus de l'atribut
     */
    private JComboBox tipuscomboBox;
    /**
     * Boto per eliminar l'atribut dels registres a processar
     */
    private JButton elimAtrButton;
    /**
     * Controladora de presentacio
     */
    private CtrlPresentacio presentacio;
    /**
     * Nom de l' atribut
     */
    private String nomAtr;

    /**
     * Constructora de la vista on definir el tipus de cada atribut per aplicar el preproces corresponent posteriorment
     * @param presentacio controlador de presentacio
     */
    public VistaAtribut(CtrlPresentacio presentacio) {
        super((Window)null);
        this.presentacio = presentacio;
        nomAtributField.setText(nomAtr);

        setModal(true);
    }

    /**
     * Metode per mostrar la vistaAtribut desde una altre
     * @param nomAtr nom de l'atribut a indicar el tipus i iniciar el preproces
     */
    public void mostrarVistaAtribut(String nomAtr){
        this.nomAtr= nomAtr;
        //System.out.println(nomAtr);
        nomAtributField.setText(nomAtr);
    }

    /**
     * Metode per tractar les accions produides a la vista
     */
    private void initListeners() {

        edPrepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(tipuscomboBox.getSelectedItem().toString() == "BOOL"){
                   presentacio.mostrarVistaAtributBool(nomAtr);
               }else if(tipuscomboBox.getSelectedItem().toString() == "FLOAT"){
                   presentacio.mostrarVistaAtributInt(nomAtr);
               }else{
                   presentacio.mostrarVistaAtributString(nomAtr);
               }
               VistaAtribut.this.dispose();
            }
        });
        elimAtrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //listener on posar eliminat a true d'un atribut
                presentacio.elimAtribut(nomAtributField.getText());
                VistaAtribut.this.dispose();
            }
        });
    }

    /**
     * Metode per inicialitzar altres parametres de la vista
     */
    public void init(){
        setContentPane(panelMainAtr);
        initListeners();
    }
}
