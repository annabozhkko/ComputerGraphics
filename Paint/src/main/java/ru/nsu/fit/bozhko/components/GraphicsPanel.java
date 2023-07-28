package ru.nsu.fit.bozhko.components;

import ru.nsu.fit.bozhko.tools.Figure;
import ru.nsu.fit.bozhko.tools.DrawFigure;
import ru.nsu.fit.bozhko.tools.DrawLine;
import ru.nsu.fit.bozhko.tools.Pouring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.lang.Math.max;

public class GraphicsPanel extends JPanel implements MouseListener {
    private int x, y;
    private int width, height;
    private BufferedImage image;
    private final DrawLine drawLine;
    private final DrawFigure drawFigure;
    private final Pouring pouring;
    private boolean isDrawLine = false;
    private boolean isDrawFigure = false;
    private boolean isPour = false;
    private Color currentColor = Color.WHITE;
    private boolean isGetFirstPoint = false;

    public GraphicsPanel(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                image.setRGB(i, j, Color.WHITE.getRGB());
            }
        }

        drawLine = new DrawLine();
        pouring = new Pouring(image);
        drawFigure = new DrawFigure(drawLine);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        if(getBounds().width > width || getBounds().height > height){
            changeSizePanel();
        }

        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(image, 0, 0 , width, height, this);
    }

    private void changeSizePanel(){
        int newWidth = max(getBounds().width, width);
        int newHeight = max(getBounds().height, height);

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        newImage.setData(image.getData());

        for(int i = width; i < newWidth; ++i){
            for(int j = 0; j < newHeight; ++j){
                newImage.setRGB(i, j, Color.WHITE.getRGB());
            }
        }

        for(int j = height; j < newHeight; ++j){
            for(int i = 0; i < width; ++i){
                newImage.setRGB(i, j, Color.WHITE.getRGB());
            }
        }

        setPreferredSize(new Dimension(newWidth, newHeight));
        image = newImage;
        width = newWidth;
        height = newHeight;
    }

    public void clear(){
        for(int i = 0; i < width; ++i){
            for(int j = 0; j < height; ++j){
                image.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isDrawLine){
            if(isGetFirstPoint) {
                drawLine.brezenhem(x, y, e.getX(), e.getY(), image, currentColor.getRGB());
                isGetFirstPoint = false;
                repaint();
            }
            else{
                x = e.getX();
                y = e.getY();
                isGetFirstPoint = true;
            }
        }
        if(isPour){
            pouring.pour(e.getX(), e.getY(), currentColor.getRGB(), image.getRGB(e.getX(), e.getY()));
            repaint();
        }
        if(isDrawFigure){
            drawLine.setDepth(1);
            if(drawFigure.getFigure().equals(Figure.POLYGON))
                drawFigure.drawPolygon(e.getX(), e.getY(), image);
            else
                drawFigure.drawStar(e.getX(), e.getY(),image);

            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void setDrawLine() {
        isDrawLine = true;
        isDrawFigure = false;
        isPour = false;
    }

    public void setDrawFigure() {
        isDrawLine = false;
        isDrawFigure = true;
        isPour = false;
    }

    public void setPour() {
        isDrawLine = false;
        isDrawFigure = false;
        isPour = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DrawLine getDrawLine() {
        return drawLine;
    }

    public DrawFigure getDrawFigure() {
        return drawFigure;
    }

    public void setCurrentColor(Color color){
        currentColor = color;
    }

    public void setImage(File file){
        Image openImage = new ImageIcon(file.getAbsolutePath()).getImage();
        Graphics2D g = image.createGraphics();
        g.drawImage(openImage, 0, 0,  width, height,this);
        repaint();
    }

    public BufferedImage getBufferedImage(){
        return image;
    }
}
