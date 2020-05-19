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
	public void insertaArticulos(Articulo art) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Articulo (idArticulo, idProveedor, nombre, precio, stock) "

		            	 + "VALUES ('" + art.getIdArticulo() + "', '"
		            	 + art.getIdProveedor() + "', '"
		            	 + art.getNombre() + "', "
		            	 + art.getPrecio() + ", "
		            	 + art.getStock() + ");";
			
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
	public void modificaArticulos(Articulo art) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Articulo "
							+ "SET"
							+ " idProveedor='"+ art.getIdProveedor()
							+ "', nombre='" + art.getNombre()
							+ "', precio=" + art.getPrecio()
							+ ", stock=" +art.getStock()						
							+ " WHERE idArticulo='" + art.getIdArticulo() + "';";
					
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
	public void deleteArticulos(Articulo art) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Articulo WHERE idArticulo='" + art.getIdArticulo() + "';";
			
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

			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Articulos;
	}
	
	//Busca Articulos por idArticulo, idProveedor y Nombre pero por letras
	public ArrayList<Articulo> buscaArticulos(String registro, String filtro) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Articulo WHERE "+ filtro +" LIKE '%" + registro + "%';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
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

			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Articulos;
	}
};