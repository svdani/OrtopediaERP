package Modelo;

public class Admin{
	String dni;
	String password;
	String nombre;
	String apellidos;
	String telf;
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	/**
	 * @param dni
	 * @param password
	 * @param nombre
	 * @param apellidos
	 * @param telf
	 */
	public Admin(String dni, String password, String nombre, String apellidos, String telf) {
		super();
		this.dni = dni;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telf = telf;
	}
	
	/**
	 * @param dni
	 * @param password
	 */
	public Admin(String dni, String password) {
		this.dni = dni;
		this.password = password;
	}
	
	/**
	 * @param dni
	 */
	public Admin(String dni) {
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
		return "Admin [dni=" + dni + ", password=" + password + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", telf=" + telf + "]";
	}
}