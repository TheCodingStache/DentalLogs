package com.example.dentallogs.Model;

import java.io.Serializable;

public class Sex implements Serializable {
    private boolean male;
    private boolean female;

    public Sex(boolean male, boolean female) {
        this.male = male;
        this.female = female;
    }

    public Sex() {

    }

    public boolean getMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean getFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;

    }
}
