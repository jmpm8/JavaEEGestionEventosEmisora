package es.accenture.emisora.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Esta interfaz que define el contrato para todos los controladores.
 * Todos los controladores que implementen esta interfaz deben tener un metodo procesarPeticion().
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public interface IControlador {
	
	/**
	 * Este metodo procesa una peticion HTTP y devuelve el nombre del JSP a mostrar.
	 * 
	 * @param request objeto HttpServletRequest con los datos de la peticion. 
	 * @param response objeto HttpServletResponse para la respuesta.
	 * @return nombre del JSP a mostrar.
	 */
	String procesarPeticion(HttpServletRequest request, HttpServletResponse response);

}
