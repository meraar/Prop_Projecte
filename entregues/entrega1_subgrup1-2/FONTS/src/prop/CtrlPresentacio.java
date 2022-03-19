package prop;

/**
 * Classe abstracta de presentació utilitzada per la lectura
 */
public abstract class CtrlPresentacio {
    protected abstract String llegeixString(String text);
    protected abstract int llegeixInt(String text);
    protected abstract boolean llegeixBool(String text);
    protected abstract float llegeixFloat(String text);
}
