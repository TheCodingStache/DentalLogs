package com.example.dentallogs.Model;

public class ModelHistory {
    private String name;
    private String lastName;
    private String date;
    private String gender;
    private String face;
    private String job;
    private String category;
    private String material;
    private String color;
    private String teeth;
    private String comment;

    public ModelHistory(String name, String lastName, String date, String gender, String face, String job, String category, String material, String color, String teeth, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.date = date;
        this.gender = gender;
        this.face = face;
        this.job = job;
        this.category = category;
        this.material = material;
        this.color = color;
        this.teeth = teeth;
        this.comment = comment;
    }

    public ModelHistory(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTeeth() {
        return teeth;
    }

    public void setTeeth(String teeth) {
        this.teeth = teeth;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
