package com.usuarios.servlet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usuarios.clases.Usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.List;

@WebServlet("/procesarNombre")
public class BuscarUserNombre extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public static String quitarAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                         .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreBuscado = request.getParameter("nombre");
        
        String ctx = request.getContextPath(); // Contexto dinámico para rutas relativas

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("  <link rel='stylesheet' type='text/css' href='" + ctx + "/css/style.css'>");
        out.println("<head><title>Resultado del Formulario</title></head>");
        
        out.println("<h1>Usuarios por nombre</h1>");

        if (nombreBuscado == null || nombreBuscado.trim().isEmpty()) {
            out.println("<h3>No se ha ingresado un nombre para buscar</h3>");
        } else {
            String queryStr = "SELECT u FROM Usuarios u WHERE LOWER(u.nombre) LIKE :nombre";
            TypedQuery<Usuarios> query = em.createQuery(queryStr, Usuarios.class);
            query.setParameter("nombre", quitarAcentos(nombreBuscado.toLowerCase()) + "%");

            List<Usuarios> userPNombres = query.getResultList();

            if (userPNombres.isEmpty()) {
                out.println("<h3>No hay personas con ese nombre</h3>");
            } else {
                out.println("<ul>");
                for (Usuarios usuario : userPNombres) {
                    out.println("<li>" + usuario.toString() + "</li>");
                }
                out.println("</ul>");
            }
        }

        out.println("</body>");
        out.println("</html>");
    }
}
