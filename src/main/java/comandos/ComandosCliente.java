package comandos;

import cliente.Cliente;
import mensajeria.Comando;

/**
 * Clase abstracta ComandosCliente.
 */
public abstract class ComandosCliente extends Comando {
    /**
     * cliente.
     */
    private Cliente cliente;
    /**
     * Setter del cliente.
     * @param cliente envia el cliente
     */
    public void setCliente(final Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Getter del cliente.
     * @return cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

}
