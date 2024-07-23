package ru.logs.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.logs.log.LogTransformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileLoad {
    @Value("${path.load}") // определяем путь к каталогу с файлами по настройке в application.property
    String path;

    // метод чтения и разбора файлов
    public List<Model> readFiles() {
        List<Model> listModel = new ArrayList<>();
        File loadPath = new File(this.path);
        String [] dataLine;

        // проверим существования такого каталога
        if (!loadPath.exists()) throw new IllegalArgumentException("Каталог " + this.path + " не найден!");

        if (loadPath.isDirectory()) {   // если это каталог
            for (File file : loadPath.listFiles()) {
                try {
                    Scanner sc = new Scanner(file);
                    while (sc.hasNextLine()) {
                        dataLine = sc.nextLine().split(";"); // разбиваем строку
                        if (dataLine.length == 4)    // только если строка правильная
                            listModel.add(new Model(dataLine[0], dataLine[1], dataLine[2], dataLine[3]));
                    }
                    sc.close();
                }
                catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listModel;
    }
}
