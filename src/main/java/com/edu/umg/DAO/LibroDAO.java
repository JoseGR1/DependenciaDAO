package com.edu.umg.DAO;

import com.edu.umg.Entity.Libro;
import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroDAO {

    // Guardar un libro
public void save(Libro libro) {
    Transaction transaction = null;
    Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(libro);
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close(); // Cerrar la sesión aquí
        }
    }
}


    // Obtener un libro por su id
    public Libro getById(Long id) {
        Libro libro = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            libro = session.get(Libro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libro;
    }

    // Obtener todos los libros
    public List<Libro> getAll() {
        List<Libro> libros = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            libros = session.createQuery("from Libro", Libro.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    // Actualizar un libro
    public void update(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Eliminar un libro por su id
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Libro libro = session.get(Libro.class, id);
            if (libro != null) {
                session.delete(libro);
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
