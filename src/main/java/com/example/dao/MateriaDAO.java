package com.example.dao;

import com.example.model.Materia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Function;

public class MateriaDAO {

    private SessionFactory factory;

    public MateriaDAO() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Materia.class).buildSessionFactory();
    }

    // Método para obtener todas las materias
    public List<Materia> getAllMaterias() {
        return executeTransaction(session -> session.createQuery("from Materia", Materia.class).getResultList());
    }

    // Método genérico para ejecutar una transacción
    private <T> T executeTransaction(Function<Session, T> command) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error during transaction", e);
        }
    }

    // Método para guardar o actualizar una materia
    public void saveMateria(Materia materia) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            session.saveOrUpdate(materia);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para eliminar una materia por su código
    public void deleteMateria(int codigomateria) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            Materia materia = session.get(Materia.class, codigomateria);
            if (materia != null) {
                session.delete(materia);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para obtener una materia por su código
    public Materia getMateria(int codigomateria) {
        Session session = null;
        Materia materia = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            materia = session.get(Materia.class, codigomateria);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return materia;
    }
}
