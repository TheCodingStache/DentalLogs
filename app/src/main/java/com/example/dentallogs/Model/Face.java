package com.example.dentallogs.Model;

import java.io.Serializable;

public class Face implements Serializable {
    private boolean triangle;
    private boolean square;
    private boolean circle;

    public Face(boolean triangle, boolean square, boolean circle) {
        this.triangle = triangle;
        this.square = square;
        this.circle = circle;
    }
    public Face(){

    }
    public boolean isTriangle() {
        return triangle;
    }

    public void setTriangle(boolean triangle) {
        this.triangle = triangle;
    }

    public boolean isSquare() {
        return square;
    }

    public void setSquare(boolean square) {
        this.square = square;
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }
}
