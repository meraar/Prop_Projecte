package prop.Presentacio;

import prop.Domini.CtrlDomini;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Carles Capilla Canovas
 * @author Albert Coma Coma
 * @author Pere Grau Molina
 * @author Muhammad Meraj Arshad
 */
public class CtrlPresentacio {
    /**
     * Vista de procés dels atributs.
     */
    private final VistaProcessarAtributs vistaProcessarAtributs;

    /**
     * Vista principal.
     */
    VistaPrincipal vistaPrincipal;

    /**
     * Vista de preprocés pels atributs bool.
     */
    VistaAtributBool vistaAtributBool;

    /**
     * Vista de preprocés pels atributs string.
     */
    VistaAtributString vistaAtributString;

    /**
     * Vista de preprocés pels atributs int.
     */
    VistaAtributInt vistaAtributInt;

    /**
     * Vista de preprocés pels atributs.
     */
    VistaAtribut vistaAtribut;

    /**
     * Vista de gestió de fitxer de dades.
     */
    VistaGestorFitxerDades vistaGestorFitxerDades;

    /**
     * Vista per consultar registres.
     */
    VistaRegistres vistaRegistres;

    /**
     * Controlador de domini.
     */
    CtrlDomini domini;

    /**
     * Guarda la referència de la finestra mostrada actualment.
     */
    Window actual;

    /**
     * Vista de generar regles.
     */
    VistaGenerarRegles vistaGenerarRegles;

    /**
     * Vista de validar regles.
     */
    VistaValidarRegles vistaValidarRegles;

    /**
     * Vista output per la validació de regles.
     */
    VistaOutputValidarRegles vistaOutputValidarRegles;

    /**
     * Vista de consulta de regles.
     */
    VistaConsultaRegles vistaConsultaRegles;

    /**
     * Vista de sortida per la consulta de regles.
     */
    VistaSortidaConsultaRegles vistaSortidaConsultaRegles;

    /**
     * Vista de filtrar regles per condició.
     */
    VistaFiltrarReglaperCondicio vistaFiltrarReglaperCondicio;

    /**
     * Vista de filtrar regles per conseqüent.
     */
    VistaFiltrarReglaperConsequent vistaFiltrarReglaperConsequent;

    /**
     * Vista d'importació de regles.
     */
    VistaImportarRegles vistaImportarRegles;

    /**
     * Vista d'exportació de regles
     */
    VistaExportarRegles vistaExportarRegles;

    /**
     * Vista d'importació de registres.
     */
    VistaImportarRegistres vistaImportarRegistres;

    /**
     * Vista d'exportació de registres.
     */
    VistaExportarRegistres vistaExportarRegistres;

    /**
     * Constructora del Controlador de Presentacio.
     *
     * Inicialitza totes les vistes.
     */
    public CtrlPresentacio() {
        domini = new CtrlDomini();
        vistaPrincipal = new VistaPrincipal(this);
        vistaAtributBool = new VistaAtributBool(this);
        vistaAtributString = new VistaAtributString(this);
        vistaAtributInt = new VistaAtributInt(this);
        vistaAtribut = new VistaAtribut(this);
        vistaGestorFitxerDades = new VistaGestorFitxerDades(this);
        vistaProcessarAtributs = new VistaProcessarAtributs(this);

        vistaRegistres = new VistaRegistres(this);
        vistaPrincipal = new VistaPrincipal(this);
        vistaGenerarRegles = new VistaGenerarRegles(this);
        vistaValidarRegles = new VistaValidarRegles(this);
        vistaOutputValidarRegles = new VistaOutputValidarRegles(this);
        vistaConsultaRegles = new VistaConsultaRegles(this);
        vistaSortidaConsultaRegles = new VistaSortidaConsultaRegles(this);
        vistaFiltrarReglaperCondicio = new VistaFiltrarReglaperCondicio(this);
        vistaFiltrarReglaperConsequent = new VistaFiltrarReglaperConsequent(this);
        vistaImportarRegles = new VistaImportarRegles(this);
        vistaExportarRegles = new VistaExportarRegles(this);
        vistaImportarRegistres = new VistaImportarRegistres(this);
        vistaExportarRegistres = new VistaExportarRegistres(this);

    }

    /**
     * Amaga la vista anterior.
     *
     * @param novaVista Nova vista a guardar com a anterior.
     */
    private void amagarVistaAnterior(Window novaVista) {
        if (actual != null) {
            actual.setVisible(false);
        }

        actual = novaVista;
    }

    /**
     * Mostra la vista principal.
     */
    public void mostrarVistaPrincipal() {
        amagarVistaAnterior(vistaPrincipal);
        vistaPrincipal.mostrarVista();
    }

    /**
     * Mostra la vista d'atributs bool.
     *
     * @param nomAtr Nom de l'atribut per la vista.
     */
    public void mostrarVistaAtributBool(String nomAtr) {
        vistaAtributBool = new VistaAtributBool(this);
        vistaAtributBool.mostrarVistaBool(nomAtr);
    }

    /**
     * Mostra la vista d'atributs string.
     *
     * @param nomAtr Nom de l'atribut per la vista.
     */
    public void mostrarVistaAtributString(String nomAtr) {
        vistaAtributString = new VistaAtributString(this);
        vistaAtributString.mostrarVistaString(nomAtr);
        vistaAtributString.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vistaAtributString.init();
        vistaAtributString.pack();
        vistaAtributString.setVisible(true);
    }

    /**
     * Mostra la vista d'atributs int.
     *
     * @param nomAtr Nom de l'atribut per la vista.
     */
    public void mostrarVistaAtributInt(String nomAtr) {
        vistaAtributInt = new VistaAtributInt(this);
        vistaAtributInt.mostrarVistaInt(nomAtr);
        vistaAtributInt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vistaAtributInt.init();
        vistaAtributInt.pack();
        vistaAtributInt.setVisible(true);
    }

    /**
     * Mostra la vista d'atributs.
     *
     * @param nomAtr Nom de l'atribut per la vista.
     */
    public void mostrarVistaAtribut(String nomAtr) {
        vistaAtribut = new VistaAtribut(this);
        vistaAtribut.mostrarVistaAtribut(nomAtr);
        vistaAtribut.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        vistaAtribut.init();
        vistaAtribut.pack();
        vistaAtribut.setVisible(true);

    }

    /**
     * Mostra la vista de gestor de fitxer de dades.
     */
    public void mostrarVistaGestorFitxerDades() {
        vistaGestorFitxerDades = new VistaGestorFitxerDades(this);
        vistaGestorFitxerDades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaGestorFitxerDades.init();
        vistaGestorFitxerDades.pack();
        vistaGestorFitxerDades.setVisible(true);
    }

    /**
     * Crida al generar atributs del controlador d'atributs.
     *
     * @param path Path del fitxer.
     * @return ArrayList que conté la capçalera del fitxer.
     * @throws Exception Error de lectura del fitxer.
     */
    public ArrayList<String> generarAtr(String path) throws Exception {
       return domini.getCtrlAtributs().generarAtributs(path);
    }

    /**
     * Crida a la generació de preprocés bool del controlador d'atributs.
     *
     * @param valorsB Valors booleans a aplicar.
     * @param nom Nom de l'atribut.
     * @throws Exception Error de creacio de nou atribut.
     */
    public void generarPreBool(ArrayList<String> valorsB, String nom) throws Exception {
        domini.getCtrlAtributs().generarPreprocesBool(valorsB,nom);
    }

    /**
     * Crida a la generació de preprocés int del controlador d'atributs.
     *
     * @param intervals Llista d'intervals a afegir.
     * @param nom Nom de l'atribut.
     * @throws Exception Error de creacio de nou atribut.
     */
    public void generarPreInt(ArrayList<ArrayList<String>> intervals, String nom) throws Exception {
        domini.getCtrlAtributs().generarPreprocesInt(intervals,nom);
    }

    /**
     * Crida a la generació de preprocés string del controlador d'atributs.
     *
     * @param valors Valors possibles de l'atribut.
     * @param nom Nom de l'atribut.
     * @throws Exception Error de creacio de nou atribut.
     */
    public void generarPreString(HashSet<String> valors, String nom) throws Exception {
        domini.getCtrlAtributs().generarPreprocesString(valors,nom);
    }

    /**
     * Crida a eliminar atribut del controlador d'atributs.
     *
     * @param nomAtr Nom de l'atribut a eliminar.
     */
    public void elimAtribut(String nomAtr) {
        domini.getCtrlAtributs().eliminarAtribut(nomAtr);
    }

    /**
     * Mostra la vista de processar atributs.
     */
    public void mostrarVistaProcessarAtributs() {
        amagarVistaAnterior(vistaProcessarAtributs);
        vistaProcessarAtributs.iniciarPreproces();
    }

    /**
     * Mostrar vista de registres.
     */
    public void mostrarVistaRegistres() {
        amagarVistaAnterior(vistaRegistres);
        vistaRegistres.mostrarVista();
    }

    /**
     * Funció que mostra la vista de Consulta de Regles.
     */
    public void mostrarVistaConsultaRegles(){
        amagarVistaAnterior(vistaConsultaRegles);
        vistaConsultaRegles.mostrarVistaRegles();
    }

    /**
     * Mostra la sortida de Regles.
     *
     * @param suport suport mínim.
     * @param confiaza confiança mínima.
     * @param numero número de condicions.
     */
    public void mostrarVistaSortidaRegles(float suport, float confiaza, Integer numero){
        amagarVistaAnterior(vistaSortidaConsultaRegles);
        vistaSortidaConsultaRegles.mostrarVistaRegles(suport,confiaza,numero);
    }

    /**
     * Mostra Vista de la Consulta de Regles per Consqüent.
     */
    public void mostrarVistaConsultaReglesPerConsequent(){
        amagarVistaAnterior(vistaFiltrarReglaperConsequent);
        vistaFiltrarReglaperConsequent.mostrarVistaReglaConsequent();
    }

    /**
     * Funció que mostra la vista Generar Regles.
     */
    public void mostrarVistaGenerarRegles() {
        amagarVistaAnterior(vistaGenerarRegles);
        vistaGenerarRegles.mostrarVista();
    }

    /**
     * Funció que mostra la vista Validar Regles.
     */
    public void mostrarVistaValidarRegles() {
        amagarVistaAnterior(vistaValidarRegles);
        vistaValidarRegles.mostrarVista();
    }

    /**
     * Funció que mostra la vista Output Validar Regles.
     */
    public void mostrarVistaOutputValidarRegles() {
        amagarVistaAnterior(vistaOutputValidarRegles);
        vistaOutputValidarRegles.mostrarVista();
    }

    /**
     * Mostra la vista de Consulta Regles per Condició.
     */
    public void mostrarVistaFitrarPerCondicio(){
        amagarVistaAnterior(vistaFiltrarReglaperCondicio);
        vistaFiltrarReglaperCondicio.mostrarVistaRegleCondicio();
    }

    /**
     * Funció que mostra la Vista Sortida de les Regles.
     *
     * @param nom Nom de l'atribut.
     * @param valor Valor de l'atribut.
     * @param cond boolea que indica si el nom i el valor es una condicio quan es true, altrament indica que es un consequent.
     */
    public void mostrarVistaSortidaRegles(String nom, String valor, boolean cond) {
        amagarVistaAnterior(vistaSortidaConsultaRegles);
        vistaSortidaConsultaRegles.mostrarVistaRegles2(nom, valor, cond);
    }

    /**
     * Funció que mostra la vista Importar Regles.
     */
    public void mostrarVistaImportarRegles() {
        amagarVistaAnterior(vistaImportarRegles);
        vistaImportarRegles.mostrarVista();
    }

    /**
     * Funció qu emostra la vista Importar Regles.
     */
    public void mostrarVistaExportarRegles() {
        amagarVistaAnterior(vistaExportarRegles);
        vistaExportarRegles.mostrarVista();
    }

    /**
     * Funció que mostra la vista Sortida Regles.
     */
    public void mostrarVistaSortidaRegles() {
        amagarVistaAnterior(vistaSortidaConsultaRegles);
        vistaSortidaConsultaRegles.mostrarReglesGenerades();
    }

    /**
     * Mostra la vista d'importar registres.
     */
    public void mostrarVistaImportarRegistres() {
        vistaImportarRegistres.mostrarVista();
    }

    /**
     * Mostra la vista d'exportar registres.
     */
    public void mostrarVistaExportarRegistres() {
        vistaExportarRegistres.mostrarVista();
    }
}
