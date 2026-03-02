package es.accenture.emisora.excepciones;

/**
 * Clase de excepción personalizada. Esta clase extiende de Exception.
 * Esta clase define mensajes de error como constantes.
 * Esta clase lanzar excepciones específicas de nuestra aplicación.
 * Esta clase mantiene todos los mensajes de error en un solo lugar.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ExcepcionPropia extends Exception{
	
	/**
	 * Identificador único para la serialización de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	//Declarar atributos constantes de la clase.
	
	//===========Errores de autenticacion============
	public static final String CREDENCIALES_VACIAS = "Usuario/Password son obligatorios";
	public static final String CREDENCIALES_INCORRECTAS = "Usuario/Password incorrecta";
	public static final String USUARIO_NO_ENCONTRADO = "El usuario especificado no existe en la base de datos"; 
	               
	//===========Errores de eventos==================
	public static final String NO_HAY_RESULTADOS = "No hay resultados";
	public static final String ERROR_OBTENER_EVENTOS = "Error al obtener la lista de eventos de la base de datos";
	public static final String EVENTOS_NO_ENCONTRADOS = "No se encontraron eventos que coincidan con el criterio de búsqueda";  
	public static final String NO_HAY_EVENTOS_DISPONIBLES = "No hay eventos disponibles en este momento";
	
	//===========Errores de base de datos============
	public static final String ERROR_CONEXION_BD = "Error al conectar con la base de datos";
	public static final String ERROR_CONSULTA_SQL = "Error al ejecutar la consulta en la base de datos";
	public static final String ERROR_INESPERADO = "Se ha producido un error inesperado";
	
	//===========Errores de base de datos============
	public static final String CRITERIO_BUSQUEDA_VACIO = "El criterio de busqueda no puede estar vacio";
	
	//Constructor vacio.
	public ExcepcionPropia() {
		super();
	}
	
	//Constructor con mensaje personalizado.
	public ExcepcionPropia(String mensaje) {
        super(mensaje);
	}     
}
