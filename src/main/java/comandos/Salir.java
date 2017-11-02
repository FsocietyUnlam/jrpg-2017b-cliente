package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import mensajeria.Comando;
import mensajeria.Paquete;
/**
 * Clase Salir.
 */
public class Salir extends ComandosCliente {

    @Override
    public final void ejecutar() {
        try {
            getCliente().getPaqueteUsuario().setInicioSesion(false);
            getCliente().getSalida().writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
            getCliente().getSocket().close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al salir");

        }

    }

}
