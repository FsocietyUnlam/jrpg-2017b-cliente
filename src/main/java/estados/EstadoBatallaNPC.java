package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Casta;
import dominio.Enemigo;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.EstadoEnemigo;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.PaqueteBatallaNPC;
import mensajeria.PaqueteEnemigo;
import mensajeria.PaqueteFinalizarBatallaNPC;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

/**
 * The Class EstadoBatallaNPC.
 */
public class EstadoBatallaNPC extends Estado {
	


    /**
     * The Constant ANCHURAALTURA.
     */
    private static final int ANCHURAALTURA = 256;

    /**
     * The mundo.
     */
    private Mundo mundo;

    /**
     * The personaje.
     */
    private Personaje personaje;

    /**
     * The enemigo.
     */
    private Enemigo enemigo;

    /**
     * The pos mouse.
     */
    private int[] posMouse;

    /**
     * The nivel de personaje.
     */
    private int nivelDePersonaje;

    /**
     * The paquete personaje.
     */
    private PaquetePersonaje paquetePersonaje;

    /**
     * The paquete enemigo.
     */
    private PaqueteEnemigo paqueteEnemigo;

    /**
     * The paquete finalizar batalla.
     */
    private PaqueteFinalizarBatallaNPC paqueteFinalizarBatalla;

    /**
     * The mi turno.
     */
    private boolean miTurno;

    /**
     * The hay spell seleccionada.
     */
    private boolean haySpellSeleccionada;

    /**
     * The se realizo accion.
     */
    private boolean seRealizoAccion;

    /**
     * The gson.
     */
    private Gson gson = new Gson();

    /**
     * The miniatura personaje.
     */
    private BufferedImage miniaturaPersonaje;

    /**
     * The miniatura enemigo.
     */
    private BufferedImage miniaturaEnemigo;

    /**
     * The menu batalla.
     */
    private MenuBatalla menuBatalla;

    /**
     * The Constant PUNTOSSKILLSPORNIVEL.
     */
    private static final int PUNTOSSKILLSPORNIVEL = 3;

    /**
     * Instantiates a new estado batalla NPC.
     *
     * @param juego
     *            the juego
     * @param paqueteBatalla
     *            the paquete batalla
     */
    public EstadoBatallaNPC(final Juego juego,
            final PaqueteBatallaNPC paqueteBatalla) {
        super(juego);
        mundo = new Mundo(juego, "recursos/mundoBatalla.txt",
                "recursos/mundoBatallaCapaDos.txt");
        miTurno = paqueteBatalla.isMiTurno();

        paquetePersonaje = juego.getPersonajesConectados()
                .get(paqueteBatalla.getId());
        paqueteEnemigo = juego.getEnemigos().get(paqueteBatalla.getIdEnemigo());

        crearPersonajes();

        menuBatalla = new MenuBatalla(miTurno, personaje);

        miniaturaEnemigo = Recursos.getShrek().get(5)[0];
        miniaturaPersonaje = Recursos.getPersonaje().
                get(personaje.getNombreRaza()).get(5)[0];

        paqueteFinalizarBatalla = new PaqueteFinalizarBatallaNPC();
        paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
        paqueteFinalizarBatalla.setIdEnemigo(paqueteEnemigo.getId());

        // por defecto batalla perdida
        juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
        		 MenuInfoPersonaje.menuPerderBatalla);

        // limpio la accion del mouse
        juego.getHandlerMouse().setNuevoClick(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see estados.Estado#actualizar()
     */
    @Override
    public void actualizar() {
        juego.getCamara().setxOffset(-350);
        juego.getCamara().setyOffset(150);

        seRealizoAccion = false;
        haySpellSeleccionada = false;

        if (miTurno) {

            if (juego.getHandlerMouse().getNuevoClick()) {
                posMouse = juego.getHandlerMouse().getPosMouse();

                if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

                    if (menuBatalla.getBotonClickeado(posMouse[0],
                            posMouse[1]) == 1) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadRaza1(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0],
                            posMouse[1]) == 2) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadRaza2(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0],
                            posMouse[1]) == 3) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta1(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0],
                            posMouse[1]) == 4) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta2(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0],
                            posMouse[1]) == 5) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta3(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0],
                            posMouse[1]) == 6) {
                        seRealizoAccion = true;
                        personaje.serEnergizado(10);
                        haySpellSeleccionada = true;
                    }
                }

                if (haySpellSeleccionada && seRealizoAccion) {
                    if (!enemigo.estaVivo()) {

                        juego.getEstadoJuego().setHaySolicitud(true,
                                juego.getPersonaje(),
                                MenuInfoPersonaje.MENUGANARBATALLA);
                        if (personaje.ganarExperiencia(enemigo.otorgarExp())) {

                            juego.getPersonaje().setNivel(personaje.getNivel());
                            juego.getEstadoJuego().setHaySolicitud(true,
                                    juego.getPersonaje(),
                                    MenuInfoPersonaje.MENUSUBIRNIVEL);
                        }
                        paqueteFinalizarBatalla.setGanadorBatalla(
                                juego.getPersonaje().getId());
                        finalizarBatalla();
                        Estado.setEstado(juego.getEstadoJuego());

                    } else {
                        enemigo.atacar(personaje);

                        if (!personaje.estaVivo()) {
                            juego.getEstadoJuego().setHaySolicitud(true,
                                    juego.getPersonaje(),
                                    MenuInfoPersonaje.menuPerderBatalla);

                            juego.getPersonaje().setEstado(Estado.estadoJuego);

                            paqueteFinalizarBatalla.setGanadorBatalla(juego
                                    .getEnemigos()
                                    .get(paqueteFinalizarBatalla.getIdEnemigo())
                                    .getId());
                            finalizarBatalla();
                            Estado.setEstado(juego.getEstadoJuego());
                        }

                        miTurno = true;
                    }
                } else if (haySpellSeleccionada && !seRealizoAccion) {
                    JOptionPane.showMessageDialog(null,
                            "No posees la energía suficiente"
                                    + "para realizar esta habilidad.");
                }

                juego.getHandlerMouse().setNuevoClick(false);
            }
        }
    }

    /**
     * Finalizar batalla.
     */
    private void finalizarBatalla() {
        try {
            paqueteFinalizarBatalla.setComando(Comando.FINALIZARBATALLANPC);
            juego.getCliente().getSalida()
                    .writeObject(gson.toJson(paqueteFinalizarBatalla));

            paquetePersonaje.setSaludTope(personaje.getSaludTope());
            paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
            paquetePersonaje.setNivel(personaje.getNivel());
            paquetePersonaje.setExperiencia(personaje.getExperiencia());
            paquetePersonaje.setDestreza(personaje.getDestreza());
            paquetePersonaje.setFuerza(personaje.getFuerza());
            paquetePersonaje.setInteligencia(personaje.getInteligencia());
            paquetePersonaje.removerBonus();

            if (paquetePersonaje.getNivel() > this.nivelDePersonaje) {
                paquetePersonaje.actualizarPuntosSkillsDisponibles(
                        PUNTOSSKILLSPORNIVEL);
            }

            paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
            paqueteEnemigo.setComando(Comando.FINALIZARBATALLANPC);

            juego.getCliente().getSalida()
                    .writeObject(gson.toJson(paquetePersonaje));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Fallo la conexión con el servidor");
        }

    }

    /**
     * Crear personajes.
     */
    private void crearPersonajes() {

        /** Se crea mi Personaje en la batalla */
        String nombre = paquetePersonaje.getNombre();
        int salud = paquetePersonaje.getSaludTope();
        int energia = paquetePersonaje.getEnergiaTope();
        int fuerza = paquetePersonaje.getFuerza();
        int destreza = paquetePersonaje.getDestreza();
        int inteligencia = paquetePersonaje.getInteligencia();
        int experiencia = paquetePersonaje.getExperiencia();
        int nivel = paquetePersonaje.getNivel();
        int id = paquetePersonaje.getId();

        nivelDePersonaje = paquetePersonaje.getNivel();

        Casta casta = null;
        try {
            casta = (Casta) Class
                    .forName("dominio" + "." + paquetePersonaje.getCasta())
                    .newInstance();
            personaje = (Personaje) Class
                    .forName("dominio" + "." + paquetePersonaje.getRaza())
                    .getConstructor(String.class, Integer.TYPE, Integer.TYPE,
                            Integer.TYPE, Integer.TYPE, Integer.TYPE,
                            Casta.class, Integer.TYPE, Integer.TYPE,
                            Integer.TYPE)
                    .newInstance(nombre, salud, energia, fuerza, destreza,
                            inteligencia, casta, experiencia, nivel, id);
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la batalla");
        }

        /** Se crea mi Enemigo en la batalla */
        nombre = paqueteEnemigo.getNombre();
        salud = paqueteEnemigo.getSaludTope();
        fuerza = paqueteEnemigo.getFuerza();
        nivel = paqueteEnemigo.getNivel();
        id = paqueteEnemigo.getId();

        enemigo = new Enemigo("Enemigo");
    }

    /*
     * (non-Javadoc)
     *
     * @see estados.Estado#graficar(java.awt.Graphics)
     */
    @Override
    public void graficar(final Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
        mundo.graficar(g);

        g.drawImage(
                Recursos.getPersonaje().get(paquetePersonaje.getRaza()).
                get(3)[0], 0, 175, ANCHURAALTURA, ANCHURAALTURA, null);
        g.drawImage(Recursos.getShrek().get(7)[0], 550, 75, ANCHURAALTURA,
                ANCHURAALTURA, null);

        mundo.graficarObstaculos(g);
        menuBatalla.graficar(g);

        g.setColor(Color.GREEN);

        EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje,
                miniaturaPersonaje);
        EstadoEnemigo.dibujarEstadoDeEnemigo(g, 550, 5, enemigo,
                miniaturaEnemigo);
    }

    /*
     * (non-Javadoc)
     *
     * @see estados.Estado#esEstadoDeJuego()
     */
    @Override
    public boolean esEstadoDeJuego() {
        return false;
    }
    

}
