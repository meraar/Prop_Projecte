package prop.Presentacio;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Carles Capilla
 */
public class VistaGestorFitxerDades extends JFrame {
    /**
     * Camp de text on introduir el path del fitxer o on apareix al seleccionar-lo
     */
    private JTextField textField1;
    /**
     * Contenidor de tots els elements de la vista
     */
    private JPanel panelMainGestor;
    /**
     * Boto per accedir a la navegacio per directoris per poder escollir el fitxer de dades
     */
    private JButton selecButton;
    /**
     * Boto per confirmar la seleccio del fitxer
     */
    private JButton confirmarButton;
    /**
     * Menu per accedir a les funcionalitats principals del domini
     */
    private prop.Presentacio.MenuNavegacio MenuNavegacio;
    /**
     * Controladora de presentacio
     */
    private CtrlPresentacio presentacio;
    /**
     * Llista dels noms dels atributs presents al fitxer
     */
    ArrayList<String> nomAtributs = new ArrayList<>();

    /**
     * Constructora de la vista on importar un fitxer de dades .csv
     * @param presentacio Controladora de presentacio
     */
    public VistaGestorFitxerDades(CtrlPresentacio presentacio) {

        this.presentacio = presentacio;
        MenuNavegacio.setPresentacio(presentacio);
    }

    /**
     * Metode per tractar les accions produides a la vista
     */
    private void initListeners() {

       selecButton.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               JFileChooser fitxSel = new JFileChooser();
               FileNameExtensionFilter filtreCSV = new FileNameExtensionFilter(".CSV","csv");
               fitxSel.setFileFilter(filtreCSV);

               int sel = fitxSel.showOpenDialog(panelMainGestor);

               if(sel == JFileChooser.APPROVE_OPTION){
                   File fitxer = fitxSel.getSelectedFile();
                   textField1.setText(fitxer.getAbsolutePath());
                           try(FileReader llegFitxer = new FileReader(fitxer)) {
                                //guardar tots els atributs en un ArrayList per passar a altres vistes
                               confirmarButton.setEnabled(true);
                               nomAtributs = presentacio.generarAtr(fitxer.getAbsolutePath());

                           } catch (FileNotFoundException ex) {
                               ex.printStackTrace();
                           } catch (IOException ex) {
                               ex.printStackTrace();
                           } catch (Exception ex) {
                               ex.printStackTrace();
                           }
               }
           }
       });
       confirmarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               VistaGestorFitxerDades.this.dispose();
               for(int i = 0;i<nomAtributs.size();++i){
                   presentacio.mostrarVistaAtribut(nomAtributs.get(i));
               }

               presentacio.mostrarVistaProcessarAtributs();

           }
       });
    }

    /**
     * Metode per inicialitzar altres parametres de la vista
     */
    public void init(){
        setContentPane(panelMainGestor);
        initListeners();
    }
}
