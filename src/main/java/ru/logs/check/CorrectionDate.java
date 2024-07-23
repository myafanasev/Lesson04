package ru.logs.check;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.logs.entity.Model;
import ru.logs.log.LogTransformation;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(30)
@LogTransformation
public class CorrectionDate implements Checkable {
    @Value("${file.err}") // путь к файлу с ошибками по настройке в application.property
    String errFile;

    @Override
    public void make(List<Model> listModel) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // в таком формате дата в файле
        List<Model> errDate = new ArrayList<>();    // список записей с некорректной датой
        for(Model m : listModel) {
            if (m.getAccessDate().isEmpty())
                errDate.add(m);
            else
                try {
                    m.setAccessDateFormat(LocalDateTime.parse(m.getAccessDate(), formatter));
                } catch (DateTimeParseException e) {
                    errDate.add(m);
                }
        }

        if (errDate.isEmpty()) return;

        // записываем информацию об ошибочных записях в файл
        try(FileWriter writer = new FileWriter(this.errFile, true)) {
            writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("Список записей с некорректной датой: \n");
            for (Model errM : errDate) {
                writer.write(errM + "\n");
            }
            writer.flush();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        // удаляем ошибочные данные из списка
        listModel.removeAll(errDate);
    }
}
