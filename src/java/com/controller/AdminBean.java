package com.controller;

import com.dao.AdminDAO;
import com.mode.pojo.TblAdmin;
import com.mode.pojo.TblItem;
import com.util.HibernateUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@ViewScoped
public class AdminBean implements Serializable{
    private TblAdmin admin = new TblAdmin(); // Admin object
    private TblItem item = new TblItem(); // Item object
    private AdminDAO adminDAO = new AdminDAO();
    private List<TblAdmin> adminList;
    private List<TblItem> itemList;

    // Constructor
    public AdminBean() {
        loadAdminList();
        loadItemList();
    }

    // Load Admin List
    public void loadAdminList() {
        adminList = adminDAO.getAllAdmins();
    }

    // Load Item List
    public void loadItemList() {
        itemList = adminDAO.getAllItems();
    }

    // Sign Out
    public void signOut() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update Item
public void updateItem(TblItem selectedItem) {
    Transaction transaction = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        transaction = session.beginTransaction();
        session.merge(selectedItem); // Use merge instead of update
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
    } 
}


    // Prepare for updating an item
// Prepare for updating an item
public void prepareUpdate(TblItem item) {
    this.item = item; // Assign the passed item to the bean
}



    // Delete Item
    public void deleteItem(TblItem item) {
        if (item != null) {
            adminDAO.deleteItem(item);
            loadItemList(); // Refresh item list
        }
    }

    // Add Item
    public void addItem() {
        if (item != null) {
            adminDAO.addItem(item);
            item = new TblItem(); // Clear item after adding
            loadItemList(); // Refresh item list
        }
    }

    // Getters and Setters
    public List<TblAdmin> getAdminList() {
        return adminList;
    }

    public List<TblItem> getItemList() {
        return itemList;
    }

    public TblAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(TblAdmin admin) {
        this.admin = admin;
    }

    public TblItem getItem() {
        return item;
    }

    public void setItem(TblItem item) {
        this.item = item;
    }
}