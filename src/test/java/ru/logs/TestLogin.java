package ru.logs;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.logs.check.Checkable;
import ru.logs.check.CorrectionApplication;
import ru.logs.check.CorrectionDate;
import ru.logs.check.CorrectionFIO;
import ru.logs.entity.FileLoad;
import ru.logs.entity.Model;
import ru.logs.repo.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestLogin {
    @Autowired
    FileLoad fileLoad;  // чтение файла
    @Autowired
    List<Checkable> check;  //промежуточные компоненты
    @Autowired
    WriteInDB write; // запись в БД
    @Autowired
    UserRepo userRepo;
    @Autowired
    LoginRepo loginRepo;
    @Autowired
    CorrectionFIO correctionFIO;
    @Autowired
    CorrectionApplication correctionApplication;

    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine").withInitScript("schema.sql");

    @BeforeAll
    static void beforeAll() {postgres.start();}

    @AfterAll
    static void afterAll() {postgres.stop();}

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    @DisplayName("Проверка работы с БД")
    void testDB() {
        // готовим данные
        List<Model> listModel = new ArrayList<>();
        listModel.add(new Model("afanasev", "Афанасьев Михаил Юрьевич", "2020-01-18 09:59:00", "web"));
        listModel.add(new Model("afanasev", "Афанасьев Михаил Юрьевич", "2020-01-18 09:59:00", "web")); // дубль не должен записаться в БД
        listModel.add(new Model("afanasev", "Афанасьев Михаил Юрьевич", "2020-01-19 00:01:00", "mobile"));
        listModel.add(new Model("uvarov", "Уваров Илья Константинович", "2023-10-20 15:01:03", "atm"));
        // выполнея промежуточные компоненты
        for(Checkable ch : this.check)
            ch.make(listModel);

        write.make(listModel); // записываем данные в БД

        List<User> users = (List<User>)userRepo.findAll();
        Assertions.assertEquals(2, users.size());   //должно быть создано 2 пользователя

        List<Login> logins = (List<Login>)loginRepo.findAll();
        Assertions.assertEquals(3, logins.size());  // должно быть 3 записи о фактах авторизации
    }

    @Test
    @DisplayName("Проверка преобразования ФИО")
    void testFIO() {
        // готовим данные
        List<Model> listModel = new ArrayList<>();
        listModel.add(new Model("afanasev", "афанасьев михаил Юрьевич", "2020-01-18 09:59:00", "web"));
        correctionFIO.make(listModel);
        Assertions.assertEquals("Афанасьев Михаил Юрьевич", listModel.get(0).getFio());
    }

    @Test
    @DisplayName("Проверка преобразования приложения")
    void testApplication() {
        // готовим данные
        List<Model> listModel = new ArrayList<>();
        listModel.add(new Model("afanasev", "Афанасьев Михаил Юрьевич", "2020-01-18 09:59:00", "ATM"));
        correctionApplication.make(listModel);
        Assertions.assertEquals("other: ATM", listModel.get(0).getApplication());
    }
}