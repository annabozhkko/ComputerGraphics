package ru.nsu.fit.bozhko.components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class SaveFile extends FileDialog {
    public SaveFile(BufferedImage image){
        super(new Frame(), "Save file", SAVE);
        setFile("*.png");
        setVisible(true);

        if(getFile() != null){
            File file = new File(getDirectory() + getFile());
            try {
                file.createNewFile();
                ImageIO.write(image, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
