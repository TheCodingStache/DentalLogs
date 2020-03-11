package com.example.dentallogs.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelLogin implements Serializable {
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("socketID")
    private String socketID;
    @Expose
    @SerializedName("_id")
    String id;
    @Expose
    @SerializedName("email")
    String email;

    public String getId() {
        return id;
    }


    public ModelLogin(String username, String socketID, String id, String email) {
        this.username = username;
        this.id = id;
        this.socketID = socketID;
        this.email = email;
    }

    public ModelLogin() {

    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSocketID() {
        return socketID;
    }

    public void setSocketID(String socketID) {
        this.socketID = socketID;
    }
}
