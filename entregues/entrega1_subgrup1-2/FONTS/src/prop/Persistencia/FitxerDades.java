package prop.Persistencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FitxerDades implements AutoCloseable {
    private BufferedReader buffer;
    private ArrayList<String> header;

    public FitxerDades(BufferedReader buffer) throws IOException {
        this.buffer = buffer;

        this.header = llegirValors();
    }

    /**
     * Llegeix la següent línia del fitxer.
     *
     * @return String que conté la línia que estem llegint
     * @throws IOException Error de lectura
     */
    public String llegirLinia() throws IOException {
        return buffer.readLine();
    }

    /**
     * Llegeix la següent línia del fitxer i retorna-ho amb format array (cada element de l'array és un element
     * del CSV).
     *
     * Torna un array buit en el cas que s'hagin acabat els valors.
     *
     * @return ArrayList amb cada un dels elements de la línia
     * @throws IOException Error de lectura
     */
    public ArrayList<String> llegirValors() throws IOException {
        String linia = this.llegirLinia();

        if (linia == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(linia.split("\\s*,\\s*")));
    }

    /**
     * Llegeix el header del fitxer.
     *
     * @return Header del fitxer de dades.
     */
    public ArrayList<String> getHeader() {
        return header;
    }

    @Override
    public void close() throws IOException {
        buffer.close();
    }
}
