package comandos;

import estados.Estado;
import estados.EstadoBatallaNPC;
import mensajeria.PaqueteBatallaNPC;

/**
 * The Class BatallaNPC.
 */
public class BatallaNPC extends ComandosEscucha {

    /*
     * (non-Javadoc)
     *
     * @see mensajeria.Comando#ejecutar()
     */
    @Override
    public void ejecutar() {
        // Recibo el paquete Batalla del servidor y seteo el estado Batalla NPC
        // para que se borren del mapa
        PaqueteBatallaNPC paqueteBatalla = (PaqueteBatallaNPC) getGson()
                .fromJson(getCadenaLeida(), PaqueteBatallaNPC.class);
        juego.getPersonaje().setEstado(Estado.ESTADOBATALLANPC);
        Estado.setEstado(null);
        juego.getEnemigos().get(paqueteBatalla.getIdEnemigo())
                .setEstado(Estado.ESTADOBATALLANPC);
        juego.setEstadoBatallaNPC(new EstadoBatallaNPC(juego, paqueteBatalla));
        Estado.setEstado(juego.getEstadoBatallaNPC());
    }

}
