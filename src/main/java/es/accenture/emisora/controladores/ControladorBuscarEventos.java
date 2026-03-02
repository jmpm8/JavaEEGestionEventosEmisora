package es.accenture.emisora.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import es.accenture.emisora.entidades.Evento;
import es.accenture.emisora.excepciones.ExcepcionPropia;
import es.accenture.emisora.modelos.ModeloEvento;

/**
 * Este controlador gestiona la busqueda de eventos.
 * 
 * @author jorge
 * @version 1.0
 */
public class ControladorBuscarEventos implements IControlador{

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
				
	//Constructor con parametro, que recibirá como parametro el pool de conexiones.
	public ControladorBuscarEventos(DataSource poolConexiones) {
						
		this.poolConexiones = poolConexiones;
					
	}
	
	/**
	 * Procesa la peticion de buscar eventos.
	 * 
	 * @param request peticion HTTP con criterio de busqueda.
	 * @param response respuesta HTTP.
	 * @return nombre del .jsp a mostrar
	 */
	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		
		//Declarar variable 'criterio' para almacenar el parametro del formulario.
		String criterio = request.getParameter("criterio");
		
		try {
			//Declarar variable 'modeloEvento' de tipo ModeloEvento para pasar pool de conexiones.
			ModeloEvento modeloEvento = new ModeloEvento(poolConexiones);
			
			//Declarar lista para almacenar los eventos encontrados.
			List<Evento> listaEventos = null;
			
			//Comprobar si 'criterio' es null o esta vacio.
			if(criterio == null || criterio.isEmpty()) {
				
				//Si 'criterio' es null o esta vacio almacenar la lista de eventos.
				listaEventos = modeloEvento.getEventos();
				
				//Guardar el mensaje de la excepcion en el objeto request.
				request.setAttribute("listaEventos", listaEventos);
				
				//Devolver .jsp de mostrar eventos.
				 return "/WEB-INF/MostrarEventos.jsp";
				
			}else {
					
				//Si 'criterio' no es null o no esta vacio almacenar el eventos que coincida con ese criterio.
				listaEventos = modeloEvento.getEventos(criterio);
					
				//Guardar el mensaje de la excepcion en el objeto request.
				request.setAttribute("listaEventos", listaEventos);
					
				 //Devolver .jsp de mostrar eventos.
				 return "/WEB-INF/MostrarEventos.jsp";
			}
		}catch(ExcepcionPropia e) {
			
			//Guardar el mensaje de la excepcion en el objeto request.
			request.setAttribute("mensajeError", ExcepcionPropia.NO_HAY_RESULTADOS );
			
			//Devolver .jsp de mostrar eventos.
			return "/WEB-INF/BuscarEventos.jsp";	
		}
	}
}
