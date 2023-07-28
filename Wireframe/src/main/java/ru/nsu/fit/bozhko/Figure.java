package ru.nsu.fit.bozhko;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static java.lang.Math.*;

public class Figure {
    private BSpline spline;
    private ArrayList<Point3D> points3D = new ArrayList<>();
    private double minX, maxX, minY, maxY, minZ, maxZ;
    private int m = 2, m1 = 1;

    public Figure(BSpline spline){
        this.spline = spline;
    }

    public Figure(int m, int m1, ArrayList<Point3D> points3D){
        this.m = m;
        this.m1 = m1;
        this.points3D = points3D;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getM() {
        return m;
    }

    public int getM1() {
        return m1;
    }

    public void setPoints3D(ArrayList<Point3D> points3D) {
        this.points3D = points3D;
    }

    public void buildFigure(){
        points3D.clear();

        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;
        minZ = Double.MAX_VALUE;
        maxZ = Double.MIN_VALUE;

        for(int j = 0; j < m * m1; ++j){
            double angle = j * 360. / (m * m1);
            for (Point2D point : spline.getSplinePoints()) {
                double x = point.getY() * cos(angle / 180 * PI);
                double y = point.getY() * sin(angle / 180 * PI);
                double z = point.getX();
                points3D.add(new Point3D(x, y, z));

                if (x < minX)
                    minX = x;
                if (x > maxX)
                    maxX = x;
                if (y < minY)
                    minY = y;
                if (y > maxY)
                    maxY = y;
                if (z < minZ)
                    minZ = z;
                if (z > maxZ)
                    maxZ = z;
            }
        }
    }

    public ArrayList<Point3D> getPoints3D() {
        return points3D;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxZ() {
        return maxZ;
    }

    public double getMinY() {
        return minY;
    }

    public double getMinZ() {
        return minZ;
    }

    public int getCountPointsSpline(){
        return spline.getSplinePoints().size();
    }

    public BSpline getSpline() {
        return spline;
    }

    public void setMinMaxCoord(double minX, double maxX, double minY, double maxY, double minZ, double maxZ){
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }
}
