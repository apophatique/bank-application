package com.sbrf.reboot.bankapplication.controller;

import com.sbrf.reboot.bankapplication.exception.LoginFailedException;
import com.sbrf.reboot.bankapplication.exception.UserAlreadyExistsException;
import com.sbrf.reboot.bankapplication.service.LoginService;
import com.sbrf.reboot.bankapplication.service.security.IJwtService;
import model.SignInRequest;
import model.SignInResponse;
import model.SignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер для авторизации и регистрации пользователя
 */
@RestController
public class LoginController {
    private final LoginService loginService;
    private final IJwtService jwtService;

    public LoginController(final LoginService loginService, final IJwtService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
    }

    @PostMapping(
            value = "/signin",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) throws LoginFailedException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new SignInResponse(
                        jwtService.createToken(loginService.signIn(signInRequest))
                ));
    }

    @PostMapping(
            value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
//    @KafkaListener(
//            topics = "signUpTopic",
//            id = "signUpId"
//    )
    public ResponseEntity<Object> signUp(@Valid @RequestBody final SignUpRequest signUpRequest) {
        System.out.println(signUpRequest);
        loginService.signUp(signUpRequest);
        return ResponseEntity
                .noContent()
                .build();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }
}
