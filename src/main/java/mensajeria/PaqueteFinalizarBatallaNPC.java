package mensajeria;

import java.io.Serializable;

/**
 * The Class PaqueteFinalizarBatallaNPC.
 */
@SuppressWarnings("serial")
public class PaqueteFinalizarBatallaNPC extends Paquete
        implements Serializable, Cloneable {

    /**
     * The id.
     */
    private int id;

    /**
     * The id enemigo.
     */
    private int idEnemigo;

    /**
     * The ganador batalla.
     */
    private int ganadorBatalla;

    /**
     * Instantiates a new paquete finalizar batalla NPC.
     */
    public PaqueteFinalizarBatallaNPC() {
        setComando(Comando.FINALIZARBATALLANPC);
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param idParam
     *            the new id
     */
    public void setId(final int idParam) {
        this.id = idParam;
    }

    /**
     * Gets the id enemigo.
     *
     * @return the id enemigo
     */
    public int getIdEnemigo() {
        return idEnemigo;
    }

    /**
     * Sets the id enemigo.
     *
     * @param idEnemigoParam
     *            the new id enemigo
     */
    public void setIdEnemigo(final int idEnemigoParam) {
        this.idEnemigo = idEnemigoParam;
    }

    /**
     * Gets the ganador batalla.
     *
     * @return the ganador batalla
     */
    public int getGanadorBatalla() {
        return ganadorBatalla;
    }

    /**
     * Sets the ganador batalla.
     *
     * @param ganadorBatallaParam
     *            the new ganador batalla
     */
    public void setGanadorBatalla(final int ganadorBatallaParam) {
        this.ganadorBatalla = ganadorBatallaParam;
    }
}
