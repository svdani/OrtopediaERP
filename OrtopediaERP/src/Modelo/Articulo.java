package Modelo;

public class Articulo{
	
	String idArticulo;
	String idProveedor;
	String nombre;
	double precio;
	int stock;

	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	/**
	 * @param idArticulo
	 * @param idProveedor
	 * @param nombre
	 * @param precio
	 * @param stock
	 */
	public Articulo(String idArticulo, String idProveedor, String nombre, double precio, int stock) {
		super();
		this.idArticulo = idArticulo;
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	
	/**
	 * @param idArticulo
	 * @param stock
	 */
	public Articulo(String idArticulo, int stock) {
		this.idArticulo = idArticulo;
		this.stock = stock;
	}	

	/**
	 * @param idArticulo
	 */
	public Articulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}	

	//---------------------------------------------------------------------------------------------GETTER AND SETTER
	
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
	 * @return the idProveedor
	 */
	public String getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	//---------------------------------------------------------------------------------------------TO STRING
	
	@Override
	public String toString() {
		return "Articulo [idArticulo=" + idArticulo + ", idProveedor=" + idProveedor + ", nombre=" + nombre
				+ ", precio=" + precio + ", stock=" + stock + "]";
	}
}