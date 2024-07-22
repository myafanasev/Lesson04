package ru.logs.entity.check;

import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;
import ru.logs.entity.Model;

import java.util.List;

@Component
@Order(20)
public class CorrectionApplication implements Checkable {
    @Override
    public void make(List<Model> listModel) {
        String sNew;
        for(Model m : listModel) {
            sNew = m.getApplication().toLowerCase();
            if (sNew.equals("web") || sNew.equals("mobile"))
                m.setApplication(sNew);
            else
                m.setApplication("other: " + m.getApplication());
        }

    }
}
