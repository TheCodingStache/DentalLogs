package com.example.dentallogs.Model;

public class Sex {
    private boolean male;
    private boolean female;

    public Sex(boolean male, boolean female) {
        this.male = male;
        this.female = female;
    }

    public Sex() {

    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public void setFemale(boolean female) {
        this.female = female;

    }

    public boolean getMale() {
        return male;
    }

    public boolean setMale() {
        return female;
    }
}
