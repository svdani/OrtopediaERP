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
	
	public Pedido(int idPedido, String idCliente, double precioTotal, String estado, String fechaInicio, String fechaLimite, String descripcion) {
		
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.precioTotal = precioTotal;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaLimite = fechaLimite;
		this.descripcion = descripcion;
	}
	
	public Pedido( String idCliente, String estado, String fechaLimite, String descripcion) {
		
		this.idCliente = idCliente;
		this.estado = estado;
		this.fechaInicio = sdf.format(new Date());
		this.fechaLimite = fechaLimite;
		this.descripcion = descripcion;
	}
	
	public Pedido(int idPedido, double precioTotal) {
	
		this.precioTotal = precioTotal;
		this.idPedido = idPedido;
	}

	public Pedido(int idPedido) {
		
		this.idPedido = idPedido;
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	//---------------------------------------------------------------------------------------------TO STRING
	

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", idCliente=" + idCliente + ", precioTotal=" + precioTotal
				+ ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaLimite=" + fechaLimite
				+ ", descripcion=" + descripcion + "]\n";
	}	
	
}