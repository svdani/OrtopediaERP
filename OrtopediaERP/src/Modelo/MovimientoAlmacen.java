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
	
	public int getIdMovimientoAlmacen() {
		return idMovimientoAlmacen;
	}

	public void setIdMovimientoAlmacen(int idMovimientoAlmacen) {
		this.idMovimientoAlmacen = idMovimientoAlmacen;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	//---------------------------------------------------------------------------------------------TO STRING	

	@Override
	public String toString() {
		return "MovimientoAlmacen [idMovimientoAlmacen=" + idMovimientoAlmacen + ", idArticulo=" + idArticulo
				+ ", tipoMovimiento=" + tipoMovimiento + ", ubicacion=" + ubicacion + ", fecha=" + fecha +  "]\n";
	}
	
}