package ru.mai;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Этот класс используется для обрезания изображения
 *
 * @author Alisa
 */
public class PicsCropper {

    /**
     * название файла, с которым проводится работа
     */
    private final File fileToWrite = new File("Персически.jpg");
    private final File output = new File("Персически_CROP.jpg");
    /**
     * размер изображения в пикселях
     */
    private static final int PICTURE_SIZE = 2500;

    /**
     * Данный метод предназначен для обрезки изображения по заданным параметрам
     *
     * @param x - координата начала построения прямоугольника для обрезки
     * @param y - координата начала построения прямоугольника для обрезки
     * @param w - ширина вырезаемого прямоугольника
     * @param h - высота вырезаемого прямоугольника
     * @return subImage - результат, обрезанная картинка
     */
    private BufferedImage cropImage(int x, int y, int w, int h) {
        try {
            BufferedImage originalImgage = ImageIO.read(fileToWrite);
            BufferedImage subImage = originalImgage.getSubimage(x, y, w, h);
            return subImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Основной метод класса, вызывает остальные методы
     *
     * @param in - поток ввода
     */
    public void mainStart(Scanner in) {
        try {
            System.out.println("Изображение для демонстрации обрезания изображения: \"Персически.jpg\"");

            int x, y, width, height;
            System.out.println("Введите координаты начальной точки, ширину и высоту области, которую хотите вырезать.");
            System.out.println("Начальная точка X:");
            x = inputCheck(in);
            System.out.println("Начальная точка Y:");
            y = inputCheck(in);
            System.out.println("Ширина области:");
            width = inputCheck(in);
            System.out.println("Высота области:");
            height = inputCheck(in);
            BufferedImage bufferedImage = cropImage(x, y, width, height);

            ImageIO.write(bufferedImage, "jpg", output);
        } catch (Exception e) {
            System.out.println("Ошибка в записи");
        }
    }

    /**
     * "Данный метод предназначен для проверки ввода с консоли
     *
     * @param in - поток ввода
     * @return number  - число после проверки
     */
    private int inputCheck(Scanner in) {
        int number = 0;
        do {
            try {
                String input = in.nextLine();
                number = Integer.parseInt(input);
                if (number > PICTURE_SIZE || number <= 0) {
                    System.out.println("Вами было введено неверное значение.\n" +
                            "Пожалуйста, повторите попытку");
                }
            } catch (Exception ex) {
                System.out.println("Вами было введено неверное значение.\n" +
                        "Пожалуйста, повторите попытку");
            }
        } while (number > PICTURE_SIZE || number <= 0);
        return number;
    }
}
