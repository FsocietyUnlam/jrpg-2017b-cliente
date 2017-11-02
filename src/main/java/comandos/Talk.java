package comandos;

import chat.MiChat;
import chat.VentanaContactos;
import juego.Pantalla;
import mensajeria.PaqueteMensaje;
/**
 * Clase Talk.
 */
public class Talk extends ComandosEscucha {

    @Override
    public final void ejecutar() {
        MiChat chat = null;
        String destino;
        getJuego().getCliente().setPaqueteMensaje((PaqueteMensaje) gson.fromJson(cadenaLeida, PaqueteMensaje.class));
        if (!(getJuego().getCliente().getPaqueteMensaje().getUserReceptor() == null)) {
            if (!(getJuego().getChatsActivos().containsKey(getJuego().getCliente()
                    .getPaqueteMensaje().getUserEmisor()))) {
                chat = new MiChat(getJuego());

                chat.setTitle(getJuego().getCliente().getPaqueteMensaje().getUserEmisor());
                chat.setVisible(true);

                getJuego().getChatsActivos().put(getJuego().getCliente().getPaqueteMensaje().getUserEmisor(), chat);
            }
            destino = getJuego().getCliente().getPaqueteMensaje().getUserEmisor();
        } else {
            // ALL
            if (!getJuego().getChatsActivos().containsKey("Sala")) {
                chat = new MiChat(getJuego());

                chat.setTitle("Sala");
                chat.setVisible(true);

                getJuego().getChatsActivos().put("Sala", chat);
                if (Pantalla.ventContac != null) {
                    VentanaContactos.getBotonMc().setEnabled(false);
                }
            }
            destino = "Sala";
        }
        getJuego().getChatsActivos().get(destino).getChat()
                .append(getJuego().getCliente().getPaqueteMensaje().getUserEmisor() + ": " + getJuego()
                .getCliente().getPaqueteMensaje().getMensaje() + "\n");
        getJuego().getChatsActivos().get(destino).getTexto().grabFocus();
    }
}
