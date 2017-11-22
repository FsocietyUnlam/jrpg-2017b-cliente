package mensajeria;

import java.io.Serializable;

import estados.Estado;

/**
 * The Class PaqueteEnemigo.
 */
@SuppressWarnings("serial")
public class PaqueteEnemigo extends Paquete implements Serializable, Cloneable {

    /**
     * The id.
     */
    private int id;

    /**
     * The id mapa.
     */
    private int idMapa;

    /**
     * The nombre.
     */
    private String nombre;

    /**
     * The estado.
     */
    private int estado;

    /**
     * The salud tope.
     */
    private int saludTope;

    /**
     * The fuerza.
     */
    private int fuerza;

    /**
     * The nivel.
     */
    private int nivel;

    /**
     * The x.
     */
    private float x;

    /**
     * The y.
     */
    private float y;

    /**
     * Instantiates a new paquete enemigo.
     *
     * @param idParam
     *            the id
     * @param xParam
     *            the x
     * @param yParam
     *            the y
     */
    public PaqueteEnemigo(final int idParam, final float xParam,
            final float yParam) {
        this.id = idParam;
        // idMapa = 1;
        nombre = "Shrek Enojado";
        estado = Estado.estadoJuego;
        saludTope = 60;
        fuerza = 25;
        nivel = 1;
        this.x = xParam;
        this.y = yParam;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estadoParam
     *            the new estado
     */
    public void setEstado(final int estadoParam) {
        this.estado = estadoParam;
    }

    /**
     * Gets the mapa.
     *
     * @return the mapa
     */
    public int getMapa() {
        return idMapa;
    }

    /**
     * Sets the mapa.
     *
     * @param mapaParam
     *            the new mapa
     */
    public void setMapa(final int mapaParam) {
        idMapa = mapaParam;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param idParam
     *            the new id
     */
    public void setId(final int idParam) {
        this.id = idParam;
    }

    /**
     * Gets the salud tope.
     *
     * @return the salud tope
     */
    public int getSaludTope() {
        return saludTope;
    }

    /**
     * Sets the salud tope.
     *
     * @param saludTopeParam
     *            the new salud tope
     */
    public void setSaludTope(final int saludTopeParam) {
        this.saludTope = saludTopeParam;
    }

    /**
     * Gets the fuerza.
     *
     * @return the fuerza
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * Sets the fuerza.
     *
     * @param fuerzaParam
     *            the new fuerza
     */
    public void setFuerza(final int fuerzaParam) {
        this.fuerza = fuerzaParam;
    }

    /*
     * (non-Javadoc)
     *
     * @see mensajeria.Paquete#clone()
     */
    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombreParam
     *            the new nombre
     */
    public void setNombre(final String nombreParam) {
        this.nombre = nombreParam;
    }

    /**
     * Gets the nivel.
     *
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Sets the nivel.
     *
     * @param nivelParam
     *            the new nivel
     */
    public void setNivel(final int nivelParam) {
        this.nivel = nivelParam;
    }

    /**
     * Gets the x.
     *
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x.
     *
     * @param xParam
     *            the new x
     */
    public void setX(final float xParam) {
        this.x = xParam;
    }

    /**
     * Gets the y.
     *
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y.
     *
     * @param yParam
     *            the new y
     */
    public void setY(final float yParam) {
        this.y = yParam;
    }

}
