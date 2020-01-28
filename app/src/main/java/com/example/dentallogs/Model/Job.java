package com.example.dentallogs.Model;

import java.io.Serializable;

public class Job implements Serializable {
    private boolean kiniti;
    private boolean akiniti;

    public Job(boolean kiniti, boolean akiniti) {
        this.kiniti = kiniti;
        this.akiniti = akiniti;
    }
    public Job(){

    }

    public boolean isKiniti() {
        return kiniti;
    }

    public void setKiniti(boolean kiniti) {
        this.kiniti = kiniti;
    }

    public boolean isAkiniti() {
        return akiniti;
    }

    public void setAkiniti(boolean akiniti) {
        this.akiniti = akiniti;
    }
}
