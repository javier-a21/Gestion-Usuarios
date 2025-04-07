package com.usuarios.servlet;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/procesarFormulario")
public class ProcesarForm extends HttpServlet {
	@PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

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
        
        try {
            // Empezamos una transacción
            userTransaction.begin();
//
//            Usuarios userNew = new Usuarios();
//            userNew.setNombre(nombre);
//            userNew.setEdad(Integer.parseInt(edad));
//            userNew.setSexo(sexo);
//            
//            entityManager.persist(userNew);
            
            int rowsAffected = entityManager.createNativeQuery("INSERT INTO Usuarios (nombre, edad, sexo) VALUES (?, ?, ?);")
                    .setParameter(1, nombre).setParameter(2, edad).setParameter(3, sexo).executeUpdate();
            if (rowsAffected > 0) {
                response.getWriter().println("<h1>¡Bienvenido, " + nombre + "!</h1>");
                response.getWriter().println("<h3>Usuario registrado correctamente.</h3>");
            } else {
                response.getWriter().println("<h3>No se ha podido añadir el user</h3>");
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
        
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Resultado del Formulario</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"></head>");
        out.println("<body>");
       
        out.println("<a class=\"volver\" href='index.jsp'>Volver</a>");
        out.println("</body>");
        out.println("</html>");
    }
}