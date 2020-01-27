package com.example.dentallogs.Model;

public class MessageModel {

    private String nickname;
    private String message;
    private String socketID;




    public MessageModel(String nickname, String message, String socketID) {
        this.nickname = nickname;
        this.message = message;
        this.socketID = socketID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSocketID(String socketID) {
        this.socketID = socketID;
    }
    public String getSocketID() {
        return socketID;
    }
    public MessageModel() {

    }
}
