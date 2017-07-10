package com.springboot.dao;

import com.springboot.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import org.hibernate.*;

import java.util.Iterator;
import java.util.List;

@Component
public class ManageUser {

    private SessionFactory factory;

    public User findbyUsername(String uName){
        User user = null;
        Transaction tx = null;

        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();

        try {
            Criteria cr = session.createCriteria(User.class);
            cr.add(Restrictions.like("username", uName));
            List users = cr.list();
            System.out.println(users);
            for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                user = (User) iterator.next();
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
            return user;
        }

    }


    public Integer addUser(String username, String password){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        Integer userID = null;

        try{
            tx = session.beginTransaction();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAuthorities("USER");
            userID = (Integer) session.save(user);
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return userID;
    }


}

