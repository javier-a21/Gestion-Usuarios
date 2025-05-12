package com.usuarios.servlet;

import com.usuarios.clases.Usuarios;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/eliminarUserId")
public class DeleteUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Resource
    private UserTransaction userTransaction;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idDelete = request.getParameter("id");
        String ctx = request.getContextPath();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("  <link rel='stylesheet' type='text/css' href='" + ctx + "/css/style.css'>");
        out.println("<head><title>Eliminar Usuario</title></head>");
        out.println("<body>");
        out.println("<h1>Eliminación de usuario</h1>");

        if (idDelete == null || idDelete.trim().isEmpty()) {
            out.println("<h3 style='color:red;'>No se ha proporcionado un ID válido</h3>");
        } else {
            try {
                int userId = Integer.parseInt(idDelete);

                userTransaction.begin();

                Usuarios usuario = em.find(Usuarios.class, userId);

                if (usuario != null) {
                    em.remove(usuario);
                    userTransaction.commit();
                    out.println("<h3>Usuario eliminado correctamente</h3>");
                } else {
                    userTransaction.rollback();
                    out.println("<h3 style='color:red;'>El usuario con ID " + idDelete + " no existe</h3>");
                }

            } catch (NumberFormatException e) {
                out.println("<h3 style='color:red;'>ID no válido</h3>");
            } catch (Exception e) {
                try {
                    userTransaction.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                out.println("<h3 style='color:red;'>Error al eliminar usuario: " + e.getMessage() + "</h3>");
            }
        }

        out.println("<a  class='volver' href='" + ctx + "/mostrarUsuarios'>Volver</a>");
            out.println("  </div>");
        out.println("</body>");
        out.println("</html>");
    }
}
