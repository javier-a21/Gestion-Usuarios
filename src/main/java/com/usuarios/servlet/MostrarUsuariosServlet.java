package com.usuarios.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.usuarios.Clases.Usuarios;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;



@WebServlet("/mostrarUsuarios")
public class MostrarUsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    private EntityManager entityManager;


    @Resource
    private UserTransaction userTransaction;
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
        try {
            // Empezamos una transacción
            userTransaction.begin();

            // Realizamos una consulta simple usando JPA (esto depende de tu entidad)
            List<Usuarios> users = entityManager.createQuery("SELECT m FROM Usuarios m", Usuarios.class).getResultList();
            if(users.isEmpty()) {
            	out.println("<h3>No hay usuarios registrados todavia: </h3>");           
            	}
            else {
            	for (Usuarios usuario : users) {
                    out.println("<li>" + usuario.toString() + "</li>");                }
            }
            userTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                userTransaction.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        
        
        out.println("</ul>");
        out.println("<a class='volver' href='index.jsp'>Volver</a>");
        out.println("</html>");
        out.close();
    }
}