package comandos;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatallaNPC;

/**
 * The Class FinalizarBatallaNPC.
 */
public class FinalizarBatallaNPC extends ComandosEscucha {

    /*
     * (non-Javadoc)
     *
     * @see mensajeria.Comando#ejecutar()
     */
    @Override
    public void ejecutar() {
        PaqueteFinalizarBatallaNPC paqueteFinalizarBatalla
        = (PaqueteFinalizarBatallaNPC) getGson().fromJson(
                getCadenaLeida(), PaqueteFinalizarBatallaNPC.class);
        juego.getPersonaje().setEstado(Estado.estadoJuego);
        juego.getEnemigos().get(paqueteFinalizarBatalla.getIdEnemigo())
                .setEstado(Estado.estadoJuego);
        juego.actualizarEnemigo();
        Estado.setEstado(juego.getEstadoJuego());

    }

}
