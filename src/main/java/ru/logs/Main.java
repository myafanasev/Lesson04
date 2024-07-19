package ru.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "ru.logs")
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        UsersRepo ur = ctx.getBean(UsersRepo.class);
        ur.findAll().forEach(System.out::println);

        LoginsRepo lr = ctx.getBean(LoginsRepo.class);
        lr.findAll().forEach(System.out::println);

    }

}
