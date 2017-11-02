package comandos;

import javax.swing.JOptionPane;

import mensajeria.PaquetePersonaje;

/**
 * Clase CrearPersonaje.
 */
public class CrearPersonaje extends ComandosCliente {

    @Override
    public final void ejecutar() {
        JOptionPane.showMessageDialog(null, "Registro exitoso.");
        getCliente().setPaquetePersonaje((PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class));
        getCliente().getPaqueteUsuario().setInicioSesion(true);

    }

}
