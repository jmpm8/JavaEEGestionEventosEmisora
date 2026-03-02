package es.accenture.emisora.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import es.accenture.emisora.entidades.Evento;
import es.accenture.emisora.excepciones.ExcepcionPropia;

/**
 * Esta clase se encarga de conectar con la BBDD.
 * Esta clase realiza diferentes operaciones contra la tabla 'eventos' de la BBDD.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ModeloEvento {

	//Declarar atributo de tipo 'DataSource' para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Constructor con parametro. Recibirá como parametro el pool de conexiones.
	public ModeloEvento(DataSource poolConexiones) {
		
		this.poolConexiones = poolConexiones;
	}
		
	/**
	 * Metodo que devuelve una lista con todos los eventos musicales de la BBDD.
	 * Si no recuperamos informacion este metodo lanzara una excepcion.
	 * 
	 * @return lista con todos los eventos musicales de la BBDD.
	 * @throws ExcepcionPropia Si no hay eventos o hay error de conexion.
	 */
	public List<Evento> getEventos() throws ExcepcionPropia{
		
		//Crear lista donde guardaremos todos los eventos.
		List<Evento> listaEventos = new ArrayList<>();
		
		//Declarar objeto de tipo 'Evento' donde guardaremos cada registro encontrado en la consulta.
		Evento evento = null;
		
		//Crear sentencia SQL.
		String sql = "SELECT eventoId, nombre, descripcion, lugar, duracion, tipoEvento, asientosDisp "+ 
					 "FROM eventos";
		
		//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
		try(
						
			//Crear un objeto de tipo Connection. Establecer conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
						
			//Crear un objeto de tipo Statement para ejecutar las sentencias SQL.
			Statement miStatement = miConexion.createStatement();
					
			//Crear un objeto de tipo ResultSet. Ejecutar la sentencia SQL y la guardamos en el objeto ResultSet.
			ResultSet resultado = miStatement.executeQuery(sql);		
		){
			//Recorrer cada fila del resultado.
			while(resultado.next()) {
							
				//Instancia objeto de la clase 'Evento'.
				//Crear objeto Evento para cada fila.
				evento = new Evento(
						 resultado.getInt("eventoId"), 
						 resultado.getString("nombre"), 
						 resultado.getString("descripcion"), 
						 resultado.getString("lugar"), 
						 resultado.getString("duracion"),
						 resultado.getString("tipoEvento"),
						 resultado.getInt("asientosDisp"));
				
				//Añadir cada objeto creado con sus atributos a la lista.
				listaEventos.add(evento);
			}
				
			//Comprobar que la lista esta vacia.
			if(listaEventos.isEmpty()) {
					
				//Lanzar excepcion con mensaje de no hay eventos disponibles. 
				throw new ExcepcionPropia(ExcepcionPropia.NO_HAY_EVENTOS_DISPONIBLES);
			}
		}catch(SQLException e) {
			
			//Lanzar excepcion con mensaje de error de conexion con la BBDD.
			throw new ExcepcionPropia(ExcepcionPropia.ERROR_CONEXION_BD);	
		}
		
		return listaEventos;
	}
	
	/**
	 * Metodo que recibe un parametro y devuelve una lista con todos los eventos de la BBDD que cumplan el criterio de búsqueda.
	 * Si no disponemos de ningun evento que cumpla dicho criterio este metodo lanzara una excepcion.
	 * 
	 * @param param. Texto a buscar en nombre o descripcion.
	 * @return  lista con los eventos de la BBDD que cumplan el criterio de búsqueda.
	 * @throws ExcepcionPropia si no hay resultados, el criterio esta vacio, o hay un error de conexion.
	 */
	public List<Evento> getEventos(String criterio) throws ExcepcionPropia{
		
		//Comprobar que usuario no sea null o este vacio.
		if(criterio == null || criterio.isEmpty()) {
					
			throw new ExcepcionPropia(ExcepcionPropia.CRITERIO_BUSQUEDA_VACIO);	
		}
		
		//Crear lista donde guardaremos todos los eventos.
		List<Evento> listaEventos = new ArrayList<>();
		
		//Declarar objeto de tipo 'Evento' donde guardaremos cada registro encontrado en la consulta.
		Evento evento = null;
		
		//Crear sentencia SQL.
		String sql = "SELECT eventoId, nombre, descripcion, lugar, duracion, tipoEvento, asientosDisp "+ 
					 "FROM eventos "+
					 "WHERE nombre LIKE ? OR descripcion LIKE ?";
		
		//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
		try(
						
			//Crear un objeto de tipo Connection. Establecer conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
						
			//Crear un objeto de tipo PreparedStatement para ejecutar las sentencias SQL.
			PreparedStatement miStatement = miConexion.prepareStatement(sql)
		){
			//Declarar variable para almacenar criterio de busqueda.
			String parametro = "%" + criterio + "%";
			
			//Pasar parametros a la consulta SQL.
			miStatement.setString(1, parametro);
			miStatement.setString(2, parametro);
			
			//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
			try(
						
				//Crear un objeto de tipo ResultSet. Ejecutar la sentencia SQL y la guardamos en el objeto ResultSet.
				ResultSet resultado = miStatement.executeQuery();
			){
				//Recorrer cada fila del resultado.
				while(resultado.next()) {
								
					//Instancia objeto de la clase 'Evento'.
					//Crear objeto Evento para cada fila.
					evento = new Evento(
							 resultado.getInt("eventoId"), 
							 resultado.getString("nombre"), 
							 resultado.getString("descripcion"), 
							 resultado.getString("lugar"), 
							 resultado.getString("duracion"),
							 resultado.getString("tipoEvento"),
							 resultado.getInt("asientosDisp"));
					
					//Añadir cada objeto creado con sus atributos a la lista.
					listaEventos.add(evento);
				}
				
				//Comprobar que la lista esta vacia.
				if(listaEventos.isEmpty()) {
						
					//Lanzar excepcion con mensaje de no hay eventos disponibles. 
					throw new ExcepcionPropia(ExcepcionPropia.NO_HAY_RESULTADOS);
				}
			}
				
		}catch(SQLException e) {
				
				//Lanzar excepcion con mensaje de error de conexion con la BBDD.
				throw new ExcepcionPropia(ExcepcionPropia.ERROR_CONEXION_BD);	
		}
			
		return listaEventos;
	}
}
