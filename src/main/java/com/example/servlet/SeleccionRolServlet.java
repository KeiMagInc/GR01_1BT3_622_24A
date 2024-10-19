package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeleccionRolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rol = request.getParameter("rol");
        if ("usuario".equals(rol)) {
            response.sendRedirect("User/usuario.jsp");
        } else if ("tutor".equals(rol)) {
            response.sendRedirect("Tutor/tutor.jsp");
        } else if ("administrator".equals(rol)) {
            response.sendRedirect("Administrator/Administrador.jsp");
        }
    }
}
