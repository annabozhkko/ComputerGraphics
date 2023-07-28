package ru.nsu.fit.bozhko.components;

import ru.nsu.fit.bozhko.tools.Figure;

import javax.swing.*;
import java.awt.*;

public class Tools {
    private Menu menu;
    private Toolbar toolbar;
    private final GraphicsPanel panel;
    private final LineParametersFrame lineParametersFrame;
    private final FigureParametersFrame figureParametersFrame;

    public Tools(GraphicsPanel panel){
        this.panel = panel;
        lineParametersFrame = new LineParametersFrame(panel);
        figureParametersFrame = new FigureParametersFrame(panel);
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public void setDrawLine(){
        lineParametersFrame.open();
        toolbar.setDrawLine();
        menu.setDrawLine();
    }

    public void setSaveButton(){
        new SaveFile(panel.getBufferedImage());
        toolbar.setSaveButton();
        menu.setSaveButton();
    }

    public void setOpenFileButton(){
        new OpenFile(panel);
        toolbar.setOpenFileButton();
        menu.setOpenFileButton();
    }

    public void setPourButton(){
        panel.setPour();
        toolbar.setPourButton();
        menu.setPourButton();
    }

    public void setStarButton(){
        figureParametersFrame.open(Figure.STAR);
        toolbar.setStarButton();
        menu.setStarButton();
    }

    public void setPolygonButton(){
        figureParametersFrame.open(Figure.POLYGON);
        toolbar.setPolygonButton();
        menu.setPolygonButton();
    }

    public void setClearButton(){
        panel.clear();
        toolbar.setClearButton();
        menu.setClearButton();
    }

    public void setAboutButton(){
        new AboutFrame();
        toolbar.setAboutButton();
        menu.setAboutButton();
    }

    public void setColorButton(){
        panel.setCurrentColor(JColorChooser.showDialog(toolbar,"Select a color", Color.BLACK));
        toolbar.setColorButton();
        menu.setColorButton();
    }
}
