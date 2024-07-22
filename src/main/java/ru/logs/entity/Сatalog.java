package ru.logs.entity;

import java.util.Scanner;

public class Сatalog {
    public void make() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлам для импорта данных: ");
        String line = scanner.nextLine();
        System.out.println("Вы ввели путь: " + line);
    }
}
