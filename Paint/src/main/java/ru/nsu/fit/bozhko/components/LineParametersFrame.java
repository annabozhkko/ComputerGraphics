package ru.nsu.fit.bozhko.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LineParametersFrame extends JDialog{
    private GraphicsPanel graphicsPanel;
    private JPanel panel = new JPanel();

    public LineParametersFrame(GraphicsPanel graphicsPanel){
        this.graphicsPanel = graphicsPanel;

        setPreferredSize(new Dimension(400, 150));
        setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(panel);
        setComponents();
        pack();
    }

    private void setComponents(){
        JTextField editBox = new JTextField();
        JSlider depthSlider = new JSlider(1, 10);

        editBox.setPreferredSize(new Dimension(100, 30));

        editBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(editBox.getText().equals(""))
                    return;
                int value = Integer.parseInt(editBox.getText());
                if(value < 0 || value > 10){
                    new ErrorFrame();
                }
                else {
                    depthSlider.setValue(value);
                }
            }
        });

        depthSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            editBox.setText(Integer.toString(source.getValue()));
        });

        editBox.setText("1");
        depthSlider.setValue(1);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        JButton okButton = new JButton("Ok");
        okButton.setPreferredSize(new Dimension(100, 30));

        okButton.addActionListener(e -> {
            graphicsPanel.setDrawLine();
            graphicsPanel.getDrawLine().setDepth(depthSlider.getValue());
            setVisible(false);
        });

        panel.add(depthSlider, BorderLayout.NORTH);
        panel.add(editBox);
        panel.add(new JLabel("Depth line"));
        panel.add(cancelButton);
        panel.add(okButton);
    }

    public void open(){
        setVisible(true);
    }
}
