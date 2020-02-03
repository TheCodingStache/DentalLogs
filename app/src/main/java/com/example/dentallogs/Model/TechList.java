package com.example.dentallogs.Model;

public class TechList {
    private String tech;
    private String location;
    private int photo;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public TechList(String tech, String location, int photo) {
        this.tech = tech;
        this.location = location;
        this.photo = photo;
    }

    public TechList() {

    }

    public String getTech() {
        return tech;
    }

    public String getLocation() {
        return location;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
