package ru.mai;

import java.util.Scanner;

/**
 * Этот класс используется для контекстного меню программы
 * Здесь пользователь выбирает с помощью ввода в консоль
 * интересующие его пункты, демонстирующие работу с изображениями
 *
 * @author Alisa
 */
public class MenuHandler {
    private Scanner in = new Scanner(System.in);

    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final int MENU_POSITIONS = 5;
    private static final int FOURTH = 4;
    private static final int THIRD = 3;
    private static final int SECOND = 2;
    private static final int FIRST = 1;

    /**
     * Метод запуска меню программы
     * При вводе ключей в диапазоне от 1 до 6 выполняет установленные действия
     * В ином случае выводит сообщение о неверном вводе
     */
    public void launchMenu() {
        int inputNum = 0;
        System.out.println(ANSI_PURPLE
                + "Добро пожаловать в PicsInJavaWorker - программу, демонстрирующую работу с изображениями в Java."
                + ANSI_RESET);
        while (inputNum != MENU_POSITIONS) {
            try {
                Thread.sleep(1500);
                printMenu();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            inputNum = inputCheck();
            switch (inputNum) {

                // создание изображения
                case FIRST:
                    System.out.println(ANSI_YELLOW + "[СОЗДАНИЕ ИЗОБРАЖЕНИЯ]" + ANSI_RESET);
                    PicsCreator picsCreator = new PicsCreator();
                    picsCreator.mainStart();
                    break;

                // загрузка изображения
                case SECOND:
                    System.out.println(ANSI_YELLOW + "[ЗАГРУЗКА ИЗОБРАЖЕНИЯ ПО URL-ССЫЛКЕ]" + ANSI_RESET);
                    PicsDownloader picsDownloader = new PicsDownloader();
                    picsDownloader.mainStart(in);
                    break;

                // обрезка изображения
                case THIRD:
                    System.out.println(ANSI_YELLOW + "[ОБРЕЗКА ИЗОБРАЖЕНИЯ ПО ЗАДАННЫМ ПАРАМЕТРАМ]" + ANSI_RESET);
                    PicsCropper picsCropper = new PicsCropper();
                    picsCropper.mainStart(in);
                    break;

                // применить к изображению фильтры
                case FOURTH:
                    System.out.println(ANSI_YELLOW + "[ПРИМЕНЕНИЕ ФИЛЬТРОВ ДЛЯ ИЗОБРАЖЕНИЯ: ЧЕРНО-БЕЛЫЙ, СЕПИЯ, НЕГАТИВ]"
                            + ANSI_RESET);
                    PicsFilterEditor picsFilterEditor = new PicsFilterEditor();
                    picsFilterEditor.mainStart();
                    break;

                // завершение программы
                case MENU_POSITIONS:
                    in.close();
                    //System.out.println("Программа завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Введенное вами значение не является элементом меню. Повторите попытку.");
            }
        }
    }

    /**
     * Данный метод предназначен для вывода составного меню
     */
    private void printMenu() {
        System.out.println(ANSI_PURPLE + "[ Выберите, что вы хотите сделать и введите выбранный пункт в консоль.]\n"
                + ANSI_RESET +
                "———————————————————————————————————————————\n" +
                "| • создать изображение             -> 1  |\n" +
                "| • загрузить изображение по ссылке -> 2  |\n" +
                "| • обрезать изображение            -> 3  |\n" +
                "| • применить фильтры к изображению -> 4  |\n" +
                "| • завершить программу             -> 5  |\n" +
                "———————————————————————————————————————————");
    }

    /**
     * Данный метод предназначен для проверки ввода с клавиатуры
     * При неверном значении выводит сообщение с просьбой повторить ввод
     *
     * @return tempNum - возвращает число после проверки
     */
    private int inputCheck() {
        int tempNum = 0;
        do {
            try {
                String input = in.nextLine();
                tempNum = Integer.parseInt(input);
                if (tempNum > MENU_POSITIONS || tempNum <= 0) {
                    System.out.println("Вами было введено неверное значение.\n" +
                            "Пожалуйста, повторите попытку");
                }
            } catch (Exception ex) {
                System.out.println("Вами было введено неверное значение.\n" +
                        "Пожалуйста, повторите попытку");
            }
        } while (tempNum > MENU_POSITIONS || tempNum <= 0);
        return tempNum;
    }
}
