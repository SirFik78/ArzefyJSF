package com.dao;

import com.mode.pojo.TblAkun;
import com.mode.pojo.TblAdmin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.util.HibernateUtil;

public class ArzefyDAO {

    // Method to check if a user with the given username or email already exists
    public static boolean doesUserExist(String username, String email) {
        boolean userExists = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = "SELECT COUNT(*) FROM TblAkun WHERE (no_hp = :username OR email = :email) AND status = TRUE";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("email", email);
            Long count = (Long) query.uniqueResult();
            userExists = count > 0;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return userExists;
    }

    public static int simpan(TblAkun newUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int status = 0;

        try {
            transaction = session.beginTransaction();

            // Set status to true (active)
            newUser.setStatus(true);

            // Print to debug the user data being saved
            System.out.println("Saving user: " + newUser);

            session.save(newUser);
            transaction.commit();
            status = 1; // Berhasil
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return status; // Kembalikan status (1 untuk berhasil, 0 untuk gagal)
    }

    // Method to save a new user (akunmodel)
    public static TblAkun login(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TblAkun user = null;

        try {
            String hql = "FROM TblAkun WHERE email = :email AND password = :password AND status = 1"; // Menambahkan kondisi status
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", password);
            user = (TblAkun) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user; // Returns the user if found and active, otherwise null
    }
    
    public static TblAdmin adminLogin(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TblAdmin admin = null;
        try {
            // Start transaction
            session.beginTransaction();
            
            // Query to fetch admin by email and password (using older Hibernate API)
            String hql = "FROM TblAdmin WHERE email = :email AND password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", password);
            
            // Get single result (cast it manually)
            admin = (TblAdmin) query.uniqueResult();
            
            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            // Handle exceptions and rollback transaction if needed
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } 
        return admin; // Return the found admin or null if not found
    }


    public static TblAkun findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TblAkun akun = null;
        try {
            String hql = "FROM TblAkun WHERE email = :email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            akun = (TblAkun) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return akun;
    }
    public static boolean updatePassword(String email, String newPassword) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
        transaction = session.beginTransaction();
        
        String hql = "UPDATE TblAkun SET password = :password WHERE email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("password", newPassword); // Ensure you encrypt the password here
        query.setParameter("email", email);

        int result = query.executeUpdate();
        transaction.commit();
        
        return result > 0; // Return true if the password was updated
    } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
        return false;
    } 
}
    
        public boolean deactivateAccount(String email) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Find account by email
            String hql = "FROM TblAkun WHERE email = :email";
            TblAkun akun = (TblAkun) session.createQuery(hql)
                                 .setParameter("email", email)
                                 .uniqueResult();

            if (akun != null) {
                akun.setStatus(false); // Set status to false
                session.update(akun);  // Update the account in the database
                transaction.commit();
                return true; // Return true if the update was successful
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of failure
            }
            e.printStackTrace();
        }
        return false; // Return false if something goes wrong
    }

}



