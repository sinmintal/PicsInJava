package ru.mai;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Этот класс используется для демонтрации создания изображения в Java
 * Созданное изображение записывается в файл CreationOutput.jpg
 *
 * @author Alisa
 */

public class PicsCreator extends JPanel {
    private Scanner sc = new Scanner(System.in);

    /*
    Для создания пустого файла используется этот код.
           private Image picCreate() {
         Canvas c = new Canvas();
          return c.createImage(400,400);
      }
  */

    /**
     * Переопределение метода paint, отвечающего за вывод изображения
     */
    @Override
    public void paint(Graphics g) {
        Image firstImage = createImageWithText();
        g.drawImage(firstImage, 20, 20, this);
    }

    /**
     * Метод создания изображения с текстом
     *
     * @return bufferedImage - созданное изображение
     */
    private Image createImageWithText() {
        BufferedImage bufferedImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.cyan);
        Rectangle2D shape = new Rectangle2D.Float();
        shape.setFrame(20, 150, 200, 100);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(shape);
        Font font = new Font("Times New Roman", Font.PLAIN, 14);
        g2.setFont(font);
        g.drawString("...этот текст на самом деле не текст", 30, 20);
        g.drawString("это картинка, с которой его не скопировать...", 10, 100);
        g.drawString("Я маленький прямоугольник", 40, 200);
        writeImageInFile(bufferedImage);
        return bufferedImage;
    }

    /**
     * Метод записи созданного изображения из буфера в файл
     */
    public void writeImageInFile(BufferedImage bufferedImage) {
        try {
            FileOutputStream fos = new FileOutputStream("CreationOutput.jpg");
            ImageIO.write(bufferedImage, "jpg", fos);
            System.out.println("Запись изображения произведена успешно в файл \"CreationOutput.jpg\"");
            fos.close();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
    }

    /**
     * Основной метод, запускающий процесс создания окна для
     * отображения буферизированного изображения
     */
    public void mainStart() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new PicsCreator());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
