package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Muhammad Meraj Arshad
 */
public class VistaFiltrarReglaperCondicio extends JFrame {
	/**
	 * panel principal de la vista
	 */
	private JPanel panel1;
	/**
	 * Label per introduir el nom del Atribut
	 */
	private JTextField NomAtri;
	/**
	 * Label per introduir el valor del Atribut
	 */
	private JTextField ValorAtri;
	/**
	 * Buto per comprovar les dades introduides
	 */
	private JButton acceptarButton;
	/**
	 * Menu Navegacio
	 */
	private MenuNavegacio menu;
	/**
	 * Controladora de Presentacio
	 */
	private CtrlPresentacio presentacio;

	/**
	 * Constructura de la Vista Filtrar Regla per Condicio
	 * @param presentacio Controlador de Presentacio
	 */
	public VistaFiltrarReglaperCondicio(CtrlPresentacio presentacio) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initListeners();
		setContentPane(panel1);
		pack();
		this.presentacio = presentacio;
		menu.setPresentacio(presentacio);
	}

	/**
	 * Funcio que mostra la Vista Consulta Regla per Condicio
	 */
	public void mostrarVistaRegleCondicio() {
		setVisible(true);
		NomAtri.setText(null);
		ValorAtri.setText(null);
	}

	/**
	 * Funcio que s'encarrega de inicialitzar les funcionalitats dels botons
	 */
	private void initListeners(){

		acceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				boolean hihaRegles = VistaFiltrarReglaperCondicio.this.presentacio.domini.getCtrlRegles().hihaRegles();
				if (hihaRegles) {
					if (NomAtri.getText().isEmpty() || ValorAtri.getText().isEmpty()) {
						JOptionPane.showMessageDialog(VistaFiltrarReglaperCondicio.this, "Has d'omplir tots els camps anteriors per poder consultar les regles.");
					} else {
						String nom = NomAtri.getText();
						String valor = ValorAtri.getText();
						VistaFiltrarReglaperCondicio.this.presentacio.mostrarVistaSortidaRegles(nom, valor, true);
						setVisible(false);
					}
				}
				else{
					JOptionPane.showMessageDialog(VistaFiltrarReglaperCondicio.this,"No hi ha regles d'associació al sistema. Genera o importa les regles.");
					VistaFiltrarReglaperCondicio.this.presentacio.mostrarVistaPrincipal();
				}
			}
		});
	}
}
