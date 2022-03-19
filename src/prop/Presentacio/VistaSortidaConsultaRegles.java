package prop.Presentacio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Muhammad Meraj Arshad
 */
public class VistaSortidaConsultaRegles extends JDialog{
	/**
	 * Panel de la vista
	 */
	private JPanel SortidaRegles;
	/**
	 * Taula per els atributs de la regla
	 */
	private JTable table1;
	/**
	 * Informacio del resultat
	 */
	private JLabel titol;
	/**
	 * Menu Navegacio
	 */
	private MenuNavegacio menu;
	/**
	 * Buto per comprovar les dades
	 */
	private JButton Acceptar;
	/**
	 * Buto per fer una nova consulta
	 */
	private JButton consulta;
	/**
	 * Informacio per poder ordenar les columnes per les files
	 */
	private JLabel informacioPerOrdenar;
	/**
	 * valor per veure quina vista s'ha de cridar
	 */
	private int vista;
	/**
	 * Controladora de Presentacio
	 */
	private CtrlPresentacio presentacio;
	/**
	 * Constructora de la vista Sortida Consulta Regles
	 * @param presentacio Controladora de Presentacio
	 */
	public VistaSortidaConsultaRegles(CtrlPresentacio presentacio) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.presentacio = presentacio;
		initListeners();
		setContentPane(SortidaRegles);
		pack();
		menu.setPresentacio(presentacio);
	}

	/**
	 *  Funcio que incialitza les funcionalitats del botons
	 */
	private void initListeners(){
		Acceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				VistaSortidaConsultaRegles.this.presentacio.mostrarVistaPrincipal();
				setVisible(false);
			}
		});
		consulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(vista == 0) VistaSortidaConsultaRegles.this.presentacio.mostrarVistaConsultaRegles();
				else if (vista == 1) VistaSortidaConsultaRegles.this.presentacio.mostrarVistaFitrarPerCondicio();
				else if (vista == 2) VistaSortidaConsultaRegles.this.presentacio.mostrarVistaConsultaReglesPerConsequent();
				else if (vista == 3) VistaSortidaConsultaRegles.this.presentacio.mostrarVistaGenerarRegles();
				setVisible(false);
			}
		});
	}

	/**
	 * Funcio que mostra la vista de les Regles per suport, confianza i numero de condicions
	 * @param suport suport minim
	 * @param confiaza confianza minima
	 * @param numero numero de condicions
	 */
	public void mostrarVistaRegles(float suport, float confiaza, Integer numero) {
		consulta.setVisible(true);
		HashMap<Integer, ArrayList<Object>> result =  presentacio.domini.getCtrlRegles().consultarRegles(suport, confiaza, numero);
		vista = 0;
		if(result.isEmpty()){
			JOptionPane.showMessageDialog(VistaSortidaConsultaRegles.this, "No hi ha cap Regla d'Associació que tingui com a minim aquest suport, confiança i amb aquest nombre de condicions." );
			VistaSortidaConsultaRegles.this.presentacio.mostrarVistaPrincipal();
		}
		else {
			VistaSortidaConsultaRegles.this.escriuRegles(result);
			setVisible(true);
		}
	}

	/**
	 * Funcio que redimensiona les columnes de la taula depenent de les dades que te
	 * @param table Taula que s'ha de redimensionar
	 */
	private void redimensionarColumna(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 25; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width +1 , width);
			}
			if(width > 300)
				width=300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	/**
	 * Mostra les regles consultades per nom i valor de l'atribut
	 * @param nom Nom del Atribut
	 * @param valor Valor del Atribut
	 * @param cond bolea que indica si es true nom i valor es de una condicio, altrament es de un consquent
	 */
	public void mostrarVistaRegles2(String nom, String valor, boolean cond) {
		HashMap<Integer, ArrayList<Object>> result = new HashMap<>();
		result = presentacio.domini.getCtrlRegles().consultarReglesPerCondicioConsequent(nom, valor, cond);
		consulta.setVisible(true);
		if(result.isEmpty()){
			String msg = "No hi ha cap Regla d'Associació que tingui aquest Atribut com a ";
			if(cond) {
				vista = 1;
				JOptionPane.showMessageDialog(VistaSortidaConsultaRegles.this, msg + "condició.");
			}
			else{
				vista = 2;
				JOptionPane.showMessageDialog(VistaSortidaConsultaRegles.this, msg + "conseqüent.");
			}
			VistaSortidaConsultaRegles.this.presentacio.mostrarVistaPrincipal();
		}
		else {
			if(cond) vista = 1;
			else vista = 2;
			VistaSortidaConsultaRegles.this.escriuRegles(result);
			setVisible(true);
		}
	}

	/**
	 * Mostra la vista per visualitzar les regles generades
	 */
	public void mostrarReglesGenerades() {
		HashMap<Integer, ArrayList<Object>> result = presentacio.domini.getCtrlRegles().ConsultarReglesGenerades();
		vista = 3;
		consulta.setVisible(false);
		if(result.isEmpty()){
			String msg = "No s'ha pogut generar cap Regla d'Associació amb suport mínim i confiança minima introduïdes";
			JOptionPane.showMessageDialog(VistaSortidaConsultaRegles.this, msg);
			VistaSortidaConsultaRegles.this.presentacio.mostrarVistaPrincipal();
		}
		else {
			VistaSortidaConsultaRegles.this.escriuRegles(result);
			setVisible(true);
		}

	}

	/**
	 * Escriu el cojunt de dades passat per parametre a la taula
	 * @param result Hashmap que conte regles d'associacions
	 */
	private void escriuRegles(HashMap<Integer, ArrayList<Object>> result){
		DefaultTableModel model = (DefaultTableModel) table1.getModel();
		model.setColumnCount(0);
		model.setRowCount(0);
		if(model.getRowCount() == 0) {
			String[] col = {"ID Regla", "Suport", "Confiança", "Condicions", "Conseqüent"};
			for (int i = 0; i < 5; i++) {
				model.addColumn(col[i]);
			}
		}
		for (Map.Entry<Integer, ArrayList<Object>> val : result.entrySet()) {
			ArrayList<Object> aux = new ArrayList<>();
			ArrayList<Object> elemets = val.getValue();
			aux.add(val.getKey());
			for(int i = 0; i < elemets.size(); ++i){
				if(i == 0 || i ==1){
					Float convert_En_Perce = (Float)elemets.get(i) * 100f;
					Float res = Math.round( convert_En_Perce*100f)/100f;
					String res2 = String.valueOf(res) + '%';
					elemets.set(i, res2);
				}
				aux.add(elemets.get(i));
			}
			model.addRow(aux.toArray());
			aux.add(val.getKey());
		}
		VistaSortidaConsultaRegles.this.redimensionarColumna(table1);
		VistaSortidaConsultaRegles.this.table1.setAutoCreateRowSorter(true);
	}
}
