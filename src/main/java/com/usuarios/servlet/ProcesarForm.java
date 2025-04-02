package com.usuarios.servlet;

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/procesarFormulario")
public class ProcesarForm extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String sexo = request.getParameter("sexo");
        
        Grupos.añadirUser (nombre, Integer.parseInt(edad), sexo);
        
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Resultado del Formulario</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"></head>");
        out.println("<body>");
        out.println("<h1>¡Bienvenido, " + nombre + "!</h1>");
        out.println("<h3>Usuario registrado correctamente.</h3>");
        out.println("<a class=\"volver\" href='index.jsp'>Volver</a>");
        out.println("</body>");
        out.println("</html>");
    }
}