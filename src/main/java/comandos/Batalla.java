package comandos;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.PaqueteBatalla;

/**
 * Clase Batalla.
 */
public class Batalla extends ComandosEscucha {

    @Override
    public final void ejecutar() {

        PaqueteBatalla paqueteBatalla = (PaqueteBatalla) gson.fromJson(cadenaLeida, PaqueteBatalla.class);
        getJuego().getPersonaje().setEstado(Estado.estadoBatalla);
        Estado.setEstado(null);
        getJuego().setEstadoBatalla(new EstadoBatalla(getJuego(), paqueteBatalla));
        Estado.setEstado(getJuego().getEstadoBatalla());

    }

}
