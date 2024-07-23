package ru.logs.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.logs.check.Checkable;
import ru.logs.repo.WriteInDB;

import java.util.List;

@Component
public class TransformApplication {
    FileLoad fileLoad;  // чтение файла
    List<Checkable> check;  //промежуточные компаненты

    WriteInDB write; // запись в БД

    @Autowired
    public void setFileLoad(FileLoad fileLoad) {
        this.fileLoad = fileLoad;
    }

    @Autowired
    public void setCheck(List<Checkable> check) {
        this.check = check;
    }

    @Autowired
    public void setWrite(WriteInDB write) {
        this.write = write;
    }

    public void run() {
        List<Model> listModel = fileLoad.readFiles(); // читаем данные из файлов

        // выполняем промежуточные компоненты
        for(Checkable ch : this.check)
            ch.make(listModel);

        write.make(listModel); // записываем данные в БД
    }
}
