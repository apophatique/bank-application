package com.sbrf.reboot.bankapplication.repositories;

import com.sbrf.reboot.bankapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с таблицей пользователей.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Поиск пользователя по имени.
     *
     * @param username {@link String} имя пользователя.
     * @return {@link User} пользователь.
     */
    User findByUsername(final String username);
}
