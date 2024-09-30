package com.controller;

import com.dao.ArzefyDAO;
import javax.inject.Named;
import java.io.Serializable;
import com.mode.pojo.TblAkun;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.enterprise.context.SessionScoped;

@Named(value = "updatePasswordBean")
@RequestScoped
public class UpdatePasswordBean implements Serializable {

    private String email;
    private String password;
    private String confirmPassword;
    private String errorMessage; // Tambahkan atribut ini

    public UpdatePasswordBean() {
        // Retrieve the email from the session
        this.email = (String) FacesContext.getCurrentInstance()
                          .getExternalContext()
                          .getSessionMap()
                          .get("email");
    }

    public String updatePassword() {
        // Validasi password
        if (!password.equals(confirmPassword)) {
            errorMessage = "Passwords do not match. Try again."; // Simpan pesan error
            return null; // Tetap di halaman yang sama
        }

        try {
            boolean isUpdated = ArzefyDAO.updatePassword(email, password); // Metode DAO untuk update password
            if (isUpdated) {
                // Redirect to signin.xhtml
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/faces/signin.xhtml");
                return null; // Sudah redirect, tidak perlu mengembalikan string
            } else {
                errorMessage = "Failed to update password. Please try again."; // Simpan pesan error
                return null; // Tetap di halaman yang sama
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "An error occurred. Please try again."; // Simpan pesan error
            return null; // Tetap di halaman yang sama
        }
    }

    // Getter dan Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email; // Optionally set the email if needed
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getErrorMessage() { // Tambahkan getter untuk errorMessage
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
