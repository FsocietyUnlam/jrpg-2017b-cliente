package estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import entidades.Entidad;
import interfaz.EstadoDePersonaje;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;
import variables.Constantes;

/**
 * Clase EstadoJuego.
 */
public class EstadoJuego extends Estado {
	/**
	 * the entidadPersonaje.
	 */
	private Entidad entidadPersonaje;
	/**
	 * the paquetePersonaje.
	 */
	private PaquetePersonaje paquetePersonaje;
	/**
	 * the mundo.
	 */
	private Mundo mundo;
	/**
	 * the ubicacionPersonajes.
	 */
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	/**
	 * the personajesConectados.
	 */
	private Map<Integer, PaquetePersonaje> personajesConectados;
	/**
	 * the haySolicitud.
	 */
	private boolean haySolicitud;
	/**
	 * the tipoSolicitud.
	 */
	private int tipoSolicitud;
	/**
	 * the MUNDOS.
	 */
	private static final String[] MUNDOS = {"Aubenor", "Aris", "Eodrim"};
	/**
	 * the gson.
	 */
	private final Gson gson = new Gson();
	/**
	 * the miniaturaPersonaje.
	 */
	private BufferedImage miniaturaPersonaje;
	/**
	 * the menuEnemigo.
	 */
	private MenuInfoPersonaje menuEnemigo;

	/**
	 * Constructor de la clase EstadoJuego.
	 * @param juego .
	 */
	public EstadoJuego(final Juego juego) {
		super(juego);
		mundo = new Mundo(juego, "recursos/" + getMundo() + ".txt", "recursos/" + getMundo() + ".txt");
		paquetePersonaje = juego.getPersonaje();
		entidadPersonaje = new Entidad(juego, mundo, Constantes.ESTADO_JUEGO_64, Constantes.ESTADO_JUEGO_64, juego.getPersonaje().getNombre(), 0, 0,
				Recursos.personaje.get(juego.getPersonaje().getRaza()), Constantes.ESTADO_JUEGO_150);
		miniaturaPersonaje = Recursos.personaje.get(paquetePersonaje.getRaza()).get(Constantes.ESTADO_JUEGO_5)[0];

		try {
			// Le envio al servidor que me conecte al mapa y mi posicion
			juego.getPersonaje().setComando(Comando.CONEXION);
			juego.getPersonaje().setEstado(Estado.getEstadoJuego());
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje(), PaquetePersonaje.class));
			juego.getCliente().getSalida()
					.writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor al ingresar al mundo");
		}
	}

	/**
	 * Actualiza.
	 */
	@Override
	public void actualizar() {
		mundo.actualizar();
		entidadPersonaje.actualizar();
	}

	/**
	 * Grafica.
	 * @param g .
	 */
	@Override
	public void graficar(final Graphics g) {
		g.drawImage(Recursos.background, 0, 0, getJuego().getAncho(), getJuego().getAlto(), null);
		mundo.graficar(g);
		// entidadPersonaje.graficar(g);
		graficarPersonajes(g);
		mundo.graficarObstaculos(g);
		entidadPersonaje.graficarNombre(g);
		g.drawImage(Recursos.marco, 0, 0, getJuego().getAncho(), getJuego().getAlto(), null);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, Constantes.ESTADO_JUEGO_GRAFICAR_5, Constantes.ESTADO_JUEGO_GRAFICAR_5, paquetePersonaje, miniaturaPersonaje);
		g.drawImage(Recursos.mochila, Constantes.ESTADO_JUEGO_GRAFICAR_738, Constantes.ESTADO_JUEGO_GRAFICAR_545, Constantes.ESTADO_JUEGO_GRAFICAR_59, Constantes.ESTADO_JUEGO_GRAFICAR_52, null);
		g.drawImage(Recursos.menu, Constantes.ESTADO_JUEGO_GRAFICAR_3, Constantes.ESTADO_JUEGO_GRAFICAR_562, Constantes.ESTADO_JUEGO_GRAFICAR_102, Constantes.ESTADO_JUEGO_GRAFICAR_35, null);
		g.drawImage(Recursos.chat, Constantes.ESTADO_JUEGO_GRAFICAR_3, Constantes.ESTADO_JUEGO_GRAFICAR_524, Constantes.ESTADO_JUEGO_GRAFICAR_102, Constantes.ESTADO_JUEGO_GRAFICAR_35, null);
		if (haySolicitud) {
			menuEnemigo.graficar(g, tipoSolicitud);
		}

	}

	/**
	 * Grafica los personajes.
	 * @param g .
	 */
	public void graficarPersonajes(final Graphics g) {

		if (getJuego().getPersonajesConectados() != null) {
			personajesConectados = new HashMap<Integer, PaquetePersonaje>(getJuego().getPersonajesConectados());
			ubicacionPersonajes = new HashMap<Integer, PaqueteMovimiento>(getJuego().getUbicacionPersonajes());
			Iterator<Integer> it = personajesConectados.keySet().iterator();
			int key;
			PaqueteMovimiento actual;
			g.setColor(Color.WHITE);
			g.setFont(new Font("Book Antiqua", Font.PLAIN, Constantes.GRAFICAR_PERSONAJES_FONT_PLAIN));
			while (it.hasNext()) {
				key = it.next();
				actual = ubicacionPersonajes.get(key);
				if (actual != null && actual.getIdPersonaje() != getJuego().getPersonaje().getId()
						&& personajesConectados.get(actual.getIdPersonaje()).getEstado() == Estado.getEstadoJuego()) {
					Pantalla.centerString(g,
							new Rectangle((int) (actual.getPosX() - getJuego().getCamara().getxOffset() + Constantes.GRAFICAR_PERSONAJES_32),
									(int) (actual.getPosY() - getJuego().getCamara().getyOffset() - Constantes.GRAFICAR_PERSONAJES_20), Constantes.GRAFICAR_NOMBRES_DRAWY_0, Constantes.GRAFICAR_PERSONAJES_10),
							personajesConectados.get(actual.getIdPersonaje()).getNombre());
					g.drawImage(
							Recursos.personaje.get(personajesConectados.get(actual.getIdPersonaje()).getRaza())
									.get(actual.getDireccion())[actual.getFrame()],
							(int) (actual.getPosX() - getJuego().getCamara().getxOffset()),
							(int) (actual.getPosY() - getJuego().getCamara().getyOffset()), Constantes.GRAFICAR_PERSONAJES_64, Constantes.GRAFICAR_PERSONAJES_64, null);
				}
			}
		}
	}

	/**
	 * Se obtiene el personaje.
	 * @return la clase Entidad .
	 */
	public Entidad getPersonaje() {
		return entidadPersonaje;
	}

	/**
	 * Se obtiene el mundo.
	 * @return un String.
	 */
	private String getMundo() {
		int mundo = getJuego().getPersonaje().getMapa();

		/*
		 * if (mundo == 1) { return "Aubenor"; } else if (mundo == 2) { return "Aris"; }
		 * else if (mundo == 3) { return "Eodrim"; }
		 * return null;
		 */
		return MUNDOS[mundo - 1];
	}

	/**
	 * @param b .
	 * @param enemigo .
	 * @param tipoSolicitud .
	 */
	public void setHaySolicitud(final boolean b, final PaquetePersonaje enemigo, final int tipoSolicitud) {
		haySolicitud = b;
		// menu que mostrara al enemigo
		menuEnemigo = new MenuInfoPersonaje(Constantes.HAY_SOLICITUD_300, Constantes.HAY_SOLICITUD_50, enemigo);
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * Pregunta si hay solitud.
	 * @return un booleano.
	 */
	public boolean getHaySolicitud() {
		return haySolicitud;
	}

	/**
	 * Actualiza personaje.
	 */
	public void actualizarPersonaje() {
		paquetePersonaje = getJuego().getPersonaje();
	}

	/**
	 * Se obtiene el menu del enemigo.
	 * @return la clase MenuInfoPersonaje.
	 */
	public MenuInfoPersonaje getMenuEnemigo() {
		return menuEnemigo;
	}

	/**
	 * Se obtiene el tipo de solicitud.
	 * @return un entero.
	 */
	public int getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * Pregunta el estado de juego.
	 * @return un booleano.
	 */
	@Override
	public boolean esEstadoDeJuego() {
		return true;
	}

}