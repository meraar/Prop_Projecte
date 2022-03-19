package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Pere Grau
 */
public class VistaPrincipal extends JFrame {
    /**
     * Contenidor principal per la vista.
     */
    private JPanel mainPanel;

    /**
     * Menú de navegació.
     */
    private MenuNavegacio menu;

    /**
     * Contenidor secundari per la vista.
     */
    private JPanel viewPanel;

    /**
     * Botó per importar un conjunt de regles.
     */
    private JButton importarConjuntDeReglesButton;

    /**
     * Botó per importar un conjunt de registres.
     */
    private JButton importarConjuntDeRegistresButton;

    /**
     * Botó per carregar un nou fitxer de dades.
     */
    private JButton carregarFitxerDeDadesButton;

    /**
     * Botó per sortir del programa.
     */
    private JButton sortirButton;

    /**
     * Controlador de presentació.
     */
    private CtrlPresentacio presentacio;

    /**
     * Constructora per defecte amb el controlador de presentació.
     *
     * @param presentacio Controlador de presentació.
     */
    public VistaPrincipal(CtrlPresentacio presentacio) {
        this.presentacio = presentacio;

        menu.setPresentacio(presentacio);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        initListeners();
    }

    /**
     * Inicialitza els listeners dels botons.
     */
    private void initListeners() {
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaPrincipal.this.dispose();
                System.exit(0);
            }
        });

        carregarFitxerDeDadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presentacio.mostrarVistaGestorFitxerDades();
                VistaPrincipal.this.dispose();
            }
        });
        importarConjuntDeReglesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                VistaPrincipal.this.presentacio.mostrarVistaImportarRegles();
                setVisible(false);
            }
        });
        importarConjuntDeRegistresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaPrincipal.this.presentacio.mostrarVistaImportarRegistres();
            }
        });
    }

    /**
     * Mostra la vista.
     */
    public void mostrarVista() {
        setVisible(true);
    }
}
