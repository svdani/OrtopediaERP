package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimientoAlmacen{
	
	int idMovimientoAlmacen;
	String idArticulo;
	String tipoMovimiento;
	String ubicacion;
	String fecha;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public MovimientoAlmacen(int idMovimientoAlmacen, String idArticulo, String tipoMovimiento, String ubicacion,String fecha) {

		this.idMovimientoAlmacen = idMovimientoAlmacen;
		this.idArticulo = idArticulo;
		this.tipoMovimiento = tipoMovimiento;
		this.ubicacion = ubicacion;
		this.fecha = sdf.format(new Date());

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
	 * @return the sdf
	 */
	public SimpleDateFormat getSdf() {
		return sdf;
	}

	/**
	 * @param sdf the sdf to set
	 */
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	
	//---------------------------------------------------------------------------------------------TO STRING
	
	@Override
	public String toString() {
		return "MovimientoAlmacen [idMovimientoAlmacen=" + idMovimientoAlmacen + ", idArticulo=" + idArticulo
				+ ", tipoMovimiento=" + tipoMovimiento + ", ubicacion=" + ubicacion + ", fecha=" + fecha + ", sdf="
				+ sdf + "]";
	}
	

	
}