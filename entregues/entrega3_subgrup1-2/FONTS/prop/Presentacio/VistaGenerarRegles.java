package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Albert Coma Coma
 */
public class VistaGenerarRegles extends JFrame {

    /**
     * Contenidor secondari.
     */
    private JPanel GenerarRegles;

    /**
     * Botó per generar les regles d'associació.
     */
    private JButton Generar;

    /**
     * Input que contidrà el mínim suport.
     */
    private JTextField OmplirSuport;

    /**
     * Input que contindrà la mínima confiança.
     */
    private JTextField OmplirConfianca;

    /**
     * Input que contindrà el nombre màxim de regles a generar.
     */
    private JTextField OmplirNombreRegles;

    /**
     * Menú de navegació.
     */
    private MenuNavegacio Menu;

    /**
     * Contenidor principal de tota la vista.
     */
    private JPanel MainPanel;

    /**
     * Text d'ajuda per introduir el mínim suport.
     */
    private JLabel Suport;

    /**
     * Text d'ajuda per introduir la mínima confiança.
     */
    private JLabel Confianca;

    /**
     * Text d'ajuda per introduir el nombre màxim de regles a generar.
     */
    private JLabel NumeroRegles;

    /**
     * Controldaora de presentació.
     */
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructora de la Vista Generar Regles amb un minSup, minConf i el nombre màxim de regles que es volen generar.
     *
     * @param ctrlPresentacio Controladora de Presentació
     */
    public VistaGenerarRegles(CtrlPresentacio ctrlPresentacio) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initListeners();
        setContentPane(MainPanel);
        pack();
        this.ctrlPresentacio = ctrlPresentacio;
        Menu.setPresentacio(ctrlPresentacio);
    }

    /**
     * Mètode que s'encarrega de mostrar la vista Generar Regles amb un minSup, minConf i el nombre màxim de regles que es volen generar.
     */
    public void mostrarVista() {
        setVisible(true);
    }

    /**
     * Mètode que s'encarrega de inicialitzar les funcionalitats del botó Generar.
     */
    private void initListeners() {
        Generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (OmplirSuport.getText().isEmpty() || OmplirConfianca.getText().isEmpty() || OmplirNombreRegles.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VistaGenerarRegles.this,"Has d'omplir tots els camps anteriors per poder generar regles d'associació.");
                }
                else {
                    float minSup = Float.parseFloat(OmplirSuport.getText());
                    float minConf = Float.parseFloat(OmplirConfianca.getText());
                    int numRegles = Integer.parseInt(OmplirNombreRegles.getText());
                    if(!VistaGenerarRegles.this.ctrlPresentacio.domini.getCtrlRegistres().consultarRegistres().isEmpty()) {
                        try {
                          VistaGenerarRegles.this.ctrlPresentacio.domini.getCtrlRegles().apriori(minSup, minConf, numRegles);
                          VistaGenerarRegles.this.ctrlPresentacio.mostrarVistaSortidaRegles();
                         } catch (Exception e) {
                            JOptionPane.showMessageDialog(VistaGenerarRegles.this, e.getMessage());
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(VistaGenerarRegles.this,"No hi ha registres al sistema. Genera o importa els registres.");
                        VistaGenerarRegles.this.ctrlPresentacio.mostrarVistaPrincipal();
                    }
                }
            }
        });
    }

}
