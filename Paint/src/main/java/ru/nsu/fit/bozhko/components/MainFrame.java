package ru.nsu.fit.bozhko.components;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        setPreferredSize(new Dimension(640, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GraphicsPanel gpanel = new GraphicsPanel(640, 480);

        JScrollPane scrollPane = new JScrollPane(gpanel);
        add(scrollPane, BorderLayout.CENTER);

        Tools tools = new Tools(gpanel);

        Toolbar toolbar = new Toolbar(gpanel, tools);
        add(toolbar, BorderLayout.PAGE_START);

        Menu menu = new Menu(tools);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setVisible(true);
        pack();
    }
}
