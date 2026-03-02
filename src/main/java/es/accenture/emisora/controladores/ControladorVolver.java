package es.accenture.emisora.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Este controlador gestiona la accion de 'volver' a la pagina de busqueda.
 * 
 * @author jorge
 * @version 1.0
 */
public class ControladorVolver implements IControlador{

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
					
	//Constructor con parametro, que recibirá como parametro el pool de conexiones.
	public ControladorVolver(DataSource poolConexiones) {
							
		this.poolConexiones = poolConexiones;
						
	}
	
	/**
	 * Procesa la peticion de 'volver' a la pagina de busqueda.
	 * 
	 * @param request peticion HTTP con criterio de busqueda.
	 * @param response respuesta HTTP.
	 * @return nombre del .jsp a mostrar
	 */
	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		
		//Devolver .jsp de mostrar eventos.
		return "/WEB-INF/BuscarEventos.jsp";	
	}
}
