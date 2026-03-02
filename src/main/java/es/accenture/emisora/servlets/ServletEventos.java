package es.accenture.emisora.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import es.accenture.emisora.controladores.ControladorBuscarEventos;
import es.accenture.emisora.controladores.ControladorCerrarSesion;
import es.accenture.emisora.controladores.ControladorIniciarSesion;
import es.accenture.emisora.controladores.ControladorVolver;
import es.accenture.emisora.controladores.IControlador;

/**
 * Servlet implementation class ServletEventos
 * Servlet principal de la aplicacion.
 * Esta clase Servlet recibira diferentes peticiones y en funcion de la peticion llamara al controlador correspondiente.
 */
@WebServlet("/ServletEventos")
public class ServletEventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//Establecer el DataSource.
	@Resource(name = "jdbc/Festival")
    private DataSource miPoolConexiones;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEventos() {
        super();
        
    }

	/**
	 * Gestiona peticiones GET que llegan desde formularios con method 'get'.
	 * 
	 * @param request peticion HTTP con los datos enviados.
	 * @param response respuesta HTTP que enviamos.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Procesar la peticion.
		procesarPeticion(request, response);
		
	}

	/**
	 * Gestiona peticiones POST que llegan desde formularios con method 'post'.
	 * 
	 * @param request peticion HTTP con los datos enviados.
	 * @param response respuesta HTTP que enviamos.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Procesar la peticion.
		procesarPeticion(request, response);
	}
	
	/**
	 * Metodo que procesa todas las peticiones.
	 * 
	 * @param request peticion HTTP
	 * @param response respuesta HTTP
	 */
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//Declara variable para almacenar el parametro de la url.
		String accion = request.getParameter("accion");
		
		//Comprobar que 'accion' no es null o esta vacio.
		if(accion == null || accion.isEmpty()) {
			
			accion = "login";
		}
		
		//Declarar variable usando la interfaz. 
		IControlador controlador = null;
		
		//Declarar variable para guardar la vista.
		String vista = "Login.jsp";
		
		try {
			
			//Segun la accion recibida ejecutar al controlador correspondiente.
			switch(accion) {
				case "login":
					controlador = new ControladorIniciarSesion(miPoolConexiones);
					break;
				case "logout":
					controlador = new ControladorCerrarSesion(miPoolConexiones);
					break;
				case "buscar":
					controlador = new ControladorBuscarEventos(miPoolConexiones);
					break;
				case "volver":
					controlador = new ControladorVolver(miPoolConexiones);
					break;
				default:
					controlador = new ControladorIniciarSesion(miPoolConexiones);
			}
			
			//Comprobar que el controlador no sea null.
			if(controlador != null) {
				
				//Si la vista no es null que el controlados llame el metodo correspondiente. 
				vista = controlador.procesarPeticion(request, response);	
			}
		}catch(Exception e) {
			
			//Guardar el mensaje de la excepcion en el objeto request.
			request.setAttribute("mensajeError", e.getMessage());
			
			//Imprimir pila de llamadas, para que en el caso de que haya un error, nos dice donde.
			e.printStackTrace();
			
			vista = "Login.jsp";
		}
		
		//Crear variable 'dispatcher' de tipo RequestDispatcher para almacenar la peticion de la vista.
		RequestDispatcher dispatcher = request.getRequestDispatcher(vista);
		
		//Enviar la peticion al .jsp 
		dispatcher.forward(request, response);
	}
}
