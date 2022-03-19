package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Muhammad Meraj Arshad
 */
public class VistaFiltrarReglaperConsequent extends JFrame{
	/**
	 * Label per introduir les dades
	 */
	private JTextField NomAtri;
	/**
	 * Boto per comprovar les dades introduides
	 */
	private JButton acceptarButton;
	/**
	 * Label per escriure el valor del Atribut
	 */
	private JTextField ValorAtri;
	/**
	 * Label per donar informacio
	 */
	private JLabel informacio;
	/**
	 * Panel per les regles de condicions
	 */
	private JPanel regcond;
	/**
	 * Menu Navegacio
	 */
	private MenuNavegacio menu;
	/**
	 * Controladora de Presentacio
	 */
	private CtrlPresentacio presentacio;

	/**
	 * Constructora de la vista Filtrar Regles per Consequent
	 * @param presentacio Controladora de presentacio
	 */
	public VistaFiltrarReglaperConsequent(CtrlPresentacio presentacio) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initListeners();
		setContentPane(regcond);
		pack();
		this.presentacio = presentacio;
		menu.setPresentacio(presentacio);
	}

	/**
	 * Funcio que s'encarrega de mostrar la vista Filtrar Regles per Consequent
	 */
	public void mostrarVistaReglaConsequent() {
		setVisible(true);
		NomAtri.setText("");
		ValorAtri.setText("");
	}

	/**
	 * Metode que s'encarrega de inicialitzar les funcionalitats dels bottons de la vista.
	 */
	private void initListeners(){
		acceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				boolean hihaRegles = VistaFiltrarReglaperConsequent.this.presentacio.domini.getCtrlRegles().hihaRegles();
				if(hihaRegles) {
					if (NomAtri.getText().isEmpty() || ValorAtri.getText().isEmpty()) {
						JOptionPane.showMessageDialog(VistaFiltrarReglaperConsequent.this, "Has d'omplir tots els camps anteriors per poder consultar les regles.");
					} else {
						VistaFiltrarReglaperConsequent.this.presentacio.mostrarVistaSortidaRegles(NomAtri.getText(), ValorAtri.getText(), false);
						setVisible(false);
					}
				}
				else{
					JOptionPane.showMessageDialog(VistaFiltrarReglaperConsequent.this,"No hi ha regles d'associació al sistema. Genera o importa les regles.");
					VistaFiltrarReglaperConsequent.this.presentacio.mostrarVistaPrincipal();
				}
			}
		});
	}
}
