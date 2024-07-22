package ru.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.logs.entity.FileLoad;
import ru.logs.entity.TransformApplication;
import ru.logs.repo.LoginRepo;
import ru.logs.repo.UserRepo;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "ru.logs")
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        TransformApplication transformApplication = ctx.getBean(TransformApplication.class);
        transformApplication.run();

//        UserRepo ur = ctx.getBean(UserRepo.class);
//        Users u = new Users( "ulus", "Улусов Егор Сергеевич");
//        u = ur.save(u);
//        ur.findAll().forEach(System.out::println);
//
//        LoginRepo lr = ctx.getBean(LoginRepo.class);
//        lr.findAll().forEach(System.out::println);




    }

}
