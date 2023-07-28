package ru.nsu.fit.bozhko.components;

import ru.nsu.fit.bozhko.tools.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FigureParametersFrame extends JDialog{
    private GraphicsPanel graphicsPanel;
    private JPanel panel = new JPanel();
    private Figure figure;

    public FigureParametersFrame(GraphicsPanel graphicsPanel){
        this.graphicsPanel = graphicsPanel;

        setPreferredSize(new Dimension(450, 200));
        setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(panel);
        setComponents();
        pack();
    }

    private void setComponents(){
        JTextField radiusEditBox = new JTextField();
        JSlider radiusSlider = new JSlider(10, 240);

        radiusEditBox.setPreferredSize(new Dimension(100, 30));

        radiusEditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(radiusEditBox.getText().equals(""))
                    return;
                int value = Integer.parseInt(radiusEditBox.getText());
                if(value < 0 || value > 240){
                    new ErrorFrame();
                }
                else {
                    radiusSlider.setValue(value);
                }
            }
        });

        radiusSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            radiusEditBox.setText(Integer.toString(source.getValue()));
        });
        radiusSlider.setValue(10);
        radiusEditBox.setText("10");

        panel.add(radiusSlider);
        panel.add(radiusEditBox);
        panel.add(new JLabel("Radius"));



        JTextField angleEditBox = new JTextField();
        JSlider angleSlider = new JSlider(0, 360);

        angleEditBox.setPreferredSize(new Dimension(100, 30));

        angleEditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(angleEditBox.getText().equals(""))
                    return;
                int value = Integer.parseInt(angleEditBox.getText());
                if(value < 0 || value > 360){
                    new ErrorFrame();
                }
                else {
                    angleSlider.setValue(value);
                }
            }
        });

        angleSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            angleEditBox.setText(Integer.toString(source.getValue()));
        });
        angleSlider.setValue(0);
        angleEditBox.setText("0");

        panel.add(angleSlider);
        panel.add(angleEditBox);
        panel.add(new JLabel("Angle"));


        JTextField nEditBox = new JTextField();
        JSlider nSlider = new JSlider(3, 16);

        nEditBox.setPreferredSize(new Dimension(100, 30));

        nEditBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(nEditBox.getText().equals(""))
                    return;
                int value = Integer.parseInt(nEditBox.getText());
                if(value < 3 || value > 16){
                    new ErrorFrame();
                }
                else{
                    nSlider.setValue(value);
                }
            }
        });



        nSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            nEditBox.setText(Integer.toString(source.getValue()));
        });

        nSlider.setValue(3);
        nEditBox.setText("3");

        panel.add(nSlider);
        panel.add(nEditBox);
        panel.add(new JLabel("Vertices"));



        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        JButton okButton = new JButton("Ok");
        okButton.setPreferredSize(new Dimension(100, 30));

        okButton.addActionListener(e -> {
            graphicsPanel.setDrawFigure();
            graphicsPanel.getDrawFigure().setFigure(figure);
            graphicsPanel.getDrawFigure().setRadius(radiusSlider.getValue());
            graphicsPanel.getDrawFigure().setAngle(angleSlider.getValue());
            graphicsPanel.getDrawFigure().setN(nSlider.getValue());
            setVisible(false);
        });

        panel.add(cancelButton);
        panel.add(okButton);
    }

    public void open(Figure figure){
        this.figure = figure;
        setVisible(true);
    }
}
