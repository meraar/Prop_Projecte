package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Muhammad Meraj Arshad
 */
public class VistaConsultaRegles extends JFrame{
	/**
	 * Panel de de la vista per consultar regles
	 */
	private JPanel Regles;
	/**
	 * Label que dona informacio per entrada de les dades
	 */
	private JLabel informacio;
	/**
	 * Label per donar informacio
	 */
	private JLabel suport;
	/**
	 * Label per introduir el suport
	 */
	private JTextField sup;
	/**
	 * Label per donar informacio
	 */
	private JLabel Confiaza;
	/**
	 * Label per possar la confianza
	 */
	private JTextField conf;
	/**
	 * Label donar informacio
	 */
	private JLabel numcond;
	/**
	 * Text per possar el numero de condicions
	 */
	private JTextField NumCond;
	/**
	 *  Butto que comprova les dades
	 */
	private JButton acceptarButton;
	/**
	 *  Tot el Panel de la vista
	 */
	private JPanel MainPanel;
	/**
	 * Menu Navegacio de la vista
	 */
	private MenuNavegacio menu;
	/**
	 * Controladora de Presentacio
	 */
	private CtrlPresentacio presentacio;

	/**
	 * Constructura de la Vista Consulta Regles per suport, confianza i numeros de condicions.
	 * @param presentacio Ctroladora de Presentacio
	 */
	public VistaConsultaRegles(CtrlPresentacio presentacio) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initListeners();
		setContentPane(MainPanel);
		pack();
		this.presentacio = presentacio;
		menu.setPresentacio(presentacio);
	}

	/**
	 * Funcio que s'encarrega de mostrar la vista Consulta Regles per suport, confianza i numeros de condicions.
	 */
	public void mostrarVistaRegles() {
		setVisible(true);
		sup.setText("");
		conf.setText("");
		NumCond.setText("");
	}

	/**
	 * Metode que s'encarrega de inicialitzar les funcionalitats del botons.
	 */
	private void initListeners(){
		acceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				boolean hihaRegles = VistaConsultaRegles.this.presentacio.domini.getCtrlRegles().hihaRegles();
				if (hihaRegles) {
					if (sup.getText().isEmpty() || conf.getText().isEmpty() || NumCond.getText().isEmpty()) {
						JOptionPane.showMessageDialog(VistaConsultaRegles.this, "Has d'omplir tots els camps anteriors per poder consultar les regles.");
					} else {
						try {
							if (Float.parseFloat(sup.getText()) > 1 || Float.parseFloat(sup.getText()) < 0) {
								JOptionPane.showMessageDialog(null, "El suport és incorrecte. El suport ha d'estar entre [0,1]. ");
								return;
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "El suport és incorrecte. El suport ha d'estar entre [0,1]. ");
							return;
						}
						try {
							if (Float.parseFloat(conf.getText()) > 1 || Float.parseFloat(conf.getText()) < 0) {
								JOptionPane.showMessageDialog(null, "La confiança és incorrecte. La confiaça ha d'estar entre [0,1]. ");
								return;
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "La confiança és incorrecte. La confiaça ha d'estar entre [0,1]. ");
							return;
						}

						try {
							if (Integer.parseInt(NumCond.getText()) < 1) {
								JOptionPane.showMessageDialog(null, "El número de condicions de la Regla ha de ser un enter més gran que 0. ");
								return;
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "El número de condicions de la Regla ha de ser un enter més gran que 0. ");
							return;
						}
						VistaConsultaRegles.this.presentacio.mostrarVistaSortidaRegles(Float.parseFloat(sup.getText()), Float.parseFloat(conf.getText()), Integer.parseInt(NumCond.getText()));
						setVisible(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(VistaConsultaRegles.this,"No hi ha regles d'associació al sistema. Genera o importa les regles.");
					VistaConsultaRegles.this.presentacio.mostrarVistaPrincipal();
				}
			}
		});
	}
}
