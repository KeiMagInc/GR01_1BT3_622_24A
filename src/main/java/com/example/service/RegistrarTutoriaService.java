package com.example.service;

import com.example.dao.TutoriaDAO;
import com.example.dao.MateriaDAO;
import com.example.model.Materia;
import com.example.model.Tutor;
import com.example.model.Tutoria;

import java.sql.Date;
import java.util.List;

public class RegistrarTutoriaService {

    private TutoriaDAO tutoriaDAO;
    private MateriaDAO materiaDAO;

    public RegistrarTutoriaService() {
        this.tutoriaDAO = new TutoriaDAO();
        this.materiaDAO = new MateriaDAO();
    }

    // Método para registrar la tutoría
    public void registrarTutoria(int codigoMateria, String fecha, String horaInicio, String horaFin, int tutorId) {
        Tutoria tutoria = new Tutoria();
        Materia materia = new Materia();
        materia.setCodigomateria(codigoMateria);

        tutoria.setMateria(materia);
        tutoria.setFecha(Date.valueOf(fecha)); // Convertir a java.sql.Date
        tutoria.setHoraInicio(horaInicio + ":00"); // Convertir a java.sql.Time
        tutoria.setHoraFin(horaFin + ":00"); // Convertir a java.sql.Time

        // Asignar el tutor
        Tutor tutor = new Tutor();
        tutor.setId(tutorId);  // ID del tutor
        tutoria.setTutor(tutor);

        // Guardar la tutoría
        tutoriaDAO.reguistarTutoria(tutoria);
    }

    // Método para obtener las materias que puede impartir un tutor
    public List<Materia> obtenerMateriasPorTutor(int tutorId) {
        return materiaDAO.getMateriasByTutorId(tutorId);
    }
}
