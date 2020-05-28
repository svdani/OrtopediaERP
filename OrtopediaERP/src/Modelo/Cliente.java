package Modelo;

public class Cliente{
	
	String dni;
	String nombre;
	String apellidos;
	String direccion;
	String email;
	String telf;
	String notas;
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	/**
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param direccion
	 * @param email
	 * @param telf
	 * @param notas
	 */
	public Cliente(String dni, String nombre, String apellidos, String direccion, String email, String telf, String notas) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.email = email;
		this.telf = telf;
		this.notas = notas;
	}
	
	/**
	 * @param dni
	 */
	public Cliente (String dni) {	
		this.dni = dni;
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
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
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	/**
	 * @return the notas
	 */
	public String getNotas() {
		return notas;
	}

	/**
	 * @param notas the notas to set
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	//---------------------------------------------------------------------------------------------TO STRING
	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", email=" + email + ", telf=" + telf + ", notas=" + notas + "]";
	}

}