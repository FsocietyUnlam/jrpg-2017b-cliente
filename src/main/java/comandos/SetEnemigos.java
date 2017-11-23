package comandos;

import mensajeria.PaqueteDeEnemigos;

/**
 * The Class SetEnemigos.
 */
public class SetEnemigos extends ComandosEscucha {

    /**
     * The Constant ULTIMOENEMIGO.
     */
    public static final int ULTIMOENEMIGO = -10;

    /* (non-Javadoc)
     * @see mensajeria.Comando#ejecutar()
     */
    @Override
    public void ejecutar() {
        // Primero seteo en el juego los enemigos
        PaqueteDeEnemigos paqueteEnemigos = (PaqueteDeEnemigos) getGson()
                .fromJson(getCadenaLeida(), PaqueteDeEnemigos.class);
        for (int i = -1; i >= ULTIMOENEMIGO; i--) {
            paqueteEnemigos.getEnemigos().get(i).setMapa(
                    juego.getCliente().getPaquetePersonaje().getMapa());
        }
        juego.setEnemigos(paqueteEnemigos.getEnemigos());
    }
}
