package com.edu.umg.config;
import com.edu.umg.extraerproperties.CargarPropiedades;

/**
 *
 * @author jose5
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
        try {
            Configuration configuration = new Configuration();
            
            CargarPropiedades cargar = new CargarPropiedades();
            
            // Configuración de Hibernate
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", cargar.getUrl());
            configuration.setProperty("hibernate.connection.username",cargar.getUser());
            configuration.setProperty("hibernate.connection.password", cargar.getPassword());
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            // Añadir la clase de entidad
            configuration.addAnnotatedClass(com.edu.umg.Entity.Autor.class);
            configuration.addAnnotatedClass(com.edu.umg.Entity.Tipos.class);
            configuration.addAnnotatedClass(com.edu.umg.Entity.Estudiante.class);
            configuration.addAnnotatedClass(com.edu.umg.Entity.Libro.class);
            configuration.addAnnotatedClass(com.edu.umg.Entity.Prestamos.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    return sessionFactory;
}

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

