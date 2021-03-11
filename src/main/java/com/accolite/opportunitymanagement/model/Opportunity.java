package com.accolite.opportunitymanagement.model;

import java.sql.Date;

public class Opportunity {

    // Table Attributes
    private int id;
    private String description;
    private String location;
    private String skills;
    private String userEmail;
    private int minExperience;
    private int demand;
    private Date date;

    // Constructor
    public Opportunity() {
    }

    // Getter and Setter


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(int minExperience) {
        this.minExperience = minExperience;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // ToString Method


    @Override
    public String toString() {
        return "Opportunity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", skills='" + skills + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", minExperience=" + minExperience +
                ", demand=" + demand +
                ", date=" + date +
                '}';
    }
}
