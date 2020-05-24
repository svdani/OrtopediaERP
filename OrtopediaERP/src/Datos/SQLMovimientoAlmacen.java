package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Pedido;
import Modelo.MovimientoAlmacen;

public class SQLMovimientoAlmacen {
	Connection c = null;

	Statement sentencia = null;

	//String idArticuloTabla;

	int idMovimientoAlmacen;
	String idArticulo;
	String tipoMovimiento;
	String ubicacion;
	String fecha;
	int cantidad;

	ArrayList<MovimientoAlmacen> MovimientoAlmacenes = new ArrayList<MovimientoAlmacen>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos MovimientoAlmacen");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos MovimientoAlmacen");

		}
		return c;

	}
	
	//Inserta en tabla MovimientoAlmacen
	public void insertaMovimientoAlmacenes(MovimientoAlmacen mov) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO MovimientoAlmacen (idArticulo, tipoMovimiento, ubicacion, fecha, cantidad) "

		            	 + "VALUES (" 
		            	 + "'" + mov.getIdArticulo() + "',"
		            	 + "'" + mov.getTipoMovimiento() + "',"
		            	 + "'" + mov.getUbicacion() + "',"
		            	 + "'" + mov.getFecha() + "',"
		            	 + "'" + mov.getCantidad() + "');";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla MovimientoAlmacen");

		}
	}
	
	//Modifica taula MovimientoAlmacen
	public void modificaMovimientoAlmacenes(MovimientoAlmacen mov) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE MovimientoAlmacen "
							+ "SET"
							+ " idArticulo='" + mov.getIdArticulo()
							+ "', tipoMovimiento='" + mov.getTipoMovimiento()
							+ "', ubicacion='" + mov.getUbicacion()
							+ "', fecha='" + mov.getFecha()
							+ "', cantidad='" + mov.getCantidad()
							+ "' WHERE idMovimientoAlmacen='" + mov.getIdMovimientoAlmacen() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla MovimientoAlmacen");

		}
	}
		
	//Elimina MovimientoAlmacen
	public void deleteMovimientoAlmacenes(MovimientoAlmacen mov) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM MovimientoAlmacen WHERE idMovimientoAlmacen='"	+ mov.getIdMovimientoAlmacen() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla MovimientoAlmacen");

		}

	}
	
	//Muestra Tabla MovimientoAlmacen
	public ArrayList<MovimientoAlmacen> consultaMovimientoAlmacenes() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM MovimientoAlmacen;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				idMovimientoAlmacen = rs.getInt("idMovimientoAlmacen");
				idArticulo = rs.getString("idArticulo");
				tipoMovimiento = rs.getString("tipoMovimiento");
				ubicacion = rs.getString("ubicacion");
				fecha = rs.getString("fecha");
				cantidad = rs.getInt("cantidad");

					
				//GUARDA EN ARRAY LIST MovimientoAlmacen
				MovimientoAlmacenes.add(new MovimientoAlmacen(
						idMovimientoAlmacen, 
						idArticulo, 
						tipoMovimiento, 
						ubicacion, 
						fecha,
						cantidad));

			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return MovimientoAlmacenes;
	}
	
	//Busca MovimientoAlmacen por Estado
		public ArrayList<MovimientoAlmacen> filtraMovimientoAlmacen(String registro, String filtro) throws SQLException {
			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM MovimientoAlmacen WHERE " + filtro + " = '" + registro + "';";
		
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				while (rs.next()) {
					
					idMovimientoAlmacen = rs.getInt("idMovimientoAlmacen");
					idArticulo = rs.getString("idArticulo");
					tipoMovimiento = rs.getString("tipoMovimiento");
					ubicacion = rs.getString("ubicacion");
					fecha = rs.getString("fecha");
					cantidad = rs.getInt("cantidad");

						
					//GUARDA EN ARRAY LIST MovimientoAlmacen
					MovimientoAlmacenes.add(new MovimientoAlmacen(
							idMovimientoAlmacen, 
							idArticulo, 
							tipoMovimiento, 
							ubicacion, 
							fecha,
							cantidad));

				}

				rs.close();
				sentencia.close();
				c.close();
			} catch (Exception e) {
				System.out.println("impossible");
				Talal: 	System.out.println(e.getMessage());
			}
			return MovimientoAlmacenes;
		}
		
		//Busca MovimientoAlmacen por Estado
		public ArrayList<MovimientoAlmacen> filtraMovimientoAlmacenFecha(String desde, String hasta) throws SQLException {
			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM MovimientoAlmacen WHERE fecha BETWEEN '" + desde + "' AND '" + hasta + "';";
			System.out.println(consultaSql);
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				while (rs.next()) {
					
					idMovimientoAlmacen = rs.getInt("idMovimientoAlmacen");
					idArticulo = rs.getString("idArticulo");
					tipoMovimiento = rs.getString("tipoMovimiento");
					ubicacion = rs.getString("ubicacion");
					fecha = rs.getString("fecha");
					cantidad = rs.getInt("cantidad");
	
					//GUARDA EN ARRAY LIST MovimientoAlmacen
					MovimientoAlmacenes.add(new MovimientoAlmacen(
							idMovimientoAlmacen, 
							idArticulo, 
							tipoMovimiento, 
							ubicacion, 
							fecha,
							cantidad));
				}

				rs.close();
				sentencia.close();
				c.close();
			} catch (Exception e) {
				System.out.println("impossible");
				Talal: 	System.out.println(e.getMessage());
			}
			return MovimientoAlmacenes;
		}
};