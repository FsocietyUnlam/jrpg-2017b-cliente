package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import chat.VentanaContactos;
import estados.Estado;
import frames.MenuEscape;
import frames.MenuInventario;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteComerciar;
import mensajeria.PaqueteMovimiento;
import mundo.Grafo;
import mundo.Mundo;
import mundo.Nodo;
import recursos.Recursos;

import variables.Constantes;

/**
 * Clase Entidad.
 */

public class Entidad {
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

	// Posiciones
	/**
	 * the x.
	 */
	private float x;
	/**
	 * the y.
	 */
	private float y;
	/**
	 * the dx.
	 */
	private float dx;
	/**
	 * the dy.
	 */
	private float dy;
	/**
	 * the xInicio.
	 */
	private float xInicio;
	/**
	 * the yInicio.
	 */
	private float yInicio;
	/**
	 * the xFinal.
	 */
	private float xFinal;
	/**
	 * theyFinal.
	 */
	private float yFinal;
	/**
	 * the xOffset.
	 */
	private int xOffset;
	/**
	 * the yOffset.
	 */
	private int yOffset;
	/**
	 * the drawX.
	 */
	private int drawX;
	/**
	 * the drawY.
	 */
	private int drawY;
	/**
	 * the posMouseRecorrido.
	 */
	private int[] posMouseRecorrido;
	/**
	 * the posMouse.
	 */
	private int[] posMouse;
	/**
	 * the tile.
	 */
	private int[] tile;

	// Movimiento Actual
	/**
	 * the HORIZANTAL_DER.
	 */
	private static final int HORIZONTAL_DER = 4;
	/**
	 * the HORIZONTAL_IZQ.
	 */
	private static final int HORIZONTAL_IZQ = 0;
	/**
	 * the VERTICAL_SUP.
	 */
	private static final int VERTICAL_SUP = 2;
	/**
	 * the VERTICAL_SUP.
	 */
	private static final int VERTICAL_INF = 6;
	/**
	 * the DIAGONAL_INF_IZQ.
	 */
	private static final int DIAGONAL_INF_IZQ = 7;
	/**
	 * the DIAGONAL_INF_DER.
	 */
	private static final int DIAGONAL_INF_DER = 5;
	/**
	 * the DIAGONAL_SUP_DER.
	 */
	private static final int DIAGONAL_SUP_DER = 3;
	/**
	 * the DIAGONAL_SUP_IZQ.
	 */
	private static final int DIAGONAL_SUP_IZQ = 1;
	/**
	 * the movimientoHacia.
	 */
	private int movimientoHacia = 6;
	/**
	 * the enMovimiento.
	 */
	private boolean enMovimiento;

	// Animaciones
	/**
	 * the moverIzq.
	 */
	private final Animacion moverIzq;
	/**
	 * the moverArribaIzq.
	 */
	private final Animacion moverArribaIzq;
	/**
	 * the moverArriba.
	 */
	private final Animacion moverArriba;
	/**
	 * the moverArribaDer.
	 */
	private final Animacion moverArribaDer;
	/**
	 * the moverDer.
	 */
	private final Animacion moverDer;
	/**
	 * the moverAbajoDer.
	 */
	private final Animacion moverAbajoDer;
	/**
	 * the moverAbajo.
	 */
	private final Animacion moverAbajo;
	/**
	 * the moverAbajoIzq.
	 */
	private final Animacion moverAbajoIzq;
	/**
	 * the Gson.
	 */
	private final Gson gson = new Gson();
	/**
	 * the intervaloEnvio.
	 */
	private int intervaloEnvio = 0;

	// pila de movimiento
	/**
	 * the pilaMovimiento.
	 */
	private PilaDeTiles pilaMovimiento;
	/**
	 * the tileActual.
	 */
	private int[] tileActual;
	/**
	 * the tileFinal.
	 */
	private int[] tileFinal;
	/**
	 * the tileMoverme.
	 */
	private int[] tileMoverme;
	/**
	 * the mundo.
	 */
	private Mundo mundo;
	/**
	 * the nombre.
	 */
	private String nombre;
	/**
	 * the tilePersonajes.
	 */
	private int[] tilePersonajes;
	/**
	 * the idEnemigo.
	 */
	private int idEnemigo;

	// Ubicacion para abrir comerciar.
	/**
	 * the idEnemigo.
	 */
	private float xComercio;
	/**
	 * the yComercio.
	 */
	private float yComercio;
	/**
	 * the comercio.
	 */
	private float[] comercio;
	/**
	 * the movimientos.
	 */
	private HashMap<Integer, Animacion> movimientos = new HashMap<Integer, Animacion>();

	/**
	 * Constructor de la clase Entidad.
	 * 
	 * @param juego
	 *            juego con el que se instancia Entidad
	 * @param mundo
	 *            mundo con el que se instancia Entidad
	 * @param ancho
	 *            ancho
	 * @param alto
	 *            alto
	 * @param nombre
	 *            nombre del personaje
	 * @param spawnX
	 *            tile X donde spawnea
	 * @param spawnY
	 *            tile Y donde spawnea
	 * @param animaciones
	 *            animaciones del personaje
	 * @param velAnimacion
	 *            velocidad de animacion del personaje
	 */
	public Entidad(final Juego juego, final Mundo mundo, final int ancho, final int alto, final String nombre,
			final float spawnX, final float spawnY, final LinkedList<BufferedImage[]> animaciones,
			final int velAnimacion) {
		this.juego = juego;
		this.ancho = ancho;
		this.alto = alto;
		this.nombre = nombre;
		this.mundo = mundo;
		xOffset = ancho / 2;
		yOffset = alto / 2;
		x = (int) (spawnX / Constantes.SPAWNX_X) * Constantes.SPAWNY_Y;
		y = (int) (spawnY / Constantes.SPAWNY_Y) * Constantes.SPAWNY_Y;

		moverIzq = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_0));
		moverArribaIzq = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_1));
		moverArriba = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_2));
		moverArribaDer = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_3));
		moverDer = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_4));
		moverAbajoDer = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_5));
		moverAbajo = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_6));
		moverAbajoIzq = new Animacion(velAnimacion, animaciones.get(Constantes.ANIMACION_POSICION_7));

		for (int i = 0; i < Constantes.I_MAXIMO_ANIMACIONES; i++) {
			movimientos.put(i, new Animacion(velAnimacion, animaciones.get(i)));
		}

		// Informo mi posicion actual
		juego.getUbicacionPersonaje().setPosX(x);
		juego.getUbicacionPersonaje().setPosY(y);
		juego.getUbicacionPersonaje().setDireccion(getDireccion());
		juego.getUbicacionPersonaje().setFrame(getFrame());
	}

	/**
	 * Actualiza el personaje.
	 */
	public void actualizar() {

		if (enMovimiento) {
			// moverIzq.actualizar();
			// moverArribaIzq.actualizar();
			// moverArriba.actualizar();
			// moverArribaDer.actualizar();
			// moverDer.actualizar();
			// moverAbajoDer.actualizar();
			// moverAbajo.actualizar();
			// moverAbajoIzq.actualizar();

			for (int i = 0; i < Constantes.I_MAXIMO_ANIMACIONES; i++) {
				movimientos.get(i).actualizar();
			}
		} else {
			// moverIzq.reset();
			// moverArribaIzq.reset();
			// moverArriba.reset();
			// moverArribaDer.reset();
			// moverDer.reset();
			// moverAbajoDer.reset();
			// moverAbajo.reset();
			// moverAbajoIzq.reset();

			for (int i = 0; i < Constantes.I_MAXIMO_ANIMACIONES; i++) {
				movimientos.get(i).reset();
			}
		}

		getEntrada();
		mover();

		juego.getCamara().centrar(this);
	}

	/**
	 * Devuelve la entrada.
	 */
	public void getEntrada() {
		posMouseRecorrido = juego.getHandlerMouse().getPosMouseRecorrido();
		posMouse = juego.getHandlerMouse().getPosMouse();
		if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= Constantes.POSICION_MOUSE_738
				&& posMouse[0] <= Constantes.POSICION_MOUSE_797 && posMouse[1] >= Constantes.POSICION_MOUSE_545
				&& posMouse[1] <= Constantes.POSICION_MOUSE_597) {
			if (Pantalla.menuInventario == null) {
				Pantalla.menuInventario = new MenuInventario(juego.getCliente());
				Pantalla.menuInventario.setVisible(true);
			}
			juego.getHandlerMouse().setNuevoClick(false);
		}
		if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= Constantes.POSICION_MOUSE_3
				&& posMouse[0] <= Constantes.POSICION_MOUSE_105 && posMouse[1] >= Constantes.POSICION_MOUSE_562
				&& posMouse[1] <= Constantes.POSICION_MOUSE_597) {
			if (Pantalla.menuEscp == null) {
				Pantalla.menuEscp = new MenuEscape(juego.getCliente());
				Pantalla.menuEscp.setVisible(true);
			}
			juego.getHandlerMouse().setNuevoClick(false);
		}
		if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= Constantes.POSICION_MOUSE_3
				&& posMouse[0] <= Constantes.POSICION_MOUSE_105 && posMouse[1] >= Constantes.POSICION_MOUSE_524
				&& posMouse[1] <= Constantes.POSICION_MOUSE_559) {
			if (Pantalla.ventContac == null) {
				Pantalla.ventContac = new VentanaContactos(juego);
				Pantalla.ventContac.setVisible(true);
			}
			juego.getHandlerMouse().setNuevoClick(false);
		}
		// Tomo el click izquierdo
		if (juego.getHandlerMouse().getNuevoClick()) {
			if (juego.getEstadoJuego().getHaySolicitud()) {

				if (juego.getEstadoJuego().getMenuEnemigo().clickEnMenu(posMouse[0], posMouse[1])) {
					if (juego.getEstadoJuego().getMenuEnemigo().clickEnBoton(posMouse[0], posMouse[1])) {
						// Pregunto si menuBatallar o menuComerciar, sino no me
						// interesa hacer esto
						if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUBATALLAR
								|| juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUCOMERCIAR) {
							// Guardo las poss con el que quiero comerciar
							xComercio = juego.getUbicacionPersonajes().get(idEnemigo).getPosX();
							yComercio = juego.getUbicacionPersonajes().get(idEnemigo).getPosY();
							comercio = Mundo.isoA2D(xComercio, yComercio);
						}
						// pregunto si el menu emergente es de tipo batalla
						if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUBATALLAR) {
							// ME FIJO SI CON EL QUE QUIERO BATALLAR ESTA EN LA
							// ZONA DE COMERCIO
							if (!((int) comercio[0] >= Constantes.GET_ENTRADA_COMERCIO_44
									&& (int) comercio[0] <= Constantes.GET_ENTRADA_COMERCIO_71
									&& (int) comercio[1] >= Constantes.GET_ENTRADA_COMERCIO_0
									&& (int) comercio[1] <= Constantes.GET_ENTRADA_COMERCIO_29)) {
								juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
								PaqueteBatalla pBatalla = new PaqueteBatalla();

								pBatalla.setId(juego.getPersonaje().getId());
								pBatalla.setIdEnemigo(idEnemigo);

								juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);

								try {
									juego.getCliente().getSalida().writeObject(gson.toJson(pBatalla));
								} catch (IOException e) {
									JOptionPane.showMessageDialog(null, "Fallo la conexión " + "con el servidor");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"El otro usuario se encuentra " + "dentro de la zona de comercio");
							}
						} else {
							// PREGUNTO SI EL MENU EMERGENTE ES DE TIPO COMERCIO
							if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUCOMERCIAR) {
								if ((int) comercio[0] >= Constantes.GET_ENTRADA_COMERCIO_44
										&& (int) comercio[0] <= Constantes.GET_ENTRADA_COMERCIO_71
										&& (int) comercio[1] >= Constantes.GET_ENTRADA_COMERCIO_0
										&& (int) comercio[1] <= Constantes.GET_ENTRADA_COMERCIO_29) {
									if (juego.getCliente().getM1() == null) {
										juego.getCliente().setPaqueteComercio(new PaqueteComerciar());
										juego.getCliente().getPaqueteComercio().setId(juego.getPersonaje().getId());
										juego.getCliente().getPaqueteComercio().setIdEnemigo(idEnemigo);

										try {
											juego.getCliente().getSalida()
													.writeObject(gson.toJson(juego.getCliente().getPaqueteComercio()));
										} catch (IOException e) {
											JOptionPane.showMessageDialog(null,
													"Fallo la conexión " + "con el servidor");
										}
									} else {
										JOptionPane.showMessageDialog(null, "Ya te encuentras comerciando!");
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"El otro usuario no se encuentra dentro de la zona de comercio");
								}
							}
						}
						juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);

					} else if (juego.getEstadoJuego().getMenuEnemigo().clickEnCerrar(posMouse[0], posMouse[1])) {
						juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
					}
				} else {
					juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
				}
			} else {
				Iterator<Integer> it = juego.getUbicacionPersonajes().keySet().iterator();
				int key;
				int[] tileMoverme = Mundo.mouseATile(posMouse[0] + juego.getCamara().getxOffset() - xOffset,
						posMouse[1] + juego.getCamara().getyOffset() - yOffset);
				PaqueteMovimiento actual;

				while (it.hasNext()) {
					key = it.next();
					actual = juego.getUbicacionPersonajes().get(key);
					tilePersonajes = Mundo.mouseATile(actual.getPosX(), actual.getPosY());
					if (actual != null && actual.getIdPersonaje() != juego.getPersonaje().getId()
							&& juego.getPersonajesConectados().get(actual.getIdPersonaje()) != null
							&& juego.getPersonajesConectados().get(actual.getIdPersonaje()).getEstado() == Estado
									.getEstadoJuego()) {

						if (tileMoverme[0] == tilePersonajes[0] && tileMoverme[1] == tilePersonajes[1]) {
							idEnemigo = actual.getIdPersonaje();
							float XY[] = Mundo.isoA2D(x, y);
							// ESTA ESTE PARA NO MOVERME HASTA EL LUGAR.
							if (XY[0] >= Constantes.GET_ENTRADA_COMERCIO_44
									&& XY[0] <= Constantes.GET_ENTRADA_COMERCIO_71
									&& XY[1] >= Constantes.GET_ENTRADA_COMERCIO_0
									&& XY[1] <= Constantes.GET_ENTRADA_COMERCIO_29) {
								// SI ESTOY DENTRO DE LA ZONA DE COMERCIO SETEO
								// QUE SE ABRA EL MENU
								// DE COMERCIO
								juego.getEstadoJuego().setHaySolicitud(true,
										juego.getPersonajesConectados().get(idEnemigo),
										MenuInfoPersonaje.MENUCOMERCIAR);
							} else {
								// SI ESTOY DENTRO DE LA ZONA DE BATALLA SETEO
								// QUE SE ABRA EL MENU
								// DE BATALLA
								juego.getEstadoJuego().setHaySolicitud(true,
										juego.getPersonajesConectados().get(idEnemigo), MenuInfoPersonaje.MENUBATALLAR);
							}
							juego.getHandlerMouse().setNuevoClick(false);
						}
					}
				}
			}
		}

		if (juego.getHandlerMouse().getNuevoRecorrido() && !juego.getEstadoJuego().getHaySolicitud()) {

			tileMoverme = Mundo.mouseATile(posMouseRecorrido[0] + juego.getCamara().getxOffset() - xOffset,
					posMouseRecorrido[1] + juego.getCamara().getyOffset() - yOffset);

			juego.getHandlerMouse().setNuevoRecorrido(false);

			pilaMovimiento = null;

			juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
		}

		if (!enMovimiento && tileMoverme != null) {

			enMovimiento = false;

			xInicio = x;
			yInicio = y;

			tileActual = Mundo.mouseATile(x, y);

			if (tileMoverme[0] < 0 || tileMoverme[1] < 0 || tileMoverme[0] >= mundo.obtenerAncho()
					|| tileMoverme[1] >= mundo.obtenerAlto()) {
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}

			if (tileMoverme[0] == tileActual[0] && tileMoverme[1] == tileActual[1]
					|| mundo.getTile(tileMoverme[0], tileMoverme[1]).esSolido()) {
				tileMoverme = null;
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				return;
			}

			if (pilaMovimiento == null) {
				pilaMovimiento = caminoMasCorto(tileActual[0], tileActual[1], tileMoverme[0], tileMoverme[1]);
			}
			// Me muevo al primero de la pila
			NodoDePila nodoActualTile = pilaMovimiento.pop();

			if (nodoActualTile == null) {
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}

			tileFinal = new int[2];
			tileFinal[0] = nodoActualTile.obtenerX();
			tileFinal[1] = nodoActualTile.obtenerY();

			xFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[0];
			yFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[1];

			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = VERTICAL_SUP;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = VERTICAL_INF;
			}
			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = HORIZONTAL_IZQ;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = HORIZONTAL_DER;
			}
			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1]) {
				movimientoHacia = DIAGONAL_SUP_IZQ;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1]) {
				movimientoHacia = DIAGONAL_INF_DER;
			}
			if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = DIAGONAL_SUP_DER;
			}
			if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = DIAGONAL_INF_IZQ;
			}
			enMovimiento = true;
		}
	}

	/**
	 * Mueve el personaje.
	 */
	public void mover() {

		dx = 0;
		dy = 0;

		double paso = 1;

		if (enMovimiento && !(x == xFinal && y == yFinal - Constantes.I_MOVIMIENTO_YFINAL)) {

			if (movimientoHacia == VERTICAL_SUP) {
				dy -= paso;
			} else if (movimientoHacia == VERTICAL_INF) {
				dy += paso;
			} else if (movimientoHacia == HORIZONTAL_DER) {
				dx += paso;
			} else if (movimientoHacia == HORIZONTAL_IZQ) {
				dx -= paso;
			} else if (movimientoHacia == DIAGONAL_INF_DER) {
				dx += paso;
				dy += paso / 2;
			} else if (movimientoHacia == DIAGONAL_INF_IZQ) {
				dx -= paso;
				dy += paso / 2;
			} else if (movimientoHacia == DIAGONAL_SUP_DER) {
				dx += paso;
				dy -= paso / 2;
			} else if (movimientoHacia == DIAGONAL_SUP_IZQ) {
				dx -= paso;
				dy -= paso / 2;
			}

			x += dx;
			y += dy;

			// Le envio la posicion
			if (intervaloEnvio == 2) {
				enviarPosicion();
				intervaloEnvio = 0;
			}
			intervaloEnvio++;

			if (x == xFinal && y == yFinal - Constantes.I_MOVIMIENTO_YFINAL) {
				enMovimiento = false;
			}
		}
	}

	/**
	 * Grafica el frame del personaje.
	 * 
	 * @param g
	 *            .
	 */
	public void graficar(final Graphics g) {
		drawX = (int) (x - juego.getCamara().getxOffset());
		drawY = (int) (y - juego.getCamara().getyOffset());
		g.drawImage(getFrameAnimacionActual(), drawX, drawY + Constantes.GRAFICAS_DRAWY, ancho, alto, null);
	}

	/**
	 * Grafica el nombre.
	 * 
	 * @param g
	 *            .
	 */
	public void graficarNombre(final Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", Font.BOLD, Constantes.FONT_BOLD));
		Pantalla.centerString(g,
				new java.awt.Rectangle(drawX + Constantes.GRAFICAR_NOMBRES_DRAWX,
						drawY - Constantes.GRAFICAR_NOMBRES_DRAWY_20, Constantes.GRAFICAR_NOMBRES_DRAWY_0,
						Constantes.GRAFICAR_NOMBRES_DRAWY_10),
				nombre);
	}

	/**
	 * Obtiene el frameActual del personaje.
	 * 
	 * @return BufferedImage de movimientos.
	 */
	private BufferedImage getFrameAnimacionActual() {
		BufferedImage bi = movimientos.get(movimientoHacia).getFrameActual();

		/*
		 * if (movimientoHacia == horizontalIzq) { return
		 * moverIzq.getFrameActual(); } else if (movimientoHacia ==
		 * horizontalDer) { return moverDer.getFrameActual(); } else if
		 * (movimientoHacia == verticalSup) { return
		 * moverArriba.getFrameActual(); } else if (movimientoHacia ==
		 * verticalInf) { return moverAbajo.getFrameActual(); } else if
		 * (movimientoHacia == diagonalInfIzq) { return
		 * moverAbajoIzq.getFrameActual(); } else if (movimientoHacia ==
		 * diagonalInfDer) { return moverAbajoDer.getFrameActual(); } else if
		 * (movimientoHacia == diagonalSupIzq) { return
		 * moverArribaIzq.getFrameActual(); } else if (movimientoHacia ==
		 * diagonalSupDer) { return moverArribaDer.getFrameActual(); }
		 */

		if (bi == null) {
			bi = Recursos.orco.get(Constantes.RECURSOS_ARCO_6)[0];
		}
		return bi;

		// return Recursos.orco.get(6)[0];

	}

	/**
	 * Pide la direccion donde va.
	 * 
	 * @return devuelve el movimiento hacia donde va.
	 */
	private int getDireccion() {
		return movimientoHacia;
	}

	/**
	 * Obtiene el frame donde esta el personaje.
	 * 
	 * @return .
	 */
	private int getFrame() {
		int frame = movimientos.get(movimientoHacia).getFrame();

		/*
		 * if (movimientoHacia == horizontalIzq) { return moverIzq.getFrame(); }
		 * else if (movimientoHacia == horizontalDer) { return
		 * moverDer.getFrame(); } else if (movimientoHacia == verticalSup) {
		 * return moverArriba.getFrame(); } else if (movimientoHacia ==
		 * verticalInf) { return moverAbajo.getFrame(); } else if
		 * (movimientoHacia == diagonalInfIzq) { return
		 * moverAbajoIzq.getFrame(); } else if (movimientoHacia ==
		 * diagonalInfDer) { return moverAbajoDer.getFrame(); } else if
		 * (movimientoHacia == diagonalSupIzq) { return
		 * moverArribaIzq.getFrame(); } else if (movimientoHacia ==
		 * diagonalSupDer) { return moverArribaDer.getFrame(); } return 0;
		 */
		if (movimientoHacia < 0 && movimientoHacia > Constantes.MOVIMIENTOS_HACIA) {
			frame = 0;
		}
		return frame;
	}

	/**
	 * Envia la posicion del personaje.
	 */
	private void enviarPosicion() {
		juego.getUbicacionPersonaje().setPosX(x);
		juego.getUbicacionPersonaje().setPosY(y);
		juego.getUbicacionPersonaje().setDireccion(getDireccion());
		juego.getUbicacionPersonaje().setFrame(getFrame());
		try {
			juego.getCliente().getSalida()
					.writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
		}
	}

	/**
	 * Busca el camino más corto a recorrer para llegar a una posición.
	 * 
	 * @param xInicial
	 *            ubicacion en X inicial
	 * @param yInicial
	 *            ubicacion en Y inicial
	 * @param xFinal
	 *            ubicacion en X final
	 * @param yFinal
	 *            ubicacion en Y final
	 * @return la pila de tiles a recorrer
	 */
	private PilaDeTiles caminoMasCorto(final int xInicial, final int yInicial, final int xFinal, final int yFinal) {
		Grafo grafoLibres = mundo.obtenerGrafoDeTilesNoSolidos();
		// Transformo las coordenadas iniciales y finales en indices
		int nodoInicial = (yInicial - grafoLibres.obtenerNodos()[0].obtenerY())
				* (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xInicial
				- grafoLibres.obtenerNodos()[0].obtenerX();

		int nodoFinal = (yFinal - grafoLibres.obtenerNodos()[0].obtenerY())
				* (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xFinal
				- grafoLibres.obtenerNodos()[0].obtenerX();

		// Hago todo
		double[] vecCostos = new double[grafoLibres.obtenerCantidadDeNodosTotal()];
		int[] vecPredecesores = new int[grafoLibres.obtenerCantidadDeNodosTotal()];
		boolean[] conjSolucion = new boolean[grafoLibres.obtenerCantidadDeNodosTotal()];
		int cantSolucion = 0;
		// Lleno la matriz de costos de numeros grandes
		for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
			vecCostos[i] = Double.MAX_VALUE;
		}
		// Adyacentes al nodo inicial
		conjSolucion[nodoInicial] = true;
		cantSolucion++;
		vecCostos[nodoInicial] = 0;
		Nodo[] adyacentes = grafoLibres.obtenerNodos()[nodoInicial].obtenerNodosAdyacentes();
		for (int i = 0; i < grafoLibres.obtenerNodos()[nodoInicial].obtenerCantidadDeAdyacentes(); i++) {
			if (estanEnDiagonal(grafoLibres.obtenerNodos()[nodoInicial],
					grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
				vecCostos[adyacentes[i].obtenerIndice()] = Constantes.OBTENER_INDICE;
			} else {
				vecCostos[adyacentes[i].obtenerIndice()] = 1;
			}
			vecPredecesores[adyacentes[i].obtenerIndice()] = nodoInicial;
		}
		// Aplico Dijkstra
		while (cantSolucion < grafoLibres.obtenerCantidadDeNodosTotal()) {
			// Elijo W perteneciente al conjunto restante tal que el costo de W
			// sea minimo
			double minimo = Double.MAX_VALUE;
			int indiceMinimo = 0;
			Nodo nodoW = null;
			for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
				if (!conjSolucion[i] && vecCostos[i] < minimo) {
					nodoW = grafoLibres.obtenerNodos()[i];
					minimo = vecCostos[i];
					indiceMinimo = i;
				}
			}
			// Pongo a W en el conj solucion
			conjSolucion[indiceMinimo] = true;
			cantSolucion++;
			// Por cada nodo I adyacente a W del conj restante
			// Le sumo 1 al costo de ir hasta W y luego ir hasta su adyacente
			adyacentes = grafoLibres.obtenerNodos()[indiceMinimo].obtenerNodosAdyacentes();
			for (int i = 0; i < grafoLibres.obtenerNodos()[indiceMinimo].obtenerCantidadDeAdyacentes(); i++) {
				double valorASumar = 1;
				if (estanEnDiagonal(grafoLibres.obtenerNodos()[indiceMinimo],
						grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
					valorASumar = Constantes.VALOR_A_SUMAR;
				}
				if (vecCostos[indiceMinimo] + valorASumar < vecCostos[adyacentes[i].obtenerIndice()]) {
					vecCostos[adyacentes[i].obtenerIndice()] = vecCostos[indiceMinimo] + valorASumar;
					vecPredecesores[adyacentes[i].obtenerIndice()] = indiceMinimo;
				}
			}
		}
		// Creo el vector de nodos hasta donde quiere llegar
		PilaDeTiles camino = new PilaDeTiles();
		while (nodoFinal != nodoInicial) {
			camino.push(new NodoDePila(grafoLibres.obtenerNodos()[nodoFinal].obtenerX(),
					grafoLibres.obtenerNodos()[nodoFinal].obtenerY()));
			nodoFinal = vecPredecesores[nodoFinal];
		}

		return camino;
	}

	/**
	 * Pregunta si los personajes estan en diagonal.
	 * 
	 * @param nodoUno
	 *            personaje 1
	 * @param nodoDos
	 *            personaje 2
	 * @return true or false
	 */
	private boolean estanEnDiagonal(final Nodo nodoUno, final Nodo nodoDos) {
		boolean coordenadasXIguales = nodoUno.obtenerX() == nodoDos.obtenerX();
		boolean coordenadasYIguales = nodoUno.obtenerY() == nodoDos.obtenerY();

		return coordenadasXIguales || coordenadasYIguales;
	}

	/**
	 * Pide el valor de X.
	 * 
	 * @return devuelve la ubicacion en X
	 */
	public float getX() {
		return x;
	}

	/**
	 * Setea el valor de X.
	 * 
	 * @param x
	 *            valor nuevo de la ubicacion en X
	 */
	public void setX(final float x) {
		this.x = x;
	}

	/**
	 * Pide el valor de Y.
	 * 
	 * @return devuelve la ubicacion en Y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Setea el valor de Y.
	 * 
	 * @param y
	 *            valor nuevo de la ubicacion en Y
	 */
	public void setY(final float y) {
		this.y = y;
	}

	/**
	 * Pide el ancho.
	 * 
	 * @return devuelve el ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Setea el ancho.
	 * 
	 * @param ancho
	 *            nuevo ancho a setear
	 */
	public void setAncho(final int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Pide el alto.
	 * 
	 * @return devuelve el alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Setea el alto.
	 * 
	 * @param alto
	 *            nuevo alto a setear
	 */
	public void setAlto(final int alto) {
		this.alto = alto;
	}

	/**
	 * Pide el offset de X.
	 * 
	 * @return devuelve el offset de X
	 */
	public int getxOffset() {
		return xOffset;
	}

	/**
	 * Pide el offset de Y.
	 * 
	 * @return devuelve el offset de Y
	 */
	public int getYOffset() {
		return yOffset;
	}
}
