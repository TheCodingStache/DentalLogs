package com.example.dentallogs.Model;

import com.google.gson.annotations.SerializedName;

public class TechList {
    private String tech;
    private String location;
    private int photo;
    @SerializedName("socketID")
    public String getSocketID() {
        return socketID;
    }

    public void setSocketID(String socketID) {
        this.socketID = socketID;
    }

    private String socketID;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTech() {
        return tech;
    }

    public String getLocation() {
        return location;
    }

    public TechList() {

    }

    public TechList(String tech, String location, int photo, String socketID) {
        this.tech = tech;
        this.location = location;
        this.photo = photo;
        this.socketID = socketID;
    }
}
