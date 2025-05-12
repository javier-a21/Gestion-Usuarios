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
import java.util.List;

@WebServlet("/mostrarUsuarios")
public class MostrarUsuariosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Resource
    private UserTransaction userTransaction;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ctx = request.getContextPath(); // Contexto dinámico para rutas relativas

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("  <meta charset='UTF-8'>");
            out.println("  <title>Lista de Usuarios</title>");
            out.println("  <link rel='stylesheet' type='text/css' href='" + ctx + "/css/style.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("  <h1 style='margin-top:3%;'>Lista de Usuarios Registrados:</h1>");

            // Botones de acción
            out.println("  <div class='button-container'>");
            out.println("    <a class='acciones' href='" + ctx + "/busquedaUserAjax.jsp'>Buscar Usuario</a>");
            out.println("    <a class='acciones' href='" + ctx + "/eliminarUserForm.jsp'>Eliminar Usuario</a>");
            out.println("  </div>");

            // Listado de usuarios
            out.println("  <ul>");
            try {
                userTransaction.begin();
                
                List<Usuarios> usuarios = em.createQuery("SELECT u FROM Usuarios u", Usuarios.class).getResultList();
                
                if (usuarios.isEmpty()) {
                    out.println("<li>No hay usuarios registrados todavía.</li>");
                } else {
                    for (Usuarios usuario : usuarios) {
                        out.println("<li>" + usuario.toString() + "</li>");
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
                out.println("<li style='color:red;'>Error al cargar usuarios: " + e.getMessage() + "</li>");
            }
            out.println("  </ul>");

            // Enlace de vuelta
            out.println("  <p><a class='volver' href='" + ctx + "/index.jsp'>Volver</a></p>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
