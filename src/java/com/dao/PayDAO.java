package com.dao;

import com.mode.pojo.TblRiwayat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.util.HibernateUtil;

public class PayDAO {

public boolean insertPayment(TblRiwayat payment) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
        transaction = session.beginTransaction();
        session.save(payment); // Save the payment object
        transaction.commit();
        return true;
    } catch (Exception e) {
        if (session.getTransaction() != null) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();
        return false;
    } 
}

}
