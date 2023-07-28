package ru.nsu.fit.bozhko.tools;

public class Point {
    private int x, y;

    public Point(double x, double y){
        this.x = (int)x;
        this.y = (int)y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
