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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Long> getBalance(@PathVariable final String id) {

        System.out.printf("GET BALANCE SERVER %S%n", id);

        return ResponseEntity
                .ok()
                .body(balanceService.getBalance(Integer.valueOf(id)));
    }

    @PutMapping("/{id}/increase")
    @KafkaListener(
            id = "increaseBalanceId",
            topics = "increaseBalanceTopic"
    )
    public ResponseEntity<Long> increaseBalance(
            @PathVariable final Integer id,
            @RequestParam final Long valueForUpdated
    ) {
        System.out.println(id + " " + valueForUpdated);
        return ResponseEntity
                .ok()
                .body(balanceService.increaseBalance(id, valueForUpdated));
    }

    @PutMapping("/{id}/decrease")
    @KafkaListener(
            id = "decreaseBalanceId",
            topics = "decreaseBalanceTopic"
    )
    public ResponseEntity<Long> decreaseBalance(
            @PathVariable final Integer id,
            @RequestParam final Long valueForUpdated
    ) {
        return ResponseEntity
                .ok()
                .body(balanceService.decreaseBalance(id, valueForUpdated));
    }
}
