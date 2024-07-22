package ru.logs.repo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "logins")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "access_date")
    LocalDateTime accessDate;
    @Column(name = "user_id")
    Long userId;
    String application;

    public Login() {}

    public Login(LocalDateTime accessDate, Long userId, String application) {
        this.accessDate = accessDate;
        this.userId = userId;
        this.application = application;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccessDate(LocalDateTime accessDate) {
        this.accessDate = accessDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setApplication(String application) {
        this.application = application;
    }

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
