package com.sbrf.reboot.bankapplication.service;

public interface BalanceService {
    Long getBalance();
    Long increaseBalance(Long value);
    Long decreaseBalance(Long value);
}
