package Modelo;

public class LiniaComanda{
	
	int idLiniaComanda;
	int idComanda;
	String idArticulo;
	String estado;
	String tipo;
	double precio;
	int cantidad;

	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public LiniaComanda(int idLiniaComanda, int idComanda, String idArticulo, String estado, String tipo, double precio, int cantidad) {

		this.idLiniaComanda = idLiniaComanda;
		this.idComanda = idComanda;
		this.idArticulo = idArticulo;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public LiniaComanda(int idComanda, String idArticulo, String estado, String tipo, double precio, int cantidad) {

		this.idComanda = idComanda;
		this.idArticulo = idArticulo;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public LiniaComanda(int idComanda, int idLiniaComanda) {
		this.idComanda = idComanda;
		this.idLiniaComanda = idLiniaComanda;
	}
	
	public LiniaComanda(int idLiniaComanda) {

		this.idLiniaComanda = idLiniaComanda;
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER
		
	public int getIdLiniaComanda() {
		return idLiniaComanda;
	}

	public void setIdLiniaComanda(int idLiniaComanda) {
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
	