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

import com.usuarios.Clases.Usuarios;


@WebServlet("/eliminarUserId")
public class DeleteUser extends HttpServlet {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idDelete = request.getParameter("id");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head><title>EliminarUser</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"></head>");
        out.println("<h1>Eliminación de usuario</h1>");
        
        try {
            // Verificar que el ID no sea nulo ni vacío
            if (idDelete == null || idDelete.trim().isEmpty()) {
                out.println("<p>Por favor, ingrese un ID de usuario válido.</p>");
                return;
            }

            // Convertir el ID a entero
            int userId = Integer.parseInt(idDelete);

            // Buscar al usuario en la base de datos por su ID
            Usuarios userToDelete = entityManager.find(Usuarios.class, userId);

            if (userToDelete != null) {
                // Iniciar la transacción
                userTransaction.begin();

                try {
                    // Si la entidad está detached, hacer merge para adjuntarla al contexto actual
                    if (!entityManager.contains(userToDelete)) {
                        userToDelete = entityManager.merge(userToDelete);
                    }

                    // Eliminar el usuario
                    entityManager.remove(userToDelete);

                    // Confirmar la transacción
                    userTransaction.commit();
                    out.println("<h3>Usuario con ID " + userId + " ha sido eliminado.</h3>");
                } catch (Exception e) {
                    // Si ocurre un error, hacer rollback
                    try {
                        userTransaction.rollback();
                    } catch (Exception rollbackEx) {
                        rollbackEx.printStackTrace();
                        out.println("<h3>Ocurrió un error al intentar realizar el rollback.</h3>");
                    }
                    throw e; // Lanzamos la excepción nuevamente para que sea capturada fuera
                }
            } else {
                out.println("<h3>No se encontró un usuario con el ID " + userId + ".</h3>");
            }

        } catch (NumberFormatException e) {
            out.println("<h3>El ID proporcionado no es válido. Asegúrate de que el ID sea un número entero.</h3>");
        } catch (Exception e) {
            e.printStackTrace();  // Mostrar el error completo en los logs del servidor
            out.println("<h3>Ocurrió un error al intentar eliminar el usuario.</h3>");
            out.println("<h3>Detalles del error: " + e.getMessage() + "</h3>");
        }

        out.println("<a class='volver' href='mostrarUsuarios'>Volver</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
