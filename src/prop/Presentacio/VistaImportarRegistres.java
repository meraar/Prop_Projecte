package prop.Presentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

/**
 * @author Pere Grau
 */
public class VistaImportarRegistres extends JDialog {
    /**
     * Contenidor principal de la vista.
     */
    private JPanel mainPanel;

    /**
     * Contenidor secundàri.
     */
    private JPanel ImportarPanel;

    /**
     * Text de capçalera.
     */
    private JLabel Capsalera;

    /**
     * Input on s'escriurà el fitxer a on exportar.
     */
    private JTextField textField1;

    /**
     * Botó per seleccionar el fitxer.
     */
    private JButton Select;

    /**
     * Botó d'acceptar.
     */
    private JButton Accept;

    /**
     * Controlador de presentació.
     */
    private CtrlPresentacio presentacio;

    /**
     * Path del fitxer on exportar les dades.
     */
    String pathFitxer;

    /**
     * Constructora per defecte amb el controlador de presentació.
     *
     * @param ctrlPresentacio Controlador de presentació.
     */
    VistaImportarRegistres(CtrlPresentacio ctrlPresentacio) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initListeners();
        setContentPane(mainPanel);
        pack();
        this.presentacio = ctrlPresentacio;
    }

    /**
     * Mostra la vista.
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
                FileNameExtensionFilter filtrePROP = new FileNameExtensionFilter("Arxiu PROP","prop");
                fitxSel.setFileFilter(filtrePROP);

                int sel = fitxSel.showOpenDialog(ImportarPanel);

                if(sel == JFileChooser.APPROVE_OPTION) {
                    File fitxer = fitxSel.getSelectedFile();
                    textField1.setText(fitxer.getAbsolutePath());
                    try {
                        FileReader llegirFitxer = new FileReader(fitxer);
                        pathFitxer = fitxer.getAbsolutePath();
                    } catch (Exception e) {
                        textField1.setText("");
                        JOptionPane.showMessageDialog(VistaImportarRegistres.this, "Hi ha hagut un error amb l'importació del fitxer", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        Accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    VistaImportarRegistres.this.presentacio.domini.getCtrlRegistres().importarRegistres(pathFitxer);
                    JOptionPane.showMessageDialog(VistaImportarRegistres.this, "Registres importats correctament!");
                    VistaImportarRegistres.this.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(VistaImportarRegistres.this, e.getMessage());
                }
            }
        });
    }
}
