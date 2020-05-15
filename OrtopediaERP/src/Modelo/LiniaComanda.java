package Modelo;

public class LiniaComanda{
	
	String idLiniaComanda;
	int idComanda;
	String idArticulo;
	String estado;
	String tipo;
	double precio;
	int cantidad;

	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public LiniaComanda(String idLiniaComanda, int idComanda, String idArticulo, String estado, String tipo, double precio, int cantidad) {

		this.idLiniaComanda = idLiniaComanda;
		this.idComanda = idComanda;
		this.idArticulo = idArticulo;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	//---------------------------------------------------------------------------------------------GETTER AND SETTER
		
	public String getIdLiniaComanda() {
		return idLiniaComanda;
	}

	public void setIdLiniaComanda(String idLiniaComanda) {
		this.idLiniaComanda = idLiniaComanda;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	//---------------------------------------------------------------------------------------------TO STRING
	
	
	@Override
	public String toString() {
		return "LiniaComanda [idLiniaComanda=" + idLiniaComanda + ", idComanda=" + idComanda + ", idArticulo="
				+ idArticulo + ", estado=" + estado + ", tipo=" + tipo + ", precio=" + precio + ", cantidad=" + cantidad
				+ "]";
	}
 
}
	