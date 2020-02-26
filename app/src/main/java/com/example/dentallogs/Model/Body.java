package com.example.dentallogs.Model;

public class Body {
    private String socketID;
    private String username;

    public Body(String socketID, String username) {
        this.socketID = socketID;
        this.username = username;
    }



    public String getSocketID() {
        return socketID;
    }

    public void setSocketID(String socketID) {
        this.socketID = socketID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
