package ru.nsu.fit.bozhko;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        setPreferredSize(new Dimension(640, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        FigurePanel figurePanel = new FigurePanel();
        add(figurePanel);

        BSplineEditor splineEditor = new BSplineEditor(figurePanel);

        Toolbar toolbar = new Toolbar(splineEditor, figurePanel);
        add(toolbar, BorderLayout.PAGE_START);

        setVisible(true);
        pack();
    }
}
