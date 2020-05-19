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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telf
	 */
	public String getTelf() {
		return telf;
	}

	/**
	 * @param telf the telf to set
	 */
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