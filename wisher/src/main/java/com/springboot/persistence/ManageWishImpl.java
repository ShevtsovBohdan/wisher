package com.springboot.persistence;

import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageWish;
import org.hibernate.*;

import org.springframework.stereotype.Component;


import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * ManageWishImpl is a base class for database connection and working with Wish object.
 */
@Component
public class ManageWishImpl implements ManageWish {
    public static final int MAX_ELEMENT_ON_THE_PAGE = 10;
    public static final int MAX_SEARCH_RESULT = 20;



    /**
     * Create Wish object by received parameters and saves it to the database.
     * <p>
     * Returns the id of the saved Wish object in database.
     *
     * @param wishName   name of the wish that will be saved in the database.
     * @param wishLink   link of the wish that will be saved in the database.
     * @param activeUser user that will be correspond to the Wish that will be saved in the database.
     * @return
     */
    @Override
    public Integer addWish(String wishName, String wishLink, User activeUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();

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
     * @param wishID the id of the Wish object that will be removed from the database.
     */
    @Override
    public void deleteWish(int wishID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
     * Returns the list of Wish objects that have been saved to the database.
     *
     * @return list of Wish objects that have been saved to the database.
     */
    @Override
    public List<Wish> listWishes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    /**
     * Returns the list of Wish objects from the database.
     *
     * @param setStartValue sets start point for the table where returning objects start takes.
     * @param userID        sets the user for which wishes will be returned.
     * @return list of Wish objects from the database.
     */
    @Override
    public List<Wish> listWishes(int setStartValue, int userID) {
        List wishes = new LinkedList<Wish>();
        Long numberOfRows = numberOfRows(userID);
        Session session = HibernateUtil.getSessionFactory().openSession();
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
                query.setParameter("offset", numberOfRows % MAX_ELEMENT_ON_THE_PAGE);
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

    /**
     * Returns the list of Wish objects from the database.
     *
     * @param setStartValue sets start point for the table where returning objects start takes.
     * @param maxValue      sets quantity of the objects that will be returned.
     * @return the list of Wish objects from the database.
     */
    @Override
    public List<Wish> listAllUsersWishes(int setStartValue, int maxValue) {
        List wishes = new LinkedList<Wish>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "select * from wishes order by userID limit :limit, :offset";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Wish.class);

            query.setParameter("limit", (setStartValue - 1) * maxValue);
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

    /**
     * Returns quantity of wishes from the database for predetermined user.
     *
     * @param userID id of the user for whom number of wishes will be returned.
     * @return quantity of wishes from the database.
     */
    @Override
    public long numberOfRows(int userID) {
        BigInteger numbToLong = new BigInteger("1");
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    /**
     * Returns quantity of wishes of all users from the database.
     *
     * @return quantity of wishes of all users from the database.
     */
    @Override
    public long numberOfRows() {
        BigInteger numbToLong = new BigInteger("1");

        Session session = HibernateUtil.getSessionFactory().openSession();
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

        }
        return numbToLong.longValue();

    }

    /**
     * Searches all wishes for user by given part of a wish name and returns List of the Wish objects.
     *
     * @param userID    id of the user for whom wishes will be searched.
     * @param searchStr part of a wish name on which the search will be carried out.
     * @return list of the Wish objects.
     */
    @Override
    public List<Wish> search(int userID, String searchStr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List searchResultList = new LinkedList<Wish>();
        try {
            session.beginTransaction();

            String hql = "select * from wishes where userID = :userID and wishName like :searchStr limit :limit, :offset";
            SQLQuery query = session.createSQLQuery(hql);
            query.addEntity(Wish.class);
            query.setParameter("userID", userID);
            query.setParameter("searchStr", "%" + searchStr + "%");
            query.setParameter("limit", 0);
            query.setParameter("offset", MAX_SEARCH_RESULT);
            searchResultList = query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return searchResultList;
        }
    }


    /**
     * Searches all wishes for all users by given part of a wish name and returns List of the Wish objects.
     *
     * @param searchStr part of a wish name on which the search will be carried out.
     * @return list of the Wish objects.
     */
    @Override
    public List<Wish> searchAllWishes(String searchStr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List searchResultList = new LinkedList<Wish>();
        try {
            session.beginTransaction();
            String hql = "select * from wishes where wishName like :searchStr limit :limit, :offset";
            SQLQuery query = session.createSQLQuery(hql);
            query.addEntity(Wish.class);
            query.setParameter("searchStr", "%" + searchStr + "%");
            query.setParameter("limit", 0);
            query.setParameter("offset", MAX_SEARCH_RESULT);
            searchResultList = query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return searchResultList;
        }
    }

    /**
     * Sets path to the image in database.
     *
     * @param wishID ID of the wish to which image will be saved.
     * @param wishLocalPath path to the image that was saved.
     */
    @Override
    public void saveWishLocalLink(int wishID, String wishLocalPath) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "update wishes set wishLocalPath = :wishLocalLink where wishID = :wishID";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Wish.class);
            query.setParameter("wishLocalPath", wishLocalPath);
            query.setParameter("wishID", wishID);
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
     * Returns path to the image from database.
     *
     * @param wishID ID of the wish for which path to the image will be returned.
     * @return path to the image from database.
     */
    @Override
    public String getWishLocalLink(int wishID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String pathToImage = "";
        try {
            session.beginTransaction();
            String sql = "select wishLocalPath from wishes where wishID = :wishID";
            SQLQuery query = session.createSQLQuery(sql);
//            query.addEntity(Wish.class);
            query.setParameter("wishID", wishID);
            List list = query.list();
            pathToImage = (String)list.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return pathToImage;
        }
    }

    /**
     * Sets original name of the image to database.
     *
     * @param wishID ID of the wish to which image's name will be saved.
     * @param originalName original name of the image.
     */
    @Override
    public void saveOriginalImageWish(Integer wishID, String originalName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "update wishes set imageOriginalName = :originalName where wishID = :wishID";
            SQLQuery query = session.createSQLQuery(sql);
//            query.addEntity(Wish.class);
            query.setParameter("originalName", originalName);
            query.setParameter("wishID", wishID);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
