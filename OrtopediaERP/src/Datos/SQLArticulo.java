package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Articulo;

public class SQLArticulo {
	Connection c = null;

	Statement sentencia = null;

	//String nombreTabla;

	String idArticulo;
	String idProveedor;
	String nombre;
	double precio;
	int stock;
	

	ArrayList<Articulo> Articulos = new ArrayList<Articulo>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos Articulo");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos Articulo");

		}
		return c;

	}
	
	//Inserta en tabla Articulo
	public void insertaArticulos(Articulo cli) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Articulo (idArticulo, idProveedor, nombre, precio, stock) "

		            	 + "VALUES ('" + cli.getIdArticulo() + "', '"
		            	 + cli.getIdProveedor() + "', '"
		            	 + cli.getNombre() + "', "
		            	 + cli.getPrecio() + ", "
		            	 + cli.getStock() + ");";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla Articulo");

		}
	}
	
	//Modifica taula Articulo
	public void modificaArticulos(Articulo cli) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Articulo "
							+ "SET"
							+ " idProveedor='"+ cli.getIdProveedor()
							+ "', nombre='" + cli.getNombre()
							+ "', precio=" + cli.getPrecio()
							+ ", stock=" +cli.getStock()						
							+ " WHERE idArticulo='" + cli.getIdArticulo() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla Articulo");

		}
	}
		
	//Elimina Articulo
	public void deleteArticulos(Articulo cli) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Articulo WHERE idArticulo='" + cli.getIdArticulo() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla Articulo");

		}

	}
	
	//Muestra Tabla Articulo
	public ArrayList<Articulo> consultaArticulos() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Articulo;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idArticulo = rs.getString("idArticulo");
				idProveedor = rs.getString("idProveedor");
				nombre = rs.getString("nombre");
				precio = rs.getDouble("precio");
				stock = rs.getInt("stock");
				

					
				//GUARDA EN ARRAY LIST Articulo
				Articulos.add(new Articulo(
						idArticulo,
						idProveedor,
						nombre, 
						precio,
						stock));

			//i++;//---------- AUMENTA CONTADOR
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Articulos;
	}
	/*
	//Muestra Deutor Tabla Articulo
	public ArrayList<Articulo> consultaDeutorArticulos() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Articulo WHERE Deutor = 'true';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idArticulo = rs.getString("idArticulo");
				idProveedor = rs.getString("idProveedor");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Articulo
				Articulos.add(new Articulo(
						idArticulo, 
						idProveedor, 
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
		return Articulos;
	}
	
	//Muestra Articulos Tabla Articulo
	public ArrayList<Articulo> consultaArticuloArticulos() throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM Articulo WHERE Rol = 'A';";
			
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				//int i = 0;//-------------CONTADOR PARA LA MATRIZ
				while (rs.next()) {
						
					idArticulo = rs.getString("idArticulo");
					idProveedor = rs.getString("idProveedor");
					rol = rs.getString("Rol");
					nom = rs.getString("Nom");
					cognom = rs.getString("Cognom");
					adresa = rs.getString("adresa");
					telf = rs.getString("Telf");
					correu = rs.getString("Correu");
					deutor = rs.getString("Deutor");
						
					//GUARDA EN ARRAY LIST Articulo
					Articulos.add(new Articulo(
							idArticulo, 
							idProveedor, 
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
			return Articulos;
		}
		
	//Busca Articulo per idArticulo
	public Articulo buscaidArticuloArticulos(Articulo cli) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Articulo WHERE idArticulo = '" + cli.getidArticulo() + "';";
		Articulo Articulo = new Articulo(cli.getidArticulo(),cli.getidProveedor());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			//int i = 0;//-------------CONTADOR PARA LA MATRIZ
			while (rs.next()) {
					
				idArticulo = rs.getString("idArticulo");
				idProveedor = rs.getString("idProveedor");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Articulo
				 Articulo = new Articulo(
						idArticulo, 
						idProveedor, 
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
		return Articulo;
	}
		
	//Busca Articulo	per idArticulo pero por letras
	public ArrayList<Articulo> buscaArticulos(Articulo cli) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Articulo WHERE idArticulo LIKE '%" + cli.getidArticulo() + "%';";
		//Articulo Articulo = new Articulo(cli.getidArticulo(),cli.getidProveedor());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				idArticulo = rs.getString("idArticulo");
				idProveedor = rs.getString("idProveedor");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				//GUARDA EN ARRAY LIST Articulo
				Articulos.add(new Articulo(
						idArticulo, 
						idProveedor, 
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
		return Articulos;
	}
	*/	
};