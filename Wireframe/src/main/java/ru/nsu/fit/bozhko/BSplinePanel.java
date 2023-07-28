package ru.nsu.fit.bozhko;

import ru.nsu.fit.bozhko.BSpline;
import ru.nsu.fit.bozhko.BSplineEditor;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

public class BSplinePanel extends JPanel implements MouseInputListener, MouseListener {
    private int n = 1, k = 4, m = 2;
    private final int SCALE = 50;
    private boolean isAddPoint = false;
    private boolean isRemovePoint = false;
    private Point2D movingPoint;
    private BSpline spline = new BSpline();
    private BSplineEditor splineEditor;

    public BSplinePanel(BSplineEditor splineEditor){
        this.splineEditor = splineEditor;
        setPreferredSize(new Dimension(630, 360));
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    private void drawAxes(Graphics2D g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.GRAY);

        // Ось X
        g.drawLine(0, height / 2, width, height / 2 );
        for (int i = width / 2; i < width; i += SCALE) {
            g.drawLine(i, height / 2 - 5, i, height / 2 + 5);
            g.drawString("" + (i - width / 2) / SCALE, i - 3, height / 2 + 15);
        }
        for (int i = width / 2; i > 0; i -= SCALE) {
            g.drawLine(i, height / 2 - 5, i, height / 2 + 5);
            g.drawString("" + (i - width / 2) / SCALE, i - 3, height / 2 + 15);
        }

        // Ось Y
        g.drawLine(width / 2, 0, width / 2, height);
        for (int i = height / 2; i < height; i += SCALE) {
            g.drawLine(width / 2 - 5, i, width / 2 + 5, i);
            g.drawString("" + -(i - height / 2) / SCALE, width / 2 + 10, i + 3);
        }
        for (int i = height / 2; i > 0; i -= SCALE) {
            g.drawLine(width / 2 - 5, i, width / 2 + 5, i);
            g.drawString("" + -(i - height / 2) / SCALE, width / 2 + 10, i + 3);

        }
    }

    private void drawPoints(Graphics2D g){
        g.setColor(Color.RED);
        for(Point2D point : spline.getPoints()){
            if(point.equals(movingPoint)){
                g.setColor(Color.YELLOW);
            }
            g.drawOval((int)point.getX() + getWidth() / 2 - 10, (int)point.getY() + getHeight() / 2 - 10, 20, 20);
            if(point.equals(movingPoint)){
                g.setColor(Color.RED);
            }
        }
    }

    private void drawSpline(Graphics2D g){
        g.setColor(Color.CYAN);
        for(int i = 0; i < spline.getSplinePoints().size() - 1; ++i){
            Point2D point1 = spline.getSplinePoints().get(i);
            Point2D point2 = spline.getSplinePoints().get(i + 1);
            g.drawLine((int)(point1.getX() + getWidth() / 2), (int)(point1.getY() + getHeight() / 2),
                    (int)(point2.getX() + getWidth() / 2), (int)(point2.getY() + getHeight() / 2));
        }

        g.setColor(Color.RED);
        for(int i = 0; i < spline.getPoints().size() - 1; ++i){
            Point2D point1 = spline.getPoints().get(i);
            Point2D point2 = spline.getPoints().get(i + 1);
            g.drawLine((int)(point1.getX() + getWidth() / 2), (int)(point1.getY() + getHeight() / 2),
                    (int)(point2.getX() + getWidth() / 2), (int)(point2.getY() + getHeight() / 2));
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.BLACK);

        drawAxes((Graphics2D)g);
        drawPoints((Graphics2D)g);
        if(spline.getPoints().size() >= 4){
            drawSpline((Graphics2D)g);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e){
        if(!isRemovePoint && !isAddPoint){
            int x = e.getX();
            int y = e.getY();
            if(movingPoint == null){
                for(Point2D point : spline.getPoints()){
                    if((point.getX() + getWidth() / 2. - x) * (point.getX() + getWidth() / 2. - x) +
                            (point.getY() + getHeight() / 2. - y) * (point.getY() + getHeight() / 2. - y) <= 100){
                        movingPoint = point;
                        break;
                    }
                }
            }
            if(movingPoint != null) {
                movingPoint.setLocation(x - getWidth() / 2., y - getHeight() / 2.);
                if(spline.getPoints().size() >= 4){
                    spline.buildSpline();
                }
                repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        movingPoint = null;

        if(isAddPoint){
            spline.addPoint(new Point2D.Double(e.getX() - getWidth() / 2., e.getY() - getHeight() / 2.));
            if(spline.getPoints().size() > k) {
                k = spline.getPoints().size();
                splineEditor.setK(k);
            }
        }

        if(isRemovePoint){
            int x = e.getX();
            int y = e.getY();
            for(Point2D point : spline.getPoints()){
                if((point.getX() + getWidth() / 2. - x) * (point.getX() + getWidth() / 2. - x) +
                        (point.getY() + getHeight() / 2. - y) * (point.getY() + getHeight() / 2. - y) <= 100){
                    spline.removePoint(point);
                    break;
                }
            }
            if(spline.getPoints().size() < k) {
                k = spline.getPoints().size();
                splineEditor.setK(k);
            }
        }
        if(spline.getPoints().size() >= 4){
            spline.buildSpline();
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void setAddPoint() {
        isAddPoint = true;
        isRemovePoint = false;
    }

    public void setRemovePoint() {
        isRemovePoint = true;
        isAddPoint = false;
    }

    public void cancel(){
        isAddPoint = false;
        isRemovePoint = false;
    }

    public void setN(int n) {
        spline.setN(n);
        spline.buildSpline();
        repaint();
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setM(int m) {
        this.m = m;
    }

    public BSpline getSpline() {
        return spline;
    }
}
