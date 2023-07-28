package ru.nsu.fit.bozhko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BSplineEditor extends JFrame {
    private BSplinePanel splinePanel;
    private FigurePanel figurePanel;
    private Figure figure;
    private int n = 1, k = 4, m = 2, m1 = 1;
    private JTextField kEditBox;

    public BSplineEditor(FigurePanel figurePanel){
        setPreferredSize(new Dimension(630, 540));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.figurePanel = figurePanel;

        splinePanel = new BSplinePanel(this);
        add(splinePanel);

        figure = new Figure(splinePanel.getSpline());
        figurePanel.setFigure(figure);

        addButtons();

        pack();
    }

    private void addButtons(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(540, 80));

        JTextField nEditBox = new JTextField();
        nEditBox.setPreferredSize(new Dimension(100, 30));
        nEditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(nEditBox.getText().equals(""))
                    return;

                int value = 0;
                try {
                    value = Integer.parseInt(nEditBox.getText());
                } catch (NumberFormatException ignored) {}

                splinePanel.setN(value);
                figurePanel.setN(value);
                n = value;
            }
        });
        nEditBox.setText("1");
        buttonsPanel.add(new JLabel("N"));
        buttonsPanel.add(nEditBox);

        kEditBox = new JTextField();
        kEditBox.setPreferredSize(new Dimension(100, 30));
        kEditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(kEditBox.getText().equals(""))
                    return;

                int value = 0;
                try {
                    value = Integer.parseInt(kEditBox.getText());
                } catch (NumberFormatException ignored) {}

                splinePanel.setK(value);
                figurePanel.setK(value);
                k = value;
            }
        });
        kEditBox.setText("4");
        buttonsPanel.add(new JLabel("K"));
        buttonsPanel.add(kEditBox);

        JTextField mEditBox = new JTextField();
        mEditBox.setPreferredSize(new Dimension(100, 30));
        mEditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(mEditBox.getText().equals(""))
                    return;

                int value = 0;
                try {
                    value = Integer.parseInt(mEditBox.getText());
                } catch (NumberFormatException ignored) {}

                figurePanel.setM(value);
                figure.setM(value);
                m = value;
            }
        });
        mEditBox.setText("2");
        buttonsPanel.add(new JLabel("M"));
        buttonsPanel.add(mEditBox);

        JTextField m1EditBox = new JTextField();
        m1EditBox.setPreferredSize(new Dimension(100, 30));
        m1EditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(m1EditBox.getText().equals(""))
                    return;

                int value = 0;
                try {
                    value = Integer.parseInt(m1EditBox.getText());
                } catch (NumberFormatException ignored) {}

                figurePanel.setM1(value);
                figure.setM1(value);
                m1 = value;
            }
        });
        m1EditBox.setText("1");
        buttonsPanel.add(new JLabel("M1"));
        buttonsPanel.add(m1EditBox);

        JTextField znEditBox = new JTextField();
        znEditBox.setPreferredSize(new Dimension(100, 30));
        znEditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(znEditBox.getText().equals(""))
                    return;

                int value = 0;
                try {
                    value = Integer.parseInt(znEditBox.getText());
                } catch (NumberFormatException ignored) {}
                figurePanel.setZn(value);
            }
        });
        znEditBox.setText("10");
        buttonsPanel.add(new JLabel("Zn"));
        buttonsPanel.add(znEditBox);


        JButton addPointButton = new JButton("Add point");
        addPointButton.addActionListener(e -> {
            splinePanel.setAddPoint();
        });
        buttonsPanel.add(addPointButton);

        JButton removePointButton = new JButton("Remove point");
        removePointButton.addActionListener(e -> {
            splinePanel.setRemovePoint();
        });
        buttonsPanel.add(removePointButton);

        JButton cancelButton = new JButton();
        cancelButton.setIcon(new ImageIcon("src/main/resources/cursor.png"));
        cancelButton.setPreferredSize(new Dimension(32, 32));
        cancelButton.addActionListener(e -> {
            splinePanel.cancel();
        });
        buttonsPanel.add(cancelButton);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            figurePanel.setN(n);
            figurePanel.setK(k);
            figurePanel.setM(m);
            figurePanel.setM1(m1);

            figure.buildFigure();
            figurePanel.repaint();
        });
        buttonsPanel.add(okButton);

        add(buttonsPanel, BorderLayout.PAGE_END);
    }

    public BSpline getSpline(){
        return splinePanel.getSpline();
    }

    public void setK(int k) {
        this.k = k;
        kEditBox.setText(Integer.toString(k));
        figurePanel.setK(k);
    }
}
