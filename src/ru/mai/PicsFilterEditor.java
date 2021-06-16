package ru.mai;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Этот класс используется для демонстрации возможных применяемых к изображению в Java фильтров
 *
 * @author Alisa
 */
public class PicsFilterEditor {

    private final File inputFile = new File("Персически.jpg");

    private static final int MAX = 255;
    private static final int TWENTY_FOUR = 24;
    private static final int SIXTEEN = 16;
    private static final int EIGHT = 8;

    /**
     * Этот метод преобразует изображение в черно-белое
     *
     * @param image  - исходное изображение
     * @param width  - ширина изображения
     * @param height - высота изображения
     */
    private void grayScalePic(BufferedImage image, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // координаты каждого пикселя-точки
                int sourcePixel = image.getRGB(x, y);
                int coord = (sourcePixel >> TWENTY_FOUR) & 0xff;
                int red = (sourcePixel >> SIXTEEN) & 0xff;
                int green = (sourcePixel >> EIGHT) & 0xff;
                int blue = sourcePixel & 0xff;
                int avg = (red + green + blue) / 3;

                // заменяем значение RGB на высчитанное как ср.знач Ч/Б
                sourcePixel = (coord << TWENTY_FOUR) | (avg << SIXTEEN) | (avg << EIGHT) | avg;
                image.setRGB(x, y, sourcePixel);
            }
        }
        String fileToSave = "grayScaleOutput.jpg";
        savePic(fileToSave, image);
    }

    /**
     * Этот метод преобразует изображение в изображение с фильтром сепии
     *
     * @param image  - исходное изображение
     * @param width  - ширина изображения
     * @param height - высота изображения
     */
    private void sepiaScalePic(BufferedImage image, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int sourcePixel = image.getRGB(x, y);
                int coord = (sourcePixel >> TWENTY_FOUR) & 0xff;
                int red = (sourcePixel >> SIXTEEN) & 0xff;
                int green = (sourcePixel >> EIGHT) & 0xff;
                int blue = sourcePixel & 0xff;
                int newRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
                int newGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
                int newBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);
                if (newRed > MAX)
                    red = MAX;
                else
                    red = newRed;
                if (newGreen > MAX)
                    green = MAX;
                else
                    green = newGreen;
                if (newBlue > MAX)
                    blue = MAX;
                else
                    blue = newBlue;
                // установить новое значение RGB
                sourcePixel = (coord << TWENTY_FOUR) | (red << SIXTEEN) | (green << EIGHT) | blue;
                image.setRGB(x, y, sourcePixel);
            }
        }
        String fileToSave = "sepiaScaleOutput.jpg";
        savePic(fileToSave, image);
    }

    /**
     * Этот метод преобразует изображение в негатив изображения
     *
     * @param image  - исходное изображение
     * @param width  - ширина изображения
     * @param height - высота изображения
     */
    private void negativePic(BufferedImage image, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int sourcePixel = image.getRGB(x, y);
                int coord = (sourcePixel >> TWENTY_FOUR) & 0xff;
                int red = (sourcePixel >> SIXTEEN) & 0xff;
                int green = (sourcePixel >> EIGHT) & 0xff;
                int blue = sourcePixel & 0xff;
                red = MAX - red;
                green = MAX - green;
                blue = MAX - blue;
                sourcePixel = (coord << TWENTY_FOUR) | (red << SIXTEEN) | (green << EIGHT) | blue;
                image.setRGB(x, y, sourcePixel);
            }
        }
        String fileToSave = "negativeOutput.jpg";
        savePic(fileToSave, image);
    }

    /**
     * Этот метод записывает преобразованные изображения в указанный файл
     *
     * @param image      - исходное изображение
     * @param fileToSave - название файла для сохранения
     */
    private void savePic(String fileToSave, BufferedImage image) {
        try {
            File fts = new File(fileToSave);
            ImageIO.write(image, "jpg", fts);
            System.out.println("Изменения успешно сохранены в файл: " + fileToSave);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
    }

    /**
     * Основной метод класса, вызывает методы фильтров
     */
    public void mainStart() {
        try {
            BufferedImage image = ImageIO.read(inputFile);
            int width = image.getWidth();
            int height = image.getHeight();

            System.out.println("На изображение " + inputFile + " применяется ч/б фильтр...");
            grayScalePic(image, width, height);
            System.out.println("На изображение " + inputFile + " применяется фильтр сепия...");
            sepiaScalePic(image, width, height);
            System.out.println("Создаётся негатив изображения" + inputFile + "..");
            negativePic(image, width, height);
        } catch (Exception e) {
            System.out.println("ошибка:" + e);
        }
    }
}
