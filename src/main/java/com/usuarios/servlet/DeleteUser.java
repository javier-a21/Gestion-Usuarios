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

@WebServlet("/eliminarUserId")
public class DeleteUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ctx = request.getContextPath();  // <-- contextPath dinámico
        String idDelete = request.getParameter("id");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("  <title>Eliminar Usuario</title>");
            out.println("  <link rel=\"stylesheet\" type=\"text/css\" "
                      + "href=\"" + ctx + "/css/style.css\">");  // <-- ruta CSS
            out.println("</head>");
            out.println("<body>");
            out.println("  <h1>Eliminación de usuario</h1>");

            // Validación del ID
            if (idDelete == null || idDelete.trim().isEmpty()) {
                out.println("<p style='color:red;'>Por favor, ingrese un ID válido.</p>");
            } else {
                try {
                    int userId = Integer.parseInt(idDelete);
                    Usuarios userToDelete = entityManager.find(Usuarios.class, userId);

                    if (userToDelete != null) {
                        userTransaction.begin();
                        if (!entityManager.contains(userToDelete)) {
                            userToDelete = entityManager.merge(userToDelete);
                        }
                        entityManager.remove(userToDelete);
                        userTransaction.commit();
                        out.println("<h3>Usuario con ID " + userId + " ha sido eliminado.</h3>");
                    } else {
                        out.println("<h3>No se encontró un usuario con el ID " + userId + ".</h3>");
                    }
                } catch (NumberFormatException nfe) {
                    out.println("<p style='color:red;'>El ID debe ser un número entero.</p>");
                } catch (Exception e) {
                    try {
                        userTransaction.rollback();
                    } catch (Exception rb) {
                        rb.printStackTrace();
                    }
                    out.println("<p style='color:red;'>Error al eliminar usuario: " 
                                + e.getMessage() + "</p>");
                    e.printStackTrace();
                }
            }

            // Enlace Volver con contextPath dinámico
            out.println("  <p><a class='volver' href='" 
                        + ctx + "/mostrarUsuarios'>Volver</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
