package com.example.servlet;
import com.example.dao.MateriaDAO;
import com.example.model.Materia;
import com.example.dao.RolDAO;
import com.example.model.Rol;
import com.example.service.ReguistroSistemaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/ReguistroSistemaServlet")
public class ReguistroSistemaServlet extends HttpServlet {

    //inializar las variables
    private MateriaDAO materiaDAO = new MateriaDAO();
    private RolDAO rolDAO = new RolDAO();
    private ReguistroSistemaService reguistroSistemaService = new ReguistroSistemaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String rolId = request.getParameter("rol");
        String[] materiasSeleccionadas = request.getParameterValues("materiasSeleccionadas");

        try {
            // Pasar los datos al servicio para registrar el usuario
            reguistroSistemaService.reguistrarUsuario(nombre, apellido, correo, rolId, materiasSeleccionadas);
        } catch (Exception e) {
            // Si ocurre un error, reenviamos al formulario con el mensaje de error
            request.setAttribute("errorMessage", e.getMessage());
            doGet(request, response);
        }
        doGet(request, response);
        //request.getRequestDispatcher("/Administrator/ReguistroUsuarios.jsp").forward(request, response);
        //response.sendRedirect(request.getContextPath() + "/Administrator/ReguistroUsuarios.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //obtenmer materias de la base de datos
        List<Materia> materias = materiaDAO.getAllMaterias();
        List<Rol> rols = rolDAO.getAllRols();

        //setear al front
        request.setAttribute("rols", rols);
        request.setAttribute("materias", materias);
        request.getRequestDispatcher("/Administrator/ReguistroUsuarios.jsp").forward(request, response);



        //response.sendRedirect(request.getContextPath() + "/Administrator/ReguistroUsuarios.jsp");
    }
}
