package ru.logs.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.logs.check.Checkable;

import java.util.List;

@Component
public class TransformApplication {
    FileLoad fileLoad;  // чтение файла
    List<Checkable> check;  //промежуточные компаненты

    @Autowired
    public void setFileLoad(FileLoad fileLoad) {
        this.fileLoad = fileLoad;
    }

    @Autowired
    public void setCheck(List<Checkable> check) {
        this.check = check;
    }

    public void run() {
        List<Model> listModel = fileLoad.readFiles(); // читаем данные из файлов
        System.out.println(listModel);

        // выполняем промежуточные компоненты
        for(Checkable ch : this.check)
            ch.make(listModel);
        System.out.println(listModel);
    }
}
