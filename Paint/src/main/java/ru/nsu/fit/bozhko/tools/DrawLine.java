package ru.nsu.fit.bozhko.tools;

import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class DrawLine {
    private int dx, dy;
    private BufferedImage image;
    private int RGB;
    private int depth;

    public void brezenhem(int x1, int y1, int x2, int y2, BufferedImage image, int RGB){
        this.RGB = RGB;
        this.image = image;

        dx = abs(x2 - x1);
        dy = abs(y2 - y1);

        if(x2 >= x1 && y2 >= y1){
            if(dx > dy){
                draw1(x1, y1, depth);
            }
            else{
                draw2(x1, y1, depth);
            }
        }
        else if(x2 <= x1 && y2 <= y1){
            if(dx > dy){
                draw3(x1, y1, depth);
            }
            else{
                draw4(x1, y1, depth);
            }
        }
        else if(x2 >= x1 && y2 <= y1){
            if(dx > dy){
                draw5(x1, y1, depth);
            }
            else{
                draw6(x1, y1, depth);
            }
        }
        else if(x2 <= x1 && y2 >= y1){
            if(dx > dy){
                draw7(x1, y1, depth);
            }
            else{
                draw8(x1, y1, depth);
            }
        }
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    private void draw1(int x, int y, int depth){   // x2 > x1, y2 > y1, dx > dy
        int err = -dx;
        for(int k = 0; k < depth; ++k) {
            if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                image.setRGB(x, y - k, RGB);
        }
        for(int i = 0; i < dx; ++i) {
            x++;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y++;
            }
            for(int k = 0; k < depth; ++k) {
                if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                    image.setRGB(x, y - k, RGB);
            }
        }
    }

    private void draw2(int x, int y, int depth){  // x2 > x1, y2 > y1, dx < dy
        int err = -dy;
        for(int k = 0; k < depth; ++k) {
            if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                image.setRGB(x - k, y, RGB);
        }
        for(int i = 0; i < dy; ++i) {
            y++;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x++;
            }
            for(int k = 0; k < depth; ++k) {
                if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                    image.setRGB(x - k, y, RGB);
            }
        }
    }

    private void draw3(int x, int y, int depth){  // x2 < x1, y2 < y1, dx > dy
        int err = -dx;
        for(int k = 0; k < depth; ++k) {
            if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                image.setRGB(x, y - k, RGB);
        }
        for(int i = 0; i < dx; ++i) {
            x--;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y--;
            }
            for(int k = 0; k < depth; ++k) {
                if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                    image.setRGB(x, y - k, RGB);
            }
        }
    }

    private void draw4(int x, int y, int depth){  // x2 < x1, y2 < y1, dx < dy
        int err = -dy;
        for(int k = 0; k < depth; ++k) {
            if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                image.setRGB(x - k, y, RGB);
        }
        for(int i = 0; i < dy; ++i) {
            y--;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x--;
            }
            for(int k = 0; k < depth; ++k) {
                if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                    image.setRGB(x - k, y, RGB);
            }
        }
    }

    private void draw5(int x, int y, int depth){  // x2 > x1, y2 < y1, dx > dy
        int err = -dx;
        for(int k = 0; k < depth; ++k) {
            if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                image.setRGB(x, y - k, RGB);
        }
        for(int i = 0; i < dx; ++i) {
            x++;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y--;
            }
            for(int k = 0; k < depth; ++k) {
                if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                    image.setRGB(x, y - k, RGB);
            }
        }
    }

    private void draw6(int x, int y, int depth){  // x2 > x1, y2 < y1, dx < dy
        int err = -dy;
        for(int k = 0; k < depth; ++k) {
            if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                image.setRGB(x - k, y, RGB);
        }
        for(int i = 0; i < dy; ++i) {
            y--;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x++;
            }
            for(int k = 0; k < depth; ++k) {
                if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                    image.setRGB(x - k, y, RGB);
            }
        }
    }

    private void draw7(int x, int y, int depth){  // x2 < x1, y2 > y1, dx > dy
        int err = -dx;
        for(int k = 0; k < depth; ++k) {
            if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                image.setRGB(x, y - k, RGB);
        }
        for(int i = 0; i < dx; ++i) {
            x--;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y++;
            }
            for(int k = 0; k < depth; ++k) {
                if(x >= 0 && x < image.getWidth() && y - k < image.getHeight() && y - k >= 0)
                    image.setRGB(x, y - k, RGB);
            }
        }
    }

    private void draw8(int x, int y, int depth){  // x2 < x1, y2 > y1, dx < dy
        int err = -dy;
        for(int k = 0; k < depth; ++k) {
            if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                image.setRGB(x - k, y, RGB);
        }
        for(int i = 0; i < dy; ++i) {
            y++;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x--;
            }
            for(int k = 0; k < depth; ++k) {
                if(x - k >= 0 && x - k < image.getWidth() && y < image.getHeight() && y >= 0)
                    image.setRGB(x - k, y, RGB);
            }
        }
    }
}
