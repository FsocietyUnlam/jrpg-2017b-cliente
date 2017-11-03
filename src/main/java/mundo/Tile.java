package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import variables.Constantes;

/**
 * Clase Tile.
 */
public class Tile {
	/**
	 * the tiles.
	 */
	private static Tile[] tiles = new Tile[Constantes.TILE_TILES_TAMANO];
	/**
	 * the aubenor.
	 */
	private static Tile[] aubenor;
	/**
	 * the aris.
	 */
	private static Tile[] aris;
	// es el piso de aubenor por defecto si queres llamarlo asi, es gris
	/**
	 * the ARIS_BASE.
	 */
	public static final int ARIS_BASE = 3;
	/**
	 * the AUBENOR_BASE.
	 */
	public static final int AUBENOR_BASE = 3;

	/**
	 * the ANCHO.
	 */
	public static final int ANCHO = 64;
	/**
	 * the ALTO.
	 */
	public static final int ALTO = 32;
	/**
	 *the textura.
	 */
	private BufferedImage textura;
	/**
	 * the id.
	 */
	private final int id;
	/**
	 * the esSolido.
	 */
	private boolean esSolido;
	/**
	 * the ancho.
	 */
	private int ancho;
	/**
	 * the alto.
	 */
	private int alto;

	/**
	 * Constructor de la clase Tile.
	 * @param textura .
	 * @param id .
	 * @param esSolido .
	 */
	public Tile(final BufferedImage textura, final int id, final boolean esSolido) {
		this.textura = textura;
		this.id = id;
		getTiles()[id] = this;
		this.esSolido = esSolido;
	}

	/**
	 * Constructor de la clase Tile.
	 * @param textura .
	 * @param id .
	 * @param esSolido .
	 * @param ancho .
	 * @param alto .
	 */
	public Tile(final BufferedImage textura, final int id, final boolean esSolido, final int ancho, final int alto) {
		this.textura = textura;
		this.id = id;
		getTiles()[id] = this;
		this.ancho = ancho;
		this.alto = alto;
		this.esSolido = esSolido;
	}

	/**
	 * Actualiza.
	 */
	public void actualizar() {

	}

	/**
	 * Grafica.
	 * @param g .
	 * @param x .
	 * @param y .
	 */
	public void graficar(final Graphics g, final int x, final int y) {
		g.drawImage(textura, x, y, ANCHO, ALTO, null);
	}

	/**
	 * Grafica.
	 * @param g .
	 * @param x .
	 * @param y .
	 * @param width .
	 * @param height .
	 */
	public void graficar(final Graphics g, final int x, final int y, final int width, final int height) {
		g.drawImage(textura, x, y, width, height, null);
	}

	/**
	 * Setea el Solido.
	 * @param solidez .
	 */
	public void setSolido(final boolean solidez) {
		esSolido = solidez;
	}

	/**
	 * Pregunta si es solido.
	 * @return un booleano.
	 */
	public boolean esSolido() {
		return esSolido;
	}

	/**
	 * Obtiene el id.
	 * @return un entero.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Obtiene el ancho.
	 * @return un entero.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Obtiene el alto.
	 * @return un entero.
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Obtiene un Aubenor.
	 * @return vector de tile.
	 */
	public static Tile[] getAubenor() {
		return aubenor;
	}

	/**
	 * Setea Aubenor.
	 * @param aubenor .
	 */
	public static void setAubenor(final Tile[] aubenor) {
		Tile.aubenor = aubenor;
	}

	/**
	 * Obtiene Aris.
	 * @return un vector de tile.
	 */
	public static Tile[] getAris() {
		return aris;
	}

	/**
	 * Setea Aris.
	 * @param aris .
	 */
	public static void setAris(final Tile[] aris) {
		Tile.aris = aris;
	}

	/**
	 * Obtiene Tiles.
	 * @return un vector de tiles.
	 */
	public static Tile[] getTiles() {
		return tiles;
	}

	/**
	 * Setea Tiles.
	 * @param tiles .
	 */
	public static void setTiles(final Tile[] tiles) {
		Tile.tiles = tiles;
	}

}
