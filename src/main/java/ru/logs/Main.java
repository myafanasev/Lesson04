package ru.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.logs.repo.LoginsRepo;
import ru.logs.repo.UsersRepo;

@SpringBootApplication(scanBasePackages = "ru.logs")
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        UsersRepo ur = ctx.getBean(UsersRepo.class);
//        Users u = new Users( "ulus", "Улусов Егор Сергеевич");
//        u = ur.save(u);
        ur.findAll().forEach(System.out::println);

        LoginsRepo lr = ctx.getBean(LoginsRepo.class);
        lr.findAll().forEach(System.out::println);



    }

}
