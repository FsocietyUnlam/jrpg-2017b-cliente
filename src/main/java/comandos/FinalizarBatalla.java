package comandos;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

/**
 * Clase FinalizarBatalla.
 */
public class FinalizarBatalla extends ComandosEscucha {

    @Override
    public final void ejecutar() {
        PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson
                .fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);
        getJuego().getPersonaje().setEstado(Estado.getEstadoJuego());
        Estado.setEstado(getJuego().getEstadoJuego());

    }

}
