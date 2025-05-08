package com.usuarios.servlet;

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

@WebServlet("/procesarFormulario")
public class ProcesarForm extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ctx = request.getContextPath();  // <-- contextPath dinámico

        String nombre = request.getParameter("nombre");
        String edad   = request.getParameter("edad");
        String sexo   = request.getParameter("sexo");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("  <meta charset='UTF-8'>");
            out.println("  <title>Resultado del Formulario</title>");
            out.println("  <link rel=\"stylesheet\" type=\"text/css\" "
                      + "href=\"" + ctx + "/css/style.css\">");  // <-- CSS dinámico
            out.println("</head>");
            out.println("<body>");
            out.println("  <h1>Registro de Usuario</h1>");

            try {
                userTransaction.begin();

                int rowsAffected = entityManager.createNativeQuery(
                        "INSERT INTO Usuarios (nombre, edad, sexo) VALUES (?, ?, ?)")
                    .setParameter(1, nombre)
                    .setParameter(2, edad)
                    .setParameter(3, sexo)
                    .executeUpdate();

                if (rowsAffected > 0) {
                    out.println("<h3 style='color:green;'>¡Bienvenido, "
                                + nombre + "! Usuario registrado correctamente.</h3>");
                } else {
                    out.println("<h3 style='color:red;'>No se pudo añadir el usuario.</h3>");
                }

                userTransaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    userTransaction.rollback();
                } catch (Exception rb) {
                    rb.printStackTrace();
                }
                out.println("<h3 style='color:red;'>Error al registrar: "
                            + e.getMessage() + "</h3>");
            }

            // Enlace “Volver” con contextPath dinámico
            out.println("  <p><a class=\"volver\" href=\"" 
                        + ctx + "/index.jsp\">Volver</a></p>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
