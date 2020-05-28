package Modelo;

public class Proveedor{
	
	String idProveedor;
	String nombre;
	String email;
	String telf;
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public Proveedor(String idProveedor, String nombre, String email, String telf) {

		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.email = email;
		this.telf = telf;
	}
	
	public Proveedor(String idProveedor) {

		this.idProveedor = idProveedor;
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}
	
	//---------------------------------------------------------------------------------------------TO STRING
	
	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + ", nombre=" + nombre + ", email=" + email + ", telf=" + telf
				+ "]";
	}	

}