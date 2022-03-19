package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Albert Coma Coma
 */
public class VistaExportarRegles extends JFrame {

    /**
     * Contenidor principal de tota la vista.
     */
    private JPanel MainPanel;

    /**
     * Contenidor secondari.
     */
    private JPanel exportarRegles;

    /**
     * Text d'ajuda de la capçalera.
     */
    private JLabel Capsalera;

    /**
     * Input que contindrà el path del fitxer.
     */
    private JTextField textField1;

    /**
     * Botó de seleccionar fitxer.
     */
    private JButton Select;

    /**
     * Botó d'acceptar.
      */
    private JButton Accept;

    /**
     * Menú de navegació.
     */
    private MenuNavegacio Menu;

    /**
     * Path del fitxer a exportar.
     */
    private String pathFitxer;

    /**
     * Controladora de presentació.
     */
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructura de la Vista Exportar Regles amb el path d'un directori.
     * @param ctrlPresentacio Controladora de Presentació
     */
    public VistaExportarRegles(CtrlPresentacio ctrlPresentacio){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initListeners();
        setContentPane(MainPanel);
        pack();
        this.ctrlPresentacio = ctrlPresentacio;
        Menu.setPresentacio(ctrlPresentacio);
    }

    /**
     * Mètode que s'encarrega de mostrar la vista Exportar Regles amb el path d'un directori.
     */
    public void mostrarVista() {
        setVisible(true);
        textField1.setEnabled(false);
    }

    /**
     * Mètode que s'encarrega de inicialitzar les funcionalitats dels butons Select i Accept.
     */
    public void initListeners() {
        Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fitxSel = new JFileChooser();
                int sel = fitxSel.showSaveDialog(VistaExportarRegles.this);
                if(sel == JFileChooser.APPROVE_OPTION) {
                    File directori = fitxSel.getSelectedFile();
                    textField1.setText(directori.getAbsolutePath());
                    pathFitxer = directori.getAbsolutePath() + ".regles.prop";
                }
            }
        });
        Accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VistaExportarRegles.this,"Selecciona la carpeta a on vols exportar les regles.");
                }
                else {
                    try {
                        VistaExportarRegles.this.ctrlPresentacio.domini.getCtrlRegles().exportarRegles(pathFitxer);
                        VistaExportarRegles.this.ctrlPresentacio.mostrarVistaPrincipal();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(VistaExportarRegles.this, e.getMessage());
                    }
                }
            }
        });
    }
}
