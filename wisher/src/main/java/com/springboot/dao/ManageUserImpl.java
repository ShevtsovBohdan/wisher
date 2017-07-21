package com.springboot.dao;

import com.springboot.domain.User;

import com.springboot.interfaces.ManageUser;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;
import org.hibernate.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ManageUserImpl is a base class for database connection and working with User object.
 */
@Component
public class ManageUserImpl implements ManageUser {

    /**
     * Returns a User object by username from the database or null if such username wasn't found.
     *
     * @param uName the name of user that will be returned.
     * @return User object or null.
     */
    @Override
    public User findbyUserName(String uName) {
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

    /**
     * Create User object with adjusted parameters and save it to the database.
     * <p>
     * Returns the id of the saved User object in database.
     *
     * @param username the name of the user that you want to be saved to database.
     * @param password the password of the user that you want to be saved to database.
     * @param auth     the role of the user that you want to be saved to database.
     * @return the id of the saved User object in database.
     */
    @Override
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

    /**
     * Returns the list of User objects that have been saved to the database.
     *
     * @return the list of User objects that have been saved to the database.
     */
    @Override
    public List<User> listUsers() {
        Session session = HibernateUnil.getSessionFactory().openSession();
        List users = new ArrayList<User>();
        try {
            session.beginTransaction();
            users = session.createQuery("FROM User").list();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }
}

