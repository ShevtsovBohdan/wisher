package com.springboot.dao;


import com.springboot.domain.User;
import com.springboot.domain.Wishes;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Iterator;
import java.util.List;

public class ManageWish {

    public Wishes findbyWishername(String wName) {
        Wishes wish = null;

        Session session = HibernateUnil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Criteria cr = session.createCriteria(Wishes.class);
            cr.add(Restrictions.like("wish", wName));
            List users = cr.list();
            for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
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


    public Integer addWish(int wishID, User user, String wishName, String link) {
        Session session = HibernateUnil.getSessionFactory().openSession();


        Integer wishAddID = null;

        try {
            session.beginTransaction();
            Wishes wish = new Wishes();
            wish.setWishID(wishID);
            wish.setUser(user);
            wish.setWishName(wishName);
            wish.setLink(link);
            wishID = (Integer) session.save(wish);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return wishAddID;
    }
}
