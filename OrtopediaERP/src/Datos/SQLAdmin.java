package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Admin;

public class SQLAdmin {
	Connection c = null;

	Statement sentencia = null;

	//String nombreTabla;

	String dni;
	String password;
	String nombre;
	String apellidos;
	String telf;
	

	ArrayList<Admin> Admins = new ArrayList<Admin>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos Admin");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos Admin");

		}
		return c;

	}
	
	//Inserta en tabla Admin
	public void insertaAdmins(Admin adm) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Admin (dni, password, nombre, apellidos, telf) "

		            	 + "VALUES (" + "\"" + adm.getDni() + "\"" + ","
		            	 + "\"" + adm.getPassword() + "\"" + ","
		            	 + "\"" + adm.getNombre() + "\"" + ","
		            	 + "\"" + adm.getApellidos() + "\"" + ","
		            	 + "\"" + adm.getTelf() + "\"" + ");";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla Admin");

		}
	}
	
	//Modifica taula Admin
	public void modificaAdmins(Admin adm) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Admin "
							+ "SET"
							+ " password='"+ adm.getPassword()
							+ "', nombre='" + adm.getNombre()
							+ "', apellidos='" + adm.getApellidos()
							+ "', telf='" +adm.getTelf()						
							+ "' WHERE dni='" + adm.getDni() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla Admin");

		}
	}
		
	//Elimina Admin
	public void deleteAdmins(Admin adm) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Admin WHERE dni='"	+ adm.getDni() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla Admin");

		}

	}
	
	//Muestra Tabla Admin
	public ArrayList<Admin> consultaAdmins() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Admin;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				dni = rs.getString("dni");
				password = rs.getString("password");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				telf = rs.getString("Telf");
				

					
				//GUARDA EN ARRAY LIST Admin
				Admins.add(new Admin(
						dni,
						password,
						nombre, 
						apellidos,
						telf));

			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Admins;
	}
	
	//Busca Admin por Dni
	public Admin buscaDniAdmins(Admin adm) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Admin WHERE Dni = '" + adm.getDni() + "';";
		Admin Admin = new Admin(adm.getDni(),adm.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				String dni;
				String password;
				String nombre;
				String apellidos;
				String telf;
				
				dni = rs.getString("dni");
				password = rs.getString("password");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				telf = rs.getString("telf");
					
				//GUARDA EN ARRAY LIST Admin
				 Admin = new Admin(
						dni, 
						password,
						nombre, 
						apellidos, 
						telf);
			}

			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("impossible");
			Talal: 	System.out.println(e.getMessage());

		}
		return Admin;
	}
		
	//Busca Admin por Dni, Nombre, Apellidos pero por letras
	public ArrayList<Admin> buscaAdmins(String registro, String filtro) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Admin WHERE "+ filtro +" LIKE '%" + registro + "%';";
		//Admin Admin = new Admin(adm.getDni(),adm.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
				
				dni = rs.getString("dni");
				password = rs.getString("password");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				telf = rs.getString("Telf");
				

					
				//GUARDA EN ARRAY LIST Admin
				Admins.add(new Admin(
						dni,
						password,
						nombre, 
						apellidos,
						telf));

			}

			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			System.out.println("fALLO AL BUSCAR ");
			Talal: 	System.out.println(e.getMessage());

		}
		return Admins;
	}
		
};