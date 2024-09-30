package com.dao;

import com.mode.pojo.TblRiwayat;
import com.util.HibernateUtil;
import org.hibernate.Query;  // Menggunakan Hibernate Query versi lama
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class RiwayatDAO {

    // Method to get all order history
    public List<TblRiwayat> getAllHistory() {
        List<TblRiwayat> resultList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM TblRiwayat";  // HQL query to fetch all data
            Query query = session.createQuery(hql);  // Menggunakan Query dari org.hibernate.Query
            resultList = query.list();  // Execute query and return result list
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed to prevent leaks
        }
        return resultList;
    }

    // Method to get order history based on user's email
    public List<TblRiwayat> getHistoryByEmail(String email) {
        List<TblRiwayat> resultList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM TblRiwayat WHERE email = :email";  // HQL query to fetch history by email
            Query query = session.createQuery(hql);
            query.setString("email", email);  // Bind email parameter
            resultList = query.list();  // Execute query and return result list
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed to prevent leaks
        }
        return resultList;
    }

    // Method to search history by query and user's email
    public List<TblRiwayat> searchHistoryByEmail(String searchQuery, String email) {
        List<TblRiwayat> resultList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM TblRiwayat WHERE email = :email AND (namaGame LIKE :searchQuery OR namaItem LIKE :searchQuery OR methodPayment LIKE :searchQuery)";
            Query query = session.createQuery(hql);  // Menggunakan Query dari org.hibernate.Query
            query.setString("email", email);  // Bind email parameter
            query.setString("searchQuery", "%" + searchQuery + "%");  // Bind search query parameter with wildcard
            resultList = query.list();  // Execute query and return result list
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed to prevent leaks
        }
        return resultList;
    }

    // Method to add new order history
    public void addHistory(TblRiwayat history) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Open session
            transaction = session.beginTransaction();  // Start transaction
            session.save(history);  // Save new history entry to the database
            transaction.commit();  // Commit transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback if error occurs
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();  // Ensure session is closed to prevent memory leaks
            }
        }
    }

    // Method to update order history
    public void updateHistory(TblRiwayat history) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Open session
            transaction = session.beginTransaction();  // Start transaction
            session.update(history);  // Update existing history entry
            transaction.commit();  // Commit transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback if error occurs
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();  // Ensure session is closed
            }
        }
    }

    // Method to delete order history by ID
    public void deleteHistory(int idHistory) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // Open session
            transaction = session.beginTransaction();  // Start transaction
            TblRiwayat history = (TblRiwayat) session.get(TblRiwayat.class, idHistory);  // Find history by ID
            if (history != null) {
                session.delete(history);  // Delete history entry
                transaction.commit();  // Commit transaction
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback if error occurs
            }
            e.printStackTrace();
        }
    }
    public List<TblRiwayat> searchAllHistory(String searchQuery) {
    List<TblRiwayat> resultList = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        String hql = "FROM TblRiwayat WHERE namaGame LIKE :searchQuery OR namaItem LIKE :searchQuery OR methodPayment LIKE :searchQuery";
        Query query = session.createQuery(hql);
        query.setString("searchQuery", "%" + searchQuery + "%");  // Bind search query parameter with wildcard
        resultList = query.list();  // Execute query and return result list
    } catch (Exception e) {
        e.printStackTrace();
    }
    return resultList;
}
public List<TblRiwayat> searchHistory(String searchQuery) {
    List<TblRiwayat> resultList = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        String hql = "FROM TblRiwayat WHERE namaGame LIKE :searchQuery OR namaItem LIKE :searchQuery OR methodPayment LIKE :searchQuery";
        Query query = session.createQuery(hql);
        query.setString("searchQuery", "%" + searchQuery + "%");  // Bind search query parameter with wildcard
        resultList = query.list();  // Execute query and return result list
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();  // Ensure session is closed to prevent leaks
    }
    return resultList;
}
}