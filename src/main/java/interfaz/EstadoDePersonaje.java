package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Clase para el estado del personaje.
 * @author Lucas
 *
 */
public class EstadoDePersonaje {

	/**
	 * Atributo estatico del tipo entero para el ancho de la barra.
	 */
	private static final int ANCHOBARRA = 122;
	/**
	 * Atributo estatico del tipo entero para el alto de salud.
	 */
	private static final int ALTOSALUD = 14;
	/**
	 * Atributo estatico del tipo entero para el alto de energia.
	 */
	private static final int ALTOENERGIA = 14;
	/**
	 * Atributo estatico del tipo entero para al de experiencia.
	 */
	private static final int ALTOEXPERIENCIA = 6;
	/**
	 * Atributo estatico del tipo entero para el de miniatura.
	 */
	private static final int ALTOMINIATURA = 64;
	/**
	 * Atributo estatico del tipo entero para ancho de miniatura.
	 */
	private static final int ANCHOMINIATURA = 64;
	/**
	 * Atributo estatico del tipo entero para el tamanio de la letra.
	 */
	private static final int SIZE_LETRA = 10;

	/**
	 * Metodo estatico para dibujar el estado del personaje.
	 * @param g es del tipo entero
	 * @param x es del tipo entero
	 * @param y es del tipo entero
	 * @param personaje es del tipo Personaje
	 * @param miniaturaPersonaje es un BufferedImage
	 */
	public static void dibujarEstadoDePersonaje(final Graphics g, final int x, final int y, final Personaje personaje,
			final BufferedImage miniaturaPersonaje) {

		int drawBarra = 0;

		g.drawImage(Recursos.estadoPersonaje, x, y, null);

		g.drawImage(miniaturaPersonaje, x + 10, y + 9, ANCHOMINIATURA, ALTOMINIATURA, null);

		if (personaje.getSalud() == personaje.getSaludTope()) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getSalud() * ANCHOBARRA) / personaje.getSaludTope();
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.PLAIN, SIZE_LETRA));
		g.drawImage(Recursos.barraSalud, x + 80, y + 26, drawBarra, ALTOSALUD, null);
		g.drawString(String.valueOf(personaje.getSalud()) + " / " + String.valueOf(personaje.getSaludTope()), x + 132,
				y + 37);

		if (personaje.getEnergia() == personaje.getEnergiaTope()) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getEnergia() * ANCHOBARRA) / personaje.getEnergiaTope();
		}

		g.drawImage(Recursos.barraEnergia, x + 80, y + 42, drawBarra, ALTOENERGIA, null);
		g.drawString(String.valueOf(personaje.getEnergia()) + " / " + String.valueOf(personaje.getEnergiaTope()),
				x + 132, y + 52);

		if (personaje.getExperiencia() == Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
					/ Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
		}

		g.setFont(new Font("Tahoma", Font.PLAIN, 8));
		g.drawImage(Recursos.barraExperiencia, x + 77, y + 65, drawBarra, ALTOEXPERIENCIA, null);
		g.drawString(String.valueOf(personaje.getExperiencia()) + " / "
				+ String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]), x + 132, y + 70);
		g.setFont(new Font("Tahoma", Font.PLAIN, SIZE_LETRA));
		g.setColor(Color.GREEN);
		g.drawString(String.valueOf(personaje.getNivel()), x + 59, y + 70);

	}

/**
 * Metodo estatico para dibujar los graficos del estado del personaje.
	 * @param g es del tipo entero
	 * @param x es del tipo entero
	 * @param y es del tipo entero
	 * @param personaje es del tipo Personaje
	 * @param miniaturaPersonaje es un BufferedImage
 */
	public static void dibujarEstadoDePersonaje(final Graphics g, final int x, final int y, final PaquetePersonaje personaje,
			final BufferedImage miniaturaPersonaje) {

		int drawBarra = 0;

		g.drawImage(Recursos.estadoPersonaje, x, y, null);

		g.drawImage(miniaturaPersonaje, x + 10, y + 9, ANCHOMINIATURA, ALTOMINIATURA, null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.PLAIN, SIZE_LETRA));
		g.drawImage(Recursos.barraSalud, x + 80, y + 26, ANCHOBARRA, ALTOSALUD, null);
		g.drawString(String.valueOf(personaje.getSaludTope()) + " / " + String.valueOf(personaje.getSaludTope()),
				x + 132, y + 37);

		g.drawImage(Recursos.barraEnergia, x + 80, y + 42, ANCHOBARRA, ALTOENERGIA, null);
		g.drawString(String.valueOf(personaje.getEnergiaTope()) + " / " + String.valueOf(personaje.getEnergiaTope()),
				x + 132, y + 52);

		if (personaje.getExperiencia() == Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
					/ Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
		}

		g.setFont(new Font("Tahoma", Font.PLAIN, 8));
		g.drawImage(Recursos.barraExperiencia, x + 77, y + 65, drawBarra, ALTOEXPERIENCIA, null);
		g.drawString(String.valueOf(personaje.getExperiencia()) + " / "
				+ String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]), x + 132, y + 70);
		g.setFont(new Font("Tahoma", Font.PLAIN, SIZE_LETRA));
		g.setColor(Color.GREEN);
		g.drawString(String.valueOf(personaje.getNivel()), x + 59, y + 70);
	}
}
