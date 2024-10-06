package com.edu.umg.DAO;

import com.edu.umg.Entity.Prestamos;
import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PrestamosDAO {

    // Método para obtener la sesión de Hibernate
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    // Método para guardar un préstamo
    public void savePrestamo(Prestamos prestamo) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para obtener un préstamo por ID
    public Prestamos getPrestamoById(Long id) {
        try (Session session = getSession()) {
            return session.get(Prestamos.class, id);
        }
    }

    // Método para obtener todos los préstamos
    public List<Prestamos> getAllPrestamos() {
        try (Session session = getSession()) {
            Query<Prestamos> query = session.createQuery("from Prestamos", Prestamos.class);
            return query.list();
        }
    }

    // Método para actualizar un préstamo
    public void updatePrestamo(Prestamos prestamo) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar un préstamo
    public void deletePrestamo(Long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Prestamos prestamo = session.get(Prestamos.class, id);
            if (prestamo != null) {
                session.delete(prestamo);
                System.out.println("Préstamo eliminado: " + prestamo);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
