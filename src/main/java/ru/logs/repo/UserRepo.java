package ru.logs.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findUserByUsername(String username); // найти запись по username

    @Query("select s.id from users s where s.username = ?1")
    Long getIdByUsername(String username);  // получить ID по username
}
