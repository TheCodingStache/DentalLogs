package com.example.dentallogs.Model;

public class Body {
    private String socketID;
    private String username;
    private String _id;

    public Body(String socketID, String username, String _id) {
        this.socketID = socketID;
        this.username = username;
        this._id = _id;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
