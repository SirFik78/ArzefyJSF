package com.controller;

import com.dao.ArzefyDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.io.IOException;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value = "deleteAccountBean")
@SessionScoped
@ManagedBean
public class DeleteAccountBean {

    private ArzefyDAO akunDAO = new ArzefyDAO();

    public void deactivateAccount() {
        String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
        System.out.println("Email retrieved from session: " + email); // Debug

        if (email != null) {
            try {
                boolean success = akunDAO.deactivateAccount(email);

                if (success) {
                    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to deactivate account."));
                }
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception for debugging
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An unexpected error occurred."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No email found in session."));
        }
    }
}




