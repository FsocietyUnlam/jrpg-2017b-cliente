package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import dominio.Item;
import juego.Pantalla;
import mensajeria.Comando;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase la cual es el menú de habilidades.
 *
 * @author Lucas
 *
 */
public class MenuAsignarSkills extends JFrame {
	/**
	 * El buen serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Cantidad de atributos.
	 */
	private static final int CANTATRIBUTOS = 3;
	/**
	 * vector con los puntos totales.
	 */
	private int[] puntosTotales;
	/**
	 * vector con los puntos asignados en cada atributo.
	 */
	private int[] puntosAsignados;
	/**
	 * vector con los límites mínimos.
	 */
	private int[] puntosLimiteMinimo;
	/**
	 * Puntos no asignados.
	 */
	private int puntosNoAsignados;
	/**
	 * Puntos a asignar por nivel.
	 */
	private int puntosAsignarPorNivel;
	/**
	 * Gson.
	 */
	private final Gson gson = new Gson();
	/**
	 * Panel contenedor.
	 */
	private JPanel contentPane;
	/**
	 * vector con labels que indican los putos de los atributos.
	 */
	private final JLabel[] labelPuntosAtributos;
	/**
	 * vector con botones more.
	 */
	private final JButton[] buttonMore;
	/**
	 * vector con botones minus.
	 */
	private final JButton[] buttonMinus;
	/**
	 * boton de confirmar.
	 */
	private final JButton buttonConfirm;
	/**
	 * label con los puntos.
	 */
	private final JLabel labelPuntos;
	/**
	 * Máxima cantidad de puntos por atributo.
	 */
	private static final int MAXIMACANTPUNTOS = 200;
	/**
	 * Indice del atributo de fuerza.
	 */
	private static final int INDICEATRIBUTOFUERZA = 0;
	/**
	 * Indice del atributo de destreza.
	 */
	private static final int INDICEATRIBUTODESTREZA = 1;
	/**
	 * Indice del atributo de inteligencia.
	 */
	private static final int INDICEATRIBUTOINTELIGENCIA = 2;
	/**
	 * Puntaje de atributo inicial.
	 */
	private static final int PUNTAJEDEATRIBUTOINICIAL = 10;
	/**
	 * Puntaje extra por la casta del personaje.
	 */
	private static final int PUNTAJEDEEXTRAPORCASTA = 5;
	/**
	 *Create the frame.
	 *@param cliente del tipo cliente
	 */
	public MenuAsignarSkills(final Cliente cliente) {

		int[] puntosBonus = new int[CANTATRIBUTOS];
		int[] puntosBase = new int[CANTATRIBUTOS];
		int[] puntosAsignadosInicialmente = new int[CANTATRIBUTOS];
		int i;

		puntosTotales = new int[CANTATRIBUTOS];
		puntosLimiteMinimo = new int[CANTATRIBUTOS];
		puntosAsignados = new int[CANTATRIBUTOS];
		labelPuntosAtributos = new JLabel[CANTATRIBUTOS];
		buttonMinus = new JButton[CANTATRIBUTOS];
		buttonMore = new JButton[CANTATRIBUTOS];

		// OBTENGO LOS PUNTOS ACTUALES
		puntosTotales[INDICEATRIBUTOFUERZA] = cliente.getPaquetePersonaje().getFuerza();
		puntosTotales[INDICEATRIBUTODESTREZA] = cliente.getPaquetePersonaje().getDestreza();
		puntosTotales[INDICEATRIBUTOINTELIGENCIA] = cliente.getPaquetePersonaje().getInteligencia();

		// ACÁ SACO LOS PUNTOS BASE DE LOS ATRIBUTOS QUE CAMBIAN DEPENDIENDO DE LA CASTA.
		String unaCasta = cliente.getPaquetePersonaje().getCasta();
		if (unaCasta.equals("Asesino")) {
			puntosBase[INDICEATRIBUTODESTREZA] = PUNTAJEDEEXTRAPORCASTA;
		} else if (unaCasta.equals("Hechicero")) {
			puntosBase[INDICEATRIBUTOINTELIGENCIA] = PUNTAJEDEEXTRAPORCASTA;
		} else {
			puntosBase[INDICEATRIBUTOFUERZA] = PUNTAJEDEEXTRAPORCASTA; // GUERRERO
		}

		ArrayList<Item> lista = cliente.getPaquetePersonaje().getItems();

		for (Item a : lista) {
			puntosBonus[INDICEATRIBUTOFUERZA] += a.getBonusFuerza();
			puntosBonus[INDICEATRIBUTODESTREZA] += a.getBonusDestreza();
			puntosBonus[INDICEATRIBUTOINTELIGENCIA] += a.getBonusInteligencia();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos//1up.png"));
		setTitle("Asignar");
		setBounds(100, 100, 298, 294);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});

		int posicionlabelPunto = 43;
		for (i = 0; i < CANTATRIBUTOS; i++) {
			puntosBase[i] += PUNTAJEDEATRIBUTOINICIAL;
			puntosLimiteMinimo[i] = puntosBase[i] + puntosBonus[i];
			puntosAsignadosInicialmente[i] = puntosTotales[i] - puntosLimiteMinimo[i];
			labelPuntosAtributos[i] = new JLabel("");
			buttonMinus[i] = new JButton("");
			buttonMore[i] = new JButton("");
			posicionlabelPunto += 58;
			formatearPuntajeAtributos(labelPuntosAtributos[i], posicionlabelPunto, i);
		}

		puntosAsignarPorNivel = ((cliente.getPaquetePersonaje().getNivel() - 1) * 3);
		puntosNoAsignados = puntosAsignarPorNivel
				- (puntosAsignadosInicialmente[INDICEATRIBUTOFUERZA] + puntosAsignadosInicialmente[INDICEATRIBUTODESTREZA] + puntosAsignadosInicialmente[INDICEATRIBUTOINTELIGENCIA]);

		labelPuntos = new JLabel("");
		labelPuntos.setForeground(Color.WHITE);
		labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntos.setBounds(39, 41, 83, 26);
		labelPuntos.setText(String.valueOf(puntosNoAsignados));
		contentPane.add(labelPuntos);

		final JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
		lblCantidadDePuntos.setForeground(Color.WHITE);
		lblCantidadDePuntos.setBounds(12, 13, 177, 29);
		contentPane.add(lblCantidadDePuntos);

		final JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteligencia.setBounds(39, 188, 83, 16);
		contentPane.add(lblInteligencia);

		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestreza.setBounds(50, 130, 56, 16);
		contentPane.add(lblDestreza);

		final JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuerza.setBounds(50, 72, 56, 16);
		contentPane.add(lblFuerza);

		buttonConfirm = new JButton("Confirmar");
		ImageIcon iconoConfirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(iconoConfirm);
		buttonConfirm.setEnabled(false);
		buttonConfirm.addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {
				cliente.getPaquetePersonaje().useBonus(0, 0, puntosAsignados[INDICEATRIBUTOFUERZA], puntosAsignados[INDICEATRIBUTODESTREZA],
						puntosAsignados[INDICEATRIBUTOINTELIGENCIA]);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
				try {

					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar stats");

				}
				JOptionPane.showMessageDialog(null, "Se han actualizado tus atributos.");
				// Esto esta para que se pueda abrir el menú después de haber confirmado
				Pantalla.menuAsignar = null;
				dispose();
			}
		});
		buttonConfirm.setBounds(176, 112, 97, 25);
		contentPane.add(buttonConfirm);

		final JButton buttonCancel = new JButton("Cancelar");
		ImageIcon iconoc = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(iconoc);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});
		buttonCancel.setBounds(176, 146, 97, 25);
		contentPane.add(buttonCancel);

		int ubicacionYBtnMinus = 34;
		ImageIcon icono1 = new ImageIcon("recursos//botonMenoss.png");
		ImageIcon icono2 = new ImageIcon("recursos//botonMass.png");

		// FORMATEAR BOTONES
		for (i = 0; i < CANTATRIBUTOS; i++) {

			// FORMATEAR BOTON MINUS
			buttonMinus[i].setIcon(icono1);
			agregarEventoBotonMenos(i);
			ubicacionYBtnMinus += 58;
			buttonMinus[i].setBounds(12, ubicacionYBtnMinus, 34, 25);
			contentPane.add(buttonMinus[i]);

			// FORMATEAR BOTON MORE
			agregarEventoBotonMas(i);
			buttonMore[i].setIcon(icono2);
			buttonMore[i].setBounds(118, ubicacionYBtnMinus, 34, 25);
			contentPane.add(buttonMore[i]);

			// DESHABILITAR BOTONES DE MINUS CUANDO LA CANTIDAD DE PUNTOS QUE TIENEN ES
			// IGUAL AL LÍMITE MÍNIMO
			if (puntosTotales[i] == puntosLimiteMinimo[i]) {
				buttonMinus[i].setEnabled(false);
			}
			// DESHABILITAR BOTONES DE MORE CUANDO LA CANTIDAD DE PUNTOS QUE TIENEN ES IGUAL
			// AL LÍMITE MÁXIMO
			if (puntosTotales[i] == MAXIMACANTPUNTOS) {
				buttonMore[i].setEnabled(false);
			}
		}

		comprobarPuntosNoAsignados();

		final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg"));
		imageLabel.setBounds(0, 0, 298, 294);
		imageLabel.setVisible(true);
		contentPane.add(imageLabel);

	}

	/**
	 * Formatea los puntos de los atributos.
	 * @param label label del atributo que se quiera formatear.
	 * @param ubicacionLabelAtributos ubicacion relativa del label.
	 * @param i que indica que tipo de atributo es.
	 */
	private void formatearPuntajeAtributos(final JLabel label, final int ubicacionLabelAtributos, final int i) {
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(50, ubicacionLabelAtributos, 56, 16);
		label.setText(String.valueOf(puntosTotales[i]));
		contentPane.add(label);
	}

	/**
	 * Agrega el evento Click de un botón Minus.
	 * @param i indice del botón Minus.
	 */
	private void agregarEventoBotonMenos(final int i) {
		buttonMinus[i].addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				puntosAsignados[i]--;
				puntosNoAsignados++;
				labelPuntos.setText(String.valueOf(puntosNoAsignados));
				labelPuntosAtributos[i].setText(String.valueOf(puntosTotales[i] + puntosAsignados[i]));

				if (puntosAsignados[INDICEATRIBUTOFUERZA] != 0 || puntosAsignados[INDICEATRIBUTODESTREZA] != 0 || puntosAsignados[INDICEATRIBUTOINTELIGENCIA] != 0) {
					buttonConfirm.setEnabled(true);
				} else {
					buttonConfirm.setEnabled(false);
				}

				// DESHABILITAR BOTONES MINUS CUANDO LA CANTIDAD DE PUNTOS NOASIGNADOS SEA IGUAL
				// A LA CANTIDAD DE PUNTOS A ASIGNAR POR NIVEL
				if (puntosNoAsignados == puntosAsignarPorNivel) {
					buttonMinus[INDICEATRIBUTOFUERZA].setEnabled(false);
					buttonMinus[INDICEATRIBUTODESTREZA].setEnabled(false);
					buttonMinus[INDICEATRIBUTOINTELIGENCIA].setEnabled(false);
				} else {
					if ((puntosTotales[i] + puntosAsignados[i]) == puntosLimiteMinimo[i]) {
						buttonMinus[i].setEnabled(false);
					}
				}

				// HABILITAR BOTONES MORE QUE NO ESTEN EN EL LÍMITE MÁXIMO
				int j;
				for (j = 0; j < CANTATRIBUTOS; j++) {
					if ((puntosTotales[j] + puntosAsignados[j]) < MAXIMACANTPUNTOS) {
						buttonMore[j].setEnabled(true);
					}
				}
			}
		});
	}

	/**
	 * Agrega el evento Click de un botón More.
	 * @param i
	 *            indice del botón More
	 */
	private void agregarEventoBotonMas(final int i) {
		buttonMore[i].addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				puntosAsignados[i]++;
				puntosNoAsignados--;
				labelPuntos.setText(String.valueOf(puntosNoAsignados));
				labelPuntosAtributos[i].setText(String.valueOf(puntosTotales[i] + puntosAsignados[i]));

				if (puntosAsignados[INDICEATRIBUTOFUERZA] != 0 || puntosAsignados[INDICEATRIBUTODESTREZA] != 0 || puntosAsignados[INDICEATRIBUTOINTELIGENCIA] != 0) {
					buttonConfirm.setEnabled(true);
				} else {
					buttonConfirm.setEnabled(false);
				}
				if ((puntosTotales[i] + puntosAsignados[i]) == MAXIMACANTPUNTOS) {
					buttonMore[i].setEnabled(false);
				}

				// HABILITAR BOTONES MINUS QUE NO ESTEN EN EL LÍMITE MÍNIMO
				int j;
				for (j = 0; j < CANTATRIBUTOS; j++) {
					if ((puntosTotales[j] + puntosAsignados[j]) > puntosLimiteMinimo[j]) {
						buttonMinus[j].setEnabled(true);
					}
				}
				comprobarPuntosNoAsignados();
			}
		});
	}

	/**
	 * Deshabilita los botones More si es que se asignaron todos los puntos.
	 */
	private void comprobarPuntosNoAsignados() {
		if (puntosNoAsignados == 0) {

			buttonMore[INDICEATRIBUTOFUERZA].setEnabled(false);
			buttonMore[INDICEATRIBUTODESTREZA].setEnabled(false);
			buttonMore[INDICEATRIBUTOINTELIGENCIA].setEnabled(false);
		}
	}

}
