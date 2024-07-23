package ru.logs.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface LoginRepo extends CrudRepository<Login, Long> {
    @Query("select l.id from logins l where l.accessDate = ?1 and l.userId = ?2 and l.application = ?3")
    Long findId(LocalDateTime accessDate, Long userId, String application);
}
