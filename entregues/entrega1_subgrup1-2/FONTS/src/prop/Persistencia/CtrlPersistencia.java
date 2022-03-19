package prop.Persistencia;

import java.io.*;
import java.util.ArrayList;

public class CtrlPersistencia {
    /**
     * Llegeix un fitxer de dades (CSV).
     *
     * @param path Path del fitxer a llegir
     * @return Objecte representant del fitxer de dades per anar llegint per línies.
     * @throws Exception Errors de lectura
     */
    public FitxerDades llegirFitxerDades(String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));

        return new FitxerDades(br);
    }

    /**
     * Converteix els objectes donats a binari i escriu-los en un fitxer al path especificat.
     *
     * El fitxer resultant tindrà el nom {nomFitxer}.prop
     *
     * @param objectes Objectes a convertir en binari
     * @param pathFitxer Path on guardar el fitxer
     * @param nomFitxer Nom del fitxer a exportar
     * @throws Exception Errors de lectura
     */
    public void escriureBinari(ArrayList<Serializable> objectes, String pathFitxer, String nomFitxer) throws Exception {
        FileOutputStream file = new FileOutputStream(pathFitxer + nomFitxer + ".prop");
        ObjectOutputStream out = new ObjectOutputStream(file);

        // Serialitzem l'objecte
        out.writeObject(objectes);

        out.close();
        file.close();
    }

    /**
     * Llegeix el fitxer binari donat i converteix-lo en un array d'objectes.
     *
     * @param pathFitxer Path on es troba el fitxer a llegir
     * @return L'ArrayList que representa el fitxer binari
     * @throws Exception Errors de lectura
     */
    public ArrayList llegirBinari(String pathFitxer) throws Exception {
        FileInputStream file = new FileInputStream(pathFitxer);
        ObjectInputStream in = new ObjectInputStream(file);

        // Deserialitzem l'objecte
        ArrayList objectes = (ArrayList) in.readObject();

        in.close();
        file.close();

        return objectes;
    }
}
