package com.example.dentallogs.Model;


import com.google.gson.annotations.SerializedName;

public class Body {
    private String socketID;
    private String username;
    @SerializedName("_id")
    private String id;

    public Body(String socketID, String username, String id) {
        this.socketID = socketID;
        this.username = username;
        this.id = id;
    }

    public void setSocketID(String socketID) {
        this.socketID = socketID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

