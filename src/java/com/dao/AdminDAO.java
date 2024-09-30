package com.dao;

import com.mode.pojo.TblAdmin;
import com.mode.pojo.TblItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query; // Ensure you're importing the correct Query class for older Hibernate
import com.util.HibernateUtil;
import java.util.List;

public class AdminDAO {

    // Get all Admins
    public List<TblAdmin> getAllAdmins() {
        Transaction transaction = null;
        List<TblAdmin> admins = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TblAdmin"); // Use older Query class
            admins = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } 
        return admins;
    }

    // Get all Items
    public List<TblItem> getAllItems() {
        Transaction transaction = null;
        List<TblItem> items = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TblItem"); // Use older Query class
            items = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); // Ensure session is closed
        }
        return items;
    }

    // Update Admin
    public void updateAdmin(TblAdmin selectedItem) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(selectedItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete Admin
    public void deleteAdmin(TblAdmin selectedItem) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(selectedItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } 
    }

    // Update Item
    public void updateItem(TblItem selectedItem) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(selectedItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } 
    }

    // Delete Item
    public void deleteItem(TblItem selectedItem) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(selectedItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); // Ensure session is closed
        }
    }

    // Add Item
    public void addItem(TblItem item) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}