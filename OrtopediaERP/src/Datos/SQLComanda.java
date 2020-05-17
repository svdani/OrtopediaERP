package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Comanda;

public class SQLComanda {
	Connection c = null;

	Statement sentencia = null;

	//String idClienteTabla;

	int idComanda;
	String idCliente;
	double precioTotal;
	String estado;
	String fechaInicio;
	String fechaLimite;
	String descripcion;	

	ArrayList<Comanda> Comandas = new ArrayList<Comanda>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos Comanda");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos Comanda");

		}
		return c;

	}
	
	//Inserta en tabla Comanda
	public void insertaComandas(Comanda cli) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Comanda (idCliente, precioTotal, estado, fechaInicio, fechaLimite, descripcion) "

		            	 + "VALUES (" 
		            	 + "'" + cli.getIdCliente() + "',"
		            	 + "" + cli.getPrecioTotal() + ","
		            	 + "'" + cli.getEstado() + "',"
		            	 + "'" + cli.getFechaInicio() + "',"
		            	 + "'" + cli.getFechaLimite() + "',"
		            	 + "'" + cli.getDescripcion() + "');";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla Comanda");

		}
	}
	
	//Modifica taula Comanda
	public void modificaComandas(Comanda cli) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Comanda "
							+ "SET"
							+ " idCliente='" + cli.getIdCliente()
							+ "', precioTotal='" + cli.getPrecioTotal()
							+ "', estado='" + cli.getEstado()
							+ "', fechaInicio='" + cli.getFechaInicio()
							+ "', fechaLimite='" +cli.getFechaLimite()
							+ "', descripcion='"+ cli.getDescripcion()
							+ "' WHERE idComanda='" + cli.getIdComanda() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla Comanda");

		}
	}
		
	//Elimina Comanda
	public void deleteComandas(Comanda cli) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Comanda WHERE idComanda='"	+ cli.getIdComanda() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla Comanda");

		}

	}
	
	//Muestra Tabla Comanda
	public ArrayList<Comanda> consultaComandas() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Comanda;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idComanda = rs.getInt("idComanda");
				idCliente = rs.getString("idCliente");
				precioTotal = rs.getDouble("precioTotal");
				estado = rs.getString("estado");
				fechaInicio = rs.getString("fechaInicio");
				fechaLimite = rs.getString("fechaLimite");
				descripcion = rs.getString("descripcion");

					
				//GUARDA EN ARRAY LIST Comanda
				Comandas.add(new Comanda(
						idComanda, 
						idCliente, 
						precioTotal,
						estado, 
						fechaInicio, 
						fechaLimite, 
						descripcion));

			//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Comandas;
	}
	/*
	//Muestra Deutor Tabla Comanda
	public ArrayList<Comanda> consultaDeutorComandas() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Comanda WHERE Deutor = 'true';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idComanda = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				fechaLimite = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Comanda
				Comandas.add(new Comanda(
						idComanda, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						fechaLimite, 
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
		return Comandas;
	}
	
	//Muestra Admins Tabla Comanda
	public ArrayList<Comanda> consultaAdminComandas() throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM Comanda WHERE Rol = 'A';";
			
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				//int i = 0;//-------------CONTADOR PARA LA MATRIZ
				while (rs.next()) {
						
					idComanda = rs.getString("Dni");
					password = rs.getString("Password");
					rol = rs.getString("Rol");
					nom = rs.getString("Nom");
					cognom = rs.getString("Cognom");
					adresa = rs.getString("adresa");
					fechaLimite = rs.getString("Telf");
					correu = rs.getString("Correu");
					deutor = rs.getString("Deutor");
						
					//GUARDA EN ARRAY LIST Comanda
					Comandas.add(new Comanda(
							idComanda, 
							password, 
							rol,
							nom, 
							cognom, 
							adresa, 
							fechaLimite, 
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
			return Comandas;
		}
		
	//Busca Comanda per Dni
	public Comanda buscaDniComandas(Comanda cli) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Comanda WHERE Dni = '" + cli.getDni() + "';";
		Comanda Comanda = new Comanda(cli.getDni(),cli.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idComanda = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				fechaLimite = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Comanda
				 Comanda = new Comanda(
						idComanda, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						fechaLimite, 
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
		return Comanda;
	}
		
	//Busca Comanda	per idComanda pero por letras
	public ArrayList<Comanda> buscaComandas(Comanda cli) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Comanda WHERE Dni LIKE '%" + cli.getDni() + "%';";
		//Comanda Comanda = new Comanda(cli.getDni(),cli.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				idComanda = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				fechaLimite = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Comanda
				Comandas.add(new Comanda(
						idComanda, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						fechaLimite, 
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
		return Comandas;
	}
	*/	
};