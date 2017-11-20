package mensajeria;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Clase paquete.
 * @author lmagallanes
 *
 */
public class Paquete implements Serializable, Cloneable {

	/**
	 * Atributo del tipo long.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo estatico para describir un mensaje de exito.
	 */
	public static String msjExito = "1";
	/**
	 * Atributo estatico para describir un mensaje de fracaso.
	 */
	public static String msjFracaso = "0";

	/**
	 * Atributo string para representar el mje.
	 */
	private String mensaje;
	/**
	 * Atributo string para representar el IP.
	 */
	private String ip;
	/**
	 * Atributo del tipo entero.
	 */
	private int comando;
	
	/**
	 * Constructor por defecto de la clase.
	 */
	public Paquete() {

	}
	/**
	 * Constructor parametrizado de la clase.
	 */
	public Paquete(String mensaje, String nick, String ip, int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}
	/**
	 * Constructor parametrizado de la clase sobrecargado.
	 */
	public Paquete(String mensaje, int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}
	/**
	 * Constructor parametrizado de la clase sobrecargado.
	 */
	public Paquete(int comando) {
		this.comando = comando;
	}
	/**
	 * Seteador del atributo Mensaje.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * Seteador del atributo IP.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * Seteador del atributo Comando.
	 */
	public void setComando(int comando) {
		this.comando = comando;
	}
	/**
	 * Getter del atributo Mensaje.
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Getter del atributo IP.
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * Getter del atributo Comando.
	 */
	public int getComando() {
		return comando;
	}

	/**
	 * Metodo para clonar.
	 */
	@Override
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			JOptionPane.showMessageDialog(null, "Error al clonar");

		}
		return obj;
	}
	
	/**
	 * Metodo para obtener objeto comando.
	 * @param nombrePaquete parametro del tipo string.
	 * @return un comando.
	 * @throws InstantiationException un tipo de excepcion para una instanciacion.
	 * @throws IllegalAccessException un tipo de excepcion para un acceso ilegal.
	 * @throws ClassNotFoundException un tipo de excepcion para clase no encontrada.
	 */
	public Comando getObjeto(String nombrePaquete)  throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//try {
			Comando c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMES[comando]).newInstance();
			return c;
		//} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			//return null;
		//}
	}
	
	/**
	 * get y set un comando.
	 * @param nombrePaquete parametro del tipo string.
	 * @param accion
	 * @return un comando.
	 * @throws InstantiationException un tipo de excepcion para una instanciacion.
	 * @throws IllegalAccessException un tipo de excepcion para un acceso ilegal.
	 * @throws ClassNotFoundException un tipo de excepcion para clase no encontrada.
	 */
	public static Comando getObjetoSet(String nombrePaquete, int accion) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	//	try {
			Comando c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMESBIS[accion]).newInstance();
			return c;
		//} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			//return null;
		//}

	}


}
