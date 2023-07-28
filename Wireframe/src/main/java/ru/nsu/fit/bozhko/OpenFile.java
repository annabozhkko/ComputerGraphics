package ru.nsu.fit.bozhko;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;

public class OpenFile extends JFrame {
    public OpenFile(FigurePanel figurePanel){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("lab4", "lab4"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            setFigure(selectedFile, figurePanel);
            figurePanel.repaint();
        }
    }

    private void setFigure(File file, FigurePanel figurePanel){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            int n = Integer.parseInt(reader.readLine());
            int k = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            int m1 = Integer.parseInt(reader.readLine());

            figurePanel.setN(n);
            figurePanel.setK(k);
            figurePanel.setM(m);
            figurePanel.setM1(m1);

            ArrayList<Point3D> points = new ArrayList<>();
            for(int i = 0; i < m * m1 * ((k - 3) * n + 1); ++i){
                String[] pointsString = reader.readLine().split(" ");
                double x = Double.parseDouble(pointsString[0]);
                double y = Double.parseDouble(pointsString[1]);
                double z = Double.parseDouble(pointsString[2]);

                points.add(new Point3D(x, y, z));
            }

            double minX = Double.parseDouble(reader.readLine());
            double maxX = Double.parseDouble(reader.readLine());
            double minY = Double.parseDouble(reader.readLine());
            double maxY = Double.parseDouble(reader.readLine());
            double minZ = Double.parseDouble(reader.readLine());
            double maxZ = Double.parseDouble(reader.readLine());

            Figure figure = figurePanel.getFigure();
            figure.setPoints3D(points);
            figure.setM(m);
            figure.setM1(m1);
            figure.setMinMaxCoord(minX, maxX, minY, maxY, minZ, maxZ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
