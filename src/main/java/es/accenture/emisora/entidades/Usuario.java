package es.accenture.emisora.entidades;

/**
 * Clase que representa la tabla 'usuario' de la BBDD.
 * Mapea cada columna de la tabla a un atributo de la clase.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class Usuario {
	
	//Declarar atributos de la clase.
	private int usuarioId;
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private String telefono;
	private String direccion;
	private String usuario;
	private String password;
	
	//Constructor vacio.
	public Usuario() {
		
	}
	
	//Constructor con parametros.
	public Usuario(int usuarioId, String nombre, String apellido, String dni, String email, String telefono,
				   String direccion, String usuario, String password) {
		
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.password = password;
	}
	
	//Constructor con parametros (sin el id, ya que la BBDD genera el id automaticamente con auto_increment).
	public Usuario(String nombre, String apellido, String dni, String email, String telefono,
				   String direccion, String usuario, String password) {
			
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.password = password;
	}

	//=================Getter y Setter================
	//Metodos publicos para acceder y modificar los atributos privados.
	
	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
