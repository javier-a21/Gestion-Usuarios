package com.usuarios.servlet;

public class Usuarios  {
	private String nombre;
	private int edad;
	private int id;
	private String sexo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Usuarios(int id, String nombre, int edad, String sexo) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
	}
	
	@Override
	public String toString() {
		return "Datos del usuario: id:" + getId() + ", nombre: " + getNombre() + ", edad: " + getEdad() + ", sexo: " + getSexo();
	}
}