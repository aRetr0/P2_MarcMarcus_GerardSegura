import java.util.ArrayList;

class Jugador<E extends ItipoPieza> {

    private final NodePieza piezasVivas;

    public Jugador(ArrayList<E> piezasVivas) {
        NodePieza cabezera = new NodePieza(null, null);
        NodePieza aux = cabezera;
        for (E pieza : piezasVivas) {
            aux.seguent = new NodePieza(pieza, null);
            aux = aux.seguent;
        }
        this.piezasVivas = cabezera.seguent;
    }

    public ArrayList<E> getPiezasVivas() {
        ArrayList<E> piezas = new ArrayList<>();
        NodePieza aux = piezasVivas;
        while (aux.seguent != null) {
            piezas.add(aux.pieza);
            aux = aux.seguent;
        }
        return piezas;
    }

    // Método para mover una pieza usando la posición anterior
    public void moverPieza(int columnaAnterior, int filaAnterior, int nuevaColumna, int nuevaFila) {
        if (this.buscarEnPosicion(nuevaFila, nuevaColumna) != null)
            throw new RuntimeException("Posició ocupada per una peça del mateix jugador");

        E item = this.buscarEnPosicion(filaAnterior, columnaAnterior);
        if (item == null)
            throw new RuntimeException("Peça no trobada fila:" + filaAnterior + " columna:" + columnaAnterior);

        item.setPosicion(nuevaFila, nuevaColumna);
        System.out.println("Peça moguda");
    }

    // Método para buscar en una posición específica
    private E buscarEnPosicion(int nuevaFila, int nuevaColumna) {
        for (E item : piezasVivas) {
            if (item.getFila() == nuevaFila && item.getColumna() == nuevaColumna) {
                return item;
            }
        }
        return null;
    }

    // Método para buscar y eliminar una pieza en una posición específica
    public boolean eliminarPiezaEnPosicion(int columna, int fila) throws FiJocException {
        E item = this.buscarEnPosicion(fila, columna);
        if (item != null) {
            piezasVivas.remove(item);
            if (item.fiJoc()) throw new FiJocException();
            System.out.println("Peça eliminada.");
            return true;
        }
        return false;
    }

    private class NodePieza {
        public E pieza;
        public NodePieza seguent;

        public NodePieza(E pieza, NodePieza seguent) {
            this.pieza = pieza;
            this.seguent = seguent;
        }
    }
}
