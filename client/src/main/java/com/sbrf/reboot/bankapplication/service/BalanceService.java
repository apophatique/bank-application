package com.sbrf.reboot.bankapplication.service;

public interface BalanceService {
    Long getBalance();
    Long modifyBalance(Long value, String type);
}
