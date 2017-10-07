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

public class MenuAsignarSkills extends JFrame {

	private JPanel contentPane;
	private int puntosAsignarInicial;
	private int puntosFuerzaBase;
	private int puntosDestrezaBase;
	private int puntosInteligenciaBase;
	private int puntosAsignar;//puntosAsignarInicial;
	private int puntosFuerzaTotal;
	private int puntosDestrezaTotal;
	private int puntosInteligenciaTotal;
	private int puntosAsignadosFuerza=0;
	private int puntosAsignadosDestreza=0;
	private int puntosAsignadosInteligencia=0;
	
	private final Gson gson = new Gson();

	/**
	 * Create the frame.
	 */
	public MenuAsignarSkills(final Cliente cliente) {
		ArrayList<Item> lista = cliente.getPaquetePersonaje().getItems();
		int puntosBonusFuerza=0;
		int puntosBonusInteligencia=0;
		int puntosBonusDestreza=0;
		
		for(Item a:lista) {
			puntosBonusFuerza += a.getBonusFuerza();
			puntosBonusInteligencia += a.getBonusInteligencia();
			puntosBonusDestreza += a.getBonusDestreza();
		}
		
		cliente.getPaquetePersonaje().removerBonus();
		puntosFuerzaBase = cliente.getPaquetePersonaje().getFuerza();
		puntosDestrezaBase = cliente.getPaquetePersonaje().getDestreza();
		puntosInteligenciaBase = cliente.getPaquetePersonaje().getInteligencia();
		
		cliente.getPaquetePersonaje().ponerBonus();
		puntosFuerzaTotal = cliente.getPaquetePersonaje().getFuerza();
		puntosDestrezaTotal = cliente.getPaquetePersonaje().getDestreza();
		puntosInteligenciaTotal = cliente.getPaquetePersonaje().getInteligencia();
		
		int puntosAsignadosFuerzaInicialmente = puntosFuerzaTotal -(puntosFuerzaBase + puntosBonusFuerza);
		int puntosAsignadosDestrezaInicialmente = puntosDestrezaTotal -(puntosDestrezaBase + puntosBonusDestreza);
		int puntosAsignadosInteligenciaInicialmente = puntosInteligenciaTotal -(puntosInteligenciaBase + puntosBonusInteligencia);
		
		int puntosAsignarPorNivel = ((cliente.getPaquetePersonaje().getNivel()-1) * 3);
		
		puntosAsignar = puntosAsignarPorNivel - (puntosAsignadosFuerzaInicialmente + puntosAsignadosDestrezaInicialmente + puntosAsignadosInteligenciaInicialmente);
		
				
		//puntosAsignar = puntosAsignarInicial;
		
		
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
			public void windowClosing(WindowEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});
		
		final JLabel labelFuerza = new JLabel("");
		labelFuerza.setForeground(Color.WHITE);
		labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		labelFuerza.setBounds(50, 101, 56, 16);
		labelFuerza.setText(String.valueOf(puntosFuerzaTotal));
		contentPane.add(labelFuerza);
		
		final JLabel labelDestreza = new JLabel("");
		labelDestreza.setForeground(Color.WHITE);
		labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		labelDestreza.setBounds(50, 159, 56, 16);
		labelDestreza.setText(String.valueOf(puntosDestrezaTotal));
		contentPane.add(labelDestreza);
		
		final JLabel labelInteligencia = new JLabel("");
		labelInteligencia.setForeground(Color.WHITE);
		labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		labelInteligencia.setBounds(50, 217, 56, 16);
		labelInteligencia.setText(String.valueOf(puntosInteligenciaTotal));
		contentPane.add(labelInteligencia);
		
		final JLabel labelPuntos = new JLabel("");
		labelPuntos.setForeground(Color.WHITE);
		labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntos.setBounds(39, 41, 83, 26);
		labelPuntos.setText(String.valueOf(puntosAsignar));
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
		
		final JButton buttonConfirm = new JButton("Confirmar");
		ImageIcon icono_confirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(icono_confirm);
		buttonConfirm.setEnabled(false);
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				puntosAsignarInicial = puntosAsignar;//Para que está?
				int bonusF = puntosAsignadosFuerza;
				int bonusD = puntosAsignadosDestreza;
				int bonusI = puntosAsignadosInteligencia;
				cliente.getPaquetePersonaje().useBonus(0, 0, bonusF, bonusD, bonusI);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
				try {
					
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar stats");

				}
				JOptionPane.showMessageDialog(null,"Se han actualizado tus atributos.");
				dispose();
			}
		});
		buttonConfirm.setBounds(176, 112, 97, 25);
		contentPane.add(buttonConfirm);
		
		final JButton buttonCancel = new JButton("Cancelar");
		ImageIcon icono_c = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(icono_c);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});
		buttonCancel.setBounds(176, 146, 97, 25);
		contentPane.add(buttonCancel);
		
		final JButton buttonMinus = new JButton("");
		final JButton buttonMinus1 = new JButton("");
		final JButton buttonMinus2 = new JButton("");
		final JButton buttonMore = new JButton("");
		final JButton buttonMore1 = new JButton("");
		final JButton buttonMore2 = new JButton("");
		buttonMinus.setEnabled(false);
		buttonMinus1.setEnabled(false);
		buttonMinus2.setEnabled(false);
		
		ImageIcon icono_1 = new ImageIcon("recursos//botonMenoss.png");
		buttonMinus.setIcon(icono_1);
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((puntosAsignadosFuerzaInicialmente + puntosAsignadosFuerza) > 0) {
					puntosAsignadosFuerza--;
					puntosAsignar++;
					/*if(puntosAsignar == 0){
						if(puntosInteligenciaTotal != 200){
							buttonMore2.setEnabled(true);
						}
						if(puntosDestrezaTotal != 200){
							buttonMore1.setEnabled(true);
						}
					} else {
							buttonMore.setEnabled(true);
							buttonMore1.setEnabled(true);
							buttonMore2.setEnabled(true);
					}*/

					if(comprobarCambiosRealizados()) {
						buttonConfirm.setEnabled(false);
					}else {
							buttonConfirm.setEnabled(true);
					}
					
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerzaTotal + puntosAsignadosFuerza));
					if((puntosAsignadosFuerzaInicialmente + puntosAsignadosFuerza)==0){
						buttonMinus.setEnabled(false);
					}
						buttonMore.setEnabled(true);
						if(!labelDestreza.getText().equals("200")) {
							buttonMore1.setEnabled(true);
						}
						if(!labelInteligencia.getText().equals("200")) {
							buttonMore2.setEnabled(true);
						}
				}
			}
		});
		buttonMinus.setBounds(12, 92, 34, 25);
		contentPane.add(buttonMinus);
		
		buttonMinus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if((puntosAsignadosDestrezaInicialmente + puntosAsignadosDestreza) > 0) {
					puntosAsignadosDestreza--;
					puntosAsignar++;

					/*if(puntosAsignar == 0){

						if(puntosInteligenciaTotal != 200){
							buttonMore2.setEnabled(true);
						}
						if(puntosFuerzaTotal != 200){
							buttonMore.setEnabled(true);
						}
					} else {
							buttonMore.setEnabled(true);
							buttonMore1.setEnabled(true);
							buttonMore2.setEnabled(true);
					}*/
					if(comprobarCambiosRealizados()) {
						buttonConfirm.setEnabled(false);
					}else {
							buttonConfirm.setEnabled(true);
					}

					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestrezaTotal + puntosAsignadosDestreza));
					if((puntosAsignadosDestrezaInicialmente + puntosAsignadosDestreza)==0){
						buttonMinus1.setEnabled(false);
					}
						buttonMore1.setEnabled(true);

						if(!labelFuerza.getText().equals("200")) {
							buttonMore.setEnabled(true);
						}
						if(!labelInteligencia.getText().equals("200")) {
							buttonMore2.setEnabled(true);
						}
					
					/*if(puntosDestrezaTotal == puntosDestrezaBase){
						buttonMinus1.setEnabled(false);
						buttonMore1.setEnabled(true);
					} else if(puntosDestrezaTotal >= puntosDestrezaBase) {
						buttonMore1.setEnabled(true);
					}*/
				}
			}
		});
		buttonMinus1.setIcon(icono_1);
		buttonMinus1.setBounds(12, 159, 34, 25);
		contentPane.add(buttonMinus1);
		
		buttonMinus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((puntosAsignadosInteligenciaInicialmente + puntosAsignadosInteligencia) > 0) {
					puntosAsignadosInteligencia--;
					puntosAsignar++;
				
					/*if(puntosAsignar == 0){
						if(puntosFuerzaTotal != 200){
							buttonMore.setEnabled(true);
						}
						if(puntosDestrezaTotal != 200){
							buttonMore1.setEnabled(true);
						}
					} else {
							buttonMore.setEnabled(true);
							buttonMore1.setEnabled(true);
							buttonMore2.setEnabled(true);
					}*/
					if(comprobarCambiosRealizados()) {
						buttonConfirm.setEnabled(false);
					}else {
							buttonConfirm.setEnabled(true);
					}

					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligenciaTotal + puntosAsignadosInteligencia));
					if((puntosAsignadosInteligenciaInicialmente + puntosAsignadosInteligencia)==0){
						buttonMinus2.setEnabled(false);
					}
						buttonMore2.setEnabled(true);

						if(!labelFuerza.getText().equals("200")) {
							buttonMore.setEnabled(true);
						}
						if(!labelDestreza.getText().equals("200")) {
							buttonMore1.setEnabled(true);
						}
					/*labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligenciaTotal + puntosAsignadosInteligencia));
					if(puntosInteligenciaTotal == puntosInteligenciaBase){
						buttonMinus2.setEnabled(false);
						buttonMore2.setEnabled(true);
					} else if(puntosInteligenciaTotal >= puntosInteligenciaBase) {
						buttonMore2.setEnabled(true);
					}*/
				}
			}
		});
		buttonMinus2.setIcon(icono_1);
		buttonMinus2.setBounds(12, 217, 34, 25);
		contentPane.add(buttonMinus2);
		
		buttonMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosAsignar != 0 && !labelFuerza.getText().equals("200")){
					puntosAsignadosFuerza++;
					puntosAsignar--;
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerzaTotal + puntosAsignadosFuerza));
									
					if(comprobarCambiosRealizados()) {
						buttonConfirm.setEnabled(false);
					}else {
							buttonConfirm.setEnabled(true);
					}
					

					if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
					}else if(labelFuerza.getText().equals("200")){
						buttonMore.setEnabled(false);
					}
					buttonMinus.setEnabled(true);
				}

			}
		});
		ImageIcon icono_2 = new ImageIcon("recursos//botonMass.png");
		buttonMore.setIcon(icono_2);
		buttonMore.setBounds(118, 92, 34, 25);
		contentPane.add(buttonMore);
		
		
		buttonMore1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosAsignar != 0 && !labelDestreza.getText().equals("200")){

					puntosAsignadosDestreza++;

					puntosAsignar--;
					if(comprobarCambiosRealizados()) {
						buttonConfirm.setEnabled(false);
					}else {
							buttonConfirm.setEnabled(true);
					}
					
					labelPuntos.setText(String.valueOf(puntosAsignar));

					labelDestreza.setText(String.valueOf(puntosDestrezaTotal + puntosAsignadosDestreza));


					if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
					}else if(labelDestreza.getText().equals("200")){
						buttonMore1.setEnabled(false);
					}
					buttonMinus1.setEnabled(true);
				}
			}
		});
		buttonMore1.setIcon(icono_2);
		buttonMore1.setBounds(118, 159, 34, 25);
		contentPane.add(buttonMore1);
		
		buttonMore2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosAsignar != 0 && !labelInteligencia.getText().equals("200")){

					puntosAsignadosInteligencia++;

					puntosAsignar--;
					if(comprobarCambiosRealizados()) {
						buttonConfirm.setEnabled(false);
					}else {
							buttonConfirm.setEnabled(true);
					}
					
					labelPuntos.setText(String.valueOf(puntosAsignar));

					labelInteligencia.setText(String.valueOf(puntosInteligenciaTotal+puntosAsignadosInteligencia));					


					if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
					}else if(labelInteligencia.getText().equals("200")){
						buttonMore2.setEnabled(false);
					}
					buttonMinus2.setEnabled(true);
				}
			}
		});
		buttonMore2.setIcon(icono_2);
		buttonMore2.setBounds(118, 217, 34, 25);
		contentPane.add(buttonMore2);
		
		final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg")); 
		imageLabel.setBounds(0, 0, 298, 294);
		imageLabel.setVisible(true);
		contentPane.add(imageLabel);
	}
	
	private boolean comprobarCambiosRealizados() {
		if(this.puntosAsignadosFuerza != 0 || this.puntosAsignadosDestreza != 0 || this.puntosAsignadosInteligencia != 0) {
			return false;
		}
		return true;
	}
	
}
