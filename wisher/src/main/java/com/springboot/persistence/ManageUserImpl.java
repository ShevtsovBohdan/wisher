package com.springboot.persistence;

import com.springboot.domain.User;

import com.springboot.persistence.interfaces.ManageUser;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ManageUserImpl is a base class for database connection and working with User object.
 */
@Service
public class ManageUserImpl implements ManageUser {

    static Logger log = Logger.getLogger(ManageUserImpl.class.getName());

    /**
     * Returns a User object by username from the database or null if such username wasn't found.
     *
     * @param uName the name of user that will be returned.
     * @return User object or null.
     */
    @Override
    public User findbyUserName(String uName) {
        log.info("Starting find user");
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

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
            log.error("Exception throws", new Exception("Exception"));
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            log.info("User found");
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
        log.info("Starting add user");
        Session session = HibernateUtil.getSessionFactory().openSession();

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
            log.error("Exception throws", new Exception("Exception"));
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            log.info("User was added successfully");
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
        log.info("Starting find users");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List users = new ArrayList<User>();
        try {
            session.beginTransaction();
            users = session.createQuery("FROM User").list();
            session.getTransaction().commit();

        } catch (Exception e) {
            log.error("Exception throws", new Exception("Exception"));
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            log.info("Returning found list of users");
        }
        return users;
    }
}

