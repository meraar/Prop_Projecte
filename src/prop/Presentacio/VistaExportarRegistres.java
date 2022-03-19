package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Pere Grau
 */
public class VistaExportarRegistres extends JDialog {
    /**
     * Contenidor principal de tota la vista.
     */
    private JPanel mainPanel;

    /**
     * Contenidor secondàri.
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
     * Controlador de presentacio.
     */
    private CtrlPresentacio presentacio;

    /**
     * Path del fitxer a exportar.
     */
    private String pathFitxer;

    /**
     * Constructora per defecte amb el controlador de presentació.
     *
     * @param presentacio Controlador de presentació.
     */
    VistaExportarRegistres(CtrlPresentacio presentacio) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initListeners();
        setContentPane(mainPanel);
        pack();
        this.presentacio = presentacio;
    }

    /**
     * Mostra la vista
     */
    public void mostrarVista() { setVisible(true);}

    /**
     * Inicialitza els listeners pels botons.
     */
    public void initListeners() {
        Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fitxSel = new JFileChooser();

                int sel = fitxSel.showSaveDialog(VistaExportarRegistres.this);

                if(sel == JFileChooser.APPROVE_OPTION) {
                    File directori = fitxSel.getSelectedFile();
                    pathFitxer = directori.getAbsolutePath() + ".registres.prop";
                    textField1.setText(pathFitxer);
                }
            }
        });
        Accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    VistaExportarRegistres.this.presentacio.domini.getCtrlRegistres().exportarRegistres(pathFitxer);
                    VistaExportarRegistres.this.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(VistaExportarRegistres.this, e.getMessage());
                }
            }
        });
    }
}
