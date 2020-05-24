package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Articulo;
import Modelo.LiniaPedido;

public class SQLLiniaPedido {
	Connection c = null;

	Statement sentencia = null;

	int idLiniaPedido;
	int idPedido;
	String idArticulo;
	String estado;
	String tipo;
	double precio;
	int cantidad;	

	ArrayList<LiniaPedido> LiniaPedidos = new ArrayList<LiniaPedido>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos LiniaPedido");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos LiniaPedido");

		}
		return c;

	}
	
	//Inserta en tabla LiniaPedido
	public void insertaLiniaPedidos(LiniaPedido lin) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO LiniaPedido (idPedido, idArticulo, estado, tipo, precio, cantidad) "

		            	 + "VALUES (" 
		            	 + "" + lin.getIdPedido() + ","
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

			System.out.println("Error al insertertar datos en la tabla LiniaPedido");

		}
	}
	
	//Modifica taula LiniaPedido
	public void modificaLiniaPedidos(LiniaPedido lin) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE LiniaPedido "
							+ "SET"
							+ " idPedido=" + lin.getIdPedido()
							+ ", idArticulo='" + lin.getIdArticulo()
							+ "', estado='" + lin.getEstado()
							+ "', tipo='" + lin.getTipo()
							+ "', precio=" +lin.getPrecio()
							+ ", cantidad="+ lin.getCantidad()
							+ " WHERE idLiniaPedido=" + lin.getIdLiniaPedido() + ";";

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla LiniaPedido");

		}
	}
		
	//Elimina LiniaPedido
	public void deleteLiniaPedidos(LiniaPedido lin) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM LiniaPedido WHERE idLiniaPedido='"	+ lin.getIdLiniaPedido() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla LiniaPedido");

		}

	}

	//Elimina LiniaPedido
	public void deletePedidos(LiniaPedido lin) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM LiniaPedido WHERE idPedido='"	+ lin.getIdPedido() + "';";

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla LiniaPedido");
		}
	}

	//Muestra Tabla LiniaPedido
	public ArrayList<LiniaPedido> consultaLiniaPedidos() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaPedido;";

		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {

				idLiniaPedido = rs.getInt("idLiniaPedido");
				idPedido = rs.getInt("idPedido");
				idArticulo = rs.getString("idArticulo");
				estado = rs.getString("estado");
				tipo = rs.getString("tipo");
				precio = rs.getDouble("precio");
				cantidad = rs.getInt("cantidad");


				//GUARDA EN ARRAY LIST LiniaPedido
				LiniaPedidos.add(new LiniaPedido(
						idLiniaPedido, 
						idPedido, 
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
		return LiniaPedidos;
	}
	
	//Busca LiniaPedido por pedido
	public ArrayList<LiniaPedido> buscaLiniasPedidos(LiniaPedido lin) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaPedido WHERE idPedido = " + lin.getIdPedido() + ";";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				idLiniaPedido = rs.getInt("idLiniaPedido");
				idPedido = rs.getInt("idPedido");
				idArticulo = rs.getString("idArticulo");
				estado = rs.getString("estado");
				tipo = rs.getString("tipo");
				precio = rs.getDouble("precio");
				cantidad = rs.getInt("cantidad");
					
				//GUARDA EN ARRAY LIST LiniaPedido
				LiniaPedidos.add(new LiniaPedido(
						idLiniaPedido, 
						idPedido, 
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
		return LiniaPedidos;
	}
	
	//Filtra LiniaPedido por estado o tipo
	public ArrayList<LiniaPedido> filtraLiniasPedidos(String registro, String columna) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM LiniaPedido WHERE " + columna + " = '" + registro + "';";

		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {

				idLiniaPedido = rs.getInt("idLiniaPedido");
				idPedido = rs.getInt("idPedido");
				idArticulo = rs.getString("idArticulo");
				estado = rs.getString("estado");
				tipo = rs.getString("tipo");
				precio = rs.getDouble("precio");
				cantidad = rs.getInt("cantidad");

				//GUARDA EN ARRAY LIST LiniaPedido
				LiniaPedidos.add(new LiniaPedido(
						idLiniaPedido, 
						idPedido, 
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
		return LiniaPedidos;
	}

	//Suma precios linias
	public double precioLinias(LiniaPedido lin) throws SQLException {
		double valor = 0;
		ResultSet rs;
		try {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT SUM(precio) FROM LiniaPedido WHERE idPedido = " + lin.getIdPedido() + ";";

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

	//Cuenta linias de un pedido
	public int numLinias(LiniaPedido lin) throws SQLException {
		int valor = 0;
		ResultSet rs;
		try {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT COUNT (*) FROM LiniaPedido WHERE idPedido = " + lin.getIdPedido() + ";";

			rs = sentencia.executeQuery(consultaSql);
			valor = rs.getInt(1);
			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {
			System.out.println("CUENTA ARTICULOS" + e.getMessage());
		}
		return valor;
	}
	
	//Cuenta linias FINALIZADAS de un pedido 
	public int numLiniasFinalizadas(LiniaPedido lin) throws SQLException {
		int valor = 0;
		ResultSet rs;
		try {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT COUNT (*) FROM LiniaPedido WHERE idPedido = " + lin.getIdPedido() + " AND estado = 'Finalizado';";

			rs = sentencia.executeQuery(consultaSql);
			valor = rs.getInt(1);
			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {
			System.out.println("CUENTA ARTICULOS" + e.getMessage());
		}
		return valor;
	}
};