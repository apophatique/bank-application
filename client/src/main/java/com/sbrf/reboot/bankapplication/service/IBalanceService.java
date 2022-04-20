package com.sbrf.reboot.bankapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface IBalanceService {
    Long getBalance();
    Long increaseBalance(Long value) throws JsonProcessingException;
    Long decreaseBalance(Long value) throws JsonProcessingException;
}
