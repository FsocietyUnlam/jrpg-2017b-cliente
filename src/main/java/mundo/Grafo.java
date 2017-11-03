package mundo;

/**
 * Clase Grafo.
 */
public class Grafo {

	/**
	 * the cantidadDeNodos.
	 */
	private int cantidadDeNodos;
	/**
	 * the cantidadDeNodosTotal.
	 */
	private int cantidadDeNodosTotal;
	/**
	 * the nodos.
	 */
	private Nodo[] nodos;

	/**
	 * Constructor de la clase Grafo.
	 * @param cantidadDeNodosTotal .
	 */
	public Grafo(final int cantidadDeNodosTotal) {
		cantidadDeNodos = 0;
		nodos = new Nodo[cantidadDeNodosTotal];
		this.cantidadDeNodosTotal = cantidadDeNodosTotal;
	}

	/**
	 * Agrega un nodo.
	 * @param nodo .
	 */
	public void agregarNodo(final Nodo nodo) {
		nodos[cantidadDeNodos++] = nodo;
	}

	/**
	 * Agrega Adyacentes.
	 * @param nodoUno .
	 * @param nodoDos .
	 */
	public void agregarAdyacentes(final Nodo nodoUno, final Nodo nodoDos) {
		nodoUno.agregarAdyacente(nodoDos);
	}

	/**
	 * Se obtiene los nodos.
	 * @return un vector de nodos.
	 */
	public Nodo[] obtenerNodos() {
		return nodos;
	}

	/**
	 * Obtiene la cantidad de nodos.
	 * @return un entero.
	 */
	public int obtenerCantidadDeNodos() {
		return cantidadDeNodos;
	}

	/**
	 * Se obtiene el total de nodos.
	 * @return un entero.
	 */
	public int obtenerCantidadDeNodosTotal() {
		return cantidadDeNodosTotal;
	}

}
