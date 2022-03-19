package prop.Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Carles Capilla Canovas
 * @author Albert Coma Coma
 * @author Pere Grau Molina
 * @author Muhammad Meraj Arshad
 */
public class MenuNavegacio extends JComponent {
    /**
     * Menú d'inici.
     */
    private JMenu MenuInici;

    /**
     * Item del menú inici per anar a la pàgina d'inici.
     */
    private JMenuItem paginaInci;

    /**
     * Contenidor principal per la vista.
     */
    private JPanel mainPanel;

    /**
     * Menú d'arxiu.
     */
    private JMenu menuArxiu;

    /**
     * Menú d'importar.
     */
    private JMenu menuImportar;

    /**
     * Item del menú importar per importar registres.
     */
    private JMenuItem itemImportarRegistres;

    /**
     * Item del menú importar per importar regles.
     */
    private JMenuItem itemImportarRegles;

    /**
     * Menú d'exportar.
     */
    private JMenu menuExportar;

    /**
     * Item del menú exportar per exportar registres.
     */
    private JMenuItem itemExportarRegistres;

    /**
     * Item del menú exportar per exportar regles.
     */
    private JMenuItem itemExportarRegles;

    /**
     * Menú de preprocés.
     */
    private JMenu menuPreproces;

    /**
     * Item del menú preprocés per carregar arxiu de dades.
     */
    private JMenuItem itemCarregarArxiuPreproces;

    /**
     * Item del menú preprocés per consultar registres.
     */
    private JMenuItem itemConsultarRegistres;

    /**
     * Menú de gestió de regles.
     */
    private JMenu menuGestioRegles;

    /**
     * Item del menú gestió de regles per generar regles.
     */
    private JMenuItem itemGenerarRegles;

    /**
     * Item del menú gestió de regles per validar regles.
     */
    private JMenuItem itemValidarRegles;

    /**
     * Submenú de consultar.
     */
    private JMenu itemConsultar;

    /**
     * Item del submenú consultar per consultar regles per suport i confiança.
     */
    private JMenuItem ConsultaSupConf;

    /**
     * Item del submenú consultar per consultar regles per condició.
     */
    private JMenuItem ConsultarperCond;

    /**
     * Item del submenú consultar per consultar regles per conseqüent.
     */
    private JMenuItem ConsultaperConseq;

    /**
     * Controlador de presentació.
     */
    private CtrlPresentacio presentacio;

    /**
     * Setter pel controlador de presentació.
     *
     * @param presentacio Controlador de presentació.
     */
    public void setPresentacio(CtrlPresentacio presentacio) {
        this.presentacio = presentacio;
    }

    /**
     * Constructora per defecte.
     * Inicialitza tots els listeners pels botons del menú.
     */
    public MenuNavegacio() {
        itemCarregarArxiuPreproces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuNavegacio.this.presentacio.mostrarVistaGestorFitxerDades();
            }
        });
        itemConsultarRegistres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuNavegacio.this.presentacio.mostrarVistaRegistres();
            }
        });
        ConsultaSupConf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuNavegacio.this.presentacio.mostrarVistaConsultaRegles();
            }
        });
        ConsultarperCond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaFitrarPerCondicio();
            }
        });
        ConsultaperConseq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaConsultaReglesPerConsequent();
            }
        });
        itemGenerarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaGenerarRegles();
            }
        });
        itemValidarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaValidarRegles();
            }
        });
        itemImportarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaImportarRegles();
            }
        });
        itemExportarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaExportarRegles();
            }
        });
	    ConsultaSupConf.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
			    MenuNavegacio.this.presentacio.mostrarVistaConsultaRegles();
		    }
	    });
        ConsultarperCond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaFitrarPerCondicio();
            }
        });
        ConsultaperConseq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaConsultaReglesPerConsequent();
            }
        });
        itemGenerarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaGenerarRegles();
            }
        });
        itemValidarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaValidarRegles();
            }
        });
        itemImportarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaImportarRegles();
            }
        });
        itemExportarRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuNavegacio.this.presentacio.mostrarVistaExportarRegles();
            }
        });
	    paginaInci.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
			    MenuNavegacio.this.presentacio.mostrarVistaPrincipal();
		    }
	    });
        itemImportarRegistres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuNavegacio.this.presentacio.mostrarVistaImportarRegistres();
            }
        });
        itemExportarRegistres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuNavegacio.this.presentacio.mostrarVistaExportarRegistres();
            }
        });
    }
}
