package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comanda{
	int idComanda;
	String idCliente;
	double precioTotal;
	String estado;
	String fechaInicio;
	String fechaLimite;
	String descripcion;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public Comanda(int idComanda, String idCliente, double precioTotal, String estado, String fechaInicio, String fechaLimite, String descripcion) {
		
		this.idComanda = idComanda;
		this.idCliente = idCliente;
		this.precioTotal = precioTotal;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaLimite = fechaLimite;
		this.descripcion = descripcion;
	}
	
	public Comanda( String idCliente, String estado, String fechaLimite, String descripcion) {
		
		this.idCliente = idCliente;
		this.estado = estado;
		this.fechaInicio = sdf.format(new Date());
		this.fechaLimite = fechaLimite;
		this.descripcion = descripcion;
	}
	
	public Comanda(int idComanda, double precioTotal) {
	
		this.precioTotal = precioTotal;
		this.idComanda = idComanda;
	}

	public Comanda(int idComanda) {
		
		this.idComanda = idComanda;
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER
	
	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
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
		return "Comanda [idComanda=" + idComanda + ", idCliente=" + idCliente + ", precioTotal=" + precioTotal
				+ ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaLimite=" + fechaLimite
				+ ", descripcion=" + descripcion + "]\n";
	}	
	
}