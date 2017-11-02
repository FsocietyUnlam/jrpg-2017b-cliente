package comandos;

import mensajeria.Comando;
/**
 * Clase RegistroSet.
 */
public class RegistroSet extends ComandosCliente {

    @Override
    public final void ejecutar() {
        getCliente().getPaqueteUsuario().setComando(Comando.REGISTRO);

    }

}
