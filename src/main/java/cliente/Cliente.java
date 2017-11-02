package cliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import comandos.ComandosCliente;
import frames.MenuCarga;
import frames.MenuComerciar;
import frames.MenuJugar;
import frames.MenuMapas;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteComerciar;
import mensajeria.PaqueteMensaje;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

/**
 * La clase Cliente tiene como función ejecutar el cliente.
 */
public class Cliente extends Thread {
    /**
    * variable cliente.
    */
    private Socket cliente;
    /**
     * miIp.
     */
    private String miIp;
    /**
     * entrada.
     */
    private ObjectInputStream entrada;
    /**
     * salida.
     */
    private ObjectOutputStream salida;

    /**
     * gson.
     */
    private final Gson gson = new Gson();

    /**
     * Paquete usuario.
     */
    private PaqueteUsuario paqueteUsuario;
    /**
     * Paquete personaje.
     */
    private PaquetePersonaje paquetePersonaje;
    /**
     * paquete comercio.
     */
    private PaqueteComerciar paqueteComercio;
    /**
     * paquete mensaje.
     */
    private PaqueteMensaje paqueteMensaje = new PaqueteMensaje();

    /**
     * Acciones que realiza el usuario.
     */
    private int accion;

    /**
     * menu comerciar m1.
     */
    private MenuComerciar m1;

    /**
     * ip.
     */
    private String ip;
    /**
     * puerto.
     */
    private int puerto;
    // private final int puerto = 55050;
    /**
     * Pide la accion.
     * @return Devuelve la accion
     */
    public int getAccion() {
        return accion;
    }

    /**
     * Setea la accion.
     * @param accion envia la accion
     */
    public void setAccion(final int accion) {
        this.accion = accion;
    }
    /**
     * vaiable wome del tipo Juego.
     */
    private Juego wome;
    /**
     * menu carga.
     */
    private MenuCarga menuCarga;

    /**
     * Constructor del Cliente.
     */
    public Cliente() {
        ip = JOptionPane.showInputDialog("Ingrese IP del servidor: (default localhost)");
        if (ip == null) {
            ip = "localhost";
        }
        try {
            puerto = getPortFromConfigFile();

            cliente = new Socket(ip, puerto);
            miIp = cliente.getInetAddress().getHostAddress();
            entrada = new ObjectInputStream(cliente.getInputStream());
            salida = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo al iniciar la aplicación. "
                       + "Revise la conexión con el servidor.");
            System.exit(1);
        }
    }
/**
 * metodo Cliente.
 * @param ip envia el ip
 * @param puerto envia el ip
 */
    public Cliente(final String ip, final int puerto) {
        try {
            cliente = new Socket(ip, puerto);
            miIp = cliente.getInetAddress().getHostAddress();
            entrada = new ObjectInputStream(cliente.getInputStream());
            salida = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo al iniciar la aplicación. "
                       + "Revise la conexión con el servidor.");
            System.exit(1);
        }
    }

    @Override
    public final void run() {
        synchronized (this) {
            try {
                ComandosCliente comand;
                // Creo el paquete que le voy a enviar al servidor
                paqueteUsuario = new PaqueteUsuario();
                MenuJugar menuJugar = null;
                while (!paqueteUsuario.isInicioSesion()) {

                    // Muestro el menú principal
                    if (menuJugar == null) {
                        menuJugar = new MenuJugar(this);
                        menuJugar.setVisible(true);

                        // Creo los paquetes que le voy a enviar al servidor
                        paqueteUsuario = new PaqueteUsuario();
                        paquetePersonaje = new PaquetePersonaje();

                        // Espero a que el usuario seleccione alguna accion
                        wait();

                        comand = (ComandosCliente) Paquete.getObjetoSet(Comando.NOMBREPAQUETE, getAccion());
                        comand.setCadena(null);
                        comand.setCliente(this);
                        comand.ejecutar();

                        // Le envio el paquete al servidor
                        salida.writeObject(gson.toJson(paqueteUsuario));
                    }
                    // Recibo el paquete desde el servidor
                    String cadenaLeida = (String) entrada.readObject();
                    Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);

                    comand = (ComandosCliente) paquete.getObjeto(Comando.NOMBREPAQUETE);
                    comand.setCadena(cadenaLeida);
                    comand.setCliente(this);
                    comand.ejecutar();
                }

                // Creo un paquete con el comando mostrar mapas
                paquetePersonaje.setComando(Comando.MOSTRARMAPAS);

                // Abro el menu de eleccion del mapa
                MenuMapas menuElegirMapa = new MenuMapas(this);
                menuElegirMapa.setVisible(true);

                // Espero a que el usuario elija el mapa
                wait();

                // Si clickeo en la Cruz al Seleccionar mapas
                if (paquetePersonaje.getMapa() == 0) {
                    paquetePersonaje.setComando(Comando.DESCONECTAR);
                    salida.writeObject(gson.toJson(paquetePersonaje));
                } else {
                    // Establezco el mapa en el paquete personaje
                    paquetePersonaje.setIp(miIp);

                    // Le envio el paquete con el mapa seleccionado
                    salida.writeObject(gson.toJson(paquetePersonaje));

                    // Instancio el juego y cargo los recursos
                    wome = new Juego("World Of the Middle Earth", 800, 600, this, paquetePersonaje);

                    // Muestro el menu de carga
                    menuCarga = new MenuCarga(this);
                    menuCarga.setVisible(true);

                    // Espero que se carguen todos los recursos
                    wait();

                    // Inicio el juego
                    wome.start();

                    // Finalizo el menu de carga
                    menuCarga.dispose();
                }
            } catch (IOException | InterruptedException | ClassNotFoundException
                    | InstantiationException | IllegalAccessException e) {
                JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor durante el inicio de sesión.");
                System.exit(1);
            }
        }

    }
    /**
     * Lee el puerto desde el archivo config.properties.
     * @return devuelve el puerto
     * @throws FileNotFoundException excepcion
     * @throws IOException excepcion
     */
    private int getPortFromConfigFile() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        return Integer.parseInt(prop.getProperty("puerto"));
    }

    /**
     * Pide el cliente.
     *
     * @return Devuelve el cliente
     */
    public Socket getSocket() {
        return cliente;
    }

    /**
     * Setea el cliente.
     *
     * @param cliente
     *            cliente a setear
     */
    public void setSocket(final Socket cliente) {
        this.cliente = cliente;
    }

    /**
     * Pide la ip.
     *
     * @return Devuelve la ip
     */
    public String getMiIp() {
        return miIp;
    }

    /**
     * Setea la ip.
     *
     * @param miIp
     *            ip a setear
     */
    public void setMiIp(final String miIp) {
        this.miIp = miIp;
    }

    /**
     * Pide la entrada.
     *
     * @return Devuelve la entrada
     */
    public ObjectInputStream getEntrada() {
        return entrada;
    }

    /**
     * Setea la entrada.
     *
     * @param entrada
     *            entrada a setear
     */
    public void setEntrada(final ObjectInputStream entrada) {
        this.entrada = entrada;
    }

    /**
     * Pide la salida.
     *
     * @return Devuelve la salida
     */
    public ObjectOutputStream getSalida() {
        return salida;
    }

    /**
     * Setea la salida.
     *
     * @param salida
     *            salida a setear
     */
    public void setSalida(final ObjectOutputStream salida) {
        this.salida = salida;
    }

    /**
     * Pide el paquete usuario.
     *
     * @return Devuelve el paquete usuario
     */
    public PaqueteUsuario getPaqueteUsuario() {
        return paqueteUsuario;
    }

    /**
     * Pide el paquete personaje.
     *
     * @return Devuelve el paquete personaje
     */
    public PaquetePersonaje getPaquetePersonaje() {
        return paquetePersonaje;
    }

    /**
     * Pide el juego.
     *
     * @return Devuelve el juego
     */
    public Juego getJuego() {
        return wome;
    }

    /**
     * Pide el menu de carga.
     *
     * @return Devuelve el menu de carga
     */
    public MenuCarga getMenuCarga() {
        return menuCarga;
    }
/**
 * metodo actualizarItems.
 * @param paqueteActualizado envia el paquete actualizado
 */
    public void actualizarItems(final PaquetePersonaje paqueteActualizado) {
        if (paquetePersonaje.getCantItems() != 0
                && paquetePersonaje.getCantItems() != paqueteActualizado.getCantItems()) {
            paquetePersonaje.anadirItem(paqueteActualizado.getItems().get(paqueteActualizado.getItems().size() - 1));
        }
    }
    /**
     * Getter del ip.
     * @return ip
     */
    public String getIp() {
        return ip;
    }
    /**
     * metodo actualizar personaje.
     * @param pP envia parametro pP
     */
    public void actualizarPersonaje(final PaquetePersonaje pP) {
        paquetePersonaje = pP;
    }
    /**
     * Getter de wome.
     * @return wome
     */
    public Juego getWome() {
        return wome;
    }
    /**
     * Setter de wome.
     * @param wome envia wome
     */
    public void setWome(final Juego wome) {
        this.wome = wome;
    }
    /**
     * Getter del puerto.
     * @return puerto retorna el puerto
     */
    public int getPuerto() {
        return puerto;
    }
    /**
     * Setter del paquete de usuario.
     * @param paqueteUsuario envia el paquete de usuario
     */
    public void setPaqueteUsuario(final PaqueteUsuario paqueteUsuario) {
        this.paqueteUsuario = paqueteUsuario;
    }
    /**
     * Setter del paquete del personaje.
     * @param paquetePersonaje envia el paquete del personaje
     */
    public void setPaquetePersonaje(final PaquetePersonaje paquetePersonaje) {
        this.paquetePersonaje = paquetePersonaje;
    }
    /**
     * Setter del ip.
     * @param ip envia el ip
     */
    public void setIp(final String ip) {
        this.ip = ip;
    }
    /**
     * Setter del menu carga.
     * @param menuCarga envia el menu carga
     */
    public void setMenuCarga(final MenuCarga menuCarga) {
        this.menuCarga = menuCarga;
    }
    /**
     * metodo menu comerciar.
     * @return m1
     */
    public MenuComerciar getM1() {
        return m1;
    }
    /**
     * Setter de m1.
     * @param m1 envia m1 por parametro
     */
    public void setM1(final MenuComerciar m1) {
        this.m1 = m1;
    }
    /**
     * Getter del paquete de comercio.
     * @return paqueteComercio
     */
    public PaqueteComerciar getPaqueteComercio() {
        return paqueteComercio;
    }
    /**
     * Setter del paquete de comercio.
     * @param paqueteComercio envia el paquete de comercio
     */
    public void setPaqueteComercio(final PaqueteComerciar paqueteComercio) {
        this.paqueteComercio = paqueteComercio;
    }
    /**
     * Getter del paquete de mensaje.
     * @return paqueteMensaje
     */
    public PaqueteMensaje getPaqueteMensaje() {
        return paqueteMensaje;
    }
    /**
     * Setter del paquete de mensaje.
     * @param paqueteMensaje envia el paquete de mensaje
     */
    public void setPaqueteMensaje(final PaqueteMensaje paqueteMensaje) {
        this.paqueteMensaje = paqueteMensaje;
    }
}
