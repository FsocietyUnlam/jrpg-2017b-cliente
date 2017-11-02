package comandos;

import mensajeria.Comando;

/**
 * Clase InicioSesionSet.
 */
public class InicioSesionSet extends ComandosCliente {

    @Override
    public final void ejecutar() {
        getCliente().getPaqueteUsuario().setComando(Comando.INICIOSESION);
    }

}
