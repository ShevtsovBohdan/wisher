package com.springboot.dao;

import com.springboot.domain.User;
import com.springboot.domain.Wishes;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import org.hibernate.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ManageUser {

    @Transactional
    public User findbyUsername(String uName) {
        User user = null;
        Session session = HibernateUnil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria cr = session.createCriteria(User.class);

            cr.add(Restrictions.like("username", uName));
            List users = cr.list();
            for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                user = (User) iterator.next();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return user;
        }

    }

    @Transactional
    public Integer addUser(String username, String password, String auth) {
        Session session = HibernateUnil.getSessionFactory().openSession();

        Integer userID = null;

        try {
            session.beginTransaction();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAuthorities(auth);
            userID = (Integer) session.save(user);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            throw (e);
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;
    }

    public void ListUser( ){
        Session session = HibernateUnil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            List employees = session.createQuery("FROM User").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                User employee = (User) iterator.next();
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List<User> listUsers(){
        Session session = HibernateUnil.getSessionFactory().openSession();
        List users = new ArrayList<User>();
        try{
            session.beginTransaction();
            users = session.createQuery("FROM User").list();
            session.getTransaction().commit();

        }catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return users;
    }


}

