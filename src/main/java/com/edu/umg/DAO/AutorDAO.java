/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.DAO;


import com.edu.umg.Entity.Autor;
import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutorDAO {

    public void save(Autor autor) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.save(autor);
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}


    public Autor getById(int id) {
        Autor autor = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            autor = session.get(Autor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autor;
    }

    public List<Autor> getAll() {
        List<Autor> autores = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            autores = session.createQuery("from Autor", Autor.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    public void update(Autor autor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(autor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Autor autor = session.get(Autor.class, id);
            if (autor != null) {
                session.delete(autor);
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

