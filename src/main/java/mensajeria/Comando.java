package mensajeria;

import com.google.gson.Gson;

public abstract class Comando {
	// Nombre del paquete donde se encuentran las clases con las responsabilidades
	public static final String NOMBREPAQUETE = "comandos";
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

	public static final int CONEXION = 0;
	public static final int CREACIONPJ = 1;
	public static final int DESCONECTAR = 2;
	public static final int INICIOSESION = 3;
	public static final int MOSTRARMAPAS = 4;
	public static final int MOVIMIENTO = 5;
	public static final int REGISTRO = 6;
	public static final int SALIR = 7;
	public static final int BATALLA = 8;
	public static final int ATACAR = 9;
	public static final int FINALIZARBATALLA = 10;
	public static final int ACTUALIZARPERSONAJE = 11;
	public static final int ACTUALIZARPERSONAJELV = 12;
	public static final int ACTUALIZARINVENTARIO = 13;
	public static final int COMERCIO = 14;
	public static final int ACTUALIZARCOMERCIO = 15;
	public static final int TRUEQUE = 16;
	public static final int ACTUALIZARTRUEQUE = 17;
	public static final int TALK = 18;
    public static final int ACTUALIZARPUNTOSSKILLS = 19;
    public static final int SETENEMIGOS = 20;
    public static final int FINALIZARBATALLANPC = 21;
    public static final int BATALLANPC = 22;

	protected final Gson gson = new Gson();
	protected String cadenaLeida;

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

	public abstract void ejecutar();
}
