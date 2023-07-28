package ru.nsu.fit.bozhko;

import java.awt.*;
import java.io.*;

public class SaveFile extends FileDialog {
    private File file;
    private Figure figure;

    public SaveFile(Figure figure){
        super(new Frame(), "Save file", SAVE);
        setFile("*.lab4");
        setVisible(true);

        this.figure = figure;

        if(getFile() != null){
            try {
                createFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile() throws IOException{
        file = new File(getDirectory() + getFile());
        file.createNewFile();

        try(PrintWriter writer = new PrintWriter(file)){
            writer.println(figure.getSpline().getN());
            writer.println(figure.getSpline().getK());
            writer.println(figure.getM());
            writer.println(figure.getM1());

            for(Point3D point : figure.getPoints3D()){
                writer.println(point.getX() + " " + point.getY() + " " + point.getZ());
            }

            writer.println(figure.getMinX());
            writer.println(figure.getMaxX());
            writer.println(figure.getMinY());
            writer.println(figure.getMaxY());
            writer.println(figure.getMinZ());
            writer.println(figure.getMaxZ());
        }
    }
}