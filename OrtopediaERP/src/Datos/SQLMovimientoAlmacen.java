package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	ArrayList<MovimientoAlmacen> MovimientoAlmacens = new ArrayList<MovimientoAlmacen>();
	
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
	public void insertaMovimientoAlmacens(MovimientoAlmacen mov) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO MovimientoAlmacen (idArticulo, tipoMovimiento, ubicacion, fecha) "

		            	 + "VALUES (" 
		            	 + "'" + mov.getIdArticulo() + "',"
		            	 + "'" + mov.getTipoMovimiento() + "',"
		            	 + "'" + mov.getUbicacion() + "',"
		            	 + "'" + mov.getFecha() + "');";
			
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
	public void modificaMovimientoAlmacens(MovimientoAlmacen mov) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE MovimientoAlmacen "
							+ "SET"
							+ " idArticulo='" + mov.getIdArticulo()
							+ "', tipoMovimiento='" + mov.getTipoMovimiento()
							+ "', ubicacion='" + mov.getUbicacion()
							+ "', fecha='" + mov.getFecha()
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
	public void deleteMovimientoAlmacens(MovimientoAlmacen mov) throws SQLException {

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
	public ArrayList<MovimientoAlmacen> consultaMovimientoAlmacens() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM MovimientoAlmacen;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idMovimientoAlmacen = rs.getInt("idMovimientoAlmacen");
				idArticulo = rs.getString("idArticulo");
				tipoMovimiento = rs.getString("tipoMovimiento");
				ubicacion = rs.getString("ubicacion");
				fecha = rs.getString("fecha");

					
				//GUARDA EN ARRAY LIST MovimientoAlmacen
				MovimientoAlmacens.add(new MovimientoAlmacen(
						idMovimientoAlmacen, 
						idArticulo, 
						tipoMovimiento, 
						ubicacion, 
						fecha));

			//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return MovimientoAlmacens;
	}
	/*
	//Muestra Deutor Tabla MovimientoAlmacen
	public ArrayList<MovimientoAlmacen> consultaDeutorMovimientoAlmacens() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM MovimientoAlmacen WHERE Deutor = 'true';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idMovimientoAlmacen = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				fecha = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST MovimientoAlmacen
				MovimientoAlmacens.add(new MovimientoAlmacen(
						idMovimientoAlmacen, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						fecha, 
						correu, 
						deutor));

			//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return MovimientoAlmacens;
	}
	
	//Muestra Admins Tabla MovimientoAlmacen
	public ArrayList<MovimientoAlmacen> consultaAdminMovimientoAlmacens() throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM MovimientoAlmacen WHERE Rol = 'A';";
			
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				//int i = 0;//-------------CONTADOR PARA LA MATRIZ
				while (rs.next()) {
						
					idMovimientoAlmacen = rs.getString("Dni");
					password = rs.getString("Password");
					rol = rs.getString("Rol");
					nom = rs.getString("Nom");
					cognom = rs.getString("Cognom");
					adresa = rs.getString("adresa");
					fecha = rs.getString("Telf");
					correu = rs.getString("Correu");
					deutor = rs.getString("Deutor");
						
					//GUARDA EN ARRAY LIST MovimientoAlmacen
					MovimientoAlmacens.add(new MovimientoAlmacen(
							idMovimientoAlmacen, 
							password, 
							rol,
							nom, 
							cognom, 
							adresa, 
							fecha, 
							correu, 
							deutor));

				//i++;//---------- AUMENTA CONTADOR
				}

				rs.close();
				sentencia.close();
				c.close();

			} catch (Exception e) {

				Talal: 	System.out.println(e.getMessage());

			}
			return MovimientoAlmacens;
		}
		
	//Busca MovimientoAlmacen per Dni
	public MovimientoAlmacen buscaDniMovimientoAlmacens(MovimientoAlmacen mov) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM MovimientoAlmacen WHERE Dni = '" + mov.getDni() + "';";
		MovimientoAlmacen MovimientoAlmacen = new MovimientoAlmacen(mov.getDni(),mov.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idMovimientoAlmacen = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				fecha = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST MovimientoAlmacen
				 MovimientoAlmacen = new MovimientoAlmacen(
						idMovimientoAlmacen, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						fecha, 
						correu, 
						deutor);

				//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("impossible");
			Talal: 	System.out.println(e.getMessage());

		}
		return MovimientoAlmacen;
	}
		
	//Busca MovimientoAlmacen	per idMovimientoAlmacen pero por letras
	public ArrayList<MovimientoAlmacen> buscaMovimientoAlmacens(MovimientoAlmacen mov) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM MovimientoAlmacen WHERE Dni LIKE '%" + mov.getDni() + "%';";
		//MovimientoAlmacen MovimientoAlmacen = new MovimientoAlmacen(mov.getDni(),mov.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				idMovimientoAlmacen = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				fecha = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST MovimientoAlmacen
				MovimientoAlmacens.add(new MovimientoAlmacen(
						idMovimientoAlmacen, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						fecha, 
						correu, 
						deutor));
				
			}

			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			System.out.println("fALLO AL BUSCAR ");
			Talal: 	System.out.println(e.getMessage());

		}
		return MovimientoAlmacens;
	}
	*/	
};