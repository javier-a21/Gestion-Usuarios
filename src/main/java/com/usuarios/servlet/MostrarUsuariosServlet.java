package com.usuarios.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mostrarUsuarios")
public class MostrarUsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("text/html");
        String contextPath = request.getContextPath();  // Obtener el contextPath
        PrintWriter out = response.getWriter();
        out.println("<html>"
        		+ "<link rel='stylesheet' type='text/css' href='css/style.css'>\r\n");
        
        out.println("<h1 style= 'margin-top: 3%;'>Lista de usuarios guardados: </h1>");
        out.println("<div class='button-container'>");
        out.println("<a class='acciones' href='" + contextPath + "/busquedaUserAjax.jsp'>Buscar</a>");
        out.println("<a class='acciones' href='" + contextPath + "/eliminarUserForm.jsp'>Eliminar Usuario por Id</a>");
        out.println("</div>");

        out.println("<ul>");
        if(Grupos.listaUsers.isEmpty()) {
        	out.println("<h3>No hay usuarios registrados todavia: </h3>");
        }else {
        	for (Usuarios usuario : Grupos.listaUsers) {
                out.println("<li>" + usuario.toString() + "</li>");
            }
        }
        
        out.println("</ul>");
        out.println("<a class='volver' href='index.jsp'>Volver</a>");
        out.println("</html>");
    }
}