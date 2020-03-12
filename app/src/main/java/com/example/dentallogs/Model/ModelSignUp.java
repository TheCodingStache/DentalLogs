package com.example.dentallogs.Model;

import com.google.gson.annotations.SerializedName;

public class ModelSignUp {
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("phone")
    private String phone;
    @SerializedName("afm")
    private String afm;
    @SerializedName("address")
    private String address;


    public ModelSignUp(String username, String email, String password, String phone, String afm, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.afm = afm;
        this.address = address;
    }

    public ModelSignUp() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
