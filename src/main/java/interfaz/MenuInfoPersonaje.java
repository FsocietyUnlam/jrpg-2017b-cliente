package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Clase para el menu de la información del personaje.
 * @author Lucas
 *
 */
public class MenuInfoPersonaje {

	/**
	 * Atributo estatico del tipo entero para .
	 */
	private static final int ANCHOPERSONAJE = 128;
	/**
	 * Atributo estatico del tipo entero para .
	 */
	private static final BufferedImage MENU = Recursos.menuEnemigo;
	/**
	 * Atributo estatico del tipo entero para el valor del menu Batallar.
	 */
	public static final int MENUBATALLAR = 0;
	/**
	 * Atributo estatico del tipo entero para el valor del menu Informacion.
	 */
	public static final int MENUINFORMACION = 1;
	/**
	 * Atributo estatico del tipo entero para el valor del menu SubirNivel.
	 */
	public static final int MENUSUBIRNIVEL = 2;
	/**
	 * Atributo estatico del tipo entero para el valor del menu GanarBatalla.
	 */
	public static final int MENUGANARBATALLA = 3;
	/**
	 * Atributo estatico del tipo entero para el valor del menu PerderBatalla.
	 */
	public static final int MENUPERDERBATALLA = 4;
	/**
	 * Atributo estatico del tipo entero para el valor del menu Ganar item.
	 */
	public static final int MENUGANARITEM = 5;
	/**
	 * Atributo estatico del tipo entero para el valor del menu comerciar.
	 */
	public static final int MENUCOMERCIAR = 6;
	/**
	 * Atributo estatico del tipo String para la leyenda del boton.
	 */
	private static final String[] LEYENDABOTON = {"Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar", "Aceptar", "Comerciar"};

	/**
	 * Atributo privador para indicar el eje x.
	 */
	private int x;
	/**
	 * Atributo privador para indicar el eje y.
	 */
	private int y;
	/**
	 * Atributo privador para indicar el personaje.
	 */
	private PaquetePersonaje personaje;
	/**
	 * Constructor de la clase.
	 * @param x del tipo entero para indicar el eje x.
	 * @param y del tipo entero para indicar el eje y.
	 * @param personaje del tipo paquetePersonaje.
	 */
	public MenuInfoPersonaje(final int x, final int y, final PaquetePersonaje personaje) {
		this.x = x;
		this.y = y;
		this.personaje = personaje;
	}
	/**
	 * Metodo para graficar.
	 * @param g del tipo Graphics.
	 * @param tipoMenu del tipo entero
	 */
	public void graficar(final Graphics g, final int tipoMenu) {

		// dibujo el menu
		g.drawImage(MENU, x, y, null);

		// dibujo el personaje
		g.drawImage(Recursos.personaje.get(personaje.getRaza()).get(6)[0], x + MENU.getWidth() / 2 - ANCHOPERSONAJE / 2,
				y + 70, 128, 128, null);

		// muestro el nombre
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", 1, 20));
		Pantalla.centerString(g, new Rectangle(x, y + 15, MENU.getWidth(), 0), personaje.getNombre());

		// Grafico la leyenda segun el tipo de menu
		switch (tipoMenu) {
		case MENUBATALLAR:
			graficarMenuInformacion(g);
			break;
		case MENUINFORMACION:
			graficarMenuInformacion(g);
			break;
		case MENUSUBIRNIVEL:
			graficarMenuSubirNivel(g);
			break;
		case MENUGANARBATALLA:
			graficarMenuGanarBatalla(g);
			break;
		case MENUPERDERBATALLA:
			graficarMenuPerderBatalla(g);
			break;
		case MENUGANARITEM:
			graficarMenuItem(g);
			break;
		case MENUCOMERCIAR:
			graficarMenuComerciar(g);
			break;
		default:
			break;
		}

		// muestro los botones
		g.setFont(new Font("Book Antiqua", 1, 20));
		g.drawImage(Recursos.botonMenu, x + 50, y + 380, 200, 25, null);
		g.setColor(Color.WHITE);
		Pantalla.centerString(g, new Rectangle(x + 50, y + 380, 200, 25), LEYENDABOTON[tipoMenu]);
	}

	/**
	 * Método para graficar el menu al perder la batalla.
	 * @param g del tipo graphics.
	 */
	private void graficarMenuPerderBatalla(final Graphics g) {

		// Informo que perdio la batalla
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), "¡Has sido derrotado!");

		g.setFont(new Font("Book Antiqua", 0, 14));
		Pantalla.centerString(g, new Rectangle(x, y + 250, MENU.getWidth(), 0), "¡No te rindas! Sigue luchando");
		Pantalla.centerString(g, new Rectangle(x, y + 270, MENU.getWidth(), 0), "contra los demás personajes");
		Pantalla.centerString(g, new Rectangle(x, y + 290, MENU.getWidth(), 0), "para aumentar tu nivel y");
		Pantalla.centerString(g, new Rectangle(x, y + 310, MENU.getWidth(), 0), "mejorar tus atributos.");
	}

	/**
	 * Método para graficar el menu al ganar la batalla.
	 * @param g del tipo graphics.
	 */
	private void graficarMenuGanarBatalla(final Graphics g) {

		// Informo que gano la batalla
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), "¡Has derrotado");
		Pantalla.centerString(g, new Rectangle(x, y + 230, MENU.getWidth(), 0), "a tu enemigo!");

		g.setFont(new Font("Book Antiqua", 0, 14));
		Pantalla.centerString(g, new Rectangle(x, y + 270, MENU.getWidth(), 0), "¡Felicitaciones! Has derrotado");
		Pantalla.centerString(g, new Rectangle(x, y + 290, MENU.getWidth(), 0), "a tu oponente, sigue así");
		Pantalla.centerString(g, new Rectangle(x, y + 310, MENU.getWidth(), 0), "para lograr subir de nivel");
		Pantalla.centerString(g, new Rectangle(x, y + 330, MENU.getWidth(), 0), "y mejorar tus atributos.");

	}

	/**
	 * Método para graficar el menu al subir de nivel.
	 * @param g del tipo graphics.
	 */
	private void graficarMenuSubirNivel(final Graphics g) {

		// Informo que subio de nivel
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), "¡Has subido de nivel!");

		g.setFont(new Font("Book Antiqua", 0, 18));
		Pantalla.centerString(g, new Rectangle(x, y + 240, MENU.getWidth(), 0), "¡Felicitaciones!");
		Pantalla.centerString(g, new Rectangle(x, y + 270, MENU.getWidth(), 0), "Nuevo Nivel");
		g.setFont(new Font("Book Antiqua", 1, 62));
		Pantalla.centerString(g, new Rectangle(x, y + 325, MENU.getWidth(), 0), String.valueOf(personaje.getNivel()));

	}

	/**
	 * Método para graficar el menu de informaciòn.
	 * @param g del tipo graphics.
	 */
	public void graficarMenuInformacion(final Graphics g) {

		// muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), personaje.getRaza());
		g.drawString("Casta: ", x + 30, y + 260);
		g.drawString("Nivel: ", x + 30, y + 290);
		g.drawString("Experiencia: ", x + 30, y + 320);

		// muestro los atributos
		g.setFont(new Font("Book Antiqua", 0, 20));
		g.drawString(personaje.getCasta(), x + 100, y + 260);
		g.drawString(personaje.getNivel() + " ", x + 100, y + 290);
		g.drawString(personaje.getExperiencia() + " / " + Personaje.getTablaDeNiveles()[personaje.getNivel() + 1],
				x + 150, y + 320);

	}

	/**
	 * Método para graficar el menu del item.
	 * @param g del tipo graphics.
	 */
	private void graficarMenuItem(final Graphics g) {

		// Informo que subio de nivel
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), "¡Aca iria algo!");

		g.setFont(new Font("Book Antiqua", 0, 18));
		Pantalla.centerString(g, new Rectangle(x, y + 240, MENU.getWidth(), 0), "¡Aca otra cosa!");
		Pantalla.centerString(g, new Rectangle(x, y + 270, MENU.getWidth(), 0), "Nuevo Nivel");
		g.setFont(new Font("Book Antiqua", 1, 62));
		Pantalla.centerString(g, new Rectangle(x, y + 325, MENU.getWidth(), 0), String.valueOf(personaje.getNivel()));

	}

	/**
	 * Método para graficar el menu de comerciar.
	 * @param g del tipo graphics.
	 */
	private void graficarMenuComerciar(final Graphics g) {

		// muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), personaje.getRaza());
		g.drawString("Casta: ", x + 30, y + 260);
		g.drawString("Nivel: ", x + 30, y + 290);
		g.drawString("Experiencia: ", x + 30, y + 320);

		// muestro los atributos
		g.setFont(new Font("Book Antiqua", 0, 20));
		g.drawString(personaje.getCasta(), x + 100, y + 260);
		g.drawString(personaje.getNivel() + " ", x + 100, y + 290);
		g.drawString(personaje.getExperiencia() + " / " + Personaje.getTablaDeNiveles()[personaje.getNivel() + 1],
				x + 150, y + 320);

	}

	/**
	 * Metodo que retorna si se hizo click en el boton.
	 * @param mouseX del tipo entero para indicar el eje x.
	 * @param mouseY del tipo entero para indicar el eje y.
	 * @return  si es verdadero o falso.
	 */
	public boolean clickEnBoton(final int mouseX, final int mouseY) {
		return mouseX >= x + 50 && mouseX <= x + 250 && mouseY >= y + 380 && mouseY <= y + 405;
	}

	/**
	 * Metodo que retorna si se hizo click en el boton cerrar.
	 * @param mouseX del tipo entero para indicar el eje x.
	 * @param mouseY del tipo entero para indicar el eje y.
	 * @return  si es verdadero o falso.
	 */
	public boolean clickEnCerrar(final int mouseX, final int mouseY) {
		return mouseX >= x + MENU.getWidth() - 24 && mouseX <= x + MENU.getWidth() + 4 && mouseY >= y + 12
				&& mouseY <= y + 36;
	}

	/**
	 * Metodo que retorna si se hizo click en el menú.
	 * @param mouseX del tipo entero para indicar el eje x.
	 * @param mouseY del tipo entero para indicar el eje y.
	 * @return  si es verdadero o falso.
	 */
	public boolean clickEnMenu(final int mouseX, final int mouseY) {
		return mouseX >= x && mouseX <= x + MENU.getWidth() && mouseY >= y && mouseY <= y + MENU.getHeight();
	}
}
