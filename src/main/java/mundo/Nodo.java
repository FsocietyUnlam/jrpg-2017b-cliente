package mundo;

import variables.Constantes;

/**
 * Clase Nodo.
 */
public class Nodo {
	/**
	 * the x.
	 */
	private int x;
	/**
	 * the y.
	 */
	private int y;
	/**
	 * the indice.
	 */
	private int indice;
	/**
	 * the cantidadDeAdyacentes.
	 */
	private int cantidadDeAdyacentes;
	/**
	 * the nodosAdyacentes.
	 */
	private Nodo[] nodosAdyacentes;

	/**
	 * Constructor de la clase Nodo.
	 * @param indice .
	 * @param x .
	 * @param y .
	 */
	public Nodo(final int indice, final int x, final int y) {
		this.x = x;
		this.y = y;
		this.indice = indice;
		cantidadDeAdyacentes = 0;
		nodosAdyacentes = new Nodo[Constantes.NODO_POSICION];
	}

	/**
	 * Obtiene el x.
	 * @return un entero.
	 */
	public int obtenerX() {
		return x;
	}

	/**
	 * Obtiene el y.
	 * @return un entero.
	 */
	public int obtenerY() {
		return y;
	}

	/**
	 * Obtiene el indice.
	 * @return un entero.
	 */
	public int obtenerIndice() {
		return indice;
	}

	/**
	 * Obtiene nodos Adyacentes.
	 * @return vector de nodos.
	 */
	public Nodo[] obtenerNodosAdyacentes() {
		return nodosAdyacentes;
	}

	/**
	 * Agrega Adyacentes.
	 * @param nodo .
	 */
	public void agregarAdyacente(final Nodo nodo) {
		nodosAdyacentes[cantidadDeAdyacentes++] = nodo;
	}

	/**
	 * Obtiene la cantidad de adyacentes.
	 * @return un entero.
	 */
	public int obtenerCantidadDeAdyacentes() {
		return cantidadDeAdyacentes;
	}
}
