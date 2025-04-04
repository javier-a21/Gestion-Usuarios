package com.usuarios.servlet;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.HttpServlet; 
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse; 


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.text.Normalizer;


    

@WebServlet("/procesarNombre")
public class BuscarUserNombre extends HttpServlet {
	public static String quitarAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                         .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombreBuscado = request.getParameter("nombre");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head><title>Resultado del Formulario</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n"
        		+ "\r\n"
        		+ "</head>");
        out.println("<h1>Usuario por nombre</h1>");
        if(Grupos.listaUsers.isEmpty()) {
        	out.println("<h3>No hay usuarios registrados todavia: </h3>");
        }else {
        	ArrayList<Usuarios> userPNombres = new ArrayList<Usuarios>() ;
        	
        	if((nombreBuscado != null && !nombreBuscado.trim().isEmpty())){
        		for (Usuarios usuario : Grupos.listaUsers) {
        			int size = nombreBuscado.length();
        			int realUserSize = usuario.getNombre().length();
        			if(realUserSize >=  size) {
        				if(quitarAcentos(usuario.getNombre().substring(0,size).toLowerCase()).equals(quitarAcentos(nombreBuscado.toLowerCase()))) {
                        	userPNombres.add(usuario);
                        	
                        }
        			}
                }
        	}
        	if(userPNombres.isEmpty()) {
        		 out.println("<h3>No hay personas con ese nombre</h3>");
        	}else {
        		out.println("<ul>");
                	for (Usuarios usuario : userPNombres) {
                        out.println("<li>" + usuario.toString() + "</li>");
                }
                
                out.println("</ul>");
        	}
        			
        }
        
        
        
        out.println("</body>");
        out.println("</html>");
    }
}