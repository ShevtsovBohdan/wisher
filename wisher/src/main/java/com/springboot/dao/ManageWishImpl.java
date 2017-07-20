package com.springboot.dao;

import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageWish;
import org.hibernate.*;

import org.springframework.stereotype.Component;


import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * ManageWishImpl is a base class for database connection and working with Wish object
 */
@Component
public class ManageWishImpl implements ManageWish {
    //    private static final int START_ELEMENT = 0;
    public static final int MAX_ELEMENT_ON_THE_PAGE = 10;

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
        List wishes = new LinkedList<Wish>();
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

    @Override
    public List<Wish> listWishes(int setStartValue, int userID) {
        List wishes = new LinkedList<Wish>();
        Long numberOfRows = numberOfRows(userID);
        Session session = HibernateUnil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "select * from wishes where userID = :userID order by userID limit :limit, :offset";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Wish.class);
            query.setParameter("userID", userID);

            query.setParameter("limit", (setStartValue - 1) * MAX_ELEMENT_ON_THE_PAGE);
            if (numberOfRows - MAX_ELEMENT_ON_THE_PAGE >= 0) {
                query.setParameter("offset", MAX_ELEMENT_ON_THE_PAGE);
            } else {
                query.setParameter("offset", numberOfRows% MAX_ELEMENT_ON_THE_PAGE);
            }

            wishes = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return wishes;
        }
    }

    @Override
    public List<Wish> listAllUsersWishes(int startValue, int maxValue) {
        List wishes = new LinkedList<Wish>();
        Session session = HibernateUnil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "select * from wishes order by userID limit :limit, :offset";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Wish.class);

            query.setParameter("limit", (startValue - 1) * maxValue);
            query.setParameter("offset", maxValue);

            wishes = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return wishes;
        }
    }

    @Override
    public long numberOfRows(int userID) {
        BigInteger numbToLong = new BigInteger("1");
        Session session = HibernateUnil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "select count(userID) from wishes WHERE userID = :userID";
            SQLQuery query = session.createSQLQuery(hql);
            query.setParameter("userID", userID);
            List numb = query.list();
            numbToLong = (BigInteger) numb.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return numbToLong.longValue();
        }

    }

    @Override
    public long numberOfRows() {
        BigInteger numbToLong = new BigInteger("1");
        Session session = HibernateUnil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "select count(userID) from wishes";
            SQLQuery query = session.createSQLQuery(hql);
            List numb = query.list();
            numbToLong = (BigInteger) numb.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return numbToLong.longValue();
        }

    }
}
