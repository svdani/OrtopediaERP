package Modelo;

public class Articulo{
	
	String idArticulo;
	String idProveedor;
	String nombre;
	double precio;
	int stock;

	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public Articulo(String idArticulo, String idProveedor, String nombre, double precio, int stock) {

		this.idArticulo = idArticulo;
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	
	public Articulo(String idArticulo, int stock) {

		this.idArticulo = idArticulo;
		this.stock = stock;
	}	
	
	public Articulo(String idArticulo) {

		this.idArticulo = idArticulo;
	}	

	//---------------------------------------------------------------------------------------------GETTER AND SETTER
	
	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

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