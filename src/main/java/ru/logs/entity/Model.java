package ru.logs.entity;

import java.time.LocalDateTime;

public class Model {
    private String username;
    private String fio;
    private String accessDate;
    private String application;

    private LocalDateTime accessDateFormat = null;

    public Model(String username, String fio, String accessDate, String application) {
        this.username = username;
        this.fio = fio;
        this.accessDate = accessDate;
        this.application = application;
    }

    public String getUsername() {
        return username;
    }

    public String getFio() {
        return fio;
    }

    public String getAccessDate() {
        return accessDate;
    }

    public String getApplication() {
        return application;
    }

    public LocalDateTime getAccessDateFormat() {
        return accessDateFormat;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setAccessDateFormat(LocalDateTime accessDateFormat) {
        this.accessDateFormat = accessDateFormat;
    }

    @Override
    public String toString() {
        return "Model{" +
                "username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                ", accessDate='" + accessDate + '\'' +
                ", application='" + application + '\'' +
                '}';
    }
}
