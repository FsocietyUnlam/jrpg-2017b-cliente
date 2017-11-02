package inventario;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import dominio.Item;
import mensajeria.PaquetePersonaje;

/**
 * Clase inventario.
 * @author Lucas
 *
 */
public class Inventario extends JPanel {
	/**
	 * El buen serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo estático para indicar la cantidad de columnas.
	 */
	private static final int CANTCOLUMNAS = 3;
	/**
	 * Atributo estático para indicar la cantidad de filas.
	 */
	private static final int CANTFILAS = 3;
	/**
	 * Una lista de items.
	 */
	private ArrayList<Item> items;

	/**
	 * Constructor de la clase.
	 * @param paquetePersonaje del tipo paquetePersonaje
	 * @throws IOException
	 *             excepcion de entrada/Salida
	 */
	public Inventario(final PaquetePersonaje paquetePersonaje) throws IOException {
		setLayout(new GridBagLayout());
		items = new ArrayList<Item>(paquetePersonaje.getItems());
		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < CANTFILAS; row++) {
			for (int col = 0; col < CANTCOLUMNAS; col++) {
				gbc.gridx = col;
				gbc.gridy = row;
				Celda cellPane;
				if (!items.isEmpty()) {
					cellPane = new Celda(items.get(0), paquetePersonaje);
					items.remove(0);
				} else {
					cellPane = new Celda();
				}

				Border border = null;
				if (row < CANTFILAS - 1) {
					if (col < CANTCOLUMNAS - 1) {
						border = new MatteBorder(1, 1, 0, 0, Color.DARK_GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.DARK_GRAY);
					}
				} else {
					if (col < CANTCOLUMNAS - 1) {
						border = new MatteBorder(1, 1, 1, 0, Color.DARK_GRAY);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.DARK_GRAY);
					}
				}
				cellPane.setBorder(border);
				gbc.weighty = 1.0;
				gbc.weightx = gbc.weighty;
				gbc.fill = GridBagConstraints.BOTH;

				add(cellPane, gbc);
			}
		}
	}
}
