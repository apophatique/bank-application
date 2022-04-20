package com.sbrf.reboot.bankapplication.service;

import com.sbrf.reboot.bankapplication.entities.Balance;
import com.sbrf.reboot.bankapplication.repositories.BalanceRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceService implements IBalanceService {
    final BalanceRepository balanceRepository;

    public BalanceService(final BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Long getBalance(final Integer id) {
        return balanceRepository.findByUserId(id).getAmount();
    }

    @Override
    public Long increaseBalance(Integer id, Long value) {
        final Balance balance = balanceRepository.findByUserId(id);
        balance.setAmount(balance.getAmount() + value);
        balanceRepository.saveAndFlush(balance);

        return balance.getAmount();
    }

    @Override
    public Long decreaseBalance(Integer id, Long value) {
        final Balance balance = balanceRepository.findByUserId(id);
        balance.setAmount(balance.getAmount() - value);
        balanceRepository.saveAndFlush(balance);

        return balance.getAmount();
    }
}
