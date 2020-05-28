package Modelo;

public class LiniaPedido{
	
	int idLiniaPedido;
	int idPedido;
	String idArticulo;
	String estado;
	String tipo;
	double precio;
	int cantidad;

	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public LiniaPedido(int idLiniaPedido, int idPedido, String idArticulo, String estado, String tipo, double precio, int cantidad) {
		this.idLiniaPedido = idLiniaPedido;
		this.idPedido = idPedido;
		this.idArticulo = idArticulo;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public LiniaPedido(int idPedido, String idArticulo, String estado, String tipo, double precio, int cantidad) {

		this.idPedido = idPedido;
		this.idArticulo = idArticulo;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public LiniaPedido(int idPedido, int idLiniaPedido) {
		this.idPedido = idPedido;
		this.idLiniaPedido = idLiniaPedido;
	}
	
	public LiniaPedido(int idLiniaPedido) {

		this.idLiniaPedido = idLiniaPedido;
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER
		
	public int getIdLiniaPedido() {
		return idLiniaPedido;
	}

	public void setIdLiniaPedido(int idLiniaPedido) {
		this.idLiniaPedido = idLiniaPedido;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
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
		return "LiniaPedido [idLiniaPedido=" + idLiniaPedido + ", idPedido=" + idPedido + ", idArticulo="
				+ idArticulo + ", estado=" + estado + ", tipo=" + tipo + ", precio=" + precio + ", cantidad=" + cantidad
				+ "]";
	}
 
}
	