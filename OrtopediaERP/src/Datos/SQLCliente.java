package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Admin;
import Modelo.Cliente;

public class SQLCliente {
	Connection c = null;

	Statement sentencia = null;

	//String nombreTabla;

	String dni;
	String nombre;
	String apellidos;
	String direccion;
	String email;
	String telf;
	String notas;	

	ArrayList<Cliente> Clientes = new ArrayList<Cliente>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos Cliente");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos Cliente");

		}
		return c;

	}
	
	//Inserta en tabla Cliente
	public void insertaClientes(Cliente cli) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Cliente (dni, nombre, apellidos, direccion, email, telf, notas) "

		            	 + "VALUES (" + "\"" + cli.getDni() + "\"" + ","
		            	 + "\"" + cli.getNombre() + "\"" + ","
		            	 + "\"" + cli.getApellidos() + "\"" + ","
		            	 + "\"" + cli.getDireccion() + "\"" + ","
		            	 + "\"" + cli.getEmail() + "\"" + ","
		            	 + "\"" + cli.getTelf() + "\"" + ","
		            	 + "\"" + cli.getNotas() + "\"" + ");";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla Cliente");

		}
	}
	
	//Modifica taula Cliente
	public void modificaClientes(Cliente cli) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Cliente "
							+ "SET"
							+ " nombre='" + cli.getNombre()
							+ "', apellidos='" + cli.getApellidos()
							+ "', direccion='" + cli.getDireccion()
							+ "', email='" + cli.getEmail()
							+ "', telf='" +cli.getTelf()
							+ "', notas='"+ cli.getNotas()
							+ "' WHERE dni='" + cli.getDni() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla Cliente");

		}
	}
		
	//Elimina Cliente
	public void deleteClientes(Cliente cli) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Cliente WHERE dni='"	+ cli.getDni() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla Cliente");

		}

	}
	
	//Muestra Tabla Cliente
	public ArrayList<Cliente> consultaClientes() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Cliente;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				direccion = rs.getString("direccion");
				email = rs.getString("email");
				telf = rs.getString("Telf");
				notas = rs.getString("notas");

					
				//GUARDA EN ARRAY LIST Cliente
				Clientes.add(new Cliente(
						dni, 
						nombre, 
						apellidos,
						direccion, 
						email, 
						telf, 
						notas));

			//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Clientes;
	}
	
	//Busca Clientes por Dni, Nombre, Apellidos pero por letras
	public  ArrayList<Cliente>  buscaClientes(String registro, String filtro) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Cliente WHERE "+ filtro +" LIKE '%" + registro + "%';";
		//Admin Admin = new Admin(adm.getDni(),adm.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
				
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				direccion = rs.getString("direccion");
				email = rs.getString("email");
				telf = rs.getString("Telf");
				notas = rs.getString("notas");
					
				//GUARDA EN ARRAY LIST Cliente
				Clientes.add(new Cliente(
						dni, 
						nombre, 
						apellidos,
						direccion, 
						email, 
						telf, 
						notas));

			}

			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			System.out.println("fALLO AL BUSCAR ");
			Talal: 	System.out.println(e.getMessage());

		}
		return Clientes;
	}
	
	/*
	//Muestra Deutor Tabla Cliente
	public ArrayList<Cliente> consultaDeutorClientes() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Cliente WHERE Deutor = 'true';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				dni = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Cliente
				Clientes.add(new Cliente(
						dni, 
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
		return Clientes;
	}
	
	//Muestra Admins Tabla Cliente
	public ArrayList<Cliente> consultaAdminClientes() throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM Cliente WHERE Rol = 'A';";
			
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				//int i = 0;//-------------CONTADOR PARA LA MATRIZ
				while (rs.next()) {
						
					dni = rs.getString("Dni");
					password = rs.getString("Password");
					rol = rs.getString("Rol");
					nom = rs.getString("Nom");
					cognom = rs.getString("Cognom");
					adresa = rs.getString("adresa");
					telf = rs.getString("Telf");
					correu = rs.getString("Correu");
					deutor = rs.getString("Deutor");
						
					//GUARDA EN ARRAY LIST Cliente
					Clientes.add(new Cliente(
							dni, 
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
			return Clientes;
		}
		
	//Busca Cliente per Dni
	public Cliente buscaDniClientes(Cliente cli) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Cliente WHERE Dni = '" + cli.getDni() + "';";
		Cliente Cliente = new Cliente(cli.getDni(),cli.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				dni = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Cliente
				 Cliente = new Cliente(
						dni, 
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
		return Cliente;
	}
		
	//Busca Cliente	per dni pero por letras
	public ArrayList<Cliente> buscaClientes(Cliente cli) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Cliente WHERE Dni LIKE '%" + cli.getDni() + "%';";
		//Cliente Cliente = new Cliente(cli.getDni(),cli.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				dni = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Cliente
				Clientes.add(new Cliente(
						dni, 
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
		return Clientes;
	}
	*/	
};