package ru.logs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Logins {
    @Id
    Long id;
    @Column(name = "access_date")
    LocalDateTime accessDate;
    @Column(name = "user_id")
    Long userId;
    String application;

    @Override
    public String toString() {
        return "Logins{" +
                "id=" + id +
                ", accessDate=" + accessDate +
                ", userId=" + userId +
                ", application='" + application + '\'' +
                '}';
    }
}
