package com.dao;

import com.mode.pojo.TblItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ItemDAO {

    private SessionFactory sessionFactory;

    public ItemDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();  // Hibernate config
    }

    @SuppressWarnings("unchecked")
    public List<TblItem> getItemsByGameIdAndType(int gameId, String itemType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<TblItem> items = null;
        
        try {
            transaction = session.beginTransaction();
            
            String hql = "FROM TblItem WHERE tblGame.idGame = :gameId AND itemType = :itemType";
            items = session.createQuery(hql)
                           .setParameter("gameId", gameId)
                           .setParameter("itemType", itemType)
                           .list();
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return items;
    }
}
