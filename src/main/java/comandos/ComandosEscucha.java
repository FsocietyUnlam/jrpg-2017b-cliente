package comandos;

import juego.Juego;
import mensajeria.Comando;

/**
 * Clase abstracta ComandosEscucha.
 */
public abstract class ComandosEscucha extends Comando {
    /**
     * juego.
     */
    private Juego juego;
    /**
     * Setter de juego.
     * @param juego envia el juego
     */
    public void setJuego(final Juego juego) {
        this.juego = juego;
    }
    /**
     * Getter de juego.
     * @return juego
     */
    public Juego getJuego() {
        return juego;
    }

}
