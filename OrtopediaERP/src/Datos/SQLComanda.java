package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Comanda;
import Modelo.LiniaComanda;

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
	public void insertaComandas(Comanda com) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Comanda (idCliente, precioTotal, estado, fechaInicio, fechaLimite, descripcion) "

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

			System.out.println("Error al insertertar datos en la tabla Comanda");

		}
	}
	
	//Modifica taula Comanda
	public void modificaComandas(Comanda com) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Comanda "
							+ "SET"
							+ " idCliente='" + com.getIdCliente()
							+ "', precioTotal='" + com.getPrecioTotal()
							+ "', estado='" + com.getEstado()
							+ "', fechaInicio='" + com.getFechaInicio()
							+ "', fechaLimite='" +com.getFechaLimite()
							+ "', descripcion='"+ com.getDescripcion()
							+ "' WHERE idComanda='" + com.getIdComanda() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla Comanda");

		}
	}
	
	//Modifica taula Comanda
	public void modificaPrecioComanda(Comanda com) throws SQLException {

		try {

			conectar();

			String sqlUpdate ="UPDATE Comanda "
					+ "SET "
					+ "precioTotal= " + com.getPrecioTotal()
					+ " WHERE idComanda= " + com.getIdComanda() + ";";
			
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
	public void deleteComandas(Comanda com) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Comanda WHERE idComanda='"	+ com.getIdComanda() + "';";
			
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
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return Comandas;
	}
	
	//Busca Comanda por Estado
	public ArrayList<Comanda> filtraComandasEstado(String est) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Comanda WHERE Estado = '" + est + "';";
	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
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
			}

			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("impossible");
			Talal: 	System.out.println(e.getMessage());
		}
		return Comandas;
	}
			
	//Busca Comanda	por Estado
	public ArrayList<Comanda> buscaComandas(String cli) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Comanda WHERE idCliente LIKE '%" + cli + "%';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

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
	
	//Busca Comanda	por Estado
	public ArrayList<Comanda> filtraComandasFecha(String column, String desde, String hasta) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Comanda WHERE "+ column +" BETWEEN '" + desde + "' AND '" + hasta + "';";
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

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

	//Obtiene la ultima comanda registrada
	public int ultimaComanda() throws SQLException {
		int valor = 0;
		ResultSet rs;
		try {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT max(idComanda) FROM Comanda;";

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