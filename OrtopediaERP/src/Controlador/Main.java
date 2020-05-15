package Controlador;

import java.sql.SQLException;

import Datos.*;

public class Main {
	public static void main(String[] args) throws SQLException {

		SQLCliente con = new SQLCliente();
		con.conectar();
	}
}