package com.controller;

import com.dao.ArzefyDAO;
import com.mode.pojo.TblAkun;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.regex.Pattern;

@Named(value = "emailCheckBean")
@RequestScoped
public class EmailCheckBean implements Serializable {

    private String email;
    private String errorMessage;

    // Method untuk memeriksa email
    public String checkEmail() {
        // Validasi format email
        if (!isValidEmail(email)) {
            errorMessage = "Format email tidak valid.";
            return null; // Tetap di halaman yang sama
        }

        try {
            TblAkun akun = ArzefyDAO.findByEmail(email); // Metode DAO untuk cek email
            if (akun != null) {
                // Memeriksa status akun
                if (akun.getStatus() != null && akun.getStatus()) { // Status 1 (true)
                    // Jika email ditemukan dan status 1, arahkan ke halaman forget1.xhtml
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.getExternalContext().getSessionMap().put("email", email);

                    // Clear the previous UpdatePasswordBean
                    context.getExternalContext().getSessionMap().remove("updatePasswordBean");

                    // Create a new instance of UpdatePasswordBean
                    UpdatePasswordBean updatePasswordBean = new UpdatePasswordBean();
                    updatePasswordBean.setEmail(email);
                    context.getExternalContext().getSessionMap().put("updatePasswordBean", updatePasswordBean);

                    return "forget1.xhtml?faces-redirect=true";
                } else {
                    errorMessage = "Akun tidak aktif. Silakan coba lagi.";
                    return null; // Tetap di halaman yang sama
                }
            } else {
                errorMessage = "Email tidak ditemukan. Silakan coba lagi.";
                return null; // Tetap di halaman yang sama
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "Terjadi kesalahan saat memproses permintaan.";
            return null;
        }
    }

    // Validasi format email
    private boolean isValidEmail(String email) {
        String emailRegex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Getter dan Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
