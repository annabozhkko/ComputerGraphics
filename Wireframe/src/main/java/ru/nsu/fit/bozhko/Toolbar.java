package ru.nsu.fit.bozhko;

import javax.swing.*;

public class Toolbar extends JToolBar {
    public Toolbar(BSplineEditor splineEditor, FigurePanel figurePanel){
        JToggleButton saveFileButton = new JToggleButton();
        saveFileButton.setIcon(new ImageIcon("src/main/resources/save.png"));
        saveFileButton.addActionListener(e -> new SaveFile(figurePanel.getFigure()));
        saveFileButton.setToolTipText("Save file");
        add(saveFileButton);

        JToggleButton openFileButton = new JToggleButton();
        openFileButton.setIcon(new ImageIcon("src/main/resources/open.png"));
        openFileButton.addActionListener(e -> new OpenFile(figurePanel));
        openFileButton.setToolTipText("Open file");
        add(openFileButton);

        JToggleButton splineEditorButton = new JToggleButton();
        splineEditorButton.setIcon(new ImageIcon("src/main/resources/spline.png"));
        splineEditorButton.addActionListener(e -> splineEditor.setVisible(true));
        splineEditorButton.setToolTipText("Draw B-spline");
        add(splineEditorButton);

        JToggleButton dropAnglesButton = new JToggleButton();
        dropAnglesButton.setIcon(new ImageIcon("src/main/resources/angle.png"));
        dropAnglesButton.addActionListener(e -> figurePanel.dropAngles());
        dropAnglesButton.setToolTipText("Drop angles");
        add(dropAnglesButton);

        JToggleButton simpleModeButton = new JToggleButton();
        simpleModeButton.setIcon(new ImageIcon("src/main/resources/lines.png"));
        simpleModeButton.addActionListener(e -> figurePanel.changeMode());
        simpleModeButton.setToolTipText("Simple mode");
        add(simpleModeButton);
    }
}
