package com.example.dao;

import com.example.model.Solicitud;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.utils.HibernateUtil;
import java.util.List;

public class SolicitudDAO {

    public void solicitarTutoria(Solicitud solicitud) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Guardar la solicitud
            session.save(solicitud);
            System.out.println("Solicitud guardada correctamente: " + solicitud.getId());


            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para obtener las tutorías aceptadas por un alumno
    public List<Solicitud> getTutoriasAceptadasPorAlumno(int alumnoId) {
        Transaction transaction = null;
        List<Solicitud> tutoriasAceptadas = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Consulta HQL para obtener las solicitudes con estado "Aceptada"
            String hql = "FROM Solicitud s WHERE s.alumno.id = :alumnoId AND s.estado = 'Aceptada'";
            tutoriasAceptadas = session.createQuery(hql, Solicitud.class)
                    .setParameter("alumnoId", alumnoId)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return tutoriasAceptadas;
    }
    // Método para obtener todas las solicitudes de un alumno, sin importar el estado
    public List<Solicitud> getSolicitudesPorAlumno(int alumnoId) {
        Transaction transaction = null;
        List<Solicitud> solicitudes = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Consulta HQL para obtener todas las solicitudes del alumno
            String hql = "FROM Solicitud s WHERE s.alumno.id = :alumnoId";
            solicitudes = session.createQuery(hql, Solicitud.class)
                    .setParameter("alumnoId", alumnoId)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return solicitudes;
    }
    // Obtener todas las solicitudes de un tutor (para las tutorías que ha creado)
    public List<Solicitud> getSolicitudesPorTutor(int tutorId) {
        Transaction transaction = null;
        List<Solicitud> solicitudes = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // HQL para obtener las solicitudes de las tutorías creadas por el tutor
            String hql = "FROM Solicitud s WHERE s.tutoria.tutor.id = :tutorId";
            solicitudes = session.createQuery(hql, Solicitud.class)
                    .setParameter("tutorId", tutorId)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return solicitudes;
    }
    // Método para obtener una solicitud por su ID
    public Solicitud getById(int id) {
        Transaction transaction = null;
        Solicitud solicitud = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            solicitud = session.get(Solicitud.class, id); // Obtener la solicitud por ID
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return solicitud;
    }

    // Método para actualizar una solicitud
    public void responderSolicitud(Solicitud solicitud) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(solicitud); // Actualizar la solicitud
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}