import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Torns<E> {

    private ArrayList<E> llistatTorns;

    // Constructor que inicialitza la llista de torns
    public Torns() {
        llistatTorns = new ArrayList<>();
    }

    // Constructor que carrega la llista de torns des d'un fitxer
    public Torns(String nomFitxer) throws IOException {
        this();
        carregarDesDeFitxer(nomFitxer);
        if (llistatTorns.isEmpty()) throw new IOException("Llistat buit");
    }

    // Mètode per afegir un torn al final de la llista
    public void afegirTorn(E torn) {
        llistatTorns.add(torn);
    }

    // Mètode per agafar el primer torn a la llista i s'elimina
    public E agafarPrimerTorn() throws NoSuchElementException {
        return llistatTorns.removeFirst();
    }

    // Mètode per guardar la llista de torns a un fitxer
    public void guardarAFitxer(String nomFitxer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer));
        for (E torn : llistatTorns) {
            writer.write(torn.toString());
            writer.newLine(); // Salt de línia per separar cada torn
        }
        writer.close();
    }

    // Mètode per carregar la llista de torns des d'un fitxer
    private void carregarDesDeFitxer(String nomFitxer) throws IOException {
        String linia;
        BufferedReader reader = new BufferedReader(new FileReader(nomFitxer));
        while ((linia = reader.readLine()) != null) {
            llistatTorns.add((E) linia); //això només funcionarà amb E=String.
        }
        reader.close();
    }

}
