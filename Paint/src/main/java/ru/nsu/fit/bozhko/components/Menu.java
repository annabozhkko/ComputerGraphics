package ru.nsu.fit.bozhko.components;

import javax.swing.*;

public class Menu extends JMenu {
    private final JRadioButtonMenuItem saveItem;
    private final JRadioButtonMenuItem openFileItem;
    private final JRadioButtonMenuItem clearItem;
    private final JRadioButtonMenuItem drawLineItem;
    private final JRadioButtonMenuItem pourItem;
    private final JRadioButtonMenuItem colorItem;
    private final JRadioButtonMenuItem aboutItem;
    private final JRadioButtonMenuItem drawStarItem;
    private final JRadioButtonMenuItem drawPolygonItem;

    public Menu(Tools tools){
        super("Menu");
        ButtonGroup group = new ButtonGroup();
        tools.setMenu(this);

        saveItem = new JRadioButtonMenuItem("Save file");
        saveItem.addActionListener(e -> tools.setSaveButton());
        add(saveItem);
        group.add(saveItem);

        openFileItem = new JRadioButtonMenuItem("Open file");
        openFileItem.addActionListener(e -> tools.setOpenFileButton());
        add(openFileItem);
        group.add(openFileItem);

        clearItem = new JRadioButtonMenuItem("Clear");
        clearItem.addActionListener(e -> tools.setClearButton());
        add(clearItem);
        group.add(clearItem);

        drawLineItem = new JRadioButtonMenuItem("Draw line");
        drawLineItem.addActionListener(e -> tools.setDrawLine());
        add(drawLineItem);
        group.add(drawLineItem);

        pourItem = new JRadioButtonMenuItem("Pour");
        pourItem.addActionListener(e -> tools.setPourButton());
        add(pourItem);
        group.add(pourItem);

        drawStarItem = new JRadioButtonMenuItem("Draw star");
        drawStarItem.addActionListener(e -> tools.setStarButton());
        add(drawStarItem);
        group.add(drawStarItem);

        drawPolygonItem = new JRadioButtonMenuItem("Draw polygon");
        drawPolygonItem.addActionListener(e -> tools.setPolygonButton());
        add(drawPolygonItem);
        group.add(drawPolygonItem);

        colorItem = new JRadioButtonMenuItem("Choose color");
        colorItem.addActionListener(e -> tools.setColorButton());
        add(colorItem);
        group.add(colorItem);

        aboutItem = new JRadioButtonMenuItem("About program");
        aboutItem.addActionListener(e -> tools.setAboutButton());
        add(aboutItem);
        group.add(aboutItem);

        JRadioButtonMenuItem exitItem = new JRadioButtonMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        add(exitItem);
        group.add(aboutItem);
    }

    public void setDrawLine(){
        drawLineItem.setSelected(true);
    }

    public void setSaveButton(){
        saveItem.setSelected(true);
    }

    public void setOpenFileButton(){
        openFileItem.setSelected(true);
    }

    public void setPourButton(){
        pourItem.setSelected(true);
    }

    public void setStarButton(){
        drawStarItem.setSelected(true);
    }

    public void setPolygonButton(){
        drawPolygonItem.setSelected(true);
    }

    public void setClearButton(){
        clearItem.setSelected(true);
    }

    public void setAboutButton(){
        aboutItem.setSelected(true);
    }

    public void setColorButton(){
        colorItem.setSelected(true);
    }
}
