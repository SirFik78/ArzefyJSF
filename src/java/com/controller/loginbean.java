package com.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import com.dao.ArzefyDAO; // Import the correct DAO
import com.mode.pojo.TblAkun; // Import the correct model for users
import com.mode.pojo.TblAdmin; // Import the correct model for admins
import javax.faces.context.FacesContext;

@Named(value = "loginbean")
@SessionScoped
public class loginbean implements Serializable {

    private String email;
    private String password;
    private TblAkun loggedInUser; // To store regular user data
    private TblAdmin loggedInAdmin; // To store admin data
    private String errorMessage; // To display login error messages

    public loginbean() {
    }

    public String login() {
        // Check if the user is a regular user
        loggedInUser = ArzefyDAO.login(email, password); // Implement login method in DAO for users
        if (loggedInUser != null) {
            // Set session attributes for regular user
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", loggedInUser.getUsername());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", loggedInUser.getEmail());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("no_hp", loggedInUser.getNoHp());
            return "index.xhtml"; // Redirect to main page for regular users
        } else {
            // Check if the user is an admin
            loggedInAdmin = ArzefyDAO.adminLogin(email, password); // Implement adminLogin method in DAO
            if (loggedInAdmin != null) {
                // Set session attributes for admin
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminLogin", true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminUsername", loggedInAdmin.getUsername());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminEmail", loggedInAdmin.getEmail());
                return "admin.xhtml"; // Redirect to admin page
            } else {
                errorMessage = "Invalid email or password."; // Set error message
                return "signin.xhtml"; // Stay on the login page
            }
        }
    }

    // Getter and Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TblAkun getLoggedInUser() {
        return loggedInUser;
    }

    public TblAdmin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
