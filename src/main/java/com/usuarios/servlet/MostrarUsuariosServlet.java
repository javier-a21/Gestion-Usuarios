package com.usuarios.servlet;

import com.usuarios.Clases.Usuarios;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
        String ctx = request.getContextPath(); // <-- contextPath dinámico

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("  <meta charset='UTF-8'>");
            out.println("  <title>Lista de Usuarios</title>");
            out.println("  <link rel='stylesheet' type='text/css' "
                      + "href='" + ctx + "/css/style.css'>"); // <-- ruta CSS
            out.println("</head>");
            out.println("<body>");
            out.println("  <h1 style='margin-top:3%;'>Lista de usuarios guardados:</h1>");
            out.println("  <div class='button-container'>");
            out.println("    <a class='acciones' href='" 
                       + ctx + "/busquedaUserAjax.jsp'>Buscar</a>");
            out.println("    <a class='acciones' href='" 
                       + ctx + "/eliminarUserForm.jsp'>Eliminar Usuario por Id</a>");
            out.println("  </div>");

            out.println("  <ul>");
            try {
                userTransaction.begin();
                List<Usuarios> users = entityManager
                    .createQuery("SELECT u FROM Usuarios u", Usuarios.class)
                    .getResultList();

                if (users.isEmpty()) {
                    out.println("    <li>No hay usuarios registrados todavía.</li>");
                } else {
                    for (Usuarios u : users) {
                        out.println("    <li>" + u.toString() + "</li>");
                    }
                }
                userTransaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    userTransaction.rollback();
                } catch (Exception rb) {
                    rb.printStackTrace();
                }
                out.println("    <li style='color:red;'>Error cargando usuarios: " 
                            + e.getMessage() + "</li>");
            }
            out.println("  </ul>");

            // Enlace Volver con contextPath dinámico
            out.println("  <p><a class='volver' href='" 
                       + ctx + "/index.jsp'>Volver</a></p>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
