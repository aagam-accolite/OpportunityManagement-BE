package com.accolite.opportunitymanagement.model;

import java.sql.Date;

public class Audit {

    // Table Columns
    private int id;
    private Date date;
    private String userName;
    private String userEmail;
    private String operation;
    private String oldValues;
    private String newValues;

    // Constructor
    public Audit() {
    }

    public Audit(String userName, String userEmail, String operation, String oldValues, String newValues) {
        this.date = new Date(System.currentTimeMillis());
        this.userName = userName;
        this.userEmail = userEmail;
        this.operation = operation;
        this.oldValues = oldValues;
        this.newValues = newValues;
    }


    // Getter and Setter Method's


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOldValues() {
        return oldValues;
    }

    public void setOldValues(String oldValues) {
        this.oldValues = oldValues;
    }

    public String getNewValues() {
        return newValues;
    }

    public void setNewValues(String newValues) {
        this.newValues = newValues;
    }
}
