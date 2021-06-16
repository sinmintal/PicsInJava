package ru.mai;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Класс, демонстрирующий загрузку изображений с сайтов по URL-ссылке
 *
 * @author Alisa
 */
public class PicsDownloader {
    private Scanner scanner = new Scanner(System.in);
    private static final String link = "https://lurkmore.so/images/8/87/Java_programmer_light_bulb_joke_tshirt-p235874910364957006t5e4_400.jpg";

    private String REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&\\/\\/=]*) ";


    private static final int MIN_RANDOM = 0; // "от"
    private static final int MAX_RANDOM = 100; // "до"
    /**
     * Метод загрузки
     *
     * @return bufferedImage - созданное изображение
     */
    private void downloadPic(String website) {
        String usersURL;
        try {
            int random_number = MIN_RANDOM + (int) (Math.random() * MAX_RANDOM);
            String outputFile = "DownloadOutput" + random_number + ".jpg";
            URL url = new URL(website);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(outputFile);

            // изображение записываем побитово
            byte[] buffer = new byte[2048];
            int bufferLength = 0;
            System.out.println("Выполняется выгрузка изображения по ссылке: " + website);
            while ((bufferLength = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bufferLength);
            }
            System.out.println("Изображение успешно загружено в файл " + outputFile);
        } catch (Exception ex) {
            System.out.println("Ошибка чтения URL");
        }
    }

    /**
     * Метод загрузки изображения из пользовательского URL
     */
    private void downloadUserPic(Scanner in) {
        try {
            String usersURL = null;
            do {
                System.out.print("Введите ссылку на изображение: ");
                usersURL = in.nextLine();
                if (usersURL.matches(REGEX)) {
                    downloadPic(usersURL);
                } else {
                    System.out.println("Вы ввели неверную ссылку на изображение. Повторите попытку");
                }
            } while (!usersURL.matches(REGEX));
        } catch (Exception ex) {
            System.out.println("Ошибка чтения URL");
        }
    }

    /**
     * Основной метод, запускающий процесс загрузки
     * Вызывает метод загрузки из предустановленной ссылки и ссылки,
     * введенной пользователем
     */
    public void mainStart(Scanner in) {
        downloadPic(link);
        downloadUserPic(in);
    }

}
