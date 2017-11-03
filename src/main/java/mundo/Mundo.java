package mundo;

import java.awt.Graphics;

import estados.Estado;
import juego.Juego;
import variables.Constantes;

/**
 * Clase Mundo.
 */
public class Mundo {
	/**
	 * the juego.
	 */
	private Juego juego;
	/**
	 * the ancho.
	 */
	private int ancho;
	/**
	 * the alto.
	 */
	private int alto;
	/**
	 * the spawnX.
	 */
	private int spawnX;
	/**
	 * the spawnY.
	 */
	private int spawnY;
	/**
	 * the xOffset.
	 */
	private int xOffset;
	/**
	 * the yOffset.
	 */
	private int yOffset;

	/**
	 * the iso.
	 */
	private float[] iso = new float[2];
	/**
	 * the tiles.
	 */
	private int[][] tiles;
	/**
	 * the tilesInv.
	 */
	private int[][] tilesInv;

	/**
	 * the xMinimo.
	 */
	private int xMinimo;
	/**
	 * the xMaximo.
	 */
	private int xMaximo;
	/**
	 * the yMinimo.
	 */
	private int yMinimo;
	/**
	 * the yMaximo.
	 */
	private int yMaximo;

	/**
	 * the grafoDeTilesNoSolidos.
	 */
	private Grafo grafoDeTilesNoSolidos;

	/**
	 * Constructor de la clase Mundo.
	 * @param juego .
	 * @param pathMap .
	 * @param pathObstac .
	 */
	public Mundo(final Juego juego, final String pathMap, final String pathObstac) {
		this.juego = juego;
		cargarMundo(pathMap, pathObstac);
		mundoAGrafo();
	}

	/**
	 * Actualiza.
	 */
	public void actualizar() {

	}

	/**
	 * Grafica.
	 * @param g .
	 */
	public void graficar(final Graphics g) {
		xOffset = juego.getEstadoJuego().getPersonaje().getxOffset();
		yOffset = juego.getEstadoJuego().getPersonaje().getYOffset();

		xMinimo = (int) (juego.getCamara().getxOffset() - xOffset - Constantes.MUNDO_GRAFICAR_30);
		xMaximo = xMinimo + juego.getAncho() + xOffset + Constantes.MUNDO_GRAFICAR_30;
		yMinimo = (int) juego.getCamara().getyOffset() + yOffset - Constantes.MUNDO_GRAFICAR_60;
		yMaximo = yMinimo + juego.getAlto() + yOffset + Constantes.MUNDO_GRAFICAR_60;

		// Grafico el el tile base
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				iso = dosDaIso(j, i);
				if ((iso[0] >= xMinimo && iso[0] <= xMaximo) && (iso[1] >= yMinimo && iso[1] <= yMaximo)) {
					int map = juego.getPersonaje().getMapa();
					if (map == 1) {
						Tile.getAubenor()[Tile.AUBENOR_BASE].graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
								(int) (iso[1] - juego.getCamara().getyOffset() - Constantes.MUNDO_GRAFICAR_32), Constantes.MUNDO_GRAFICAR_64, Constantes.MUNDO_GRAFICAR_64);
					} else if (map == 2) {
						Tile.getAris()[Tile.ARIS_BASE].graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
								(int) (iso[1] - juego.getCamara().getyOffset() - Constantes.MUNDO_GRAFICAR_32), Constantes.MUNDO_GRAFICAR_64, Constantes.MUNDO_GRAFICAR_64);
					} else if (map == Constantes.MUNDO_GRAFICAR_3) {
						Tile.getAubenor()[Tile.AUBENOR_BASE].graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
								(int) (iso[1] - juego.getCamara().getyOffset() - Constantes.MUNDO_GRAFICAR_32), Constantes.MUNDO_GRAFICAR_64, Constantes.MUNDO_GRAFICAR_64);
					}
					if (!getTile(j, i).esSolido()) {
						getTile(j, i).graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
								(int) (iso[1] - juego.getCamara().getyOffset() - Constantes.MUNDO_GRAFICAR_32), Constantes.MUNDO_GRAFICAR_64, Constantes.MUNDO_GRAFICAR_64);
					}
				}
			}
		}
	}

	/**
	 * Grafica los obstaculos.
	 * @param g .
	 */
	public void graficarObstaculos(final Graphics g) {
		Tile obst;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				iso = dosDaIso(j, i);
				// Grafico al personaje
				if (Estado.getEstado() == juego.getEstadoJuego()) {
					if (Mundo.mouseATile(juego.getUbicacionPersonaje().getPosX(),
							juego.getUbicacionPersonaje().getPosY())[0] == j
							&& Mundo.mouseATile(juego.getUbicacionPersonaje().getPosX(),
									juego.getUbicacionPersonaje().getPosY())[1] == i) {
						juego.getEstadoJuego().getPersonaje().graficar(g);
					}
				}

				// Grafico los obstaculos
				if ((iso[0] >= xMinimo && iso[0] <= xMaximo) && (iso[1] >= yMinimo && iso[1] <= yMaximo)
						&& getTile(j, i).esSolido()) {
					obst = getTile(j, i);
					obst.graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
							(int) (iso[1] - juego.getCamara().getyOffset() - obst.getAlto() / 2), obst.getAncho(),
							obst.getAlto());
				}
			}
		}
	}

	/**
	 * Se obtiene el Tile.
	 * @param x .
	 * @param y .
	 * @return un Tile.
	 */
	public Tile getTile(final int x, final int y) {
		Tile t = Tile.getTiles()[tiles[x][y]];
		if (t == null) {
			int map = juego.getPersonaje().getMapa();
			if (map == 1) {
				return Tile.getAubenor()[Tile.AUBENOR_BASE];
			} else if (map == 2) {
				return Tile.getAris()[Tile.ARIS_BASE];
			} else if (map == Constantes.MUNDO_GRAFICAR_GET_TILE_3) {
				return Tile.getAubenor()[Tile.AUBENOR_BASE];
			}
		}
		return t;
	}

	/**
	 * Carga Mundos.
	 * @param pathMapa .
	 * @param pathObstaculos .
	 */
	private void cargarMundo(final String pathMapa, final String pathObstaculos) {
		String archivo = Utilitarias.archivoAString(pathMapa);
		String[] tokens = archivo.split("\\s+");
		ancho = Utilitarias.parseInt(tokens[0]);
		alto = Utilitarias.parseInt(tokens[1]);
		spawnX = Utilitarias.parseInt(tokens[2]);
		spawnY = Utilitarias.parseInt(tokens[Constantes.MUNDO_GRAFICAR_GET_TILE_3]);

		tiles = new int[ancho][alto];
		tilesInv = new int[alto][ancho];

		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {

				tiles[x][y] = Utilitarias.parseInt(tokens[(x + y * ancho + Constantes.MUNDO_GRAFICAR_CARGA_MUNDO_4)]);
				tilesInv[y][x] = tiles[x][y];
			}
		}

	}

	/**
	 * Mundo a grafo.
	 */
	private void mundoAGrafo() {
		// Creo una matriz de nodos
		Nodo[][] nodos = new Nodo[ancho][alto];
		int indice = 0;
		// Lleno la matriz con los nodos
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				nodos[y][x] = new Nodo(indice++, x, y);
			}
		}
		// Variables finales
		int xFinal = ancho;
		int yFinal = alto;
		// Uno cada nodo con sus adyacentes
		for (int x = 0; x < yFinal; x++) {
			for (int y = 0; y < xFinal; y++) {
				if (!Tile.getTiles()[tilesInv[x][y]].esSolido()) {
					// Si no es la ultima fila y el tile de abajo es no solido,
					// lo uno
					if (y < yFinal - 1 && !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
						nodos[x][y].agregarAdyacente(nodos[x][y + 1]);
						nodos[x][y + 1].agregarAdyacente(nodos[x][y]);
					}
					// Si no es la ultima columna
					if (x < xFinal - 1) {
						// Si el de arriba a la derecha no es un tile solido
						// Y ademas el de arriba ni el de la derecha lo son, lo
						// uno
						// Tiene que ser a partir de la segunda fila
						if (y > 0 && !Tile.getTiles()[tilesInv[x + 1][y - 1]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x][y - 1]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y - 1]);
							nodos[x + 1][y - 1].agregarAdyacente(nodos[x][y]);
						}
						// Si el de la derecha no es un tile solido lo uno
						if (!Tile.getTiles()[tilesInv[x + 1][y]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y]);
							nodos[x + 1][y].agregarAdyacente(nodos[x][y]);
						}
						// Si el de abajo a la derecha no es un tile solido
						// Y ademas el de abajo ni el de la derecha lo son, lo
						// uno
						// Debe ser antes de la ultima fila
						if (y < yFinal - 1 && !Tile.getTiles()[tilesInv[x + 1][y + 1]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y + 1]);
							nodos[x + 1][y + 1].agregarAdyacente(nodos[x][y]);
						}
					}
				}
			}
		}
		// Creo un grafo para almacenar solo los tiles no solidos
		grafoDeTilesNoSolidos = new Grafo(ancho * alto);
		indice = 0;
		// Paso la matriz a un array
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				grafoDeTilesNoSolidos.agregarNodo(nodos[i][j]);
			}
		}
	}

	/**
	 * Se obtiene los grafos de tiles no solidos.
	 * @return la clase Grafo.
	 */
	public Grafo obtenerGrafoDeTilesNoSolidos() {
		return grafoDeTilesNoSolidos;
	}

	/**
	 * Obtiene el ancho.
	 * @return un entero.
	 */
	public int obtenerAncho() {
		return ancho;
	}

	/**
	 * Obtiene el alto.
	 * @return un entero.
	 */
	public int obtenerAlto() {
		return alto;
	}

	/**
	 * iso a 2d.
	 * @param x .
	 * @param y .
	 * @return un vector de float.
	 */
	public static float[] isoA2D(final float x, final float y) {
		float[] dosD = new float[2];

		dosD[0] = (x / (Tile.ANCHO / 2) + y / (Tile.ALTO / 2)) / 2;
		dosD[1] = (y / (Tile.ALTO / 2) - (x / (Tile.ANCHO / 2))) / 2;

		return dosD;
	}

	/**
	 * dos da iso.
	 * @param x .
	 * @param y .
	 * @return un vector de float.
	 */
	public static float[] dosDaIso(final float x, final float y) {
		float[] iso = new float[2];

		iso[0] = (x - y) * (Tile.ANCHO / 2);
		iso[1] = (x + y) * (Tile.ALTO / 2);

		return iso;
	}

	/**
	 * mouse a Tile.
	 * @param x .
	 * @param y .
	 * @return un vector de enteros.
	 */
	public static int[] mouseATile(final float x, final float y) {
		int tile[] = new int[2];

		tile[0] = (int) Math.floor((y / Tile.ALTO) + (x / Tile.ANCHO)) + 1;
		tile[1] = (int) Math.floor((-x / Tile.ANCHO) + (y / Tile.ALTO)) + 1;

		return tile;
	}
}