package comandos;

import javax.swing.JOptionPane;

import frames.MenuCreacionPj;
import mensajeria.Paquete;
/**
 * Clase Registro.
 */
public class Registro extends ComandosCliente {

    @Override
    public final void ejecutar() {
        synchronized (this) {

            Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
            if (paquete.getMensaje().equals(Paquete.msjExito)) {

                // Abro el menu para la creaci�n del personaje
                MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(getCliente(), getCliente()
                        .getPaquetePersonaje(), gson);
                menuCreacionPJ.setVisible(true);

                // Espero a que el usuario cree el personaje

                // Recibo el paquete personaje con los datos (la id incluida)

                // Indico que el usuario ya inicio sesion

            } else {
                if (paquete.getMensaje().equals(Paquete.msjFracaso)) {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar.");
                }
                // El usuario no pudo iniciar sesión
                getCliente().getPaqueteUsuario().setInicioSesion(false);
            }

        }
    }

}
