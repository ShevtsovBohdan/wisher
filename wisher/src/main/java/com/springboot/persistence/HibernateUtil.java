package com.springboot.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class that used for creating SessionFactory object for realization of database query.
 */
public class HibernateUtil {
    public static final SessionFactory sessionFactory;

    static {
        try {
            //TODO review code
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns Session Factory object.
     *
     * @return Session Factory object.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
