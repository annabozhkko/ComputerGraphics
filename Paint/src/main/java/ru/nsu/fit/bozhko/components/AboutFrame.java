package ru.nsu.fit.bozhko.components;

import javax.swing.*;

public class AboutFrame extends JFrame {
    public AboutFrame(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea("Версия 1.0\n" +
                "Божко Анна, группа 20210\n" +
                "Приложение для рисования двумерных изображений с помощью линий, штампов и заливок.\n" +
                "На панели представлены следующие инструменты: очистка поля, \"линия\", \"заливка\", \n" +
                "несколько видов рисования \"штампа\", несколько цветов.\n" +
                "Чтобы нарисовать линию необоходимо задать ее толщину в появившемся окне, нажать ок,\n" +
                "выбрать цвет на панели и выбрать две точки для соединения на поле.\n" +
                "Для заливки выбрать цвет и нажать на нужную область.\n" +
                "Для штампа задать радиус и угол поворота фигуры, и выбрать центр штампа на поле.\n" +
                "Так же доступны сохранение изображения и открытие файла.");

        JPanel aboutPanel = new JPanel();
        aboutPanel.add(area);
        add(aboutPanel);

        setVisible(true);
        setResizable(true);
        pack();
    }
}