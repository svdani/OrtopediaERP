package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Pedido;
import Modelo.LiniaPedido;

public class SQLPedido {
	Connection c = null;

	Statement sentencia = null;

	//String idClienteTabla;

	int idPedido;
	String idCliente;
	double precioTotal;
	String estado;
	String fechaInicio;
	String fechaLimite;
	String descripcion;	

	ArrayList<Pedido> Pedidos = new ArrayList<Pedido>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:src/servidor/Ortopedia.db");
			System.out.println("Exito al conectar con base de datos Pedido");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos Pedido");

		}
		return c;

	}
	
	//Inserta en tabla Pedido
	public void insertaPedidos(Pedido com) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Pedido (idCliente, precioTotal, estado, fechaInicio, fechaLimite, descripcion) "

		            	 + "VALUES (" 
		            	 + "'" + com.getIdCliente() + "',"
		            	 + "" + com.getPrecioTotal() + ","
		            	 + "'" + com.getEstado() + "',"
		            	 + "'" + com.getFechaInicio() + "',"
		            	 + "'" + com.getFechaLimite() + "',"
		            	 + "'" + com.getDescripcion() + "');";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla Pedido");

		}
	}
	
	//Modifica taula Pedido
	public void modificaPedidos(Pedido com) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Pedido "
							+ "SET"
							+ " idCliente='" + com.getIdCliente()
							+ "', precioTotal='" + com.getPrecioTotal()
							+ "', estado='" + com.getEstado()
							+ "', fechaInicio='" + com.getFechaInicio()
							+ "', fechaLimite='" +com.getFechaLimite()
							+ "', descripcion='"+ com.getDescripcion()
							+ "' WHERE idPedido='" + com.getIdPedido() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
			System.out.println("Datos actualizados");

		} catch (Exception e) {
			System.out.println("Error al actualizar datos en la tabla Pedido");

		}
	}
	
	//Modifica taula Pedido
	public void modificaPrecioPedido(Pedido com) throws SQLException {

		try {

			conectar();

			String sqlUpdate ="UPDATE Pedido "
					+ "SET "
					+ "precioTotal= " + com.getPrecioTotal()
					+ " WHERE idPedido= " + com.getIdPedido() + ";";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
			System.out.println("Datos actualizados");

		} catch (Exception e) {
			System.out.println("Error al actualizar precio en la tabla Pedido");

		}
	}
	
	//Modifica taula Pedido
	public void modificaEstadoPedido(Pedido com, String estado) throws SQLException {

		try {
			conectar();

			String sqlUpdate ="UPDATE Pedido "
					+ "SET "
					+ "estado = '" +  estado
					+ "' WHERE idPedido= " + com.getIdPedido() + ";";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
			System.out.println("Datos actualizados");

		} catch (Exception e) {
			System.out.println("Error al actualizar Estado en la tabla Pedido");
			
		}
	}
		
	//Elimina Pedido
	public void deletePedidos(Pedido com) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Pedido WHERE idPedido='"	+ com.getIdPedido() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla Pedido");

		}

	}
	
	//Muestra Tabla Pedido
	public ArrayList<Pedido> consultaPedidos() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Pedido;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				idPedido = rs.getInt("idPedido");
				idCliente = rs.getString("idCliente");
				precioTotal = rs.getDouble("precioTotal");
				estado = rs.getString("estado");
				fechaInicio = rs.getString("fechaInicio");
				fechaLimite = rs.getString("fechaLimite");
				descripcion = rs.getString("descripcion");

					
				//GUARDA EN ARRAY LIST Pedido
				Pedidos.add(new Pedido(
						idPedido, 
						idCliente, 
						precioTotal,
						estado, 
						fechaInicio, 
						fechaLimite, 
						descripcion));
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Pedidos;
	}
	
	//Busca Pedido por Estado
	public ArrayList<Pedido> filtraPedidosEstado(String est) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Pedido WHERE Estado = '" + est + "';";
	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				idPedido = rs.getInt("idPedido");
				idCliente = rs.getString("idCliente");
				precioTotal = rs.getDouble("precioTotal");
				estado = rs.getString("estado");
				fechaInicio = rs.getString("fechaInicio");
				fechaLimite = rs.getString("fechaLimite");
				descripcion = rs.getString("descripcion");
				
				//GUARDA EN ARRAY LIST Pedido
				Pedidos.add(new Pedido(
						idPedido, 
						idCliente, 
						precioTotal,
						estado, 
						fechaInicio, 
						fechaLimite, 
						descripcion));
			}

			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("impossible");
			Talal: 	System.out.println(e.getMessage());
		}
		return Pedidos;
	}
			
	//Busca Pedido	por Estado
	public ArrayList<Pedido> buscaPedidos(String cli) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Pedido WHERE idCliente LIKE '%" + cli + "%';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				idPedido = rs.getInt("idPedido");
				idCliente = rs.getString("idCliente");
				precioTotal = rs.getDouble("precioTotal");
				estado = rs.getString("estado");
				fechaInicio = rs.getString("fechaInicio");
				fechaLimite = rs.getString("fechaLimite");
				descripcion = rs.getString("descripcion");
				
				//GUARDA EN ARRAY LIST Pedido
				Pedidos.add(new Pedido(
						idPedido, 
						idCliente, 
						precioTotal,
						estado, 
						fechaInicio, 
						fechaLimite, 
						descripcion));
			}

			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			System.out.println("fALLO AL BUSCAR ");
			Talal: 	System.out.println(e.getMessage());

		}
		return Pedidos;
	}
	
	//Busca Pedido	por Estado
	public ArrayList<Pedido> filtraPedidosFecha(String column, String desde, String hasta) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Pedido WHERE "+ column +" BETWEEN '" + desde + "' AND '" + hasta + "';";
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				idPedido = rs.getInt("idPedido");
				idCliente = rs.getString("idCliente");
				precioTotal = rs.getDouble("precioTotal");
				estado = rs.getString("estado");
				fechaInicio = rs.getString("fechaInicio");
				fechaLimite = rs.getString("fechaLimite");
				descripcion = rs.getString("descripcion");
				
				//GUARDA EN ARRAY LIST Pedido
				Pedidos.add(new Pedido(
						idPedido, 
						idCliente, 
						precioTotal,
						estado, 
						fechaInicio, 
						fechaLimite, 
						descripcion));
			}

			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			System.out.println("fALLO AL BUSCAR ");
			Talal: 	System.out.println(e.getMessage());

		}
		return Pedidos;
	}

	//Obtiene el ultimo pedido registrado
	public int ultimaPedido() throws SQLException {
		int valor = 0;
		ResultSet rs;
		try {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT max(idPedido) FROM Pedido;";

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
	
	//Obtiene el precio del pedido seleccioando
	public double precioPedido(LiniaPedido lin) throws SQLException {
		double valor = 0;
		ResultSet rs;
		try {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT precioTotal FROM Pedido Where idPedido = " + lin.getIdPedido() + ";";

			rs = sentencia.executeQuery(consultaSql);
			valor = rs.getDouble(1);
			System.out.println(valor);
			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {
			System.out.println("CUENTA ARTICULOS" + e.getMessage());
		}
		return valor;
	}
		
};