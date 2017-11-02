package comandos;

import mensajeria.PaqueteAtacar;

/**
 * Clase Atacar.
 */
public class Atacar extends ComandosEscucha {

    @Override
    public final void ejecutar() {
        PaqueteAtacar paqueteAtacar = (PaqueteAtacar) gson.fromJson(cadenaLeida, PaqueteAtacar.class);
        getJuego().getEstadoBatalla().getEnemigo().actualizarAtributos(paqueteAtacar.getMapPersonaje());
        getJuego().getEstadoBatalla().getPersonaje().actualizarAtributos(paqueteAtacar.getMapEnemigo());
        getJuego().getEstadoBatalla().setMiTurno(true);

    }

}
