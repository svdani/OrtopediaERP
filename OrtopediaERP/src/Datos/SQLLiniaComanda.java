package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Articulo;
import Modelo.LiniaComanda;

public class SQLLiniaComanda {
	Connection c = null;

	Statement sentencia = null;

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
	public void insertaLiniaComandas(LiniaComanda lin) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO LiniaComanda (idComanda, idArticulo, estado, tipo, precio, cantidad) "

		            	 + "VALUES (" 
		            	 + "" + lin.getIdComanda() + ","
		            	 + "'" + lin.getIdArticulo() + "',"
		            	 + "'" + lin.getEstado() + "',"
		            	 + "'" + lin.getTipo()+ "',"
		            	 + "" + lin.getPrecio() + ","
		            	 + "'" + lin.getCantidad() + "');";
			
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
	public void modificaLiniaComandas(LiniaComanda lin) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE LiniaComanda "
							+ "SET"
							+ " idComanda=" + lin.getIdComanda()
							+ ", idArticulo='" + lin.getIdArticulo()
							+ "', estado='" + lin.getEstado()
							+ "', tipo='" + lin.getTipo()
							+ "', precio=" +lin.getPrecio()
							+ ", cantidad="+ lin.getCantidad()
							+ " WHERE idLiniaComanda=" + lin.getIdLiniaComanda() + ";";
				System.out.println(sqlUpdate);	
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
	public void deleteLiniaComandas(LiniaComanda lin) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM LiniaComanda WHERE idLiniaComanda='"	+ lin.getIdLiniaComanda() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla LiniaComanda");

		}

	}

	//Elimina LiniaComanda
	public void deleteComandas(LiniaComanda lin) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM LiniaComanda WHERE idComanda='"	+ lin.getIdComanda() + "';";

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
	
	//Busca LiniaComanda por comanda
	public ArrayList<LiniaComanda> buscaLiniasComandas(LiniaComanda lin) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaComanda WHERE idComanda = " + lin.getIdComanda() + ";";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
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
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return LiniaComandas;
	}
	
	//Filtra LiniaComanda por estado o tipo
	public ArrayList<LiniaComanda> filtraLiniasComandas(String registro, String columna) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaComanda WHERE " + columna + " = '" + registro + "';";

		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
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
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return LiniaComandas;
	}

	//Suma precios linias
	public double precioLinias(LiniaComanda lin) throws SQLException {
		double valor = 0;
		ResultSet rs;
		try {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT SUM(precio) FROM LiniaComanda WHERE idComanda = " + lin.getIdComanda() + ";";

			rs = sentencia.executeQuery(consultaSql);
			valor = rs.getDouble(1);
			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {
			System.out.println("CUENTA ARTICULOS" + e.getMessage());
		}
		return valor;
	}	

};