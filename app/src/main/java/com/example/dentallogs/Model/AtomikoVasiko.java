package com.example.dentallogs.Model;

import java.io.Serializable;

public class AtomikoVasiko implements Serializable {
    private boolean atomiko;
    private boolean vasiki;

    public AtomikoVasiko(boolean atomiko, boolean vasiki) {
        this.atomiko = atomiko;
        this.vasiki = vasiki;
    }
    public AtomikoVasiko(){

    }

    public boolean getAtomiko() {
        return atomiko;
    }

    public void setAtomiko(boolean atomiko) {
        this.atomiko = atomiko;
    }

    public boolean getVasiki() {
        return vasiki;
    }

    public void setVasiki(boolean vasiki) {
        this.vasiki = vasiki;
    }
}
