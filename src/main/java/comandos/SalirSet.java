package comandos;

import mensajeria.Comando;
/**
 * Clase SalirSet.
 */
public class SalirSet extends ComandosCliente {

    @Override
    public final void ejecutar() {
        getCliente().getPaqueteUsuario().setIp(getCliente().getMiIp());
        getCliente().getPaqueteUsuario().setComando(Comando.SALIR);
    }

}
