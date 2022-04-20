package com.sbrf.reboot.bankapplication.controller;

import com.sbrf.reboot.bankapplication.service.IBalanceService;
import model.SignInResponse;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/balance")
public class BalanceController {
    private final IBalanceService balanceService;

    public BalanceController(final IBalanceService balanceService) {
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
