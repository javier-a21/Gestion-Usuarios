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

@WebServlet("/procesarFormulario")
public class ProcesarForm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Resource
    private UserTransaction userTransaction;

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String ctx = request.getContextPath(); // Contexto dinámico para rutas relativas

    	
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nombre = request.getParameter("nombre");
            String edadStr = request.getParameter("edad");
            String sexo = request.getParameter("sexo");
            
            out.println("  <link rel='stylesheet' type='text/css' href='" + ctx + "/css/style.css'>");

            int edad = 0;
            try {
                edad = Integer.parseInt(edadStr);
            } catch (NumberFormatException e) {
                out.println("<p style='color:red;'>Edad inválida.</p>");
                return;
            }

            try {
                userTransaction.begin();
                Usuarios usuario = new Usuarios(nombre, edad, sexo);
                em.persist(usuario);
                userTransaction.commit();

                out.println("<h1 style='margin-top:50px;'>Usuario " + usuario.getNombre() + " añadido correctamente.</h1>");
                out.println("  <p><a class='volver' href='" + ctx + "/index.jsp'>Volver</a></p>");

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    userTransaction.rollback();
                } catch (Exception rb) {
                    rb.printStackTrace();
                }
                out.println("<p style='color:red;'>Error al añadir usuario: " + e.getMessage() + "</p>");
            }
        }
    }
}
