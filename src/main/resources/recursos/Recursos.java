package recursos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import dominio.Item;
import frames.MenuCarga;
import frames.MenuMapas;
import mundo.Tile;

public class Recursos {

	private static int ELEMENTOS = 65;
	private static int ANCHOBARRA = 345;

	private static final int ANCHO = 256; // Ancho del frame a obtener
	private static final int ALTO = 256; // Alto del frame a obtener

	// Inicio Personajes
	// Hash de imagenes para los personajes (humano, ogro, elfo)
	public static Map<String, LinkedList<BufferedImage[]>> personaje = new HashMap<>();

	private static SpriteSheet spriteHumano;
	public static LinkedList<BufferedImage[]> humano = new LinkedList<>();
	private static BufferedImage[] humanoIzq;
	private static BufferedImage[] humanoArribaIzq;
	private static BufferedImage[] humanoArriba;
	private static BufferedImage[] humanoArribaDer;
	private static BufferedImage[] humanoDer;
	private static BufferedImage[] humanoAbajoDer;
	private static BufferedImage[] humanoAbajo;
	private static BufferedImage[] humanoAbajoIzq;

	private static SpriteSheet spriteOgro;
	public static LinkedList<BufferedImage[]> orco = new LinkedList<>();
	private static BufferedImage[] orcoIzq;
	private static BufferedImage[] orcoArribaIzq;
	private static BufferedImage[] orcoArriba;
	private static BufferedImage[] orcoArribaDer;
	private static BufferedImage[] orcoDer;
	private static BufferedImage[] orcoAbajoDer;
	private static BufferedImage[] orcoAbajo;
	private static BufferedImage[] orcoAbajoIzq;

	private static SpriteSheet spriteElfo;
	public static LinkedList<BufferedImage[]> elfo = new LinkedList<>();
	private static BufferedImage[] elfoIzq;
	private static BufferedImage[] elfoArribaIzq;
	private static BufferedImage[] elfoArriba;
	private static BufferedImage[] elfoArribaDer;
	private static BufferedImage[] elfoDer;
	private static BufferedImage[] elfoAbajoDer;
	private static BufferedImage[] elfoAbajo;
	private static BufferedImage[] elfoAbajoIzq;
	// Fin Personajes

	// Entorno
	private static SpriteSheet trees;
	public static BufferedImage cesped;
	public static BufferedImage roca;
	public static BufferedImage background;
	public static BufferedImage marco;
	public static BufferedImage botonMenu;
	public static BufferedImage menuEnemigo;
	public static BufferedImage greenTree;
	public static BufferedImage nievePiso1;
	public static BufferedImage iceBlock;
	// Fin Entorno

	// Batalla
	public static BufferedImage barraSpells;
	public static BufferedImage estadoPersonaje;
	public static BufferedImage barraSalud;
	public static BufferedImage barraEnergia;
	public static BufferedImage barraExperiencia;
	public static BufferedImage menuBatalla;
	public static BufferedImage menuBatallaDeshabilitado;
	public static BufferedImage noItem;
	public static BufferedImage mochila;
	public static BufferedImage menu;
	public static BufferedImage chat;
	public static Map<String, BufferedImage> habilidades = new HashMap<>();
	// Fin Batalla
	
	public static final int TAMANIO_BUFFER = 4;

	// Se cargan todos los recursos del juego una sola vez al inicio

	public static void cargar(MenuCarga menuCarga) throws NumberFormatException, IOException {

		int elementosCargados = 0;
		// Items

		noItem = ImageIO.read(new File("recursos//noItem.png"));
		mochila = ImageIO.read(new File("recursos//mochila.png"));
		menu = ImageIO.read(new File("recursos//menu.png"));
		chat = ImageIO.read(new File("recursos//chat.png"));

		// Inicio humano
		spriteHumano = new SpriteSheet(CargadorImagen.cargarImagen("/Humano.png"));

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		humanoIzq = new BufferedImage[TAMANIO_BUFFER];
		humanoArribaIzq = new BufferedImage[TAMANIO_BUFFER];
		humanoArriba = new BufferedImage[TAMANIO_BUFFER];
		humanoArribaDer = new BufferedImage[TAMANIO_BUFFER];
		humanoDer = new BufferedImage[TAMANIO_BUFFER];
		humanoAbajoDer = new BufferedImage[TAMANIO_BUFFER];
		humanoAbajo = new BufferedImage[TAMANIO_BUFFER];
		humanoAbajoIzq = new BufferedImage[TAMANIO_BUFFER];

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoIzq[i] = spriteHumano.getTile(ANCHO * i, 0, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoArribaIzq[i] = spriteHumano.getTile(ANCHO * i, ALTO, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoArriba[i] = spriteHumano.getTile(ANCHO * i, ALTO * 2, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoArribaDer[i] = spriteHumano.getTile(ANCHO * i, ALTO * 3, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoDer[i] = spriteHumano.getTile(ANCHO * i, ALTO * 4, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoAbajoDer[i] = spriteHumano.getTile(ANCHO * i, ALTO * 5, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoAbajo[i] = spriteHumano.getTile(ANCHO * i, ALTO * 6, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			humanoAbajoIzq[i] = spriteHumano.getTile(ANCHO * i, ALTO * 7, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		humano.add(humanoIzq);
		humano.add(humanoArribaIzq);
		humano.add(humanoArriba);
		humano.add(humanoArribaDer);
		humano.add(humanoDer);
		humano.add(humanoAbajoDer);
		humano.add(humanoAbajo);
		humano.add(humanoAbajoIzq);
		// Fin humano

		// Inicio Ogro
		spriteOgro = new SpriteSheet(CargadorImagen.cargarImagen("/Ogro.png"));

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		orcoIzq = new BufferedImage[TAMANIO_BUFFER];
		orcoArribaIzq = new BufferedImage[TAMANIO_BUFFER];
		orcoArriba = new BufferedImage[TAMANIO_BUFFER];
		orcoArribaDer = new BufferedImage[TAMANIO_BUFFER];
		orcoDer = new BufferedImage[TAMANIO_BUFFER];
		orcoAbajoDer = new BufferedImage[TAMANIO_BUFFER];
		orcoAbajo = new BufferedImage[TAMANIO_BUFFER];
		orcoAbajoIzq = new BufferedImage[TAMANIO_BUFFER];

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoIzq[i] = spriteOgro.getTile(ANCHO * i, 0, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoArribaIzq[i] = spriteOgro.getTile(ANCHO * i, ALTO, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoArriba[i] = spriteOgro.getTile(ANCHO * i, ALTO * 2, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoArribaDer[i] = spriteOgro.getTile(ANCHO * i, ALTO * 3, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoDer[i] = spriteOgro.getTile(ANCHO * i, ALTO * 4, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoAbajoDer[i] = spriteOgro.getTile(ANCHO * i, ALTO * 5, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoAbajo[i] = spriteOgro.getTile(ANCHO * i, ALTO * 6, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			orcoAbajoIzq[i] = spriteOgro.getTile(ANCHO * i, ALTO * 7, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		orco.add(orcoIzq);
		orco.add(orcoArribaIzq);
		orco.add(orcoArriba);
		orco.add(orcoArribaDer);
		orco.add(orcoDer);
		orco.add(orcoAbajoDer);
		orco.add(orcoAbajo);
		orco.add(orcoAbajoIzq);

		// Fin Ogro

		// Inicio Elfo
		spriteElfo = new SpriteSheet(CargadorImagen.cargarImagen("/elfo2.png"));

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		elfoIzq = new BufferedImage[TAMANIO_BUFFER];
		elfoArribaIzq = new BufferedImage[TAMANIO_BUFFER];
		elfoArriba = new BufferedImage[TAMANIO_BUFFER];
		elfoArribaDer = new BufferedImage[TAMANIO_BUFFER];
		elfoDer = new BufferedImage[TAMANIO_BUFFER];
		elfoAbajoDer = new BufferedImage[TAMANIO_BUFFER];
		elfoAbajo = new BufferedImage[TAMANIO_BUFFER];
		elfoAbajoIzq = new BufferedImage[TAMANIO_BUFFER];

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoIzq[i] = spriteElfo.getTile(ANCHO * i, 0, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoArribaIzq[i] = spriteElfo.getTile(ANCHO * i, ALTO, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoArriba[i] = spriteElfo.getTile(ANCHO * i, ALTO * 2, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoArribaDer[i] = spriteElfo.getTile(ANCHO * i, ALTO * 3, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoDer[i] = spriteElfo.getTile(ANCHO * i, ALTO * 4, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoAbajoDer[i] = spriteElfo.getTile(ANCHO * i, ALTO * 5, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoAbajo[i] = spriteElfo.getTile(ANCHO * i, ALTO * 6, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < TAMANIO_BUFFER; i++) {
			elfoAbajoIzq[i] = spriteElfo.getTile(ANCHO * i, ALTO * 7, ANCHO, ALTO);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		elfo.add(elfoIzq);
		elfo.add(elfoArribaIzq);
		elfo.add(elfoArriba);
		elfo.add(elfoArribaDer);
		elfo.add(elfoDer);
		elfo.add(elfoAbajoDer);
		elfo.add(elfoAbajo);
		elfo.add(elfoAbajoIzq);

		// Fin Elfo

		// Agrego los pj al hash
		personaje.put("Humano", humano);
		personaje.put("Orco", orco);
		personaje.put("Elfo", elfo);

		// Inicio Entorno
		cesped = CargadorImagen.cargarImagen("/Cesped.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		roca = CargadorImagen.cargarImagen("/rock.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		background = CargadorImagen.cargarImagen("/background.jpg");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		marco = CargadorImagen.cargarImagen("/marco.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		botonMenu = CargadorImagen.cargarImagen("/botonMenu.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		menuEnemigo = CargadorImagen.cargarImagen("/MenuEnemigo.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		trees = new SpriteSheet(CargadorImagen.cargarImagen("/trees.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		greenTree = trees.getTile(0, 0, 42, 50);
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		nievePiso1 = CargadorImagen.cargarImagen("/nieve piso.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		iceBlock = CargadorImagen.cargarImagen("/nieve cubo.png");

		// Mapa
		if (MenuMapas.numberMap == 1) {
			SpriteSheet mapaAubenor = new SpriteSheet(CargadorImagen.cargarImagen("/Aubenor.png"));
			Tile.aubenor = new Tile[81];
			boolean[][] solidezAubenor = {{true, true, false, true, false, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true},
					{true, false, false, false, false, false, false, false, true, true},
					{false, false, false, false, false, false, false, false, true, true},
					{false, true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true}};
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 10; x++) {
					Tile.aubenor[y * 10 + x + 1] = new Tile(
							mapaAubenor.getTile(x * 64, y * 64, 64, 64), y * 10 + x + 1,
							solidezAubenor[y][x], 64, 64);
				}
			}
		} else {
			SpriteSheet mapaAris = new SpriteSheet(CargadorImagen.cargarImagen("/Aris.png"));
			Tile.aris = new Tile[81];
			boolean[][] solidezAris = {{true, false, false, false, false, false, false, true, true, true},
					{false, false, false, false, false, false, false, false, true, true},
					{false, false, false, false, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true},
					{false, true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true},
					{true, true, true, true, true, true, true, true, true, true}};
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 10; x++) {
					Tile.aris[y * 10 + x + 1] = new Tile(mapaAris.getTile(x * 64, y * 64, 64, 64),
							y * 10 + x + 1, solidezAris[y][x], 64, 64);
				}
			}
		}

		// Fin Entorno

		// Inicio Batalla
		barraSpells = CargadorImagen.cargarImagen("/BarraSpells.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		estadoPersonaje = CargadorImagen.cargarImagen("/EstadoPersonaje.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		barraSalud = CargadorImagen.cargarImagen("/BarraDeSalud.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		barraEnergia = CargadorImagen.cargarImagen("/BarraDeEnergia.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		barraExperiencia = CargadorImagen.cargarImagen("/BarraDeExperiencia.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Level", CargadorImagen.cargarImagen("/Golpe Level.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ataque Bosque", CargadorImagen.cargarImagen("/Ataque Bosque.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Defensa", CargadorImagen.cargarImagen("/Golpe Defensa.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Mordisco de Vida", CargadorImagen.cargarImagen("/Mordisco de Vida.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Incentivar", CargadorImagen.cargarImagen("/Incentivar.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Fatal", CargadorImagen.cargarImagen("/Golpe Fatal.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ataque Doble", CargadorImagen.cargarImagen("/Ataque Doble.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Aumentar Defensa", CargadorImagen.cargarImagen("/Aumentar Defensa.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ignorar Defensa", CargadorImagen.cargarImagen("/Ignorar Defensa.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Bola de Fuego", CargadorImagen.cargarImagen("/Bola de Fuego.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Curar Aliado", CargadorImagen.cargarImagen("/Curar Aliado.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Robar Energia y Salud", CargadorImagen.cargarImagen("/Robar Energia y Salud.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Critico", CargadorImagen.cargarImagen("/Golpe Critico.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Aumentar Evasion", CargadorImagen.cargarImagen("/Aumentar Evasion.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Robar", CargadorImagen.cargarImagen("/Robar.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ser Energizado", CargadorImagen.cargarImagen("/Ser Energizado.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		menuBatalla = CargadorImagen.cargarImagen("/MenuBatalla.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		menuBatallaDeshabilitado = CargadorImagen.cargarImagen("/MenuBatallaDeshabilitado.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		// Fin Batalla
	}

	private static void actualizarBarraDeCarga(int elementosCargados, MenuCarga menuCarga) {
		menuCarga.setBarraCargando(elementosCargados * ANCHOBARRA / ELEMENTOS);
	}
}
