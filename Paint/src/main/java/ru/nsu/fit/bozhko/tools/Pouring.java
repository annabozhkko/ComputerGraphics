package ru.nsu.fit.bozhko.tools;

import java.awt.image.BufferedImage;
import java.util.Stack;

public class Pouring {
    private BufferedImage image;
    private Stack<Span> spans = new Stack<>();

    public Pouring(BufferedImage image){
        this.image = image;
    }

    public void pour(int x, int y, int newRGB, int oldRGB){
        if(newRGB == oldRGB){
            return;
        }
        spans.push(getSpan(x, y, oldRGB));

        while(!spans.isEmpty()){
            Span span = spans.pop();
            paintSpan(span, newRGB);
            if(span.getY() > 0)
                findSpans(span, span.getY() - 1, oldRGB);
            if(span.getY() < image.getHeight() - 1)
                findSpans(span, span.getY() + 1, oldRGB);
        }
    }

    private Span getSpan(int x, int y, int oldRGB){
        Span span = new Span();
        span.setY(y);

        int curX = x;

        while (image.getRGB(curX, y) == oldRGB){
            curX--;
            if(curX < 0)
                break;
        }
        span.setX1(curX + 1);

        curX = x;

        while (image.getRGB(curX, y) == oldRGB){
            curX++;
            if(curX > image.getWidth() - 1)
                break;
        }
        span.setX2(curX - 1);

        return span;
    }

    private void paintSpan(Span span, int rgb){
        for(int i = span.getX1(); i <= span.getX2(); ++i){
            //panel.setPixel(i, span.getY(), rgb);
            image.setRGB(i, span.getY(), rgb);
        }
    }

    private void findSpans(Span span, int y, int oldRGB){
        for(int x = span.getX1(); x <= span.getX2(); ++x){
            if(image.getRGB(x, y) == oldRGB){
                Span newSpan = new Span();
                newSpan.setY(y);
                int curX = x;
                while (image.getRGB(curX, y) == oldRGB){
                    curX--;
                    if(curX < 0)
                        break;
                }
                newSpan.setX1(curX + 1);
                curX = x;
                while (image.getRGB(curX, y) == oldRGB){
                    curX++;
                    if(curX > image.getWidth() - 1)
                        break;
                }
                newSpan.setX2(curX - 1);
                spans.push(newSpan);
                x = curX;
            }
        }
    }
}
