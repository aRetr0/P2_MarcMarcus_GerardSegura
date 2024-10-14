import java.util.ArrayList;

class Jugador<E extends ItipoPieza> {

    private NodePieza piezasVivas;

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

    // Método para mover una pieza usando la posición anterior TODO
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
        NodePieza aux = piezasVivas;
        while (aux != null) {
            if (aux.pieza.getColumna() == nuevaColumna && aux.pieza.getFila() == nuevaFila) {
                return aux.pieza;
            }
            aux = aux.seguent;
        }
        return null;
    }

    // Método para buscar y eliminar una pieza en una posición específica TODO
    public boolean eliminarPiezaEnPosicion(int columna, int fila) throws FiJocException {
        NodePieza aux = piezasVivas;
        NodePieza anterior = null;
        while (aux != null) {
            if (aux.pieza.getColumna() == columna && aux.pieza.getFila() == fila) {
                if (anterior == null) {
                    piezasVivas = aux.seguent;
                } else {
                    anterior.seguent = aux.seguent;
                }
                if (aux.pieza.fiJoc()) {
                    throw new FiJocException();
                }
                return true;
            }
            anterior = aux;
            aux = aux.seguent;
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
