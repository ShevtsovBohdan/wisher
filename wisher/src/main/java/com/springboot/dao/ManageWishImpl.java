package com.springboot.dao;

import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageWish;
import org.hibernate.*;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ManageWishImpl is a base class for database connection and working with Wish object
 */
@Component
public class ManageWishImpl implements ManageWish {
    /**
     * Create Wish object by received parameters and saves it to the database.
     * <p>
     * Returns the id of the saved Wish object in database.
     *
     * @param wishName   Name of the wish that will be saved in the database
     * @param wishLink   Link of the wish that will be saved in the database
     * @param activeUser User that will be correspond to the Wish that will be saved in the database
     * @return
     */
    @Override
    public Integer addWish(String wishName, String wishLink, User activeUser) {
        Session session = HibernateUnil.getSessionFactory().openSession();

        Integer wishAddedID = null;

        Wish wish = new Wish();
        wish.setWishName(wishName);
        wish.setLink(wishLink);
        wish.setUser(activeUser);

        try {
            session.beginTransaction();
            wishAddedID = (Integer) session.save(wish);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return wishAddedID;
    }

    /**
     * Delete Wish object from the database by received id.
     *
     * @param wishID the id of the Wish object that will be removed from the database
     */
    @Override
    public void deleteWish(int wishID) {
        Session session = HibernateUnil.getSessionFactory().openSession();
        String hql = "DELETE FROM Wish WHERE wishID = :wishID";
        Query query = session.createQuery(hql);
        query.setParameter("wishID", wishID);
        try {
            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Returns the list of Wish objects that have been saved to the database
     *
     * @return the list of Wish objects that have been saved to the database
     */
    @Override
    public List<Wish> listWishes() {
        Session session = HibernateUnil.getSessionFactory().openSession();
        List wishes = new ArrayList<Wish>();
        try {
            session.beginTransaction();
            wishes = session.createQuery("FROM Wish").list();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return wishes;
    }
}
