package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido{
	int idPedido;
	String idCliente;
	double precioTotal;
	String estado;
	String fechaInicio;
	String fechaLimite;
	String descripcion;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	/**
	 * @param idPedido
	 * @param idCliente
	 * @param precioTotal
	 * @param estado
	 * @param fechaInicio
	 * @param fechaLimite
	 * @param descripcion
	 */
	public Pedido(int idPedido, String idCliente, double precioTotal, String estado, String fechaInicio, String fechaLimite, String descripcion) {
		super();
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.precioTotal = precioTotal;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaLimite = fechaLimite;
		this.descripcion = descripcion;
	}

	/**
	 * @param idCliente
	 * @param estado
	 * @param fechaLimite
	 * @param descripcion
	 */
	public Pedido( String idCliente, String estado, String fechaLimite, String descripcion) {
		this.idCliente = idCliente;
		this.estado = estado;
		this.fechaInicio = sdf.format(new Date());
		this.fechaLimite = fechaLimite;
		this.descripcion = descripcion;
	}
	
	/**
	 * @param idPedido
	 * @param precioTotal
	 */
	public Pedido(int idPedido, double precioTotal) {
	
		this.precioTotal = precioTotal;
		this.idPedido = idPedido;
	}

	/**
	 * @param idPedido
	 */
	public Pedido(int idPedido) {
		
		this.idPedido = idPedido;
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER

	/**
	 * @return the idPedido
	 */
	public int getIdPedido() {
		return idPedido;
	}

	/**
	 * @param idPedido the idPedido to set
	 */
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the precioTotal
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaLimite
	 */
	public String getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * @param fechaLimite the fechaLimite to set
	 */
	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return "Pedido [idPedido=" + idPedido + ", idCliente=" + idCliente + ", precioTotal=" + precioTotal
				+ ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaLimite=" + fechaLimite
				+ ", descripcion=" + descripcion + "]\n";
	}

}