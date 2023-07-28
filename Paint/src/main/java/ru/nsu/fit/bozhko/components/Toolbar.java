package ru.nsu.fit.bozhko.components;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar {
    private final GraphicsPanel graphicsPanel;
    private final ButtonGroup group = new ButtonGroup();
    private final ButtonGroup colorGroup = new ButtonGroup();
    private final Tools tools;
    private final JToggleButton saveButton;
    private final JToggleButton openFileButton;
    private final JToggleButton clearButton;
    private final JToggleButton drawLineButton;
    private final JToggleButton pourButton;
    private final JToggleButton colorButton;
    private final JToggleButton aboutButton;
    private JToggleButton starButton;
    private JToggleButton polygonButton;


    public Toolbar(GraphicsPanel graphicsPanel, Tools tools){
        this.graphicsPanel = graphicsPanel;
        this.tools = tools;
        tools.setToolbar(this);

        saveButton = new JToggleButton();
        saveButton.setIcon(new ImageIcon("src/main/resources/save.png"));
        saveButton.addActionListener(e -> tools.setSaveButton());
        saveButton.setToolTipText("Save");
        add(saveButton);
        group.add(saveButton);

        openFileButton = new JRadioButton();
        openFileButton.setIcon(new ImageIcon("src/main/resources/open.png"));
        openFileButton.addActionListener(e -> tools.setOpenFileButton());
        openFileButton.setToolTipText("Open file");
        add(openFileButton);
        group.add(openFileButton);

        clearButton = new JToggleButton();
        clearButton.setIcon(new ImageIcon("src/main/resources/clear.png"));
        clearButton.addActionListener(e -> tools.setClearButton());
        clearButton.setToolTipText("Clear");
        add(clearButton);
        group.add(clearButton);

        drawLineButton = new JToggleButton();
        drawLineButton.setIcon(new ImageIcon("src/main/resources/line.png"));
        drawLineButton.addActionListener(e -> tools.setDrawLine());
        drawLineButton.setToolTipText("Draw line");
        add(drawLineButton);
        group.add(drawLineButton);

        pourButton = new JToggleButton();
        pourButton.setIcon(new ImageIcon("src/main/resources/pour.png"));
        pourButton.addActionListener(e -> tools.setPourButton());
        pourButton.setToolTipText("Pour");
        add(pourButton);
        group.add(pourButton);

        addFigures();
        addColors();

        colorButton = new JToggleButton();
        colorButton.setIcon(new ImageIcon("src/main/resources/color.png"));
        colorButton.addActionListener(e -> tools.setColorButton());
        colorButton.setToolTipText("Choose color");
        add(colorButton);
        colorGroup.add(colorButton);

        aboutButton = new JToggleButton();
        aboutButton.setIcon(new ImageIcon("src/main/resources/about.gif"));
        aboutButton.addActionListener(e -> tools.setAboutButton());
        aboutButton.setToolTipText("About program");
        add(aboutButton);
        group.add(aboutButton);

        JToggleButton exitButton = new JToggleButton();
        exitButton.setIcon(new ImageIcon("src/main/resources/exit.gif"));
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setToolTipText("Exit");
        add(exitButton);
        group.add(exitButton);
    }

    private void addFigures(){
        starButton = new JToggleButton();
        starButton.setIcon(new ImageIcon("src/main/resources/star.png"));
        starButton.addActionListener(e -> tools.setStarButton());
        starButton.setToolTipText("Star");
        add(starButton);
        group.add(starButton);

        polygonButton = new JToggleButton();
        polygonButton.setIcon(new ImageIcon("src/main/resources/polygon.png"));
        polygonButton.addActionListener(e -> tools.setPolygonButton());
        polygonButton.setToolTipText("Polygon");
        add(polygonButton);
        group.add(polygonButton);
    }

    private void addColors(){
        JToggleButton redButton = new JToggleButton();
        redButton.setIcon(new ImageIcon("src/main/resources/colors/red.png"));
        redButton.addActionListener(e -> graphicsPanel.setCurrentColor(Color.RED));
        redButton.setToolTipText("Red");
        add(redButton);
        colorGroup.add(redButton);

        JToggleButton greenButton = new JToggleButton();
        greenButton.setIcon(new ImageIcon("src/main/resources/colors/green.png"));
        greenButton.addActionListener(e -> graphicsPanel.setCurrentColor(Color.GREEN));
        greenButton.setToolTipText("Green");
        add(greenButton);
        colorGroup.add(greenButton);

        JToggleButton yellowButton = new JToggleButton();
        yellowButton.setIcon(new ImageIcon("src/main/resources/colors/yellow.png"));
        yellowButton.addActionListener(e -> graphicsPanel.setCurrentColor(Color.YELLOW));
        yellowButton.setToolTipText("Yellow");
        add(yellowButton);
        colorGroup.add(yellowButton);

        JToggleButton blueButton = new JToggleButton();
        blueButton.setIcon(new ImageIcon("src/main/resources/colors/blue.png"));
        blueButton.addActionListener(e -> graphicsPanel.setCurrentColor(Color.CYAN));
        blueButton.setToolTipText("Blue");
        add(blueButton);
        colorGroup.add(blueButton);

        JToggleButton darkBlueButton = new JToggleButton();
        darkBlueButton.setIcon(new ImageIcon("src/main/resources/colors/dark_blue.png"));
        darkBlueButton.addActionListener(e -> graphicsPanel.setCurrentColor(Color.BLUE));
        darkBlueButton.setToolTipText("Dark blue");
        add(darkBlueButton);
        colorGroup.add(darkBlueButton);

        JToggleButton magentaButton = new JToggleButton();
        magentaButton.setIcon(new ImageIcon("src/main/resources/colors/magenta.png"));
        magentaButton.addActionListener(e -> graphicsPanel.setCurrentColor(Color.MAGENTA));
        magentaButton.setToolTipText("Magenta");
        add(magentaButton);
        colorGroup.add(magentaButton);
    }

    public void setDrawLine(){
        drawLineButton.setSelected(true);
    }

    public void setSaveButton(){
        saveButton.setSelected(true);
    }

    public void setOpenFileButton(){
        openFileButton.setSelected(true);
    }

    public void setPourButton(){
        pourButton.setSelected(true);
    }

    public void setStarButton(){
        starButton.setSelected(true);
    }

    public void setPolygonButton(){
        polygonButton.setSelected(true);
    }

    public void setClearButton(){
        clearButton.setSelected(true);
    }

    public void setAboutButton(){
        aboutButton.setSelected(true);
    }

    public void setColorButton(){
        colorButton.setSelected(true);
    }
}
