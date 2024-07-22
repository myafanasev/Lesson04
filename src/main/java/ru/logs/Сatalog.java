package ru.logs;

import java.util.Scanner;

public class Сatalog implements Loading{
    @Override
    public void make() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлам для импорта данных: ");
        String line = scanner.nextLine();
        System.out.println("Вы ввели путь: " + line);
    }
}
