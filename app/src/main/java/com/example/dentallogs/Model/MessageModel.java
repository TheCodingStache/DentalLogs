package com.example.dentallogs.Model;

public class MessageModel {

    private String message;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MessageModel(String message, String name) {
        this.message = message;
        this.name = name;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageModel() {

    }
}
