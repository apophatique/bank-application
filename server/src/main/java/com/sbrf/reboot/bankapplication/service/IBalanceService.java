package com.sbrf.reboot.bankapplication.service;

public interface IBalanceService {
    Long getBalance();
    Long increaseBalance(Long value);
    Long decreaseBalance(Long value);
}
