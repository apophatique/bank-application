package com.sbrf.reboot.bankapplication.service;

public interface IBalanceService {
    Long getBalance(Integer id);
    Long increaseBalance(Integer id, Long value);
    Long decreaseBalance(Integer id, Long value);
}
