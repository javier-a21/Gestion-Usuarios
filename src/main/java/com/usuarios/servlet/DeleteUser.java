package com.usuarios.servlet;

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import java.io.IOException;
import java.io.PrintWriter;



    

@WebServlet("/eliminarUserId")
public class DeleteUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idDelete = request.getParameter("id");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head><title>EliminarUser</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n"
        		+ "\r\n"
        		+ "</head>");
        out.println("<h1>Eliminacion de usuarios</h1>");
        if(Grupos.listaUsers.isEmpty()) {
        	out.println("<h3>No hay usuarios registrados todavia: </h3>");
        }
        else {
        	if (Grupos.eliminarUser(Integer.parseInt(idDelete))){	
        		out.println("<h3>Usuario eliminado Correctamente</h3>");
			}
        	else {
        		out.println("<h3>El usuario con el id "+ idDelete +" no se ha podido eliminar"+"</h3>");
        	}
        }
        out.println("<a class='volver' href='mostrarUsuarios'>Volver</a>");
        out.println("</body>");
        out.println("</html>");
    }
}