package comandos;

import mensajeria.PaqueteDeMovimientos;
/**
 * Clase Movimiento.
 */
public class Movimiento extends ComandosEscucha {

    @Override
    public final void ejecutar() {
        PaqueteDeMovimientos pdm = (PaqueteDeMovimientos) gson.fromJson(cadenaLeida, PaqueteDeMovimientos.class);
        getJuego().setUbicacionPersonajes(pdm.getPersonajes());

    }

}
