package juego;

import entidades.Entidad;

/**
 * Clase camara.
 * @author Lucas
 *
 */
public class Camara {

	/**
	 * Atributo del tipo juego.
	 */
	private Juego juego;
	/**
	 * Atributo privado del tipo flotante para representar al offset del eje y.
	 */
	private float yOffset;
	/**
	 * Atributo privado del tipo flotante para representar al offset del eje x.
	 */
	private float xOffset;

	/**
	 * Constructor de la clase.
	 * @param juego recibe el juego.
	 * @param xOffset recibe el offset del eje x
	 * @param yOffset recibe el offset del eje y
	 */
	public Camara(final Juego juego, final float xOffset, final float yOffset) {
		this.juego = juego;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * Método para centrar la camara.
	 * @param e la Entidad
	 */
	public void centrar(final Entidad e) {
		xOffset = e.getX() - juego.getAncho() / 2 + e.getAncho() / 2;
		yOffset = e.getY() - juego.getAlto() / 2 + e.getAlto() / 2;
	}

	/**
	 * Método para mover la camara.
	 * @param dx flotante para representar el offset x.
	 * @param dy flotante para representar el offset y.
	 */
	public void mover(final float dx, final float dy) {
		xOffset += dx;
		yOffset += dy;
	}

	/**
	 * Método para obtener el offset y.
	 * @return flotante para representar el offset.
	 */
	public float getyOffset() {
		return yOffset;
	}

	/**
	 * Método para setear el offset y.
	 * @param yOffset flotante para representar el offset.
	 */
	public void setyOffset(final float yOffset) {
		this.yOffset = yOffset;
	}

	/**
	 * Método para obtener el offset x.
	 * @return flotante para representar el offset.
	 */
	public float getxOffset() {
		return xOffset;
	}

	/**
	 * Método para setear el offset x.
	 * @param xOffset flotante para representar el offset.
	 */
	public void setxOffset(final float xOffset) {
		this.xOffset = xOffset;
	}
}
