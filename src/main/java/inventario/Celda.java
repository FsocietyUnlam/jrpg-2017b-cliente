package inventario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Item;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Clase celda.
 * @author Lucas
 *
 */
public class Celda extends JPanel {

	/**
	 * El buen serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo privado que simboliza al item.
	 */
	private BufferedImage item;
	/**
	 * Atributo privado que es del tipo paquetePersonaje.
	 */
	private PaquetePersonaje paquetePersonaje;
	/**
	 * Atributo privado que es del tipo label.
	 */
	private JLabel label;
	/**
	 * Atributo privado que es del tipo item.
	 */
	private Item it;

	/**
	 * Constructor de la clase.
	 * @param item es del tipo item
	 * @param paquetePersonaje es del tipo paquetePersonaje
	 * @throws IOException una excepción de entrada/Salida
	 */
	public Celda(final Item item, final PaquetePersonaje paquetePersonaje) throws IOException {
		this.item = item.getFoto();
		it = item;
		this.paquetePersonaje = paquetePersonaje;
		label = new JLabel(new ImageIcon(this.item.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
		actionListenersYLabel(item);
	}

	/**
	 * Método que crea un label y lo agrega a la celda.
	 */
	public Celda() {
		label = new JLabel(new ImageIcon(Recursos.noItem.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
		add(label);
	}

	/**
	 * Método que añade un tooltip al label.
	 * @param item es del tipo item.
	 */
	private void actionListenersYLabel(final Item item) {
		StringBuilder s = new StringBuilder();

		s.append("<html>" + item.getNombre() + "<br>");

		if (item.getBonusSalud() != 0) {
			s.append("+" + item.getBonusSalud() + " Salud " + "<br>");
		}
		if (item.getBonusEnergia() != 0) {
			s.append("+" + item.getBonusEnergia() + " Energia " + "<br>");
		}
		if (item.getBonusFuerza() != 0) {
			s.append("+" + item.getBonusFuerza() + " Fuerza " + "<br>");
		}
		if (item.getBonusDestreza() != 0) {
			s.append("+" + item.getBonusDestreza() + " Destreza " + "<br>");
		}
		if (item.getBonusInteligencia() != 0) {
			s.append("+" + item.getBonusInteligencia() + " Inteligencia");
		}
		s.append("</html>");
		label.setToolTipText(s.toString());

		label.addMouseListener(mouseListener);

		addMouseListener(mouseListener);

		add(label);
		this.validate();
		this.repaint();

	}

	/**
	 * Reinicia las caracteristicas del label.
	 */
	protected void resetLabel() {
		label.setIcon(new ImageIcon(Recursos.noItem.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
		label.setToolTipText(null);
		paquetePersonaje.removerItem(it);
		label.removeMouseListener(mouseListener);
		removeMouseListener(mouseListener);
	}

	/**
	 * Método que obtiene una dimensión ya establecida.
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(60, 60);
	}
	/**
	 * Método para obtener el label.
	 * @return el label
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * Método del evento click del mouse.
	 */
	private MouseListener mouseListener =  new MouseAdapter() {
		public void mouseClicked(final MouseEvent e) {
			Object[] options = {"Tirar", "Cancelar" };
			if (e.getClickCount() == 2) {
				int answer = JOptionPane.showOptionDialog(getParent(), "¿Qué desea hacer?", "Item: " + it.getNombre(),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
				// Tirar
				if (answer == 0) {
					paquetePersonaje.sacarBonus(it.getBonusSalud(), it.getBonusEnergia(), it.getBonusFuerza(),
							it.getBonusDestreza(), it.getBonusInteligencia());
					resetLabel();
				}
			}
		}
	};
}
