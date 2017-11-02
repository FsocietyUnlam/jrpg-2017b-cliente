package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import frames.MenuComerciar;
import mensajeria.Paquete;
import mensajeria.PaqueteComerciar;

/**
 * Clase Comercio.
 */
public class Comercio extends ComandosEscucha {

    @Override
    public final void ejecutar() {
        PaqueteComerciar paqueteComerciar;
        paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
        // Cuando recibo el paquete de comercio actualizado intercambio user/ destino
        paqueteComerciar.setIdEnemigo(paqueteComerciar.getId());
        paqueteComerciar.setId(getJuego().getCliente().getPaquetePersonaje().getId());

        if (paqueteComerciar.isSolicitudDeComercio()) {
            if (getJuego().getCliente().getM1() != null) {
                paqueteComerciar.setMensaje(Paquete.msjFracaso);
            } else {
                getJuego().getCliente().setPaqueteComercio(paqueteComerciar);
                getJuego().getCliente().setM1(new MenuComerciar(getJuego().getCliente()));
                getJuego().getCliente().getM1().setVisible(true);
                paqueteComerciar.setMensaje(Paquete.msjExito);
            }
            paqueteComerciar.setSolicitudDeComercio(false);
            try {
                getJuego().getCliente().getSalida().writeObject(gson.toJson(paqueteComerciar));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No se envio la solicitud de comercio");
            }

        } else {
            if (paqueteComerciar.getMensaje().equals(Paquete.msjFracaso)) {
                JOptionPane.showMessageDialog(null, "Ya esta comerciando");
            } else {
                if (getJuego().getCliente().getM1() == null) {
                    getJuego().getCliente().setPaqueteComercio(paqueteComerciar);
                    getJuego().getCliente().setM1(new MenuComerciar(getJuego().getCliente()));
                    getJuego().getCliente().getM1().setVisible(true);
                }
            }
        }

    }

}
