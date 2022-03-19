package prop.Presentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Albert Coma Coma
 */
public class VistaValidarRegles extends JFrame {

    /**
     * Contenidor principal de tota la vista.
     */
    private JPanel MainPanel;

    /**
     * Menú de navegació.
     */
    private MenuNavegacio Menu;

    /**
     * Contenidor secondari.
     */
    private JPanel FiltrarRegles;

    /**
     * Text d'ajuda de la capçalera.
     */
    private JLabel Capsalera;

    /**
     * Text d'ajuda per introudir el mínim suport.
     */
    private JLabel MinSuport;

    /**
     * Text d'ajuda per introduir la mínima confiança.
     */
    private JLabel MinConf;

    /**
     * Input que contidrà el valor de la mínima confiança.
     */
    private JTextField OmplirMinConf;

    /**
     * Botó per validar les regles.
     */
    private JButton Validar;

    /**
     * Input que contindrà el valor del mínim suport.
     */
    private JTextField OmplirMinSuport;

    /**
     * Input que contidrà el path del fitxer.
     */
    private JTextField textField1;

    /**
     * Botó per seleccionar fitxer.
     */
    private JButton selectReg;

    /**
     * Text d'ajuda per seleccionar el fitxer.
     */
    private JLabel Select;

    /**
     * Path del fitxer de noves dades.
     */
    private String pathFitxer;

    /**
     * Controladora de presentació.
     */
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructora de la Vista Validar Regles amb un nouMinSuport, nouMinConfiança i un nou fitxer de dades .csv.
     *
     * @param ctrlPresentacio Controladora de Presentació.
     */
    public VistaValidarRegles(CtrlPresentacio ctrlPresentacio) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initListeners();
        setContentPane(MainPanel);
        pack();
        this.ctrlPresentacio = ctrlPresentacio;
        Menu.setPresentacio(ctrlPresentacio);
    }

    /**
     * Mètode que s'encarrega de mostrar la vista Validar Regles amb un nouMinSuport, nouMinConfiança i un nou conjunt de dades .csv.
     */
    public void mostrarVista() {
        setVisible(true);
        textField1.setEnabled(false);
    }

    /**
     * Mètode que s'encarrega de inicialitzar les funcionalitats dels butons selectReg i Validar.
     */
    private void initListeners() {
        selectReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fitxSel = new JFileChooser();
                FileNameExtensionFilter filtreCSV = new FileNameExtensionFilter(".CSV","csv");
                fitxSel.setFileFilter(filtreCSV);

                int sel = fitxSel.showOpenDialog(FiltrarRegles);

                if(sel == JFileChooser.APPROVE_OPTION) {
                    File fitxer = fitxSel.getSelectedFile();
                    textField1.setText(fitxer.getAbsolutePath());
                    try(FileReader llegirFitxer = new FileReader(fitxer)) {
                        pathFitxer = fitxer.getAbsolutePath();
                        VistaValidarRegles.this.ctrlPresentacio.domini.getCtrlRegistres().processarAtributs(pathFitxer,false);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(VistaValidarRegles.this, "Hi ha hagut un error amb l'importació del fitxer", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        Validar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (OmplirMinSuport.getText().isEmpty() || OmplirMinConf.getText().isEmpty() || textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VistaValidarRegles.this,"Has d'omplir tots els camps anteriors per poder validar les regles d'associació.");
                }
                else {
                    float minSup = Float.parseFloat(OmplirMinSuport.getText());
                    float minConf = Float.parseFloat(OmplirMinConf.getText());
                    if (VistaValidarRegles.this.ctrlPresentacio.domini.getCtrlRegles().hihaRegles()) {
                        try {
                            VistaValidarRegles.this.ctrlPresentacio.domini.getCtrlRegles().validarRegles(minSup, minConf);
                            VistaValidarRegles.this.ctrlPresentacio.mostrarVistaOutputValidarRegles();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(VistaValidarRegles.this, e.getMessage());
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(VistaValidarRegles.this,"No hi ha regles d'associació al sistema. Genera o importa les regles.");
                        VistaValidarRegles.this.ctrlPresentacio.mostrarVistaPrincipal();
                    }
                }
            }
        });
    }
}
