package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import dominio.Personaje;
import juego.Pantalla;
import recursos.Recursos;

/**
 * Clase del menu de la batalla.
 * @author Lucas
 *
 */
public class MenuBatalla {

	/**
	 * Atributo estático del tipo entero para indicar el eje x.
	 */
	private static final int X = 100;
	/**
	 * Atributo estático del tipo entero para indicar el eje y.
	 */
	private static final int Y = 380;
	/**
	 * Atributo estático del tipo entero para indicar el ancho del boton.
	 */
	private static final int ANCHOBOTON = 40;
	/**
	 * Atributo estático del tipo matriz de enteros.
	 */
	private static final int[][] BOTONES = {{X + 48, Y + 72},
			{X + 48, Y + 146},
			{X + 221, Y + 72},
			{X + 221, Y + 146},
			{X + 394, Y + 72},
			{X + 394, Y + 146}};
	/**
	 * Atributo privado del tipo booleano.
	 */
	private boolean habilitado;
	/**
	 * Atributo privado del tipo personaje.
	 */
	private Personaje personaje;

	/**
	 * Constructor del menu de la batalla.
	 * @param habilitado es del tipo boolean
	 * @param personaje es del tipo personaje
	 */
	public MenuBatalla(final boolean habilitado, final Personaje personaje) {
		this.habilitado = habilitado;
		this.personaje = personaje;
	}

	/**
	 * Metodo para graficar.
	 * @param g del tipo graphics.
	 */
	public void graficar(final Graphics g) {

		if (habilitado) {
			g.drawImage(Recursos.menuBatalla, X, Y, null);
		} else {
			g.drawImage(Recursos.menuBatallaDeshabilitado, X, Y, null);
		}

		// Dibujo los boones
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[0]), BOTONES[0][0], BOTONES[0][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[1]), BOTONES[1][0], BOTONES[1][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[0]), BOTONES[2][0], BOTONES[2][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[1]), BOTONES[3][0], BOTONES[3][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[2]), BOTONES[4][0], BOTONES[4][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get("Ser Energizado"), BOTONES[5][0], BOTONES[5][1], ANCHOBOTON,
				ANCHOBOTON, null);

		// Dibujo las leyendas
		g.setFont(new Font("Book Antiqua", 1, 14));
		g.drawString(personaje.getHabilidadesRaza()[0], X + 95, Y + 94);
		g.drawString(personaje.getHabilidadesRaza()[1], X + 95, Y + 168);
		g.drawString(personaje.getHabilidadesCasta()[0], X + 268, Y + 94);
		g.drawString(personaje.getHabilidadesCasta()[1], X + 268, Y + 168);
		g.drawString(personaje.getHabilidadesCasta()[2], X + 442, Y + 94);
		g.drawString("Ser energizado", X + 442, Y + 168);

		// Dibujo el turno de quien es
		g.setColor(Color.WHITE);
		if (habilitado) {
			Pantalla.centerString(g, new Rectangle(X, Y + 5, Recursos.menuBatalla.getWidth(), 20), "Mi Turno");
		} else {
			Pantalla.centerString(g, new Rectangle(X, Y + 5, Recursos.menuBatalla.getWidth(), 20),
					"Turno Rival");
		}
	}

	/**
	 * Metodo para obtener el click.
	 * @param mouseX del tipo entero para indicar el eje x.
	 * @param mouseY del tipo entero para indicar el eje y.
	 * @return del tipo entero.
	 */
	public int getBotonClickeado(final int mouseX, final int mouseY) {
		if (!habilitado) {
			return 0;
			}
		for (int i = 0; i < BOTONES.length; i++) {
			if (mouseX >= BOTONES[i][0] && mouseX <= BOTONES[i][0] + ANCHOBOTON && mouseY >= BOTONES[i][1]
					&& mouseY <= BOTONES[i][1] + ANCHOBOTON) {
				return i + 1;
				}
		}
		return 0;
	}

	/**
	 * Metodo para el click en el menu.
	 * @param mouseX del tipo entero para indicar el eje x
	 * @param mouseY del tipo entero para indicar el eje y
	 * @return retorna un boolean para indicar si esta habilitado o no.
	 */
	public boolean clickEnMenu(final int mouseX, final int mouseY) {
		if (mouseX >= X && mouseX <= X + Recursos.menuBatalla.getWidth() && mouseY >= Y
				&& mouseY <= Y + Recursos.menuBatalla.getHeight()) {
			return habilitado;
			}
		return false;
	}
/**
 * Metodo para setear si está habilitado o no.
 * @param b del tipo boolean
 */
	public void setHabilitado(final boolean b) {
		habilitado = b;
	}
}
