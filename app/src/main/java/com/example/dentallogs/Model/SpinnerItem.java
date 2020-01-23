package com.example.dentallogs.Model;

public class SpinnerItem {
    private String material;
    private int position;

    public SpinnerItem(int position, String material) {
        this.material = material;
        this.position = position;
    }

    public String getMaterial() {
        return material;
    }

    public int getPosition() {
        return position;
    }
}
