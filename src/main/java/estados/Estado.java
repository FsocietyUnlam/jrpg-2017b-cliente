package estados;

import java.awt.Graphics;

import juego.Juego;
/**
 * Clase Estado.
 */
public abstract class Estado {
	/**
	 * the estadoActual.
	 */
	private static Estado estadoActual = null;

	// Tipo de estados
	/**
	 * the estadoOffline.
	 */
	private static int estadoOffline = 0;
	/**
	 * the estadoJuego.
	 */
	private static int estadoJuego = 1;
	/**
	 * the estadoBatalla.
	 */
	private static int estadoBatalla = 2;
	/**
	 * the juego.
	 */
	private Juego juego;

	/**
	 * Constructor de la clase Juego.
	 * @param juego .
	 */
	public Estado(final Juego juego) {
		this.setJuego(juego);
	}

	/**
	 * Actualiza.
	 */
	public abstract void actualizar();

	/**
	 * Grafica.
	 * @param g .
	 */
	public abstract void graficar(Graphics g);

	/**
	 * Setea el estado.
	 * @param estado .
	 */
	public static void setEstado(final Estado estado) {
		estadoActual = estado;
	}

	/**
	 * Se obtiene el estado.
	 * @return la clase Estado.
	 */
	public static Estado getEstado() {
		return estadoActual;
	}

	/**
	 * Averigua si esEstadoDeJuego.
	 * @return un booleano.
	 */
	public abstract boolean esEstadoDeJuego();

	/**
	 * Se obtiene el Estado Offline.
	 * @return el estado offline.
	 */
	public static int getEstadoOffline() {
		return estadoOffline;
	}

	/**
	 * Setea el estado offline.
	 * @param estadoOffline .
	 */
	public static void setEstadoOffline(final int estadoOffline) {
		Estado.estadoOffline = estadoOffline;
	}

	/**
	 * Se obtiene el estado de juego.
	 * @return el estado del juego.
	 */
	public static int getEstadoJuego() {
		return estadoJuego;
	}

	/**
	 * Setea el estado del juego.
	 * @param estadoJuego .
	 */
	public static void setEstadoJuego(final int estadoJuego) {
		Estado.estadoJuego = estadoJuego;
	}

	/**
	 * Se obtiene el estado de la batalla.
	 * @return el estado de batalla.
	 */
	public static int getEstadoBatalla() {
		return estadoBatalla;
	}

	/**
	 * Setea el estado de batalla.
	 * @param estadoBatalla .
	 */
	public static void setEstadoBatalla(final int estadoBatalla) {
		Estado.estadoBatalla = estadoBatalla;
	}

	/**
	 * Se obtiene el juego.
	 * @return el juego.
	 */
	public Juego getJuego() {
		return juego;
	}

	/**
	 * Setea el juego.
	 * @param juego .
	 */
	public void setJuego(final Juego juego) {
		this.juego = juego;
	}
}
