/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.DAO;


import com.edu.umg.Entity.Tipos;
import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TiposDAO {

    public void save(Tipos tipo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tipo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Tipos getById(int id) {
        Tipos tipo = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tipo = session.get(Tipos.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }

    public List<Tipos> getAll() {
        List<Tipos> tipos = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tipos = session.createQuery("from Tipos", Tipos.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipos;
    }

    public void update(Tipos tipo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tipo);
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
            Tipos tipo = session.get(Tipos.class, id);
            if (tipo != null) {
                session.delete(tipo);
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
