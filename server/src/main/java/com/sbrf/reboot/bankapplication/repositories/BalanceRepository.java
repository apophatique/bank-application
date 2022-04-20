package com.sbrf.reboot.bankapplication.repositories;

import com.sbrf.reboot.bankapplication.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с таблицей баланса пользователя.
 */
@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    /**
     * Поиск сущности {@link Balance} по userId.
     *
     * @param userId - id пользователя.
     * @return баланс пользователя.
     */
    Balance findByUserId(@Param("userId") final Integer userId);
}
