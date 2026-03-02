package es.accenture.emisora.entidades;

/**
 * Clase que representa la tabla 'eventos' de la BBDD.
 * Mapea cada columna de la tabla a un atributo de la clase.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class Evento {
	
	//Declarar atributos de la clase.
	private int eventoId;
	private String nombre;
	private String descripcion;
	private String lugar;
	private String duracion;
	private String tipoEvento;
	private int asientosDisp;
	
	//Constructor vacio.
	public Evento() {
		
	}
	
	//Constructor con parametros.
	public Evento(int eventoId, String nombre, String descripcion, String lugar, String duracion, String tipoEvento,
				  int asientosDisp) {

		this.eventoId = eventoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.lugar = lugar;
		this.duracion = duracion;
		this.tipoEvento = tipoEvento;
		this.asientosDisp = asientosDisp;
	}
	
	//Constructor con parametros (sin el id, ya que la BBDD genera el id automaticamente con auto_increment).
	public Evento(String nombre, String descripcion, String lugar, String duracion, String tipoEvento,
				  int asientosDisp) {
	
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.lugar = lugar;
		this.duracion = duracion;
		this.tipoEvento = tipoEvento;
		this.asientosDisp = asientosDisp;
	}
	

	//=================Getter y Setter================
	//Metodos publicos para acceder y modificar los atributos privados.
	
	public int getEventoId() {
		return eventoId;
	}

	public void setEventoId(int eventoId) {
		this.eventoId = eventoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public int getAsientosDisp() {
		return asientosDisp;
	}

	public void setAsientosDisp(int asientosDisp) {
		this.asientosDisp = asientosDisp;
	}
}
