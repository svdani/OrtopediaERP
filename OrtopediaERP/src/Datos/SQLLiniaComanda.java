package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.LiniaComanda;

public class SQLLiniaComanda {
	Connection c = null;

	Statement sentencia = null;

	//String idComandaTabla;

	int idLiniaComanda;
	int idComanda;
	String idArticulo;
	String estado;
	String tipo;
	double precio;
	int cantidad;	

	ArrayList<LiniaComanda> LiniaComandas = new ArrayList<LiniaComanda>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos LiniaComanda");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos LiniaComanda");

		}
		return c;

	}
	
	//Inserta en tabla LiniaComanda
	public void insertaLiniaComandas(LiniaComanda cli) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO LiniaComanda (idComanda, idArticulo, estado, tipo, precio, cantidad) "

		            	 + "VALUES (" 
		            	 + "" + cli.getIdComanda() + ","
		            	 + "'" + cli.getIdArticulo() + "',"
		            	 + "'" + cli.getEstado() + "',"
		            	 + "'" + cli.getTipo()+ "',"
		            	 + "" + cli.getPrecio() + ","
		            	 + "'" + cli.getCantidad() + "');";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla LiniaComanda");

		}
	}
	
	//Modifica taula LiniaComanda
	public void modificaLiniaComandas(LiniaComanda cli) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE LiniaComanda "
							+ "SET"
							+ " idComanda='" + cli.getIdComanda()
							+ "', idArticulo='" + cli.getIdArticulo()
							+ "', estado='" + cli.getEstado()
							+ "', tipo='" + cli.getTipo()
							+ "', precio='" +cli.getPrecio()
							+ "', cantidad='"+ cli.getCantidad()
							+ "' WHERE idLiniaComanda=" + cli.getIdLiniaComanda() + ";";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla LiniaComanda");

		}
	}
		
	//Elimina LiniaComanda
	public void deleteLiniaComandas(LiniaComanda cli) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM LiniaComanda WHERE idLiniaComanda='"	+ cli.getIdLiniaComanda() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla LiniaComanda");

		}

	}
	
	//Muestra Tabla LiniaComanda
	public ArrayList<LiniaComanda> consultaLiniaComandas() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaComanda;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idLiniaComanda = rs.getInt("idLiniaComanda");
				idComanda = rs.getInt("idComanda");
				idArticulo = rs.getString("idArticulo");
				estado = rs.getString("estado");
				tipo = rs.getString("tipo");
				precio = rs.getDouble("precio");
				cantidad = rs.getInt("cantidad");

					
				//GUARDA EN ARRAY LIST LiniaComanda
				LiniaComandas.add(new LiniaComanda(
						idLiniaComanda, 
						idComanda, 
						idArticulo,
						estado, 
						tipo, 
						precio, 
						cantidad));

			//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return LiniaComandas;
	}
	/*
	//Muestra Deutor Tabla LiniaComanda
	public ArrayList<LiniaComanda> consultaDeutorLiniaComandas() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaComanda WHERE Deutor = 'true';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idLiniaComanda = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				precio = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST LiniaComanda
				LiniaComandas.add(new LiniaComanda(
						idLiniaComanda, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						precio, 
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
		return LiniaComandas;
	}
	
	//Muestra Admins Tabla LiniaComanda
	public ArrayList<LiniaComanda> consultaAdminLiniaComandas() throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM LiniaComanda WHERE Rol = 'A';";
			
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				//int i = 0;//-------------CONTADOR PARA LA MATRIZ
				while (rs.next()) {
						
					idLiniaComanda = rs.getString("Dni");
					password = rs.getString("Password");
					rol = rs.getString("Rol");
					nom = rs.getString("Nom");
					cognom = rs.getString("Cognom");
					adresa = rs.getString("adresa");
					precio = rs.getString("Telf");
					correu = rs.getString("Correu");
					deutor = rs.getString("Deutor");
						
					//GUARDA EN ARRAY LIST LiniaComanda
					LiniaComandas.add(new LiniaComanda(
							idLiniaComanda, 
							password, 
							rol,
							nom, 
							cognom, 
							adresa, 
							precio, 
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
			return LiniaComandas;
		}
		
	//Busca LiniaComanda per Dni
	public LiniaComanda buscaDniLiniaComandas(LiniaComanda cli) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaComanda WHERE Dni = '" + cli.getDni() + "';";
		LiniaComanda LiniaComanda = new LiniaComanda(cli.getDni(),cli.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idLiniaComanda = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				precio = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST LiniaComanda
				 LiniaComanda = new LiniaComanda(
						idLiniaComanda, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						precio, 
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
		return LiniaComanda;
	}
		
	//Busca LiniaComanda	per idLiniaComanda pero por letras
	public ArrayList<LiniaComanda> buscaLiniaComandas(LiniaComanda cli) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaComanda WHERE Dni LIKE '%" + cli.getDni() + "%';";
		//LiniaComanda LiniaComanda = new LiniaComanda(cli.getDni(),cli.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				idLiniaComanda = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				precio = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST LiniaComanda
				LiniaComandas.add(new LiniaComanda(
						idLiniaComanda, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						precio, 
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
		return LiniaComandas;
	}
	*/	
};