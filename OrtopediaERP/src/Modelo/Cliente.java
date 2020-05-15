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
	
	public Cliente (String dni, String nombre, String apellidos, String direccion, String email, String telf, String notas	) {
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.email = email;
		this.telf = telf;
		this.notas = notas;
	
	}
	
	public Cliente (String dni) {
		
		this.dni = dni;
	
	}
	
	//---------------------------------------------------------------------------------------------GETTER AND SETTER

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getNotas() {
		return notas;
	}

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