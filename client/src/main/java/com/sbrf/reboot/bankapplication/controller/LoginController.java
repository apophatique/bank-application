package com.sbrf.reboot.bankapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sbrf.reboot.bankapplication.service.LoginService;
import model.SignInRequest;
import model.SignUpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Контроллер для авторизации и регистрации пользователя
 */
@RestController
@RequestMapping(value = "/client")
public class LoginController {
    private final LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> signIn(@Valid @RequestBody final SignInRequest signInRequest) throws JsonProcessingException {
        return ResponseEntity.ok(
                loginService.signIn(signInRequest)
        );
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signUp(@Valid @RequestBody final SignUpRequest signUpRequest) throws JsonProcessingException {
        loginService.signUp(signUpRequest);
        return ResponseEntity
                .noContent()
                .build();
    }
}
