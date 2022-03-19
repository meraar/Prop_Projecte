package prop.Presentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

/**
 * @author Albert Coma Coma
 */
public class VistaImportarRegles extends JFrame {

	/**
	 * Contenidor principal de tota la vista.
	 */
	private JPanel MainPanel;

	/**
	 * Contenidor secondari.
	 */
	private JPanel ImportarPanel;

	/**
	 * Text d'ajuda de la capçalera.
	 */
	private JLabel Capsalera;

	/**
	 * Input que contindrà el path del fitxer.
	 */
	private JTextField textField1;

	/**
	 * Botó de seleccionar fitxer.
	 */
	private JButton Select;

	/**
	 * Botó d'acceptar.
	 */
	private JButton Accept;

	/**
	 * Menú de navegació.
	 */
	private MenuNavegacio Menu;

	/**
	 * Path del fitxer a importar.
	 */
	private String pathFitxer;

	/**
	 * Controladora de presentació.
	 */
	private CtrlPresentacio ctrlPresentacio;

	/**
	 * Constructora de la Vista Importar Regles amb el path d'un fitxer binari .prop.
	 *
	 * @param ctrlPresentacio Controladora de Presentació
	 */
	public VistaImportarRegles(CtrlPresentacio ctrlPresentacio){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initListeners();
		setContentPane(MainPanel);
		pack();
		this.ctrlPresentacio = ctrlPresentacio;
		Menu.setPresentacio(ctrlPresentacio);
	}

	/**
	 * Mètode que s'encarrega de mostrar la vista Importar Regles amb un path d'un fitxer binari .prop.
	 */
	public void mostrarVista() {
		setVisible(true);
		textField1.setEnabled(false);
	}

	/**
	 * Mètode que s'encarrega de inicialitzar les funcionalitats dels botons Select i Accept.
	 */
	public void initListeners() {
		Select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFileChooser fitxSel = new JFileChooser();
				FileNameExtensionFilter filtrePROP = new FileNameExtensionFilter(".PROP","prop");
				fitxSel.setFileFilter(filtrePROP);

				int sel = fitxSel.showOpenDialog(ImportarPanel);

				if(sel == JFileChooser.APPROVE_OPTION) {
					File fitxer = fitxSel.getSelectedFile();
					textField1.setText(fitxer.getAbsolutePath());
					try(FileReader llegirFitxer = new FileReader(fitxer)) {
						pathFitxer = fitxer.getAbsolutePath();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(VistaImportarRegles.this, "Hi ha hagut un error amb l'importació del fitxer", "Error!", JOptionPane.ERROR_MESSAGE);

					}
				}
			}
		});
		Accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (textField1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(VistaImportarRegles.this,"Selecciona un fitxer.");
				}
				else {
					try {
						VistaImportarRegles.this.ctrlPresentacio.domini.getCtrlRegles().importarRegles(pathFitxer);
						VistaImportarRegles.this.ctrlPresentacio.mostrarVistaPrincipal();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(VistaImportarRegles.this, e.getMessage());
					}
				}
			}
		});
	}

}
