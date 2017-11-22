package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.google.gson.Gson;

import cliente.Cliente;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import variables.Constantes;

/**
* Clase Michat.
*/
public class MiChat extends JFrame {
    /**
    * variable contentPane.
    */
    private JPanel contentPane;
    /**
    * texto.
    */
    private JTextField texto;
    /**
    * chat.
    */
    private JTextArea chat;
    /**
    * juego.
    */
    private Juego juego;
    /**
    * gson.
    */
    private final Gson gson = new Gson();
    /**
    * background.
    */
    private final JLabel background = new JLabel(new ImageIcon("recursos//background.jpg"));
   /**
    *
   */
    private DefaultCaret caret;

    /**
     * Create the frame.
     * @param juego del tipo Juego
     */
    public MiChat(final Juego juego) {
        this.juego = juego;
        setTitle("Mi Chat");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 11, 414, 201);
        contentPane.add(scrollPane);

        chat = new JTextArea();
        chat.setEditable(false);
        scrollPane.setViewportView(chat);
        caret = (DefaultCaret) chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        texto = new JTextField();
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent e) {
                texto.requestFocus();
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                if (getTitle() == "Sala") {
                    if (Pantalla.ventContac != null) {
                        VentanaContactos.getBotonMc().setEnabled(true);
                    }
                }
                juego.getChatsActivos().remove(getTitle());
            }
        });

        // SI TOCO ENTER
        texto.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	Cliente cliente = juego.getCliente();
            	
            	String mje = "";
        		boolean cheatUsado=true;
        		
        		if(texto.getText().equals("sonria")) {
        			Clip sound = getSound("recursos//sonria.wav");
        			playSound(sound);
        			cheatUsado = false;
        		}else if(texto.getText().equals("bigdaddy")) {
        			if (cliente.getPaquetePersonaje().getDobleFuerza() == 0) {
        				mje= "bigdaddy activado, Será el doble de fuerte en las próximas batallas.";
        				cliente.getPaquetePersonaje().setDobleFuerza(cliente.getPaquetePersonaje().getFuerza());
        				cliente.getPaquetePersonaje().useBonus(0, 0, cliente.getPaquetePersonaje().getFuerza(), 0,0);
        			}else {
        				mje =  "bigdaddy desactivado, tenés la fuerza que tenías antes.";
        				cliente.getPaquetePersonaje().sacarBonus(0, 0, cliente.getPaquetePersonaje().getDobleFuerza(), 0, 0);
        				cliente.getPaquetePersonaje().setDobleFuerza(0);
        			}
        		}else if(texto.getText().equals("tinydaddy")) {
        			if (cliente.getPaquetePersonaje().getMitadFuerza() == 0) {
        				mje= "tinydaddy activado, Será la mitad de fuerte en las próximas batallas.";
        				cliente.getPaquetePersonaje().setMitadFuerza(cliente.getPaquetePersonaje().getFuerza()/2);
        				cliente.getPaquetePersonaje().sacarBonus(0, 0, cliente.getPaquetePersonaje().getMitadFuerza(), 0, 0);
        			}else {
        				mje =  "tinydaddy desactivado, tenés la fuerza que tenías antes.";
        				cliente.getPaquetePersonaje().useBonus(0, 0, cliente.getPaquetePersonaje().getMitadFuerza(), 0,0);
        				cliente.getPaquetePersonaje().setMitadFuerza(0);
        			}
        		}else if(texto.getText().equals("iddqd")) {
        			if (cliente.getPaquetePersonaje().getInvulnerable() == 0) {
        				mje= "iddqd activado, No recibirá daño alguno en las próximas batallas.";
        				cliente.getPaquetePersonaje().setInvulnerable(1);
        			}else {
        				mje =  "iddqd desactivado, ahora sos un simple mortal y podes recibir daño muajaja.";
        				cliente.getPaquetePersonaje().setInvulnerable(0);
        			}
        		}else if(texto.getText().equals("cheta nordelta")) {
        			cliente.getPaquetePersonaje().setNivel(cliente.getPaquetePersonaje().getNivel()+1);
        			cliente.getPaquetePersonaje().removerBonus();
        			cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
        			JOptionPane.showMessageDialog(null, "Cheta de nordelta activado, aumentó su nivel a " + cliente.getPaquetePersonaje().getNivel() + ".");
        		}else if(texto.getText().equals("noclip")) {
        			if (cliente.getPaquetePersonaje().getAtravesarParedes() == 0) {
        				mje= "noclip activado, podrá atravesar las paredes.";
        				cliente.getPaquetePersonaje().setAtravesarParedes(1);
        			}else {
        				mje =  "noclip desactivado, dejara de atravesar las paredes.";
        				cliente.getPaquetePersonaje().setAtravesarParedes(0);
        			}
        		}else if(texto.getText().equals("war aint what it used to be")) {
        			if (cliente.getPaquetePersonaje().getInvisible() == 0) {
        				mje= "war aint what it used to be activado, nadie te podrá ver.";
        				cliente.getPaquetePersonaje().setInvisible(1);
        			}else {
        				mje =  "war aint what it used to be desactivado, sos visible nuevamente.";
        				cliente.getPaquetePersonaje().setInvisible(0);
        			}
        		}else {
        			cheatUsado = false;
        			if (!texto.getText().equals("")) {
                        chat.append("Me: " + texto.getText() + "\n");

                        juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
                        juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
                        juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());

                        // MANDO EL COMANDO PARA QUE ENVIE EL MSJ
                        juego.getCliente().getPaqueteMensaje().setComando(Comando.TALK);
                        // El user receptor en espacio indica que es para todos
                        if (getTitle() == "Sala") {
                            juego.getCliente().getPaqueteMensaje().setUserReceptor(null);
                        }

                        try {
                            juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaqueteMensaje()));
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
                        }
                        texto.setText("");
                    }
        			texto.requestFocus();
        		}
            	
        		if (cheatUsado == true) {
        			cliente.getPaquetePersonaje().removerBonus();
        			cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
        			try {
        				cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
        				JOptionPane.showMessageDialog(null, mje);
        			} catch (IOException e1) {
        				JOptionPane.showMessageDialog(null, "Error al introducir el cheat.");
        			}
        			texto.setText("");
        		}
            	
            	
                /*if (!texto.getText().equals("")) {
                    chat.append("Me: " + texto.getText() + "\n");

                    juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
                    juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
                    juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());

                    // MANDO EL COMANDO PARA QUE ENVIE EL MSJ
                    juego.getCliente().getPaqueteMensaje().setComando(Comando.TALK);
                    // El user receptor en espacio indica que es para todos
                    if (getTitle() == "Sala") {
                        juego.getCliente().getPaqueteMensaje().setUserReceptor(null);
                    }

                    try {
                        juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaqueteMensaje()));
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
                    }
                    texto.setText("");
                }*/
                
            }
        });

        // SI TOCO ENVIAR
        JButton enviar = new JButton("ENVIAR");
        enviar.setIcon(new ImageIcon("recursos//enviarButton.png"));
        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!texto.getText().equals("")) {
                    chat.append("Me: " + texto.getText() + "\n");

                    juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
                    juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
                    juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());

                    // MANDO EL COMANDO PARA QUE ENVIE EL MSJ
                    juego.getCliente().getPaqueteMensaje().setComando(Comando.TALK);
                    // El user receptor en espacio indica que es para todos
                    if (getTitle() == "Sala") {
                        juego.getCliente().getPaqueteMensaje().setUserReceptor(null);
                    }

                    try {
                        juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaqueteMensaje()));
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Error al enviar mensaje");

                    }
                    texto.setText("");
                }
                texto.requestFocus();
            }
        });
        enviar.setBounds(Constantes.MI_CHAT_334, Constantes.MI_CHAT_225, Constantes.MI_CHAT_81, Constantes.MI_CHAT_23);
        contentPane.add(enviar);

        texto.setBounds(Constantes.MI_CHAT_10, Constantes.MI_CHAT_223, Constantes.MI_CHAT_314, Constantes.MI_CHAT_27);
        contentPane.add(texto);
        texto.setColumns(Constantes.MI_CHAT_10);
        background.setBounds(-Constantes.MI_CHAT_20, Constantes.MI_CHAT_0, Constantes.MI_CHAT_480, Constantes.MI_CHAT_283);
        contentPane.add(background);
    }
/**
 * Getter del chat.
 * @return chat
 */
    public JTextArea getChat() {
        return chat;
    }
/**
 * Getter del texto.
 * @return texto
 */
    public JTextField getTexto() {
        return texto;
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
