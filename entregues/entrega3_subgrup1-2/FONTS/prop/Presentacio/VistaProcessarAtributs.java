package prop.Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Pere Grau
 */
public class VistaProcessarAtributs extends JDialog {
    /**
     * Contenidor principal de la vista.
     */
    private JPanel mainPanel;

    /**
     * Barra de progrés pel preprocés dels atributs.
     */
    private JProgressBar progressBar;

    /**
     * Botó per consultar els registres processats.
     */
    private JButton consultarRegistresButton;

    /**
     * Contenidor dels botons.
     */
    private JPanel buttonPanel;

    /**
     * Label pel text de "procés completat".
     */
    private JLabel progresCompletatLabel;

    /**
     * Label pel títol dels errors.
     */
    private JLabel titolErrorsLabel;

    /**
     * Contenidor dels errors.
     */
    private JPanel errorsPanel;

    /**
     * Llista que mostra els errors del preprocés.
     */
    private JList errorsList;

    /**
     * Controlador de presentació.
     */
    private CtrlPresentacio presentacio;

    /**
     * Constructora per defecte amb el controlador de presentació.
     *
     * @param presentacio Controlador de presentació.
     */
    public VistaProcessarAtributs(CtrlPresentacio presentacio) {
        this.presentacio = presentacio;

        setContentPane(mainPanel);
        progresCompletatLabel.setVisible(false);
        pack();
        initListeners();
    }

    /**
     * Inicialitza els listeners pels botons.
     */
    private void initListeners() {
        consultarRegistresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VistaProcessarAtributs.this.presentacio.mostrarVistaRegistres();
            }
        });
    }

    /**
     * Mostra la vista i incialitza el preprocés de les dades.
     */
    public void iniciarPreproces() {
        Object[] options = {"Cancelar", "Avortar el preprocés", "Eliminar el registre"};

        int opcio = JOptionPane.showOptionDialog(this,
                "Què desitges fer en cas que es trobi un atribut que no coincideix amb cap valor indicat al preprocés:\n" +
                        "- Avortar tot el preprocés\n" +
                        "- No tenir en compte l'atribut i eliminar el registre",
                "Indica la teva opció",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                options[2]);//default button title

        if (opcio == 0) {
            this.dispose();
            this.presentacio.mostrarVistaGestorFitxerDades();
            return;
        }

        boolean avortar = opcio == 1;

        setVisible(true);
        progressBar.setIndeterminate(true);
        consultarRegistresButton.setEnabled(false);
        errorsPanel.setVisible(false);

        new MyWorker(avortar).execute();
    }

    /**
     * @author Pere Grau
     */
    class MyWorker extends SwingWorker<Void, Void> {
        /**
         * Indica si volem avortar o no al trobar un error.
         */
        private boolean avortar;

        /**
         * Conté els errors del preprocés.
         */
        ArrayList<String> errors;

        /**
         * Constructora per defecte.
         *
         * @param avortar Indica si volem avortar o no al trobar un error.
         */
        MyWorker(boolean avortar) {
            this.avortar = avortar;
        }

        /**
         * Fer la feina del preprocés en background.
         *
         * @return null
         * @throws Exception Hi ha un error al preprocés.
         */
        protected Void doInBackground() throws Exception {
            String pathFitxer = presentacio.domini.getCtrlAtributs().getPath();
            errors = presentacio.domini.getCtrlRegistres().processarAtributs(pathFitxer, avortar);

            return null;
        }

        /**
         * Callback quan acaba el preprocés; comprova que no hi hagi cap error i el mostra si és el cas.
         */
        protected void done() {
            try {
                get();

                // Finalitzat, desactivem la barra de progrés i el botó
                progressBar.setIndeterminate(false);
                progressBar.setForeground(Color.green);
                progressBar.setBackground(Color.green);
                progresCompletatLabel.setVisible(true);
                consultarRegistresButton.setEnabled(true);

                // Mostrem els errors si n'hi ha algun
                if (!errors.isEmpty()) {
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    for (String error : errors) {
                        listModel.addElement(error);
                    }
                    errorsList.setModel(listModel);

                    errorsPanel.setVisible(true);

                    // Recalculem el tamany de la pantalla perquè es vegin tots els errors
                    revalidate();
                    pack();
                    repaint();
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(VistaProcessarAtributs.this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                VistaProcessarAtributs.this.dispose();
                VistaProcessarAtributs.this.presentacio.mostrarVistaGestorFitxerDades();
            }
        }
    }
}
