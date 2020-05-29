package Controlador;

import java.sql.SQLException;

import javax.swing.JDialog;

import Datos.*;
import Modelo.*;
import Vista.*;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		System.out.println("hola");
		ViewLogin window = new ViewLogin();
		window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		
		
		//----------------------------------------- TEST SQL
		
		//CLIENTE
		//SQLCliente con = new SQLCliente();
		//Cliente cli = new Cliente("dni", "nombre", "apellidos", "direccion", "email", "telf", "notas");
		//Cliente cli = new Cliente("dni", "Nombre", "Apellidos", "Direccion", "Email", "Telf", "Notas");	
		//con.conectar();
		//con.deleteClientes(cli);
		//con.insertaClientes(cli);
		//con.modificaClientes(cli);
		//System.out.println(con.consultaClientes());		
		
		//ADMIN 
		/*
		SQLAdmin conA = new SQLAdmin();
		Admin adm = new Admin("dni", "password", "nombre", "apellidos", "telf");
		Admin adm = new Admin("dni", "Password", "Nombre", "Apellidos", "Telf");
		conA.conectar();
		conA.deleteAdmins(adm);
		conA.insertaAdmins(adm);
		conA.modificaAdmins(adm);
		System.out.println(conA.consultaAdmins());
		*/		
		
		//ARTICULO 
		/*	
		SQLArticulo conAr = new SQLArticulo();
		Articulo art = new Articulo("idArticulo", "idProveedor", "nombre", 1.5, 2); 
		Articulo art = new Articulo("idArticulo", "idProveedor", "nombre", 5.5, 8); 
		conAr.conectar();
		conAr.deleteArticulos(art);
		conAr.insertaArticulos(art);
		conAr.modificaArticulos(art);
		System.out.println(conAr.consultaArticulos());
		 */		 		
		
		//COMANDA 
		/*	
		SQLComanda conCo = new SQLComanda();
		Comanda com = new Comanda( "idCliente", 20.5, "estado", "fechaLimite", "descripcion");
		Comanda com = new Comanda(2, "idCliente", 250.5, "estado", "fechaInicial", "fechaLimite", "descripcion");
		conCo.conectar();
		conCo.deleteComandas( new Comanda(3));
		conCo.insertaComandas(com);
		conCo.modificaComandas(com);
		System.out.println(conCo.consultaComandas());
		*/		 			
		
		//MOVIMIENTO ALMACEN
		/*			
		SQLMovimientoAlmacen conM = new SQLMovimientoAlmacen();
		MovimientoAlmacen mov =   new MovimientoAlmacen("IdArticulo", "TipoMovimiento", "Ubicacion" );
		MovimientoAlmacen mov =   new MovimientoAlmacen(1,"IdArticulo", "TipoMovimiento", "Ubicacion", "fecha");
		conM.deleteMovimientoAlmacens(mov);
		conM.insertaMovimientoAlmacens(mov);
		conM.modificaMovimientoAlmacens(mov);
		System.out.println(conM.consultaMovimientoAlmacens());
		*/		
		
		//MOVIMIENTO ALMACEN
		/*
		SQLProveedor conP = new SQLProveedor();
		Proveedor pro = new Proveedor("idProveedor", "nombre", "email", "telf");
		Proveedor pro = new Proveedor("idProveedor", "Nombre", "Email", "Telf");
		conP.deleteProveedors(pro);
		conP.insertaProveedors(pro);
		conP.modificaProveedors(pro);
		System.out.println(conP.consultaProveedors());
		*/
		
	}
}