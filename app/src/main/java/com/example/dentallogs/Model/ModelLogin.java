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

    public ModelLogin(String username, String socketID) {
        this.username = username;
        this.socketID = socketID;
    }

    public ModelLogin() {

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
