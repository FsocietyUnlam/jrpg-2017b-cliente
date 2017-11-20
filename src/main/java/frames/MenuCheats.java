package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
//import com.google.gson.Gson;
import cliente.Cliente;
import estados.Estado;
//import dominio.Item;
import juego.Pantalla;
//import mensajeria.Comando;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Clase la cual es el menú de cheats.
 *
 * @author Lucas
 *
 */
public class MenuCheats extends JFrame {
	/**
	 * El buen serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * vector con los puntos totales.
	 */
	private int[] puntosTotales;
	/**
	 * Panel contenedor.
	 */
	private JPanel contentPane;
	/**
	 * boton de confirmar.
	 */
	private final JButton buttonConfirm;
	/**
	 *Create the frame.
	 *@param cliente del tipo cliente
	 */
	public MenuCheats(final Cliente cliente) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 220, 90);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos//1up.png"));
		setTitle("CHEATS");
		setBounds(70, 100, 375, 155);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent arg0) {
				Pantalla.menuCheats = null;
				dispose();
			}
		});
		
		final JLabel lblCodigoSecreto = new JLabel("Introduzca el código ultra secreto:");
		lblCodigoSecreto.setFont(new Font("Fuente",Font.BOLD,22));
		lblCodigoSecreto.setForeground(Color.BLACK);
		lblCodigoSecreto.setBounds(12, 13, 377, 29);
		contentPane.add(lblCodigoSecreto);

		final JTextField tbCodigoSecreto = new JTextField(10);
		tbCodigoSecreto.setForeground(Color.BLACK);
		tbCodigoSecreto.setBounds(42, 48, 277, 29);
		tbCodigoSecreto.addActionListener(new ActionListener(){
            public void actionPerformed(final ActionEvent e){
            	verificarCheats(tbCodigoSecreto.getText(),cliente);
            }});
		contentPane.add(tbCodigoSecreto);
		
		buttonConfirm = new JButton("Confirmar");
		ImageIcon iconoConfirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(iconoConfirm);
		buttonConfirm.setEnabled(true);
		buttonConfirm.addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {
				verificarCheats(tbCodigoSecreto.getText(),cliente);
			}
		});
		buttonConfirm.setBounds(70, 90, 97, 25);
		contentPane.add(buttonConfirm);

		final JButton buttonCancel = new JButton("Cancelar");
		ImageIcon iconoc = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(iconoc);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				Pantalla.menuCheats = null;
				dispose();
			}
		});
		buttonCancel.setBounds(190, 90, 97, 25);
		contentPane.add(buttonCancel);
	}
	
	/**
	 * Verifica el código ingresado en el Textbox.
	 * @param clave
	 * @param cliente
	 */
	void verificarCheats(String clave,final Cliente cliente){
		if(clave.equals("sonria")) {
			Clip sound = getSound("recursos//sonria.wav");
			playSound(sound);
		}else if(clave.equals("puntos libres")) {
			JOptionPane.showMessageDialog(null, "puntos libres activados, podrá asignar puntos libremente.");
			if (Estado.getEstado().esEstadoDeJuego()) {
				if (Pantalla.menuAsignarGodMode == null) {
					Pantalla.menuAsignarGodMode = new MenuAsignarSkillsGodMode(cliente);
					Pantalla.menuAsignarGodMode.setVisible(true);
				}
			}
		}else if(clave.equals("papaman")) {
			JOptionPane.showMessageDialog(null, "PapaMan activado, tendrá la fuerza al máximo.");
			//puntosTotales[0] = cliente.getPaquetePersonaje().getFuerza();
			//Actualizar
		}else if(clave.equals("atr")) {
			JOptionPane.showMessageDialog(null, "ATR activado, tendrá la destreza al máximo.");
			//puntosTotales[1] = cliente.getPaquetePersonaje().getDestreza();
			//Actualizar
		}else if(clave.equals("bochoman")) {
			JOptionPane.showMessageDialog(null, "BochoMan activado, tendrá la inteligencia al máximo.");
			//puntosTotales[2] = cliente.getPaquetePersonaje().getInteligencia();
			//Actualizar
		}else {
			JOptionPane.showMessageDialog(null, "Código incorrecto LTA.");
		}
		Pantalla.menuCheats = null;
		dispose();
	}
	
	/**
	 * Permite obtener un audio a partir de la ruta.
	 * @param file
	 * @return
	 */
	public static Clip getSound(String file)
	{
		try
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file));
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip sound = (Clip)AudioSystem.getLine(info);
			sound.open(audioInputStream);
			return sound;
		}
		catch(Exception e)
		{
			return null;
		}
	}
 
	/**
	 * Permite reproducir un audio.
	 * @param clip
	 */
	public static void playSound(Clip clip)
	{
		clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}
}
