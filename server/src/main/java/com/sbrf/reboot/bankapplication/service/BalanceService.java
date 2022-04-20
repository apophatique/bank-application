package com.sbrf.reboot.bankapplication.service;

import com.sbrf.reboot.bankapplication.entities.Balance;
import com.sbrf.reboot.bankapplication.entities.User;
import com.sbrf.reboot.bankapplication.repositories.BalanceRepository;
import com.sbrf.reboot.bankapplication.service.security.WhoAmIService;
import org.springframework.stereotype.Service;

@Service
public class BalanceService implements IBalanceService {
    final BalanceRepository balanceRepository;
    final WhoAmIService whoAmIService;

    public BalanceService(
            final BalanceRepository balanceRepository,
            final WhoAmIService whoAmIService
    ) {
        this.balanceRepository = balanceRepository;
        this.whoAmIService = whoAmIService;
    }

    @Override
    public Long getBalance() {
        final User user = whoAmIService.whoami();
        return balanceRepository.findByUserId(user.getId()).getAmount();
    }

    @Override
    public Long increaseBalance(final Long value) {
        final User user = whoAmIService.whoami();

        final Balance balance = balanceRepository.findByUserId(user.getId());
        balance.setAmount(balance.getAmount() + value);
        balanceRepository.saveAndFlush(balance);

        return balance.getAmount();
    }

    @Override
    public Long decreaseBalance(final Long value) {
        final User user = whoAmIService.whoami();

        final Balance balance = balanceRepository.findByUserId(user.getId());
        balance.setAmount(balance.getAmount() - value);
        balanceRepository.saveAndFlush(balance);

        return balance.getAmount();
    }
}
