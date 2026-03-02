package es.accenture.emisora.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import es.accenture.emisora.entidades.Usuario;
import es.accenture.emisora.excepciones.ExcepcionPropia;
import es.accenture.emisora.modelos.ModeloUsuario;

/**
 * Este controlador gestiona el inicion de sesion.
 * 
 * @author jorge
 * @version 1.0
 */
public class ControladorIniciarSesion implements IControlador{

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Constructor con parametro, que recibirá como parametro el pool de conexiones.
	public ControladorIniciarSesion(DataSource poolConexiones) {
			
		this.poolConexiones = poolConexiones;
		
	}
	
	/**
	 * Procesa la peticion de inicio de sesion.
	 * 
	 * @param request peticion HTTP con los datos del formulario.
	 * @param response respuesta HTTP que enviamos.
	 * @return nombre del .jsp a mostrar
	 */
	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		
		//Declarar variables para recibir los parametros del formulario.
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		//Comprobar que los paramteros no son null o estan vacio.
		if(usuario == null || usuario.isEmpty() || password == null || password.isEmpty()) {
			
			//Guardar mensaje de error en el request.
			request.setAttribute("mensajeError", ExcepcionPropia.CREDENCIALES_VACIAS);
			
			//Devolver .jsp de inicio de sesion.
			return "Login.jsp";
		}
		
		try {
			//Crear objeto 'modeloUsuario' de tipo ModeloUsuario para pasar el pool de conexiones.
			ModeloUsuario modeloUsuario = new ModeloUsuario(poolConexiones);
			
			//Crear objeto 'usuarioEncontrado' de tipo Usuario para almacenar el 'usuario' y la 'password'
			Usuario usuarioEncontrado = modeloUsuario.getUsuario(usuario, password);
			
			//Obtener la sesion HTTP. El parametro 'true' si no existe sesion la crea.
			HttpSession session = request.getSession(true);
			
			//Guardar 'usuarioEncontrado' en la sesion.
			session.setAttribute("usuarioLogueado", usuarioEncontrado);
			
			//Devolver .jsp de Buscar Eventos
			return "/WEB-INF/BuscarEventos.jsp";
			
		}catch(ExcepcionPropia e) {
			
			//Guardar el mensaje de la excepcion en el objeto request.
			request.setAttribute("mensajeError", ExcepcionPropia.CREDENCIALES_INCORRECTAS);
			
			//Devolver .jsp de inicio de sesion.
			return "Login.jsp";
		}	
	}
}
