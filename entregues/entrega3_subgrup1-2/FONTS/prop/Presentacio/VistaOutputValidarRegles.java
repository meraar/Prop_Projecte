package prop.Presentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Albert Coma Coma
 */
public class VistaOutputValidarRegles extends JFrame {

    /**
     * Contenidor principal de tota la vista.
     */
    private JPanel MainPanel;

    /**
     * Contenidor secondari.
     */
    private JPanel OutValidar;

    /**
     * Taula amb totes les regles validades.
     */
    private JTable TaulaReglesValidades;

    /**
     * Text d'ajuda de la capçalera.
     */
    private JLabel Capsalera;

    /**
     * Botó d'acceptar.
     */
    private JButton Ok;

    /**
     * Menú navegació.
     */
    private MenuNavegacio Menu;

    /**
     * Controladora de presentació.
     */
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructora de la Vista Output Validar Regles.
     *
     * @param ctrlPresentacio Controladora de Presentació
     */
    public VistaOutputValidarRegles(CtrlPresentacio ctrlPresentacio) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ctrlPresentacio = ctrlPresentacio;
        initListeners();
        setContentPane(MainPanel);
        pack();
        Menu.setPresentacio(ctrlPresentacio);
    }

    /**
     * Mètode que s'encarrega de mostrar la vista Output Validar Regles amb una taula omplerta amb totes les regles que
     * s'han validat, amb el identificador, suport, confiança i el nou suport i confiança obtinguts al validar.
     */
    public void mostrarVista() {
        DefaultTableModel model = (DefaultTableModel) TaulaReglesValidades.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        String[] col = {"ID Regla", "Suport", "Confiança", "Nou Suport", "Nova Confiança"};

       for (int i = 0; i < 5; i++) {
            model.addColumn(col[i]);
        }

        HashMap<Integer, ArrayList<Float>> validats = ctrlPresentacio.domini.getCtrlRegles().ConsultaReglesValidades();

        for (Map.Entry<Integer, ArrayList<Float>> val : validats.entrySet()) {
            ArrayList<Object> aux = new ArrayList<>();
            ArrayList<Float> elemets = val.getValue();
            aux.add(val.getKey());
            for(int i = 0; i < elemets.size(); ++i){
                Float convert_En_Perce = (Float)elemets.get(i) * 100f;
                Float res = Math.round( convert_En_Perce*100f)/100f;
                aux.add(String.valueOf(res)+"%");
            }
            model.addRow(aux.toArray());
            aux.add(val.getKey());
        }
        setVisible(true);
        VistaOutputValidarRegles.this.TaulaReglesValidades.setAutoCreateRowSorter(true);
    }

    /**
     * Mètode que s'encarrega de inicialitzar la funcionalitat del butó Ok.
     */
    private void initListeners() {
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                VistaOutputValidarRegles.this.ctrlPresentacio.mostrarVistaPrincipal();
            }
        });
    }
}
