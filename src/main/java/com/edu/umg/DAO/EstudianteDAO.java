/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.DAO;


import com.edu.umg.Entity.Estudiante;
import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EstudianteDAO {

    // Método para obtener la sesión de Hibernate
    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    // Método para guardar un estudiante
    public void save(Estudiante estudiante) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(estudiante);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para obtener un estudiante por ID
    public Estudiante getById(int id) {
        Estudiante estudiante = null;
        try (Session session = getSession()) {
            estudiante = session.get(Estudiante.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudiante;
    }

    // Método para obtener todos los estudiantes
    public List<Estudiante> getAll() {
        List<Estudiante> estudiantes = null;
        try (Session session = getSession()) {
            Query<Estudiante> query = session.createQuery("from Estudiante", Estudiante.class);
            estudiantes = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    // Método para actualizar un estudiante
    public void update(Estudiante estudiante) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(estudiante);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar un estudiante
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Estudiante estudiante = session.get(Estudiante.class, id);
            if (estudiante != null) {
                session.delete(estudiante);
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
