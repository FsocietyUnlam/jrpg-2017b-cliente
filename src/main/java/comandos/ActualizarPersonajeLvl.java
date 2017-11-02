package comandos;

import mensajeria.PaquetePersonaje;

/**
 * Clase Actualizar personaje lvl.
 */
public class ActualizarPersonajeLvl extends ComandosEscucha {

    @Override
    public final void ejecutar() {
        PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class);

        getJuego().getPersonajesConectados().remove(paquetePersonaje.getId());
        getJuego().getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);

        if (getJuego().getPersonaje().getId() == paquetePersonaje.getId()) {
            getJuego().actualizarPersonaje();
            getJuego().getEstadoJuego().actualizarPersonaje();
            getJuego().getCliente().actualizarPersonaje(getJuego()
                    .getPersonajesConectados().get(paquetePersonaje.getId()));
        }

    }

}
