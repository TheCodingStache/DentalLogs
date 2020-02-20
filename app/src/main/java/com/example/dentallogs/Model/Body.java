package com.example.dentallogs.Model;

public class Body {
    private String active;
    private String _id;
    private String socketID;
    private String username;
    private String email;
    private String password;
    private int __v;

    public Body(String active, String _id, String socketID, String username, String email, String password, int __v) {
        this.active = active;
        this._id = _id;
        this.socketID = socketID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.__v = __v;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
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

    public int is__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
