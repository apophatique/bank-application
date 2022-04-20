package com.sbrf.reboot.bankapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;

public interface IBalanceService {
    Long getBalance(Integer id) throws JsonProcessingException, URISyntaxException;
    Long increaseBalance(Integer id, Long value);
    Long decreaseBalance(Integer id, Long value);
}
