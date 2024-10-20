import java.util.ArrayList;

class Jugador<E extends ItipoPieza> {

    private NodePieza piezasVivas;

    public Jugador(ArrayList<E> piezasVivas) {
        NodePieza aux = null;
        for (E pieza : piezasVivas) {
            if (this.piezasVivas == null) {
                this.piezasVivas = new NodePieza(pieza, null);
                aux = this.piezasVivas;
            } else {
                aux.seguent = new NodePieza(pieza, null);
                aux = aux.seguent;
            }
        }
    }

    public ArrayList<E> getPiezasVivas() {
        ArrayList<E> piezas = new ArrayList<>();
        NodePieza aux = piezasVivas;
        while (aux != null) {
            piezas.add(aux.pieza);
            aux = aux.seguent;
        }
        return piezas;
    }

    public void moverPieza(int columnaAnterior, int filaAnterior, int nuevaColumna, int nuevaFila) {
        if (this.buscarEnPosicion(nuevaFila, nuevaColumna) != null)
            throw new RuntimeException("Posició ocupada per una peça del mateix jugador");

        E item = this.buscarEnPosicion(filaAnterior, columnaAnterior);
        if (item == null)
            throw new RuntimeException("Peça no trobada fila:" + filaAnterior + " columna:" + columnaAnterior);

        item.setPosicion(nuevaFila, nuevaColumna);
        System.out.println("Peça moguda");
    }

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