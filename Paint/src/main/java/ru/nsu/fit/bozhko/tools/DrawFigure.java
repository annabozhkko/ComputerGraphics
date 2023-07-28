package ru.nsu.fit.bozhko.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawFigure {
    private DrawLine drawLine;
    private Figure figure;
    private double radius;
    private double angle;
    private int n;

    public DrawFigure(DrawLine drawLine){
        this.drawLine = drawLine;
    }

    public void drawPolygon(int x, int y, BufferedImage image){
        List<Point> points = new ArrayList<>();

        for(int i = 0; i < n; ++i){
            Point point = new Point(x + radius * Math.cos(angle * Math.PI / 180.0), y + radius * Math.sin(angle * Math.PI / 180.0));
            points.add(point);
            angle += 360.0 / (double)n;
        }

        for(int i = 0; i < n - 1; ++i){
            drawLine.brezenhem(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(),
                    points.get(i + 1).getY(), image, Color.BLACK.getRGB());
        }

        drawLine.brezenhem(points.get(n - 1).getX(), points.get(n - 1).getY(), points.get(0).getX(),
                points.get(0).getY(), image, Color.BLACK.getRGB());

    }

    public void drawStar(int x, int y, BufferedImage image){
        double r = radius / 2.0;
        List<Point> points = new ArrayList<>();

        for(int i = 0; i <= 2 * n + 1; i++){
            double l = (i % 2 == 0) ? radius : r;
            Point point = new Point((double) x + l * Math.cos(angle), (double) y + l * Math.sin(angle));
            points.add(point);
            angle += Math.PI / (double)n;
        }

        for(int i = 0; i < 2 * n + 1; ++i){
            drawLine.brezenhem(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(),
                    points.get(i + 1).getY(), image, Color.BLACK.getRGB());
        }
    }

    public Figure getFigure() {
        return figure;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setN(int n) {
        this.n = n;
    }
}
