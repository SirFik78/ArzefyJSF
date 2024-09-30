package com.controller;

import com.mode.pojo.TblRiwayat;
import com.dao.PayDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.sql.Timestamp;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PayBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String UID;
    private String server;
    private String game;
    private String item;
    private int quantity;
    private int price;
    private String methodPayment;

    public String processPayment() {

        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
            // Check for null values
    if ( game == null) {
        System.out.println("game nya null");
        return null; // Stay on the same page if any value is null
    }
    if ( server == null) {
        System.out.println("server nya null");
        return null; // Stay on the same page if any value is null
    }
    if ( UID == null) {
        System.out.println("UID nya na null na null");
        return null; // Stay on the same page if any value is null
    }
    if ( item == null) {
        System.out.println("Item nya na null na null");
        return null; // Stay on the same page if any value is null
    }


    
    // Logging the values before inserting to database
        // Create a new payment record with the details
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TblRiwayat payment = new TblRiwayat(timestamp, email, UID, game, server, item, methodPayment, quantity, price);

        // Insert the payment record into the database
        PayDAO payDAO = new PayDAO();
        boolean isInserted = payDAO.insertPayment(payment);

        if (isInserted) {
            // Payment success - redirect to the index page
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paymentSuccess", true);
            return "index?faces-redirect=true"; // Redirect to the index page
        } else {
            // Payment failed - stay on the same page
            return null;
        }
    }

// Ensure setter methods for hidden fields are present
    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setMethodPayment(String methodPayment) {
        this.methodPayment = methodPayment;
    }

    // Getters and setters for all fields
    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getMethodPayment() {
        return methodPayment;
    }

}
