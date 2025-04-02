package com.usuarios.servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grupos  {
	static List<Usuarios> listaUsers = new ArrayList<Usuarios>();
	static int aux = 0;
	
	public static void añadirUser(String nombre, int edad, String sexo) {
		aux++;
		listaUsers.add(new Usuarios(aux, nombre, edad, sexo));
	}
	
	public static List<Usuarios> listarUsers() {
		return listarUsers();
	}
	public static boolean eliminarUser(int id) {
		Iterator<Usuarios> iterator = listaUsers.iterator();

		while (iterator.hasNext()) {
		   if(iterator.next().getId() == id) {
			   iterator.remove();
			  return true;
		   };
		}
		return false;
	}
	
	
	public static void inicializarUsuarios() {
	    listaUsers.clear();
	    añadirUser("Juan", 25, "Hombre");
	    añadirUser("Ana", 30, "Mujer");
	    añadirUser("Carlos", 28, "Hombre");
	    añadirUser("María", 22, "Mujer");
	    añadirUser("Pedro", 35, "Hombre");
	    añadirUser("Laura", 27, "Mujer");
	    añadirUser("Sergio", 40, "Hombre");
	    añadirUser("Elena", 33, "Mujer");
	    añadirUser("David", 29, "Hombre");
	    añadirUser("Lucía", 26, "Mujer");
	}

}