package ru.logs.check;

import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;
import ru.logs.entity.Model;

import java.util.List;

@Component
@Order(10) // чтобы в списке проверки оказались в заданном порядке
public class CorrectionFIO implements Checkable {   // исправляет ФИО так, чтобы каждый его компонент начинался с большой буквы
    @Override
    public void make(List<Model> listModel) {
        String sOld, sNew;
        for(Model m : listModel) {
            sOld = m.getFio();
            sNew = "";
            for(int i = 0; i < sOld.length(); i++)
                if (i == 0 || " ".equals(sOld.substring(i-1, i)))   // если это первый символ или если это следующий за пробелом символ
                    sNew += sOld.substring(i, i+1).toUpperCase();
                else
                    sNew += sOld.substring(i, i+1).toLowerCase();
            m.setFio(sNew); // сохраняем преобразованное ФИО
        }
    }
}
