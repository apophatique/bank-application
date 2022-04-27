package com.sbrf.reboot.bankapplication.controller;

import com.sbrf.reboot.bankapplication.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/balance")
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(final BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public ResponseEntity<Long> getBalance() {
        return ResponseEntity
                .ok()
                .body(balanceService.getBalance());
    }

    @PutMapping("/increase")
    public ResponseEntity<Long> increaseBalance(@RequestParam final Long value) {
        return ResponseEntity
                .ok()
                .body(balanceService.increaseBalance(value));
    }

    @PutMapping("/decrease")
    public ResponseEntity<Long> decreaseBalance(@RequestParam final Long value) {
        return ResponseEntity
                .ok()
                .body(balanceService.decreaseBalance(value));
    }
}
