package com.sbrf.reboot.bankapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sbrf.reboot.bankapplication.service.IBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/client/balance")
public class BalanceController {
    private final IBalanceService balanceService;

    public BalanceController(final IBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public ResponseEntity<Long> getBalance()  {
        return ResponseEntity.ok().body(balanceService.getBalance());
    }

    @PutMapping("/increase")
    public ResponseEntity<JsonNode> increaseBalance(@RequestParam final Long value) throws JsonProcessingException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceService.increaseBalance(value));
    }

    @PutMapping("/decrease")
    public ResponseEntity<JsonNode> decreaseBalance(@RequestParam final Long value) throws JsonProcessingException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceService.decreaseBalance(value));
    }
}
