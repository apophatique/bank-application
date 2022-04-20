package com.sbrf.reboot.bankapplication.service;

public interface IBalanceService {
    Long getBalance();
    Long modifyBalance(Long value, String type);
}
