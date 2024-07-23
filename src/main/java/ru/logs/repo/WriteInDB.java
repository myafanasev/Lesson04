package ru.logs.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.logs.entity.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WriteInDB {
    UserRepo userRepo;
    LoginRepo loginRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setLoginRepo(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    public void make(List<Model> listModel) {
        // формируем список пользователей через stream, при этом оставим только уникальные записи, которые пока что отсутсвуют в БД
        List<User> users;
        users = listModel
                .stream()
                .map(u -> new User(u.getUsername(), u.getFio()))
                .distinct()
                .filter(u -> userRepo.findUserByUsername(u.username) == null)   // оставляем только тех пользователей, которых пока нет в БД
                .toList();
        userRepo.saveAll(users);    // сохраняем новых пользователей

        // Теперь формируем список записей о фактах авторизации
        List<Login> logins;
        logins = listModel
                .stream()
                .map(l -> new Login(l.getAccessDateFormat(), userRepo.getIdByUsername(l.getUsername()), l.getApplication()))
                .distinct()
                .filter(l -> loginRepo.findId(l.accessDate, l.userId, l.application) == null)   // исключаем записи, которе уже есть в БД
                .toList();
        loginRepo.saveAll(logins); // сохраняем факты авторизации
    }
}
