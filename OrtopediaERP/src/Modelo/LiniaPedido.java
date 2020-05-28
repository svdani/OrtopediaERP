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
	
	/**
	 * @param idLiniaPedido
	 * @param idPedido
	 * @param idArticulo
	 * @param estado
	 * @param tipo
	 * @param precio
	 * @param cantidad
	 */
	public LiniaPedido(int idLiniaPedido, int idPedido, String idArticulo, String estado, String tipo, double precio,
			int cantidad) {
		super();
		this.idLiniaPedido = idLiniaPedido;
		this.idPedido = idPedido;
		this.idArticulo = idArticulo;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	/**
	 * @param idPedido
	 * @param idArticulo
	 * @param estado
	 * @param tipo
	 * @param precio
	 * @param cantidad
	 */
	public LiniaPedido(int idPedido, String idArticulo, String estado, String tipo, double precio, int cantidad) {

		this.idPedido = idPedido;
		this.idArticulo = idArticulo;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	/**
	 * @param idLiniaPedido
	 * @param idPedido
	 * @param cantidad
	 */
	public LiniaPedido(int idPedido, int idLiniaPedido) {
		this.idPedido = idPedido;
		this.idLiniaPedido = idLiniaPedido;
	}
	
	/**
	 * @param idLiniaPedido
	 */
	public LiniaPedido(int idLiniaPedido) {

		this.idLiniaPedido = idLiniaPedido;
	}

	//---------------------------------------------------------------------------------------------GETTER AND SETTER
	
	/**
	 * @return the idLiniaPedido
	 */
	public int getIdLiniaPedido() {
		return idLiniaPedido;
	}

	/**
	 * @param idLiniaPedido the idLiniaPedido to set
	 */
	public void setIdLiniaPedido(int idLiniaPedido) {
		this.idLiniaPedido = idLiniaPedido;
	}

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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
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
		return "LiniaPedido [idLiniaPedido=" + idLiniaPedido + ", idPedido=" + idPedido + ", idArticulo="
				+ idArticulo + ", estado=" + estado + ", tipo=" + tipo + ", precio=" + precio + ", cantidad=" + cantidad
				+ "]";
	}


}
	