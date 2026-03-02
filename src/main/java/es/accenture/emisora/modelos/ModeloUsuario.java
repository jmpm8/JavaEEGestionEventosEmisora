package es.accenture.emisora.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import es.accenture.emisora.entidades.Usuario;
import es.accenture.emisora.excepciones.ExcepcionPropia;

/**
 * Esta clase se encarga de conectar con la BBDD.
 * Esta clase realiza diferentes operaciones contra la tabla 'usuarios' de la BBDD.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ModeloUsuario {
	
	//Declarar atributo de tipo 'DataSource' para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Constructor con parametro. Recibirá como parametro el pool de conexiones.
	public ModeloUsuario(DataSource poolConexiones) {
			
		this.poolConexiones = poolConexiones;
	}

	/**
	 * Método debe buscar el usuario y password en la BBDD, y si encuentra dicho usuario y 
	 * password en la BBDD, devolverá toda la información para ese usuario. 
	 * Si no encuentra el usuario y la password en la BBDD, entonces lanzará una excepción.
	 *  
	 * @param usuario
	 * @param password
	 * @return objeto usuario con todos los datos del usuario encontrado.
	 * @throws ExcepcionPropia Si las credenciales estan vacias, son incorrectas o hay error de conexion.
	 */
	public Usuario getUsuario(String usuario, String password) throws ExcepcionPropia{
			
		//Comprobar que usuario no sea null o este vacio.
		if(usuario == null || usuario.isEmpty()) {
			
			throw new ExcepcionPropia(ExcepcionPropia.CREDENCIALES_VACIAS);	
		}
		
		//Comprobar que password no sea null o este vacia.
		if(password == null || password.isEmpty()) {
			
			throw new ExcepcionPropia(ExcepcionPropia.CREDENCIALES_VACIAS);
		}
		
		//Declarar objeto de tipo 'Usuario' donde guardaremos el usuario encontrado.
		Usuario usuarioEncontrado = null;
		
		//Crear sentencia SQL.
		String sql = "SELECT usuarioId, nombre, apellido, dni, email, telefono, direccion, usuario, password "+
				     "FROM usuarios "+ 
				     "WHERE usuario=? AND password=?";
		
		//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
		try(
				
			//Crear un objeto de tipo Connection. Establecer conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
				
			//Crear un objeto de tipo PreparedStatement para ejecutar las sentencias SQL.
			PreparedStatement miStatement = miConexion.prepareStatement(sql)
		){
			//Pasar parametros a la consulta SQL.
			miStatement.setString(1, usuario);
			miStatement.setString(2, password);
			
			//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
			try(
				
				//Crear un objeto de tipo ResultSet. Ejecutar la sentencia SQL y la guardamos en el objeto ResultSet.
				ResultSet resultado = miStatement.executeQuery();
			){
				//Comprobar el resultado de la consulta.
				if(resultado.next()) {
					
					//Instancia objeto de la clase 'Usuario'.
					//Crear objeto Usuario para cada fila.
					usuarioEncontrado = new Usuario(
									  	resultado.getInt("usuarioId"), 
									  	resultado.getString("nombre"), 
									  	resultado.getString("apellido"), 
									  	resultado.getString("dni"), 
									  	resultado.getString("email"),
									  	resultado.getString("telefono"),
									  	resultado.getString("direccion"),
									  	resultado.getString("usuario"),
									  	resultado.getString("password"));
				}else {
					
					//Lanzar excepcion con mensaje de credenciales incorrectas.
					throw new ExcepcionPropia(ExcepcionPropia.CREDENCIALES_INCORRECTAS);
				}	
			}
		}catch(SQLException e) {
			
			//Lanzar excepcion con mensaje de error de conexion con la BBDD.
			throw new ExcepcionPropia(ExcepcionPropia.ERROR_CONEXION_BD);
		}
		
		//Devovleer el usuario encontrado.
		return usuarioEncontrado;
	}
}
