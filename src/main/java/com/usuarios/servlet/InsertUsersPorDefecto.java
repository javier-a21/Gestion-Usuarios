package com.usuarios.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.HttpServlet; 
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse; 


@WebServlet("/insertUsersPorDefecto")
public class InsertUsersPorDefecto extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Grupos.inicializarUsuarios();
        
        response.sendRedirect(request.getContextPath() + "/mostrarUsuarios");
    }
}
