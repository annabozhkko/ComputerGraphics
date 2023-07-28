package ru.nsu.fit.bozhko;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BSpline {
    private ArrayList<Point2D> points = new ArrayList<>();
    private ArrayList<Point2D> splinePoints = new ArrayList<>();
    private int n = 1;
    private double[][] matrix = {
            {-1, 3, -3, 1},
            {3, -6, 3, 0},
            {-3, 0, 3, 0},
            {1, 4, 1, 0}};

    public void addPoint(Point2D point){
        points.add(point);
    }

    public void removePoint(Point2D point){
        points.remove(point);
    }

    public ArrayList<Point2D> getSplinePoints(){
        return splinePoints;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void buildSpline(){
        splinePoints.clear();
        for(int x = 1; x < points.size() - 2; ++x){
            ArrayList<Point2D> areaPoints = new ArrayList<>();
            for(int i = -1; i <= 2; ++i){
                areaPoints.add(points.get(x + i));
            }

            for(double t = 0; t <= 1; t += 1. / n){
                if(t == 0 && x > 1){
                    continue;
                }
                double[] TM = new double[]{0, 0, 0, 0};
                for(int i = 0; i < 4; ++i){
                    for(int j = 0; j < 4; ++j){
                        TM[i] += Math.pow(t, 3 - j) * matrix[j][i] / 6;
                    }
                }

                double u = 0, v = 0;
                for(int i = 0; i < 4; ++i){
                    u += TM[i] * areaPoints.get(i).getX();
                    v += TM[i] * areaPoints.get(i).getY();
                }

                splinePoints.add(new Point2D.Double(u, v));
            }
        }
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    public int getN() {
        return n;
    }

    public int getK(){
        return points.size();
    }
}
