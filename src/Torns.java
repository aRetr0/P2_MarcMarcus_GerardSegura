import java.io.*;
import java.util.NoSuchElementException;


public class Torns<E> {

    private final NodeTorn llistatTorns; // seqüència enllaçada de torns
    // amb capçalera

    // Constructor que inicialitza la sequencia de torns buida
    public Torns() {
        llistatTorns = new NodeTorn(null, null);
    }


    // Constructor que carrega la llista de torns des d'un fitxer
    public Torns(String nomFitxer) throws IOException {
        this();
        carregarDesDeFitxer(nomFitxer);
        if (llistatTorns.isEmpty()) throw new IOException("Llistat buit");
    }

    // Mètode per afegir un torn al final de la llista
    public void afegirTorn(E torn) {
        NodeTorn nouTorn = new NodeTorn(torn, null);
        NodeTorn actual = llistatTorns;
        while (actual.seguent != null) {
            actual = actual.seguent;
        }
        actual.seguent = nouTorn;
    }

    // Mètode per agafar el primer torn a la llista i s'elimina
    public E agafarPrimerTorn() throws NoSuchElementException {
        if (llistatTorns.seguent == null) throw new NoSuchElementException("No hi ha torns disponibles");
        NodeTorn primerTorn = llistatTorns.seguent;
        llistatTorns.seguent = primerTorn.seguent;
        return primerTorn.moviment;
    }

    // Mètode per guardar la llista de torns a un fitxer
    public void guardarAFitxer(String nomFitxer) throws IOException {
        if (llistatTorns.seguent == null) throw new IOException("Nom del fitxer incorrecte");
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer));
        NodeTorn actual = llistatTorns.seguent;
        while (actual != null) {
            writer.write(actual.moviment.toString());
            writer.newLine();
            actual = actual.seguent;
        }
        writer.close();
    }

    // Mètode per carregar la llista de torns des d'un fitxer
    private void carregarDesDeFitxer(String nomFitxer) throws IOException {
        String linia;
        BufferedReader reader = new BufferedReader(new FileReader(nomFitxer));
        while ((linia = reader.readLine()) != null) {
            afegirTorn((E) linia);
        }
        reader.close();
    }

    public boolean isEmpty() {
        NodeTorn actual = llistatTorns.seguent;
        while (actual != null) {
            if (actual.moviment != null) {
                return false;
            }
            actual = actual.seguent;
        }
        return true;
    }


    private class NodeTorn {
        public E moviment;
        public NodeTorn seguent;

        public NodeTorn(E moviment, NodeTorn seguent) {
            this.moviment = moviment;
            this.seguent = seguent;
        }

        public boolean isEmpty() {
            NodeTorn actual = llistatTorns.seguent;
            while (actual != null) {
                if (actual.moviment != null) {
                    return false;
                }
                actual = actual.seguent;
            }
            return true;
        }
    }

}
