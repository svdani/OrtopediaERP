package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimientoAlmacen{
	
	int idMovimientoAlmacen;
	String idArticulo;
	String tipoMovimiento;
	String ubicacion;
	String fecha;
	int cantidad;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public MovimientoAlmacen(int idMovimientoAlmacen, String idArticulo, String tipoMovimiento, String ubicacion, String fecha, int cantidad) {

		this.idMovimientoAlmacen = idMovimientoAlmacen;
		this.idArticulo = idArticulo;
		this.tipoMovimiento = tipoMovimiento;
		this.ubicacion = ubicacion;
		this.fecha = fecha;
		this.cantidad = cantidad;
	}
	
	public MovimientoAlmacen(String idArticulo, String tipoMovimiento, String ubicacion, int cantidad) {


		this.idArticulo = idArticulo;
		this.tipoMovimiento = tipoMovimiento;
		this.ubicacion = ubicacion;
		this.fecha = sdf.format(new Date());
		this.cantidad = cantidad;
	}

	public MovimientoAlmacen(int idMovimientoAlmacen) {

		this.idMovimientoAlmacen = idMovimientoAlmacen;

	}
	//---------------------------------------------------------------------------------------------GETTER AND SETTER
	
	/**
	 * @return the idMovimientoAlmacen
	 */
	public int getIdMovimientoAlmacen() {
		return idMovimientoAlmacen;
	}

	/**
	 * @param idMovimientoAlmacen the idMovimientoAlmacen to set
	 */
	public void setIdMovimientoAlmacen(int idMovimientoAlmacen) {
		this.idMovimientoAlmacen = idMovimientoAlmacen;
	}

	/**
	 * @return the idArticulo
	 */
	public String getIdArticulo() {
		return idArticulo;
	}

	/**
	 * @param idArticulo the idArticulo to set
	 */
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	/**
	 * @return the tipoMovimiento
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}	

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	//---------------------------------------------------------------------------------------------TO STRING
	

	@Override
	public String toString() {
		return "MovimientoAlmacen [idMovimientoAlmacen=" + idMovimientoAlmacen + ", idArticulo=" + idArticulo
				+ ", tipoMovimiento=" + tipoMovimiento + ", ubicacion=" + ubicacion + ", fecha=" + fecha +  "]\n";
	}
	

	
}