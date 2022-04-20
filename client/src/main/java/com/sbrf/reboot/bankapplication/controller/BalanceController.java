package com.sbrf.reboot.bankapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbrf.reboot.bankapplication.service.IBalanceService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import model.SignInResponse;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/client/balance")
public class BalanceController {
    private final IBalanceService balanceService;

    public BalanceController(final IBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getBalance(@PathVariable final String id) throws JsonProcessingException, URISyntaxException {
        return ResponseEntity
                .ok()
                .body(balanceService.getBalance(Integer.valueOf(id)));
    }

    @PutMapping("/{id}/increase")
    public ResponseEntity<Long> increaseBalance(
            @PathVariable final Integer id,
            @RequestParam final Long valueForUpdated
    ) {
        return ResponseEntity
                .ok()
                .body(balanceService.increaseBalance(id, valueForUpdated));
    }

    @PutMapping("/{id}/decrease")
    public ResponseEntity<Long> decreaseBalance(
            @PathVariable final Integer id,
            @RequestParam final Long valueForUpdated
    ) {
        return ResponseEntity
                .ok()
                .body(balanceService.decreaseBalance(id, valueForUpdated));
    }
}
