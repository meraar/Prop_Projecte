package prop;

import prop.Presentacio.CtrlPresentacio;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (() -> {
            CtrlPresentacio pres = new CtrlPresentacio();
            String nomAtr = new String();
            pres.mostrarVistaPrincipal();
            //pres.mostrarVistaAtributBool(nomAtr);
            //pres.mostrarVistaAtributString(nomAtr);
            //pres.mostrarVistaAtributInt(nomAtr);
            //pres.mostrarVistaAtribut(nomAtr);
            //pres.mostrarVistaGestorFitxerDades();
        });
    }
}
