package ru.nsu.fit.bozhko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FigurePanel extends JPanel{
    private ArrayList<Point3D> points;
    private Figure figure;
    private double zf;
    private double zn = 10;
    private double sw;
    private double sh;
    private int m = 2;
    private int m1 = 1;
    private double angleX, angleY, angleZ;
    private int n = 1;
    private int k = 4;
    private double fov = 30;

    private Point3D eye = new Point3D(-10, 0, 0);
    private Point3D ref = new Point3D(10, 0, 0);
    private Point3D up = new Point3D(0, 1, 0);

    private Point2D prevPoint;

    private Matrix resultMatrixBox;
    private Matrix boxMatrix;
    private Matrix scaleMatrix;

    private boolean isSimpleMode = false;

    private double[][] pointsBox = {
            {-1, -1, -1},
            {-1, -1, 1},
            {-1, 1, -1},
            {-1, 1, 1},
            {1, -1, -1},
            {1, -1, 1},
            {1, 1, -1},
            {1, 1, 1}
    };

    private int[][] edgesBox = {
            {0, 1},
            {0, 2},
            {0, 4},
            {1, 3},
            {1, 5},
            {2, 3},
            {2, 6},
            {3, 7},
            {4, 5},
            {4, 6},
            {5, 7},
            {6, 7}
    };

    public FigurePanel(){
        setPreferredSize(new Dimension(640, 480));

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                zn -= e.getPreciseWheelRotation();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point2D curPoint = new Point2D.Double(e.getX(), e.getY());

                double diffx = prevPoint.getX() - curPoint.getX();
                double diffy = prevPoint.getY() - curPoint.getY();

                angleZ += diffy * (Math.PI / 500);
                angleY += diffx * (Math.PI / 500);

                prevPoint = curPoint;
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                prevPoint = null;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                prevPoint = new Point2D.Double(e.getX(), e.getY());
            }
        });
    }

    public void setFigure(Figure figure){
        this.figure = figure;
        m = figure.getM();
        m1 = figure.getM1();
    }

    private void drawLine3DBox(Graphics2D g, Point3D point1, Point3D point2){
        Matrix mp = new Matrix(4, 1, point1.getX(), point1.getY(), point1.getZ(), 1);
        Matrix nmp = Matrix.multiply(resultMatrixBox, mp);
        Point3D np1 = new Point3D(nmp.get(0, 0), nmp.get(1, 0), nmp.get(2, 0));
        double w1 = nmp.get(3, 0);

        mp = new Matrix(4, 1, point2.getX(), point2.getY(), point2.getZ(), 1);
        nmp = Matrix.multiply(resultMatrixBox, mp);
        Point3D np2 = new Point3D(nmp.get(0, 0), nmp.get(1, 0), nmp.get(2, 0));
        double w2 = nmp.get(3, 0);

        int x1 = (int)((np1.getX() / w1 + 1) / 2 * sw);
        int y1 = (int)((np1.getY() / w1 + 1) / 2 * sh);

        int x2 = (int)((np2.getX() / w2 + 1) / 2 * sw);
        int y2 = (int)((np2.getY() / w2 + 1) / 2 * sh);

        g.drawLine(x1, y1, x2, y2);
    }


    private void drawLine3D(Graphics2D g, Point3D point1, Point3D point2){
        Matrix mp = new Matrix(4, 1, point1.getX(), point1.getY(), point1.getZ(), 1);
        Matrix nmp = Matrix.multiply(boxMatrix, mp);

        nmp = Matrix.multiply(resultMatrixBox, nmp);
        Point3D np1 = new Point3D(nmp.get(0, 0), nmp.get(1, 0), nmp.get(2, 0));
        double w1 = nmp.get(3, 0);


        mp = new Matrix(4, 1, point2.getX(), point2.getY(), point2.getZ(), 1);
        nmp = Matrix.multiply(boxMatrix, mp);

        nmp = Matrix.multiply(resultMatrixBox, nmp);
        Point3D np2 = new Point3D(nmp.get(0, 0), nmp.get(1, 0), nmp.get(2, 0));
        double w2 = nmp.get(3, 0);

        int x1 = (int)((np1.getX() / w1 + 1) / 2 * sw);
        int y1 = (int)((np1.getY() / w1 + 1) / 2 * sh);

        int x2 = (int)((np2.getX() / w2 + 1) / 2 * sw);
        int y2 = (int)((np2.getY() / w2 + 1) / 2 * sh);

        g.drawLine(x1, y1, x2, y2);
    }

    private void drawFigure(Graphics2D g){
        g.setColor(Color.BLACK);
        zf = figure.getMaxX();
        createMatrix();
        int countPointsSpline = n * (k - 3) + 1;
        Point3D prevPoint = points.get(0);

        for(int i = 0; i < points.size(); ++i){
            Point3D curPoint = points.get(i);
            if(i / countPointsSpline % m1 != 0 && !isSimpleMode){
                prevPoint = curPoint;
                continue;
            }
            if(i % countPointsSpline == 0){
                prevPoint = curPoint;
                continue;
            }

            drawLine3D(g, prevPoint, curPoint);
            prevPoint = curPoint;
        }
        drawCircles(g);
    }

    private void drawCircles(Graphics2D g){
        for(int i = 0; i < n * (k - 3) + 1; i += n){
            Point3D prevPoint = points.get(i);
            for(int j = 1; j < m * m1; ++j){
                Point3D curPoint = points.get(i + (n * (k - 3) + 1) * j);
                drawLine3D(g, prevPoint, curPoint);
                prevPoint = curPoint;
            }
            drawLine3D(g, prevPoint, points.get(i));
        }
    }

    private void drawBox(Graphics2D g){
        zf = zn + 2;
        createMatrix();
        g.setColor(Color.MAGENTA);
        for (int[] edge : edgesBox) {
            Point3D p1 = new Point3D(pointsBox[edge[0]][0], pointsBox[edge[0]][1], pointsBox[edge[0]][2]);
            Point3D p2 = new Point3D(pointsBox[edge[1]][0], pointsBox[edge[1]][1], pointsBox[edge[1]][2]);

            drawLine3DBox(g, p1, p2);
        }
    }

    private void createMatrix(){
        sw = getWidth();
        sh = getHeight();

        Matrix projectionMatrix = Matrix.getProjectionMatrix(sw, sh, zf, zn);
        Matrix viewMatrix = Matrix.getViewMatrix(eye, ref, up);
        scaleMatrix = Matrix.getScaleMatrix(100, 100, 100);

        Matrix rotZ = new Matrix(4, 4,
                Math.cos(angleZ), Math.sin(angleZ), 0, 0,
                -Math.sin(angleZ), Math.cos(angleZ), 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);

        Matrix rotX = new Matrix(4, 4,
                1, 0, 0, 0,
                0, Math.cos(angleX), Math.sin(angleX), 0,
                0, -Math.sin(angleX), Math.cos(angleX), 0,
                0, 0, 0, 1);

        Matrix rotY = new Matrix(4, 4,
                Math.cos(angleY), 0, Math.sin(angleY), 0,
                0, 1, 0, 0,
                -Math.sin(angleY), 0, Math.cos(angleY), 0,
                0, 0, 0, 1);

        Matrix rotateMatrix = Matrix.multiply(rotZ, Matrix.multiply(rotX, rotY));

        double minX = figure.getMinX();
        double maxX = figure.getMaxX();
        double minY = figure.getMinY();
        double maxY = figure.getMaxY();
        double minZ = figure.getMinZ();
        double maxZ = figure.getMaxZ();

        double maxDim = Math.max(Math.max(maxX - minX, maxY - minY), maxZ - minZ);

        Matrix boxTranslateMatrix = new Matrix(4, 4,
                1, 0, 0, -minX - (maxX - minX) / 2,
                0, 1, 0, -minY - (maxY - minY) / 2,
                0, 0, 1, -minZ - (maxZ - minZ) / 2,
                0, 0, 0, 1);

        Matrix boxScaleMatrix = new Matrix(4, 4,
                2. / maxDim, 0, 0, 0,
                0, 2. / maxDim, 0, 0,
                0, 0, 2. / maxDim, 0,
                0, 0, 0, 1);

        boxMatrix = Matrix.multiply(boxScaleMatrix, boxTranslateMatrix);

        resultMatrixBox = Matrix.multiply(scaleMatrix, projectionMatrix);
        resultMatrixBox = Matrix.multiply(resultMatrixBox, viewMatrix);
        resultMatrixBox = Matrix.multiply(resultMatrixBox, rotateMatrix);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        points = figure.getPoints3D();
        if(points.size() > 0) {
            drawFigure((Graphics2D)g);
            drawBox((Graphics2D)g);
        }
    }

    public void dropAngles(){
        angleX = 0;
        angleY = 0;
        angleZ = 0;
        repaint();
    }

    public void changeMode(){
        isSimpleMode = !isSimpleMode;
        repaint();
    }

    public Figure getFigure() {
        return figure;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public void setZn(double zn) {
        this.zn = zn;
    }

    public void setN(int n){
        this.n = n;
    }

    public void setK(int k) {
        this.k = k;
    }
}
