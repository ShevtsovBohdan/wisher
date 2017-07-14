package com.springboot.dao;


import com.springboot.domain.Wishes;
import org.hibernate.*;

import org.hibernate.criterion.Restrictions;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManageWish {


    public Wishes findbyWishername(String wName) {
        Wishes wish = null;
        Session session = HibernateUnil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria cr = session.createCriteria(Wishes.class);
            cr.add(Restrictions.like("wishName", wName));
            List wishes = cr.list();
            for (Iterator iterator = wishes.iterator(); iterator.hasNext(); ) {
                wish = (Wishes) iterator.next();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
           session.close();
            return wish;
        }

    }

    public Integer addWish(Wishes wish) {
        Session session = HibernateUnil.getSessionFactory().openSession();

        Integer wishAddedID = null;

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

    public void deleteWish(String wishName) {
        Session session = HibernateUnil.getSessionFactory().openSession();
        String hql = "DELETE FROM Wishes WHERE wishName = :wishName";
        Query query = session.createQuery(hql);
        query.setParameter("wishName", wishName);


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

    public void deleteWish(int wishID) {
        Session session = HibernateUnil.getSessionFactory().openSession();
        String hql = "DELETE FROM Wishes WHERE wishID = :wishID";
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

    public List<Wishes> listWishes(){
        Session session = HibernateUnil.getSessionFactory().openSession();
        List wishes = new ArrayList<Wishes>();
        try{
            session.beginTransaction();
            wishes = session.createQuery("FROM Wishes").list();
            session.getTransaction().commit();

        }catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return wishes;
    }
}
