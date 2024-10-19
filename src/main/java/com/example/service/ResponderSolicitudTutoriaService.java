package com.example.service;

import com.example.dao.SolicitudTutoriaDAO;
import com.example.model.SolicitudTutoria;

public class ResponderSolicitudTutoriaService {

    private SolicitudTutoriaDAO solicitudTutoriaDAO;

    public ResponderSolicitudTutoriaService() {
        this.solicitudTutoriaDAO = new SolicitudTutoriaDAO();
    }

    // Método para responder a una solicitud (aceptar o rechazar)
    public void responderSolicitud(int solicitudId, String accion) {
        SolicitudTutoria solicitud = solicitudTutoriaDAO.getById(solicitudId);

        // Actualizar el estado según la acción
        if ("aceptar".equals(accion)) {
            solicitud.setEstado("aceptada");
        } else if ("rechazar".equals(accion)) {
            solicitud.setEstado("rechazada");
        }

        // Guardar la actualización
        solicitudTutoriaDAO.update(solicitud);
    }
}
