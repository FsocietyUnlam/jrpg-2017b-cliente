package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * The Class PaqueteDeEnemigos.
 */
@SuppressWarnings("serial")
public class PaqueteDeEnemigos extends Paquete
        implements Serializable, Cloneable {

    /**
     * The enemigos.
     */
    private Map<Integer, PaqueteEnemigo> enemigos;

    /**
     * Instantiates a new paquete de enemigos.
     */
    public PaqueteDeEnemigos() {

    }

    /**
     * Instantiates a new paquete de enemigos.
     *
     * @param enemigosParam
     *            the enemigos
     */
    public PaqueteDeEnemigos(final Map<Integer, PaqueteEnemigo> enemigosParam) {
        this.enemigos = enemigosParam;
    }

    /**
     * Gets the enemigos.
     *
     * @return the enemigos
     */
    public Map<Integer, PaqueteEnemigo> getEnemigos() {
        return enemigos;
    }

    /*
     * (non-Javadoc)
     *
     * @see mensajeria.Paquete#clone()
     */
    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }

}
