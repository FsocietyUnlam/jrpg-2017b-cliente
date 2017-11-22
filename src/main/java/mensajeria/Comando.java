package mensajeria;

import com.google.gson.Gson;

/**
 * Clase Comando -Nombre del paquete donde se encuentran las clases con las responsabilidades.
 * @author lmagallanes
 *
 */
public abstract class Comando {
	/**
	 * atributo estatico con el nombre del paquete.
	 */
	public static final String NOMBREPAQUETE = "comandos";

	/**
	 * atributo estatico con el nombre de las clases.
	 *//*
	public static final String[] CLASSNAMES = {"Conexion", "CrearPersonaje", "Desconectar", "InicioSesion",
			"MostrarMapas", "Movimiento", "Registro", "Salir", "Batalla", "Atacar", "FinalizarBatalla",
			"ActualizarPersonaje", "ActualizarPersonajeLvl", "ActualizarInventario", "Comercio", "ActualizarComercio",
			"Trueque", "ActualizarTrueque", "Talk" };
	/**
	 * atributo estatico.
	 */
	/*public static final String[] CLASSNAMESBIS = {"Conexion", "CrearPersonaje", "Desconectar", "InicioSesionSet",
			"MostrarMapas", "Movimiento", "RegistroSet", "SalirSet", "Batalla", "Atacar", "FinalizarBatalla",
			"ActualizarPersonaje", "ActualizarPersonajeLvl", "ActualizarInventario", "Comercio", "ActualizarComercio",
			"Trueque", "ActualizarTrueque", "Talk" };*/

    public static final String[] CLASSNAMES = {"Conexion", "CrearPersonaje",
            "Desconectar", "InicioSesion", "MostrarMapas", "Movimiento",
            "Registro", "Salir", "Batalla", "Atacar", "FinalizarBatalla",
            "ActualizarPersonaje", "ActualizarPersonajeLvl",
            "ActualizarInventario", "Comercio", "ActualizarComercio", "Trueque",
            "ActualizarTrueque", "Talk", "ActualizarPuntosSkills",
            "SetEnemigos", "FinalizarBatallaNPC", "BatallaNPC" };

    /**
     * The Constant CLASSNAMESBIS.
     */
    public static final String[] CLASSNAMESBIS = {"Conexion", "CrearPersonaje",
            "Desconectar", "InicioSesionSet", "MostrarMapas", "Movimiento",
            "RegistroSet", "SalirSet", "Batalla", "Atacar", "FinalizarBatalla",
            "ActualizarPersonaje", "ActualizarPersonajeLvl",
            "ActualizarInventario", "Comercio", "ActualizarComercio", "Trueque",
            "ActualizarTrueque", "Talk", "ActualizarPuntosSkills",
            "SetEnemigos", "FinalizarBatallaNPC", "BatallaNPC" };

	/**
	 * Atributo estático de conexion.
	 */
	public static final int CONEXION = 0;
	/**
	 * Atributo estático de CREACIONPJ.
	 */
	public static final int CREACIONPJ = 1;
	/**
	 * Atributo estático de DESCONECTAR.
	 */
	public static final int DESCONECTAR = 2;
	/**
	 * Atributo estático de INICIOSESION.
	 */
	public static final int INICIOSESION = 3;
	/**
	 * Atributo estático de MOSTRARMAPAS.
	 */
	public static final int MOSTRARMAPAS = 4;
	/**
	 * Atributo estático de MOVIMIENTO.
	 */
	public static final int MOVIMIENTO = 5;
	/**
	 * Atributo estático de REGISTRO.
	 */
	public static final int REGISTRO = 6;
	/**
	 * Atributo estático de SALIR.
	 */
	public static final int SALIR = 7;
	/**
	 * Atributo estático de BATALLA.
	 */
	public static final int BATALLA = 8;
	/**
	 * Atributo estático de ATACAR.
	 */
	public static final int ATACAR = 9;
	/**
	 * Atributo estático de FINALIZARBATALLA.
	 */
	public static final int FINALIZARBATALLA = 10;
	/**
	 * Atributo estático de ACTUALIZARPERSONAJE.
	 */
	public static final int ACTUALIZARPERSONAJE = 11;
	/**
	 * Atributo estático de ACTUALIZARPERSONAJELV.
	 */
	public static final int ACTUALIZARPERSONAJELV = 12;
	/**
	 * Atributo estático de ACTUALIZARINVENTARIO.
	 */
	public static final int ACTUALIZARINVENTARIO = 13;
	/**
	 * Atributo estático de COMERCIO.
	 */
	public static final int COMERCIO = 14;
	/**
	 * Atributo estático de ACTUALIZARCOMERCIO.
	 */
	public static final int ACTUALIZARCOMERCIO = 15;
	/**
	 * Atributo estático de TRUEQUE.
	 */
	public static final int TRUEQUE = 16;
	/**
	 * Atributo estático de ACTUALIZARTRUEQUE.
	 */
	public static final int ACTUALIZARTRUEQUE = 17;
	/**
	 * Atributo estático de TALK.
	 */
	public static final int TALK = 18;
    public static final int ACTUALIZARPUNTOSSKILLS = 19;
    public static final int SETENEMIGOS = 20;
    public static final int FINALIZARBATALLANPC = 21;
    public static final int BATALLANPC = 22;

	/**
	 * Atributo del tipo Gson.
	 */
	protected final Gson gson = new Gson();
	/**
	 * Atributo del tipo string.
	 */
	protected String cadenaLeida;

	/**
	 * Método seteador para asigna la cadena.
	 */
	public void setCadena(String cadenaLeida) {
		this.cadenaLeida = cadenaLeida;
	}
	
    /**
     * @return the cadenaLeida
     */
    public String getCadenaLeida() {
        return cadenaLeida;
    }
    /**
     * @return the gson
     */
    public Gson getGson() {
        return gson;
    }

	/**
	 * Método abstracto.
	 */
	public abstract void ejecutar();
}
