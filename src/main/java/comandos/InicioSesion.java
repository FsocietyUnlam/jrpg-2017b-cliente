package comandos;

import javax.swing.JOptionPane;

import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;
/**
 * Clase inicioSesion.
 */
public class InicioSesion extends ComandosCliente {

    @Override
    public final void ejecutar() {
        Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
        if (paquete.getMensaje().equals(Paquete.msjExito)) {

            // El usuario ya inicio sesi�n
            getCliente().getPaqueteUsuario().setInicioSesion(true);

            // Recibo el paquete personaje con los datos
            getCliente().setPaquetePersonaje(gson.fromJson(cadenaLeida, PaquetePersonaje.class));

        } else {
            if (paquete.getMensaje().equals(Paquete.msjFracaso)) {
                JOptionPane.showMessageDialog(null, "Error al iniciar sesión." + " Revise el usuario y la contraseña");
            }

            // El usuario no pudo iniciar sesión
            getCliente().getPaqueteUsuario().setInicioSesion(false);
        }

    }

}
