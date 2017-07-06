package hello;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.stereotype.Component;

import org.hibernate.*;



@Component
public class UserDao {
    private static SessionFactory sessionFactory;
    private static SessionFactory factory;

//    public User findByUsername(String uName) {
//    }

    public Integer save(String username, String password) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try{
            tx = session.beginTransaction();
            User user = new User();
            user.setUsername(username);

            employeeID = (Integer) session.save(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employeeID;
    }


}

