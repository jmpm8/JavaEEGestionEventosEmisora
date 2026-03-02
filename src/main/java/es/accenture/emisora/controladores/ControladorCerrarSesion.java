package es.accenture.emisora.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Este controlador gestiona el cierre de sesion.
 * 
 * @author jorge
 * @version 1.0
 */
public class ControladorCerrarSesion implements IControlador{

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
			
	//Constructor con parametro, que recibirá como parametro el pool de conexiones.
	public ControladorCerrarSesion(DataSource poolConexiones) {
					
		this.poolConexiones = poolConexiones;
				
	}
	
	/**
	 * Procesa la peticion de cierre de sesion.
	 * 
	 * @param request peticion HTTP.
	 * @param response respuesta HTTP.
	 * @return nombre del .jsp a mostrar
	 */
	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		
		//Obtener la sesion HTTP. El parametro 'false' para que devuelva la sesion existente. Si no existe devuelve null.
		HttpSession session = request.getSession(false);
		
		//Comprobar si existe sesion.
		if(session != null) {
			
			//Borrar todos los atributos de la sesion.
			session.invalidate();
		}

		//Devolver .jsp de inicio de sesion.
		return "Login.jsp";
	}
}
