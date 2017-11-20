package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import comandos.ComandosEscucha;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteEnemigo;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;

/**
 * La clase EscuchaMensajes tiene como función esuchar los mensajes que se
 * enviaran al servidor.
 */

public class EscuchaMensajes extends Thread {
    /**
     * juego.
     */
    private Juego juego;
    /**
     * cliente.
     */
    private Cliente cliente;
    /**
     * entrada.
     */
    private ObjectInputStream entrada;
    /**
     * gson.
     */
    private final Gson gson = new Gson();

    // private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
    // private Map<Integer, PaquetePersonaje> personajesConectados;
    /**
     * Constructor de EsuchaMensaje.
     *
     * @param juego
     *            juego del que se escucha el mensaje
     */
    public EscuchaMensajes(final Juego juego) {
        this.juego = juego;
        cliente = juego.getCliente();
        entrada = cliente.getEntrada();
    }

    @Override
    public final void run() {

        try {

            Paquete paquete;

            ComandosEscucha comand;
            juego.setPersonajesConectados(new HashMap<Integer, PaquetePersonaje>());
            juego.setUbicacionPersonajes(new HashMap<Integer, PaqueteMovimiento>());
            juego.setEnemigos(new HashMap<Integer, PaqueteEnemigo>());

            while (true) {

                String objetoLeido = (String) entrada.readObject();

                paquete = gson.fromJson(objetoLeido, Paquete.class);
                comand = (ComandosEscucha) paquete.getObjeto(Comando.NOMBREPAQUETE);
                System.out.println(comand.getClass());
                comand.setJuego(juego);
                comand.setCadena(objetoLeido);
                comand.ejecutar();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor.");
        }
    }

}