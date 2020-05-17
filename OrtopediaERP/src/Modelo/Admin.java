package Modelo;

public class Admin{
	String dni;
	String password;
	String nombre;
	String apellidos;
	String telf;
	
	//---------------------------------------------------------------------------------------------CONSTRUCTOR
	
	public Admin(String dni, String password, String nombre, String apellidos, String telf) {
		this.dni = dni;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telf = telf;
	}
	
	public Admin(String dni, String password) {
		this.dni = dni;
		this.password = password;
	}
	
	public Admin(String dni) {
		this.dni = dni;	
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
		return "Admin [dni=" + dni + ", password=" + password + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", telf=" + telf + "]";
	}
	
}