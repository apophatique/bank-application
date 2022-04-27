package com.sbrf.reboot.bankapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbrf.reboot.bankapplication.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/client/balance")
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(final BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public ResponseEntity<Long> getBalance()  {
        return ResponseEntity.ok().body(balanceService.getBalance());
    }

    @PutMapping("/increase")
    public ResponseEntity<Long> increaseBalance(@RequestParam final Long value) throws JsonProcessingException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceService.modifyBalance(value, "increase"));
    }

    @PutMapping("/decrease")
    public ResponseEntity<Long> decreaseBalance(@RequestParam final Long value) throws JsonProcessingException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceService.modifyBalance(value, "decrease"));
    }
}
