package com.mode.pojo;
// Generated Sep 24, 2024 12:45:37 PM by Hibernate Tools 4.3.1



/**
 * TblAdmin generated by hbm2java
 */
public class TblAdmin  implements java.io.Serializable {


     private Integer idAdmin;
     private String username;
     private String email;
     private String password;

    public TblAdmin() {
    }

    public TblAdmin(String username, String email, String password) {
       this.username = username;
       this.email = email;
       this.password = password;
    }
   
    public Integer getIdAdmin() {
        return this.idAdmin;
    }
    
    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}

