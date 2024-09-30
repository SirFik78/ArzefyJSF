package com.controller;

import com.dao.ArzefyDAO;
import com.mode.pojo.TblAkun;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable {

    private String username;
    private String no_hp;
    private String email;
    private String password;
    private String errorMessage;

    public RegisterBean() {
    }

    public String register() {
        // Validasi input
        if (!isInputValid()) {
            errorMessage = "Please input valid data.";
            return "register.xhtml"; // Kembali ke halaman registrasi
        }

        // Cek jika pengguna sudah ada
        boolean userExists = ArzefyDAO.doesUserExist(username, email);
        if (userExists) {
            errorMessage = "Username or email already exists.";
            return "signin.xhtml"; // Kembali ke halaman sign in
        } else {
            // Buat objek TblAkun baru
            TblAkun newUser = new TblAkun();
            newUser.setUsername(username);
            newUser.setNoHp(no_hp);
            newUser.setEmail(email);
            newUser.setPassword(password);

            // Simpan pengguna baru ke database
            int status = ArzefyDAO.simpan(newUser);
            if (status > 0) {
                return "signin.xhtml"; // Redirect ke halaman signin
            } else {
                errorMessage = "Registration failed. Please try again.";
                return "register.xhtml"; // Kembali ke halaman registrasi
            }
        }
    }

    private boolean isInputValid() {
        return username != null && !username.trim().isEmpty() &&
               no_hp != null && !no_hp.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               password != null && !password.trim().isEmpty();
    }

    // Getter dan Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

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

    public String getErrorMessage() {
        return errorMessage;
    }
}
