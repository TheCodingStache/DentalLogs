package com.example.dentallogs.Model;


import com.google.gson.annotations.SerializedName;

public class Body {
    private String socketID;
    private String username;

    public Body(String socketID, String username) {
        this.socketID = socketID;
        this.username = username;
    }

    public void setSocketID(String socketID) {
        this.socketID = socketID;
    }

    public String getSocketID() {
        return socketID;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

