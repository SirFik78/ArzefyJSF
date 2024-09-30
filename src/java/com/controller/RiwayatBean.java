package com.controller;

import com.dao.RiwayatDAO;
import com.mode.pojo.TblRiwayat;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped
public class RiwayatBean implements Serializable{

    private String query;  // Stores search input from the page
    private List<TblRiwayat> orderHistoryList;  // List of search results
    private String email;  // Logged-in user's email
    private boolean isAdmin;  // Flag to check if the user is an admin

    private RiwayatDAO riwayatDAO = new RiwayatDAO();

    // Constructor
    public RiwayatBean() {
        // Get the email of the logged-in user from the session
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
        // Check if the logged-in user is an admin
        isAdmin = (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adminLogin") != null);
    }

    // Getter and Setter for query
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    // Getter for orderHistoryList
    public List<TblRiwayat> getOrderHistoryList() {
        if (orderHistoryList == null) {
            // If no search has been made, display order history based on whether the user is an admin or not
            if (isAdmin) {
                orderHistoryList = riwayatDAO.getAllHistory();  // Admin can view all order history
            } else {
                orderHistoryList = riwayatDAO.getHistoryByEmail(email);  // Regular user can only view their own history
            }
        }
        return orderHistoryList;
    }

    // Method to search order history
    public void searchHistory() {
        if (query != null && !query.isEmpty()) {
            // Search based on query and email if the user is not an admin
            if (isAdmin) {
                // Admin can search all history
                orderHistoryList = riwayatDAO.searchHistory(query);
            } else {
                // Regular user can only search within their own history
                orderHistoryList = riwayatDAO.searchHistoryByEmail(query, email);
            }
        } else {
            // If query is empty, display history based on whether the user is an admin or not
            if (isAdmin) {
                orderHistoryList = riwayatDAO.getAllHistory();
            } else {
                orderHistoryList = riwayatDAO.getHistoryByEmail(email);
            }
        }
    }
}
