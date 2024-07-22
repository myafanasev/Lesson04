package ru.logs.entity.check;


import ru.logs.entity.Model;

import java.util.List;

public interface Checkable {
    void make(List<Model> listModel);
}
