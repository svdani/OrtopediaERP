package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Proveedor;

public class SQLProveedor {
	Connection c = null;

	Statement sentencia = null;

	String idProveedor;
	String nombre;
	String email;
	String telf;

	ArrayList<Proveedor> Proveedors = new ArrayList<Proveedor>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos Proveedor");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos Proveedor");

		}
		return c;

	}
	
	//Inserta en tabla Proveedor
	public void insertaProveedors(Proveedor pro) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Proveedor (idProveedor, nombre, email, telf) "

		            	 + "VALUES (" + "\"" + pro.getIdProveedor() + "\"" + ","
		            	 + "\"" + pro.getNombre() + "\"" + ","
		            	 + "\"" + pro.getEmail() + "\"" + ","
		            	 + "\"" + pro.getTelf() + "\"" + ");";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla Proveedor");

		}
	}
	
	//Modifica taula Proveedor
	public void modificaProveedors(Proveedor pro) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Proveedor "
							+ "SET"
							+ " nombre='" + pro.getNombre()
							+ "', email='" + pro.getEmail()
							+ "', telf='" +pro.getTelf()
							+ "' WHERE idProveedor='" + pro.getIdProveedor() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla Proveedor");

		}
	}
		
	//Elimina Proveedor
	public void deleteProveedors(Proveedor pro) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Proveedor WHERE idProveedor='"	+ pro.getIdProveedor() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla Proveedor");

		}

	}
	
	//Muestra Tabla Proveedor
	public ArrayList<Proveedor> consultaProveedors() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Proveedor;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idProveedor = rs.getString("idProveedor");
				nombre = rs.getString("nombre");
				email = rs.getString("email");
				telf = rs.getString("Telf");
					
				//GUARDA EN ARRAY LIST Proveedor
				Proveedors.add(new Proveedor(
						idProveedor, 
						nombre, 
						email, 
						telf));

			//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Proveedors;
	}
	/*
	//Muestra Deutor Tabla Proveedor
	public ArrayList<Proveedor> consultaDeutorProveedors() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Proveedor WHERE Deutor = 'true';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idProveedor = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Proveedor
				Proveedors.add(new Proveedor(
						idProveedor, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						telf, 
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
		return Proveedors;
	}
	
	//Muestra Admins Tabla Proveedor
	public ArrayList<Proveedor> consultaAdminProveedors() throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM Proveedor WHERE Rol = 'A';";
			
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				//int i = 0;//-------------CONTADOR PARA LA MATRIZ
				while (rs.next()) {
						
					idProveedor = rs.getString("Dni");
					password = rs.getString("Password");
					rol = rs.getString("Rol");
					nom = rs.getString("Nom");
					cognom = rs.getString("Cognom");
					adresa = rs.getString("adresa");
					telf = rs.getString("Telf");
					correu = rs.getString("Correu");
					deutor = rs.getString("Deutor");
						
					//GUARDA EN ARRAY LIST Proveedor
					Proveedors.add(new Proveedor(
							idProveedor, 
							password, 
							rol,
							nom, 
							cognom, 
							adresa, 
							telf, 
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
			return Proveedors;
		}
		
	//Busca Proveedor per Dni
	public Proveedor buscaDniProveedors(Proveedor pro) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Proveedor WHERE Dni = '" + pro.getDni() + "';";
		Proveedor Proveedor = new Proveedor(pro.getDni(),pro.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idProveedor = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Proveedor
				 Proveedor = new Proveedor(
						idProveedor, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						telf, 
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
		return Proveedor;
	}
		
	//Busca Proveedor	per idProveedor pero por letras
	public ArrayList<Proveedor> buscaProveedors(Proveedor pro) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Proveedor WHERE Dni LIKE '%" + pro.getDni() + "%';";
		//Proveedor Proveedor = new Proveedor(pro.getDni(),pro.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				idProveedor = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Proveedor
				Proveedors.add(new Proveedor(
						idProveedor, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						telf, 
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
		return Proveedors;
	}
	*/	
};